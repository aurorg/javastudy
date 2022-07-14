/*package src.Test1;

import java.util.ArrayList;
import java.util.List;

public class SzlBlockingQueue {
    public static void main(String[] args) {
        BlockingQueue queue =  new BlockingQueue(5);

}
class BlockingQueue1 {
    private int maxSize;//阻塞队列的最大容量
    private List<Object> list = new ArrayList<Object>(); //元素集合
    private final Object lock = new Object();//用final修饰锁，防止锁在使用的时候被修改引用导致锁失效


    public BlockingQueue1(int maxSize) {
        this.maxSize = maxSize;
        System.out.println("阻塞队列-" + Thread.currentThread().getName() + "长度为" + this.maxSize + "的队列");
    }

    //加入元素，如果阻塞队列满了则阻塞，直到有被元素取出


    public void put(E e) throws InterruptedException {
        synchronized (lock) {
            if (this.list.size() == this.maxSize) {
                try {
                    System.out.println("阻塞队列：" + Thread.currentThread().getName() + "队列已满，阻塞并等待元素取出");
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Thread.sleep(1000 * 1);//等待一秒
            this.list.add(e);
            System.out.println("阻塞队列：" + Thread.currentThread().getName() + "向队列中加入元素：" + element);
            lock.notifyAll();
        }
    }

    //获取元素，如果阻塞队列为空则阻塞，直到有新元素加入

    public E take() {
        synchronized (lock) {
            if (this.list.size() == 0) {
                try {
                    System.out.println("阻塞队列：" + Thread.currentThread().getName() + "队列已空，阻塞并等待元素加入");
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            E result = this.list.remove(0);
            System.out.println("阻塞队列：" + Thread.currentThread().getName() + "从队列中取出元素：" + result);
            lock.notifyAll();
            return result;
        }
    }

    //获取元素，如果阻塞队列为空则返回空

    public Object poll() {
        synchronized (lock) {
            if (this.list.size() == 0) {
                System.out.println("阻塞队列：" + Thread.currentThread().getName() + "队列已空，返回null");
                return null;
            }
            Object result = this.list.remove(0);
            System.out.println("阻塞队列：" + Thread.currentThread().getName() + "从队列中取出元素：" + result);
            lock.notifyAll();
            return result;
        }
    }


}

 */

