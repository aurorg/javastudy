package src.main.java.temp1111;

import java.util.concurrent.locks.ReentrantLock;

public class Tempjava2_2 {
    public static void main(String[] args) {
        Windows w = new Windows();

        Thread t1 = new Thread(w);
        Thread t2 = new Thread(w);
        Thread t3 = new Thread(w);

        t1.setName("窗口一");
        t2.setName("窗口二");
        t3.setName("窗口三");

        t1.start();
        t2.start();
        t3.start();
    }
}

class Windows implements Runnable{
    private int ticket =100;

    private ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        while(true){
            try{
                lock.lock();
                if(ticket >0){
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        System.out.println(Thread.currentThread().getName()+
                                ": 售票，票号是：" + ticket);

                        ticket--;
                    }else{
                    break;
                }
            }finally{
                lock.unlock();
            }
        }
    }
}
