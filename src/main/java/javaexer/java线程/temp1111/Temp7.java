package temp1111;

public class Temp7 {
    public static void main(String[] args) {
        Window2 t1 = new Window2();
        Window2 t2 = new Window2();
        Window2 t3 = new Window2();

        t1.setName("窗口一");
        t2.setName("窗口二");
        t3.setName("窗口三");

        t1.start();
        t2.start();
        t3.start();
    }
}

class Window2 extends Thread{
    private static int ticket =100;
    private static Object obj = new Object();

    @Override
    public void run() {
         while(true){
             synchronized (Window2.class){
                 if(ticket>0){
                     try {
                         Thread.sleep(100);
                     } catch (InterruptedException e) {
                         e.printStackTrace();
                     }
                     System.out.println(getName() +
                             ": 卖票，票号是：" + ticket);
                     ticket--;
                 }else{
                     break;
                 }
             }
         }
    }
}