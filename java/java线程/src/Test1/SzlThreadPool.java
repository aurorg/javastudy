
package src.Test1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;


public class SzlThreadPool {
    public static void main(String[] args) throws InterruptedException {
        //测试
        MyThreadPool myThreadPool = new MyThreadPool(3,0,1,TimeUnit.MILLISECONDS);
        for(int i=0;i<10;i++){
            myThreadPool.execute(new MyRunnable(i));
        }
        Thread.sleep(1000);//主线程休息
        myThreadPool.shutDown();//销毁线程
        System.out.println("线程都死亡了");

    }
}

class MyRunnable implements Runnable {
    private int num;
    MyRunnable(int num) {
        this.num = num;
    }
    //重写
    @Override
    public void run() {
        System.out.println("正在执行任务: " + num);
    }
}



 class Worker extends Thread {

    //每个worker都需要从任务队列中取出任务,所以需要获取到阻塞队列实例
    private BlockingQueue<Runnable> queue;

    //传入存储任务的阻塞队列
    public Worker(BlockingQueue<Runnable> queue) {
        this.queue = queue;
    }

    //重写
    @Override
    public void run() {
        try
        {
            //如果当前线程没有中断,就从阻塞队列中取出新的任务,继续执行，如果队列为空，线程阻塞
            while (!Thread.currentThread().isInterrupted()) {
                Runnable command = queue.take();//调用take方法
                command.run();
            }
        }
        catch (InterruptedException e)
        {
            System.out.println(Thread.currentThread().getName() + "线程结束");
        }
    }
}

class MyThreadPool {
    //线程池中最多可同时执行的线程数量
    private static final int MAXPOOL = 3;

    //阻塞队列用于组织若干个任务，如果队列中的任务数量小于线程池中最大线程数量，任务将全部被加载，
    // 如果大于，则等线程执行完其任务后，将从队列中取出新任务继续执行
    private BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();

    //线程池中的所有运行中的线程（线程池）
    private List<Worker> pool = new ArrayList<>();

     //参数解析

    //①poolSize最大的线程数
    private int poolSize = 3;//线程池大小

    //②corePoolSize（线程池的基本大小）：线程池维护的一个最小线程数量。
    // 一旦创建出来，只要不大于corePoolSize数量，就算线程处于空闲也不会被销毁。
    // 这个参数就是线程池的最小线程数量。
    private int corePoolSize = 0;//创建的最少核心线程总数

    //③keepAliveTime（空闲线程存活时间）
    // 如果线程处于空闲状态，且当前线程数量大于corePoolSize的最小线程数量，
    // 那么过了这个参数指定的时间后，这些空闲的线程就会被销毁。
    private long keepAliveTime=1;

    //④allowKeepAliveTime是否允许空闲线程有空闲时间
    private boolean allowKeepAliveTime = false;

    //⑤unit（空闲线程存活的单位）：就是keepAliveTime的时间单位
    private TimeUnit timeUnit;

    public MyThreadPool(int corePoolSize, int poolSize, long keepAliveTime, TimeUnit unit) {
        this.corePoolSize = corePoolSize;
        this.poolSize = poolSize;
        this.keepAliveTime = keepAliveTime;
        allowKeepAliveTime = true;
        timeUnit = unit;
    }

    //实现execute方法
    public void execute(Runnable command) throws InterruptedException {
        //懒汉式创建
        //当线程池中的线程数量小于可运行的线程数标准,则创建新线程执行该任务，
        // 线程数量大于可运行的线程数标准，添加进队列阻塞，等待其他线程结束之前的任务再执行该任务

        //小于的情况
        if (pool.size() < MAXPOOL) {
            //将阻塞队列中的任务取出一个创建新的线程
            Worker w1 = new Worker(queue);

            //开始执行
            w1.start();

            //将其添加进线程池
            pool.add(w1);
        }

        //大于的情况
        else {
            queue.put(command);
            //阻塞队列中的put方法，若向队尾添加元素的时候发现队列已经满了会发生阻塞一直等待有空间才加入元素
        }
    }


    //实现关闭线程池的方法
    public void shutDown() throws InterruptedException
    {
        //终止掉所有线程
        for (Worker w1 : pool)
        {
            w1.interrupt();//interrupted（）方法作用是中断此线程
                          //此线程不一定是当前线程，而是指调用该方法的Thread实例所代表的线程），
                         // 但实际上只是给线程设置一个中断标志，线程仍会继续运行。
        }
        //interrupt后，每个线程不是立刻结束，等待每个线程分别执行结束
        for (Worker w1 : pool)
        {
            w1.join();
        }

        //结束循环后,回收才结束
    }
}


