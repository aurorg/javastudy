package src.temp;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Test2 {
    public static void main(String[] args) {

    }
}
class Worker extends Thread{
    private BlockingQueue<Runnable> queue;

    public Worker(BlockingQueue<Runnable> queue){
        this.queue  =queue;
    }

    @Override
    public void run(){
        try{
            while(!Thread.currentThread().isInterrupted()){
               Runnable command = queue.take();
               command.run();
            }
        }
        catch (InterruptedException e){
            System.out.println(Thread.currentThread().getName());
        }
    }
}
class MyThreadPool{
    private static final int MAXPOOL =3;

    private BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();

    private List<Worker> pool = new ArrayList<>();

    private int poolSize=3;
    private int corePoolSize =0;
    private long keepAliveTime=1;
    private boolean allowKeepAliveTime =false;
    private TimeUnit timeUnit;

    public MyThreadPool(int corePoolSize,int poolSize,long KeepAliveTime,TimeUnit unit){
        this.corePoolSize =corePoolSize;
        this.poolSize = poolSize;
        this.keepAliveTime = keepAliveTime;
        allowKeepAliveTime = true;
        timeUnit =unit;
    }

    public void execute(Runnable command) throws InterruptedException{
        if(pool.size() <MAXPOOL){
            Worker w1 = new Worker(queue);
            w1.start();
            pool.add(w1);

        }
        else{
            queue.put(command);
        }
    }

    public void shutDown() throws InterruptedException{
        for(Worker w1:pool){
            w1.interrupt();
        }
        for(Worker w1 :pool){
            w1.join();
        }
    }

}
