package src.main.java.temp1111;

public class Temp9 {
    public static void main(String[] args) {
        Window4 t1 = new Window4();
        Window4 t2 = new Window4();
        Window4 t3 = new Window4();

        t1.setName("窗口一");
        t2.setName("窗口二");
        t3.setName("窗口三");

        t1.start();
        t2.start();
        t3.start();
    }
}
class Window4 extends Thread{
    private static int ticket =100;

    @Override
    public void run() {
         while(true){
             show();
         }
    }
    private static synchronized void show(){
        if(ticket >0){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName()
            + ":卖票，票号为： " + ticket);
            ticket--;
        }
    }
}
