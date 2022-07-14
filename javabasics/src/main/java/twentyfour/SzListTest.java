package twentyfour;

public class SzListTest {
    public static void main(String[] args){

        //创建一个SzArrayList对象，用来测试
        SzArrayList list = new SzArrayList();

        //前三个方法的测试
        System.out.println(list.isEmpty());//判断是否是空的
        System.out.println(list.getSize());//返回个数
        //添加元素后再测试
        list.insert(0,"yang");
        list.insert(1,"lin");
        list.insert(2,"na");
        list.insert(2,"333");
        list.insert(4,"xue");
        list.insert(5,"qian");
        System.out.println(list.isEmpty());
        System.out.println(list.getSize());
        System.out.println(list);//输出刚刚添加的内容
        //思考为什么不重写toString方法输不出来
        System.out.println("*******************************************************");

        //判断某个元素是否存在
        System.out.println(list.contains("na"));
        System.out.println(list.contains("333"));
        System.out.println(list.contains("lll"));

        //返回某个元素的下标测试
        System.out.println(list.indexOf("333"));
        System.out.println(list.indexOf("na"));
        System.out.println(list.indexOf("lin"));
        System.out.println("*******************************************************");

        //删除i位置上的元素并且返回这个元素
        System.out.println(list.remove(4));
        System.out.println(list);//看一下删除该元素之后的线性表

        //删除线性表中第一个与e元素相同的元素并且返回这个元素
        System.out.println(list.removes("qian"));
        System.out.println(list);//看一下删除该元素之后的线性表
        System.out.println("*********************************************************");

        //将下标值为i的元素替换成元素e
        System.out.println(list.replace(1,"happy"));
        System.out.println(list);
        //返回下标值为i的元素值
        System.out.println(list.get(0));
        System.out.println(list);
        System.out.println(list.get(1));
        System.out.println(list);
        System.out.println("****************************************************");

    }

}
interface SzList{

    //1.判断线性表是不是空的
    boolean isEmpty();

    //2.返回线性表中元素的个数
    int getSize();

    //3.向线性表中写入元素
    void insert(int i, Object e);

    //4.判断线性表中是否包含某个元素
    boolean contains(Object e);

    //5.返回线性表中元素e对应的下标
    int indexOf(Object e);

    //6.删除线性表中第一个索引值为i的元素并且返回这个元素
    Object remove(int i);

    //7.删除线性表中第一个与e元素相同的元素并且返回这个元素
    Object removes(Object e);

    //8.使用元素e代替线性表中位置是i的元素
    Object replace(int i,Object e);

    //9.返回下标值为i的元素
    Object get(int i);

}

class SzArrayList implements SzList{
    private Object[] elements;//定义一个数组保存元素
    private static final int DEFAULT_CAPACITY =26;//数组默认的容量
    private int size;//保存元素的个数

    //构造方法
    public SzArrayList(){
        elements = new Object[DEFAULT_CAPACITY];
    }

    public SzArrayList(int initialCapacity){
        elements = new Object[initialCapacity];
    }

    //1.判断线性表是不是空的
    @Override
    public boolean isEmpty(){
        return size==0;
    }

    //2.返回线性表中元素的个数
    @Override
    public int getSize(){
        return size;
    }

    //3.向线性表中写入元素(eg:向i位置写入元素e）
    @Override
    public void insert(int i ,Object e){
        //先判断i是否越界
        if(i<0 || i>size){
            throw new IndexOutOfBoundsException(i + "越界啦");
        }

        //如果数组满了就对数组进行扩容
        if(size >=elements.length){
            //expandSpace();//调用扩容函数
        }

        //从i+1开始将元素向后移动
//        for(int j = size;j>i;j--){
//            elements[j] =elements[j-1];//向后移
//        }

        for(int j=size;j>i;j--){
            elements[j] = elements[j-1];
        }

        //把元素e存到i的位置上
        elements[i] =e;

        //元素的总数增加1
        size++;
    }

    //4.判断线性表中是否包含某个元素
    @Override
    public boolean contains(Object e){
        //方法一有问题，因为equals只能比较字符串，无法比较两个整形的数
//        boolean flag =true;
//        for(int i=0;i<size;i++) {
//            if (e.equals(elements[i])) {//比较是否相同
//                 flag =true;
//            }
//            else{
//                flag=false;
//            }
//        }
//        return  flag;

        //方法二：调用方法5
        return indexOf(e) >=0;
    }

    //5.返回线性表中元素e对应的下标
    @Override
    public int indexOf(Object e){
        if(e == null){//当之前添加的元素为空的时候（有这种可能哦）
            for(int i=0;i<size;i++){
                if(elements[i] ==null);
                   return i;
            }
        }
        else{
            for(int i=0;i<size;i++){
                if(e.equals(elements[i])){
                    return i;
                }
            }
        }
        return -1;
    }

    //6.删除线性表中第一个索引值为i的元素并且返回这个元素
    @Override
    public Object remove(int i){
        //判断i元素是否超过了数组的界限
        if(i<0 || i>=size){
            throw new IndexOutOfBoundsException(i+ "越界啦");
        }

        //保存要删除的元素，因为后面需要返回这个元素
        Object old = elements[i];

        //把i+1后面的元素都要向前移动，这样最后一个位置上就没有元素了
        for(int j=i;j<size -1;j++){
            elements[j] = elements[j+1];
        }
        elements[size-1]=0;//因为前面删除了一个元素，所以最后一个位置上面就没有元素了
        size--;//总的元素数量减少一个

        return old; //返回删除了的元素

    }
    //7.删除线性表中第一个与e元素相同的元素并且返回这个元素(排除重复）
    @Override
    public Object removes(Object e){
        //得到元素e的下标值，这样就可以调用方法6
        int index = indexOf(e);//调用方法indexOf,返回线性表中元素e对应的下标
        if(index <0)
            return 0;
        return remove(index);

    }
    //8.使用元素e代替线性表中位置是i的元素，返回被替代的那个元素（也就是老元素）
    public Object replace(int i,Object e){
        //还是先判断下标i是否越界了
        if(i<0 || i>=size){
            throw new IndexOutOfBoundsException(i + "越界啦啦啦");

        }
        //保存原来的元素
        Object old = elements[i];
        //替换
        elements[i] =e;

        return old;

    }

    //9.返回下标值为i的元素值
    public Object get(int i){

        return elements[i];
    }

    public void expandSpace(){
        //先定义一个容量更大的数组
        Object[] newElements = new Object[elements.length *2+1];

        //方法一
        //把原来的数据复制入到新的数组中
        for(int i=0;i<elements.length;i++){
            newElements[i] = elements[i];
        }
        //方法二：可以直接使用System.arraycopy(list1,0 ,list2 ,0 , size)

        //让原来的数组名指向新的数组
        elements = newElements;
    }

    //重写toString方法
    @Override
    public String toString() {
        //把线性表中的每个元素连接起来，遍历数组中的已添加的元素
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(elements[i]);
            if (i < size - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }

}
