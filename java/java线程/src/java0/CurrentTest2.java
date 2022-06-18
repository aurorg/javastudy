/*
public class CurrentTest2 {
    public static void main(String[] args){
        Window t1 = new Window();
        Window t2 = new Window();
        Window t3 = new Window();

        t1.setName("窗口一");
        t2.setName("窗口二");
        t3.setName("窗口三");

        t1.start();
        t2.start();
        t3.start();
    }
}
class Window extends Thread{
    private static int ticket =100;
    private static Object obj = new Object();

    @Override
    public void run(){
        while(true){
            synchronized(obj){
                if(ticket>0){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(getName() +
                            ": 卖票，票号是： " + ticket);
                    ticket--;
                }
                else{
                    break;
                }
            }
        }
    }
}

 */
