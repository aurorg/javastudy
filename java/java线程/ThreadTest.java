/**
多线程的创建
第一种方法：继承于Thread类
1.创建一个继承于Thread类的子类
2.重写Thread类的run()
 -->将此线程执行的操作（这个线程要干的事，eg：遍历100以内的所有的偶数）声明在run()中
3.创建Thread类的子类的对象
4.通过此对象调用start()
*/

//eg：遍历100以内的所有的偶数

//1.创建一个继承于Thread类的子类
class MyThread extends Thread {
    //2.重写Thread类的run()
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if(i %2==0) {
                System.out.println(i);
            }
        }
    }
}


public class ThreadTest {
    public static void main(String[] args) {
        //3.创建Thread类的子类的对象
        MyThread t1 = new MyThread();

        //4.通过此对象调用start()
        //start的作用
        //         ①启动当前线程
        //         ②调用当前线程的run()
        t1.start();//调用Thread类中的start方法

        //***问题一*****不能使用t1.run()的方式启动线程
        //如果是t1.run()的话；就相当于在调用方法，并没有分出来一个线程

        //****问题二***再启动一个线程
        //重新创建一个线程的的对象
        MyThread t2 = new MyThread();
        t2.start();


        //下面这段代码实在main线程中进行的
        for(int i=0;i<10;i++) {
            System.out.println("*****main****");
        }
    }


}
