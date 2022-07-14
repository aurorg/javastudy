import java.util.Collections;
import java.util.PriorityQueue;

public class PriorityQueueDemo {
    public static void main(String[] args){
        PriorityQueue<String> queue1 =new PriorityQueue<>();
        queue1.offer("happy");
        queue1.offer("luck");
        queue1.offer("life");
        queue1.offer("aurora");
        System.out.println("Priority queue using Comparable: ");
        while (queue1.size() >0){
            System.out.print(queue1.remove() + " ");
            
        }
        PriorityQueue<String> queue2 = new PriorityQueue<>(4, Collections.reverseOrder());
        queue2.offer("111");
        queue2.offer("222");
        queue2.offer("333");
        queue2.offer("444");

        System.out.println("\nPriority queue using Comparator:");
        while(queue2.size() > 0){
            System.out.print(queue2.remove() + " ");
        }
    }
}
