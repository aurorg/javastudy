package src.main.java.src.Test1;

public class TestSzlBlockingQueue {
    MyBlockingQueue queue = new MyBlockingQueue(3);

    public static void main(String[] args) {

    }

    //基于循环队列实现阻塞队列
    class MyBlockingQueue<E> {
        //初始化循环队列
        private Object[] elements;

        //队头指针
        private int head = 0;

        //队尾指针
        private int tail = 0;

        //元素个数
        private int size = 0;

        //创建一个专门锁对象
        private final Object locker = new Object();

        public MyBlockingQueue(int n) {
            elements = new Object[n];
        }

        //        public void MyBlockingQueue(int index){
//            this.elements = new Object[index];
//        }


//队尾入元素,如果队列此时是满的,就阻塞（等到有元素出去了再入元素）
        public void put(Object e) throws InterruptedException {
            //队列如果满了,则需要使线程阻塞,直到循环队列出元素后（队列里面有位置）才通知线程继续执行该操作
            synchronized (locker) {
                //如果队列是满的话就等一下
                if (size == elements.length) {
                    locker.wait();//等待
                }

                elements[tail++] = e;//添加元素
                if (tail >= elements.length) {
                    tail = 0;
                }
                size++;
                //队列入元素后,队列就不是空的了,然后就通知线程可以继续进行出队操作
                locker.notify();
            }
        }


        //队头出元素,如果队列为空则阻塞
        public E take() throws InterruptedException {
            //循环队列为空,需要阻塞线程,直到队列入元素后才通知线程继续执行该操作
            synchronized (locker) {
                E ret = null;
                if (size == 0) {
                    locker.wait();
                }

                ret = (E) elements[head++];

                if (head >= elements.length) {
                    head = 0;
                }

                size--;
                //循环队列出元素后,队列就不是满满的了,可以通知线程继续进行入队操作
                locker.notify();
                return ret;//返回头部元素
            }
        }


    }
}



