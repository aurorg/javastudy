package src.main.java.src.test_temp;

public class Test3 {
    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        t1.start();

        MyThread t2 = new MyThread();
        t2.start();
    }
}

class MyThread extends Thread{
    @Override
    public void run() {
         for(int i=0;i<100;i++){
             if(i%2==0){
                 System.out.println(i);
             }
         }
    }
}
