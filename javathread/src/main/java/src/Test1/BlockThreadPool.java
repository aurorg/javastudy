package src.main.java.src.Test1;

/*

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


 // 自定义阻塞型线程池 当池满时会阻塞任务提交

public class BlockThreadPool {

    private ThreadPoolExecutor pool = null;

    public BlockThreadPool(int poolSize) {
        //初始化线程池
        pool = new ThreadPoolExecutor(poolSize, poolSize, 0, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(5), new CustomThreadFactory(),
                new CustomRejectedExecutionHandler());
    }

    //销毁线程池
    public void destory() {
        if (pool != null) {
            pool.shutdownNow();
        }
    }

    //自定义创建线程的工厂  自定义线程名称
    private class CustomThreadFactory implements ThreadFactory {
        private AtomicInteger count = new AtomicInteger(0);
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            String threadName = BlockThreadPool.class.getSimpleName() + count.addAndGet(1);
            t.setName(threadName);
            return t;
        }
    }

    //  如果队列已满 就堵塞继续等待 直到队列有空闲
    private class CustomRejectedExecutionHandler implements RejectedExecutionHandler {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            try {
                // 核心改造点，由blockingqueue的offer改成put阻塞方法
                executor.getQueue().put(r);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //开始执行
    public void execute(Runnable runnable) {
        this.pool.execute(runnable);
    }

    // 测试构造的线程池
    public static void main(String[] args) {
        BlockThreadPool pool = new BlockThreadPool(3);
        for (int i = 1; i < 100; i++) {
            System.out.println("提交第" + i + "个任务!");
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getId() + "=====开始");
                        TimeUnit.SECONDS.sleep(10);
                        System.out.println(Thread.currentThread().getId() + "=====【结束】");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            System.out.println("【提交第" + i + "个任务成功！】");
        }

        // 2.销毁----此处不能销毁,因为任务没有提交执行完,如果销毁线程池,任务也就无法执行了
        // exec.destory();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

*/

