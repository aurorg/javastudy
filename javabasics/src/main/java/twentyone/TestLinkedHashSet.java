package twentyone;

import java.util.LinkedHashSet;
import java.util.Set;

public class TestLinkedHashSet {
    public static void main(String[] args){
        Set<String> set = new LinkedHashSet<>();
        set.add("happy");
        set.add("aurora");
        set.add("luck");
        set.add("life");
        set.add("candy");

        System.out.println(set);

        for(String elements: set){
            System.out.print(elements.toLowerCase() + " ");//按照输入顺序来输出
        }
    }
}
