/**
 * 例子：创建三个窗口卖票，总票数为100张.使用实现Runnable接口的方式
 * 存在线程的安全问题，待解决。
 *
 *
 */
class Window1 implements Runnable{

    private int ticket = 100;
    Object obj = new Object();//加锁的准备
    @Override
    public void run() {
        //synchronized (obj){//加锁
        //也可以这样，就不用创建对象
        synchronized (this){//this就表示的是唯一的window1对象
        while(true) {
            if (ticket > 0) {

                //加上下面的会出现票为0，-1（原来也会出现这种情况，只是概率变高了），出错票
//                try {
//                    Thread.sleep(100);//阻塞一下，切换线程的概率变大
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }

                System.out.println(Thread.currentThread().getName()
                        + ":卖票，票号为：" + ticket);
                ticket--;
            } else {
                break;
            }
         }
        }
    }
}


public class WindowTest1 {
    public static void main(String[] args) {
        Window1 w = new Window1();

        Thread t1 = new Thread(w);
        Thread t2 = new Thread(w);
        Thread t3 = new Thread(w);

        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();
    }

}
