import java.util.*;
public class TestIterator {
    public static void main(String[] args){
        Collection<String> collection = new ArrayList<>();//创建一个集体的集合对象
        collection.add("New York");
        collection.add("Atlanta");
        collection.add("Dallas");
        collection.add("Madison");

        Iterator<String> iterator = collection.iterator(); //获得集合的一个迭代器，并且使用该迭代器来遍历线性表中的所有字符串
        while (iterator.hasNext()){
            System.out.print(iterator.next().toUpperCase() + " "); //大写
        }
        System.out.println();
    }
}
