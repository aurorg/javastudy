package src.java3;

import java.sql.SQLOutput;

/**
 * 线程通信的例子
 * wait();
 * notify();
 * notifyAll();
 */


class Number implements Runnable{
    private int number =1;

    @Override
    public void run() {
        while(true) {

            synchronized (this) {

                this.notify();//唤醒，可以省略this

                if (number <= 100) {

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName() + ":" + number);
                    number++;

                    try {
                        //使得调用如下wait()方法的线程进去阻塞状态
                        this.wait();//可以省略this
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                } else {
                    break;
                }
            }
        }
    }
}

public class CommunicationTest {
    public static void main(String[] args) {

        Number number=new Number();

        Thread t1 = new Thread(number);
        Thread t2 = new Thread(number);

        t1.setName("线程1");
        t2.setName("线程2");

        t1.start();
        t2.start();
    }
}

