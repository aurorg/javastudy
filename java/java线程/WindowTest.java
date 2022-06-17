//eg:创建三个窗口卖票，总票数为100张
//解决多线程安全问题


class Window extends Thread{

    private  static int ticket =100;//注意static，必须设置为静态的，共同用
    private  static Object obj = new Object();//同上

    @Override
    public void run(){
        while(true) {
            synchronized (obj) {
                //不能这样写
                //synchronized (this){   【因为这里的this代表三个对象t1,t2,t3】

                //可以这样写
                // synchronized(Window2.class){      【因为类也是对象】

                if (ticket > 0) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(getName() + ": 卖票，票号为： " + ticket);//输出有三个100，线程安全问题
                    ticket--;
                } else {
                    break;
                }
            }
        }
    }
}

public class WindowTest {
    public static void main(String[] args) {
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


