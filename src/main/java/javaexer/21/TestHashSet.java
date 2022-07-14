import java.util.HashSet;
import java.util.Set;

public class TestHashSet {
    public static void main(String[] args){
        Set<String> set = new HashSet<>();
        set.add("luck");//插入规则集没有固定的顺序，要强加顺序的话，就要使用LinkedHashSet
        set.add("aurora");
        set.add("fairy");
        set.add("happy");
        set.add("aurora");
        System.out.println(set);//输出的顺序是随机的

        for(String s:set){
            System.out.print(s.toUpperCase() + " ");
        }
        System.out.println();
        set.forEach(e -> System.out.print(e.toLowerCase() + " "));
    }
}
