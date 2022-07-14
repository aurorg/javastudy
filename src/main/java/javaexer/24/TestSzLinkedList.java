public class TestSzLinkedList {
    public static void main(String[] args){
        //创建一个类的对象
        SzLinkedList<String> list = new SzLinkedList<>();

        //测试
        //当链表为空的时候测试一下
        System.out.println(list.getFirst());//null
        System.out.println(list.getLast());//null
        System.out.println("**********************************");


        //给链表中插入元素
         list.add(1,"zhang");
         list.add(2,"kun");
         list.add(3,"cao");
         list.add(4,"wen");
         list.add(5,"qin");
         list.add(6,"111");
         list.add(7,"222");
         list.addFirst("happy");
         list.addLast("luck");

         System.out.println(list);
         System.out.println(list.getFirst());
         System.out.println(list.getLast());
         System.out.println("***********************************");

         //删除的部分
        System.out.println(list);
        System.out.println(list.removeFirst());
        // System.out.println(list);
        System.out.println(list.removeLast());
        System.out.println(list.remove(2));

        //System.out.println(list);

    }
}

//接口，把通用的方法放在里面
interface SzLinked<E>{

//    boolean add(E e);
//    int size();


    //3.返回链表的第一个元素
    E getFirst();

    //4.返回链表的最后一个元素
    E getLast ();

    //5.添加一个元素到链表的头部
    void addFirst(E e);

    //6.添加一个元素到链表的尾部
    void addLast(E e);

    //7.将一个元素插入到链表的指定位置中（把元素e插入到链表中index位置上）
    void add(int index,E e);

    //8.从链表中删除第一个元素，返回第一个元素的值
    E removeFirst();

    //9.从链表中删除最后一个元素，返回最后一个元素的值
    E removeLast();

    //10.找到需要删除元素的下标，然后删除指定下表的结点
    E remove(int index);

}

//实现接口里面的方法
class SzLinkedList<E> implements SzLinked<E>{
    private Node<E> head,tail;
    private int size =0;

    //1.创建一个默认的链表
    public SzLinkedList(){

    }

    //2.从一个元素数组中创建一个链表
    public SzLinkedList(E[] objects){
        for(int i=0;i<objects.length;i++)
            add(i,objects[i]);//改过
    }

//    @Override
//    public  boolean add(E e){
//        add(size(),e);
//        return true;
//    }

//    @Override
//    public int size(){
//        return size;
//    }

    //3.返回链表的第一个元素
    @Override
    public E getFirst(){
        if(size==0){
            return null;
        }
        else{
            return head.element;
        }
    }

    //4.返回链表的最后一个元素
    @Override
    public E getLast(){
        if(size==0){
            return null;
        }
        else{
            return tail.element;
        }
    }

    //5.添加一个元素到链表的头部
    // 【创建一个包含元素e的新结点，让这个结点成为链表的第一个结点】
    @Override
    public void addFirst(E e) {
        Node<E> newNode = new Node<>(e);//创建一个新的结点
        newNode.next = head;//让这个新结点插入链表的起始位置
        head = newNode;//head指向新元素结点
        size++;

        //如果链表是空的话，头结点和尾结点都指向这个新节点
        if (tail == null) {
            tail = head;
        }
    }

    //6.添加一个元素到链表的尾部
    //【创建一个包含元素的新结点，并且将它追加到链表的末尾】
    @Override
    public void addLast(E e){
        Node<E> newNode = new Node<>(e);//创建一个新的结点

        //分两种情况
        //①链表是空的时候，头结点（head）和尾结点（tail）都指向这个新结点
        //②否则的话，将该结点和该链表的最后一个结点相连接，tail(尾结点）指向这个新结点

        //第一种情况
        if(tail ==null){
            head = tail = newNode;
        }
        //第二种情况
        else{
            tail.next =newNode;//将该结点和该链表的最后一个结点相连接
            tail = newNode;//tail(尾结点）指向这个新结点
        }
        size++;
    }

    //7.将一个元素插入到链表的指定位置中（把元素e插入到链表中index位置上）
    //分三种情况
    //1》当指定的下标index=0的时候，直接调用addFirst()方法将元素插入到链表的起始位置
    //2》当指定的下标index>=链表的大小的时候，调用addLast()方法将元素插入到链表的末尾
    //3》当指定的下标index在链表的中间任意位置时
    //   创建一个新的结点来存储新元素，并且确定它的插入位置
    //   新的结点应该插入到current 和 temp中间
    @Override
    public void add(int index,E e){
        if(index==0)
            addFirst(e);

        else if(index>=size)
            addLast(e);

        else{
            Node<E> current = head;//开始让current这个结点指向头结点

            for(int i=1;i<index;i++){//通过for循环,
                current=current.next;//让currant这个结点(移动到）指向插入结点的前一个位置上.
            }

            Node<E> temp;//创建一个临时结点
            temp = current.next;//让这个临时结点指向current结点的下一个位置
            current.next = new Node<>(e);//将元素e插入到current 和temp中间
            (current.next).next =temp;//因为插入了元素e（元素e在current的下一个位置），所以让current的下下一个位置指向temp
            size++;
        }
    }

    //8.从链表中删除第一个元素
    //分两种情况
    //1>当链表是空的时候，就没有什么需要删除，返回Null就可以啦
    //2》当链表不是空的时候，让head指向第二个结点从链表中删除第一个结点
    //
    @Override
    public E removeFirst(){
        //链表是空的时候
        if(size==0) {
            return null;
        }
        else{
            Node<E> temp =head;
            head = head.next;//让head指向第二个结点
            size--;//链表的大小-1

            //当删除第一个元素之后，如果链表变成空的时候，那么head=tail=null
            if(head == null)
                tail =null;
            return temp.element;
        }
    }


    //9.从链表中删除最后一个元素，并且返回最后一个元素的值
    @Override
    public E removeLast(){
        if(size==0 || size==1){
            return removeFirst();
        }
        else{
            Node<E> current = head;//创建结点current指向头结点（head）
            for(int i=2;i <size-2;i++){//使用for循环
                current = current.next;//让current结点指向倒数第二个结点
            }

            E temp = tail.element;//保存最后一个结点的值
            tail = current;//将tail设置为current，tail现在指向倒数第二个
            tail.next = null;// 将最后一个结点设置为null
            size--;
            return temp;//返回删除元素的值
        }
    }

    //10.找到需要删除元素的下标，然后删除指定下标的结点
    public E remove(int index){
        if(index < 0 || index >=size){
            return null;
        }
        else if(index==0){
            return removeFirst();
        }
        else if(index==size-1){
            return removeLast();
        }
        else{
            Node<E> previous = head;

            for(int i=1;i<index;i++){
                previous = previous.next;//让previous指向要删除结点的前一个结点
            }

            Node<E> current = previous.next;//current指向要删除的结点
            previous.next = current.next;//将current.next赋值给previous.next，从而消除要删除的结点
            size--;
            return current.element;
        }

    }

   //重写toString方法
    public String toString(){
        StringBuilder result = new StringBuilder("[");

        Node<E> current = head;

        for(int i=0;i<size;i++){
            result.append(current.element);
            current = current.next;

            if(current!=null){//当current结点的指向不为空的时候
                result.append(", ");//打印','
            }

            else{//当current结点指向的为空的时候,也就是尾结点
                result.append("]");//打印‘]’
            }
        }
        return result.toString();
    }
}


//创建一个类结点
class Node<E>{
    E element;
    Node<E> next;

    public Node(E element){//构造器
        this.element =element;

    }
}


