package src.test_temp;
public class Test1{
    public static void main(String[] args) {

    }
}
class MyBlockingQueue<E>{
    private Object[] elements;
    private int head=0;
    private int tail=0;
    private int size =0;
    private final Object locker = new Object();

    public MyBlockingQueue(int n){
        elements = new Object[n];
    }

    public E take() throws InterruptedException{
        synchronized(locker){
            E ret =null;
            if(size==0){
                locker.wait();
            }

            ret =(E)elements[head++];
            if(head >=elements.length){
                head=0;
            }
            size--;
            locker.notify();
            return ret;
        }
    }

    public void put(Object e) throws InterruptedException{
        synchronized (locker){
            if(size==elements.length){
                locker.wait();
            }

            elements[tail++] =e;
            if(tail >=elements.length){
                tail=0;
            }
            size++;
            locker.notify();
        }
    }
}
