package twentyone;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class TestMap {
    public static void main(String[] args){
        Map<String,Integer> hashMap = new HashMap<>();//HashMap中的顺序是随机的
        hashMap.put("qianqian",1 );
        hashMap.put("nana",2);
        hashMap.put("lili",3);
        hashMap.put("yuyu",4);


        System.out.println("Display entries in hashMap");
        System.out.println(hashMap + "\n");


        Map<String,Integer> treeMap = new TreeMap<>(hashMap);//TreeMap中的条目是按照键的升序排列的
        System.out.println("Display entries in ascending order of key");
        System.out.println(treeMap);

        //linkedHashMap是按照元素最后一次被访问的时间
        Map<String,Integer> linkedHashMap = new LinkedHashMap<>(16,0.75f,true);
        linkedHashMap.put("qianqian",1);
        linkedHashMap.put("nana",2);
        linkedHashMap.put("lili",3);
        linkedHashMap.put("yuyu",4);

        System.out.println("\nThe age for " + "Lewis is " + linkedHashMap.get("Lewis"));

        System.out.println("Display entries in LinkedHashMap");
        System.out.println(linkedHashMap);

        System.out.println("\nNames and ages are ");
        treeMap.forEach((name,age) -> System.out.print(name + ":" + age + " "));

    }
}
