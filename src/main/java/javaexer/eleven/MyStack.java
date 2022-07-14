import java.util.ArrayList;
public class MyStack {
    private ArrayList<Object> list = new ArrayList<>();  //创建一个数组列表用来存储栈中的元素

    public boolean isEmpty(){
        return list.isEmpty();  //如果栈为空，返回true
    }

    public int getSize(){
        return list.size();  //返回该栈中元素的个数
    }

    public Object peek(){
        return list.get(getSize()-1);  //返回栈顶元素并且不移除
    }

    public Object pop(){          //返回该栈的栈顶元素并且移除
        Object o = list.get(getSize()-1);
        list.remove(getSize()-1);
        return o;
    }

    public void push(Object o){   //添加一个新的元素到该栈的顶部
        list.add(o);
    }

    @Override
    public String toString(){
        //list.toString( )方法重写了Object类中定义的toString()方法，用来显示栈中的内容
        return "stack: " + list.toString(); //ArrayList中实现的toString()方法返回表示数组列表中所有元素的字符串表示
    }
}
