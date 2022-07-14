/*
public class CurrentTest1 {
    private static final MyBlockingQueue queue = new MyBlockingQueue();

    public static void main(String[] args) {

    }
}

class MyBlockingQueue{
    private int[] elements = new int[100];
    private int head;
    private int tail;
    private int size;

    private final Object locker = new Object();

    public Integer take() throws InterruptedException{
        synchronized (locker){
            if(size==0){
                locker.wait();
            }
            int ret = elements[head++];
            if(head >= elements.length){
                head =0;
            }
            size--;
            locker.notify();
            return ret;
        }
    }

    public void put(int val) throws InterruptedException{
        synchronized (locker){
            if(size == elements.length){
                locker.wait();
            }
            elements[tail++] = val;
            if(tail >= elements.length){
                tail=0;
            }
            size++;
            locker.notify();
        }
    }

}


 */
