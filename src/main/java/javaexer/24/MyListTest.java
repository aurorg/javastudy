 /*public class MyListTest {
    public static void main(String[] args) {
        //创建一个MyArrayList对象 测试
        MyArrayList list = new MyArrayList();

        //判断是否为空 测试
        System.out.println(list.isEmpty());
        System.out.println(list.getSize());

        //添加元素 测试
        list.insert(0, "Cheng");
        list.insert(1, "Shang");
        list.insert(0, "2001");
        System.out.println(list.isEmpty());
        System.out.println(list.getSize());

        //把线性表中的内容输出 测试
        System.out.println(list);

        //判断元素是否存在 测试
        System.out.println(list.indexOf("2001"));
        System.out.println(list.indexOf("Shang"));
        System.out.println(list.indexOf("2019"));
        System.out.println(list.contains("2001"));
        System.out.println(list.contains("2000"));

        //删除 测试
        list.remove("2001");
        System.out.println(list);

        //替换 测试
        list.insert(0, "Poke");
        list.insert(0, "Tao");
        list.insert(0, "Jie");
        list.insert(0, "Hao");
        System.out.println(list);
        list.replace(0, "Van");
        System.out.println(list);

        //返回指定索引的元素 测试
        System.out.println(list.get(0));
        System.out.println(list.get(1));
        System.out.println(list.get(2));
        System.out.println(list.get(3));

        //在指定的元素前面或后面插入 测试
        list.insertBefore("Van", "YYDS");
        System.out.println(list);
        list.insertAfter("Jie", "Ge");
        System.out.println(list);
        list.insertAfter("AWei", "BinBin");
        System.out.println(list);



        //最大最小值 测试
//        list.insert(0, 22);
//        list.insert(1, 26);
//        list.insert(2, 28);
//        list.insert(3, 24);
//        list.insert(4, 16);
//        list.insert(5, 12);
//        System.out.println(list);
//        System.out.println(list.isEmpty());
//
//        System.out.println(list.getMax());
//        System.out.println(list.getMin());


    }
}

interface MyList {
    int  getSize();     //返回线性表中元素的个数
    boolean isEmpty();     //判断线性表是否为空
    void insert(int i,Object e);     //在线性表的i索引值添加元素e
    boolean contains(Object e);     //判断线性表中是否包含元素e
    int indexOf(Object e);      //返回线性表中元素e的索引值
    Object remove(Object e);     //删除线性表中第一个与e相同的元素并返回该元素
    Object remove(int i);     //删除线性表中第一个索引值为i的元素并返回该元素
    Object replace(int i,Object e);     //使用元素e替换线性表中i位置的元素并返回旧元素
    Object get(int i);     //返回索引值为i的元素
    boolean insertBefore(Object p,Object e);     //在线性表元素p之前插入元素e
    boolean insertAfter(Object p,Object e);     //在线性表元素p之后插入元素e
    int getMax();     //返回线性表中的最大值
    int getMin();     //返回线性表中的最小值
}

class MyArrayList implements MyList {
    private Object[] elements;     //定义数组保存数据元素
    private static final int DEFAULT_CAPACITY = 26;     //数组默认初始化容量
    private int size;     //保存数据元素个数

    //构造方法
    public MyArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    public MyArrayList(int initialCapacity) {
        elements = new Object[initialCapacity];
    }

    //返回元素个数
    @Override
    public int getSize() {
        return size;
    }

    //判断线性表是否为空
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    //在线性表的i索引值添加元素e
    @Override
    public void insert(int i, Object e) {
        //判断索引值i是否超出
        if (i < 0 || i > size) {
            throw new IndexOutOfBoundsException(i + "越界");
        }
        //如果数组溢满则对数组扩容
        if (size >= elements.length) {
            expandSpace();
        }
        //从i+1开始元素依次后移
        for (int j = size; j > i; j--) {
            elements[j] = elements[j - 1];
        }
        //把元素e存储到i位置
        elements[i] = e;
        //元素个数加一
        size++;
    }

    //判断线性表中是否包含元素e
    @Override
    public boolean contains(Object e) {
        return indexOf(e) >= 0;
    }

    //返回线性表中元素e的索引值
    @Override
    public int indexOf(Object e) {
        //遍历数组
        if (e == null) {
            //线性表中用户可能添加null
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (e.equals(elements[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    //删除线性表中第一个与e相同的元素并返回该元素
    @Override
    public Object remove(Object e) {
        //获得e在线性表中的索引值
        int index = indexOf(e);
        if (index < 0) {
            return 0;
        }
        return remove(index);
    }

    //删除线性表中第一个索引值为i的元素并返回该元素
    @Override
    public Object remove(int i) {
        //判断i是否越界
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException(i + "越界");
        }
        //保存即将删除的元素
        Object old = elements[i];
        //把i+1开始的元素依次前移
        for (int j = i; j < size - 1; j++) {
            elements[j] = elements[j + 1];
        }
        elements[size - 1] = 0;
        //修改元素个数
        size--;
        //返回删除元素
        return old;
    }

    //使用元素e替换线性表中i位置的元素并返回旧元素
    @Override
    public Object replace(int i, Object e) {
        //判断索引值是否越界
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException(i + "越界");
        }
        //保存元素原来的值
        Object old = elements[i];
        //替换
        elements[i] = e;
        //把原来的元素值返回
        return old;
    }

    //返回索引值为i的元素
    @Override
    public Object get(int i) {
        //判断索引值是否越界
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException(i + "越界");
        }
        return elements[i];
    }

    //在线性表元素p之前插入元素e
    @Override
    public boolean insertBefore(Object p, Object e) {
        //确定元素p在线性表中的位置
        int index = indexOf(p);
        if (index < 0) {
            //元素p不存在
            return false;
        }
        //插入元素
        insert(index, e);
        return true;
    }

    //在线性表元素p之后插入元素e
    @Override
    public boolean insertAfter(Object p, Object e) {
        //确定元素p在线性表中的位置
        int index = indexOf(p);
        if (index < 0) {
            //元素p不存在
            return false;
        }
        //插入元素
        insert(index + 1, e);
        return true;
    }

    //返回线性表中的最大值
    @Override
    public int getMax() {
        int maxIndex = (int) elements[0];
        for (int i = 1; i < size; i++) {
            if (maxIndex < (int) elements[i]) {
                maxIndex = (int) elements[i];
            }
        }
        return maxIndex;
    }

    //返回线性表中的最小值
    @Override
    public int getMin() {
        int minIndex = (int) elements[0];
        for (int i = 1; i < size; i++) {
            if (minIndex > (int) elements[i]) {
                minIndex = (int) elements[i];
            }
        }
        return minIndex;
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

    //数组扩容
    private void expandSpace() {
        //定义一个更大的数组
        Object[] newElements = new Object[elements.length * 2];
        //把原来的数据复制到新数组中
        for (int i = 0; i < elements.length; i++) {
            newElements[i] = elements[i];
        }
        //让原来的数组名指向新的数组
        elements = newElements;
    }
}
 */