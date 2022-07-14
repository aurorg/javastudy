public class TestQueue {
    public static void main(String[] args){
        java.util.Queue<String> queue= new java.util.LinkedList<>(); //创建一个队列
        //将字符串添加到队列中
        queue.offer("happy");
        queue.offer("luck");
        queue.offer("aurora");
        queue.offer("life");

        while(queue.size() > 0){
            System.out.print(queue.remove() + " ");//remove( )获取并且移出位于队列头部的元素
        }
    }
}
