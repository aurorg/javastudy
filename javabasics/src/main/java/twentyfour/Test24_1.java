package twentyfour;//用单链表实现队列

public class Test24_1 {
    public static void main(String[] args) {
        MyQueue<Integer> myQueue = new MyQueue<>();
        myQueue.offer(2);
        myQueue.offer(4);
        myQueue.offer(6);
        myQueue.offer(8);
        myQueue.offer(100);
        myQueue.offer(101);

        System.out.println("入队offer[2 ,4 , 6, 8,100]: "+ myQueue);
        System.out.println("出队poll:" + myQueue.poll());
        System.out.println("出队poll:" + myQueue.poll());
        System.out.println("出队poll:" + myQueue.poll());
        System.out.println("获取队头元素peek:" + myQueue.peek());
        System.out.println("队列中的元素:" + myQueue);
    }
}

interface SQueue<E>{
    //1.入队
    boolean offer(E e);

    //2.出队
    E poll();

    //3.获取队头的元素
    E peek();

}

class MyQueue<E> implements SQueue<E>{
    public ListNode<E> head;//指向单链表的头
    public ListNode<E> last;//指向单链表的尾

    @Override
    public boolean offer(E e) {
        //队尾(单链表的尾)入队,尾插
        ListNode<E> node = new ListNode<>(e);

        //如果是空的时候
        if (isEmpty()) {
            this.head = node;
            this.last = node;
            return true;
        }
        this.last.next = node;
        this.last = node;
        return true;
    }


    @Override
    public E poll() {
        //队头出队(单链表的头),头删
        if (isEmpty()) {
            return null;
        }
        ListNode<E> current = head;//创建一个临时的结点指向头
        head = current.next;
        return current.val;
    }

    @Override
    public E peek() {
        //获取队头（单链表的头）的元素
        if (isEmpty()) {
            return null;
        }
        return this.head.val;
    }
    public boolean isEmpty() {
        return this.head == null;
    }



    @Override
    public String toString() {
        ListNode<E> cur = this.head;
        if (cur == null) {
            return "[]";
        }
        StringBuilder stringBuilder = new StringBuilder("[");
        while (cur.next != null) {
            stringBuilder.append(cur.val);
            stringBuilder.append(",");
            cur = cur.next;
        }
        stringBuilder.append(cur.val);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}

//结点类
class ListNode<E> {
    public E val;
    public ListNode<E> next;
    public ListNode(E val) {
        this.val = val;
    }
}

