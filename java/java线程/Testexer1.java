//创建两个分线程，一个遍历偶数，一个遍历奇数

public class Testexer1 {
    public static void main(String[] args) {
        MyThread1 m1 = new MyThread1();
        MyThread2 m2 = new MyThread2();

        m1.start();
        m2.start();


    }
}

class MyThread1 extends Thread{
    @Override
    public void run(){
        for(int i=0;i<100;i++ ){
            if(i%2==0){
                System.out.println(Thread.currentThread().getName()+ ":" +i);
            }
        }
    }
}
class MyThread2 extends Thread{
    @Override
    public void run(){
        for(int i=0;i<100;i++ ){
            if(i%2!=0){
                System.out.println(Thread.currentThread().getName()+ ":" +i);
            }
        }
    }
}