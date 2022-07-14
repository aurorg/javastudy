import java.util.Set;
import java.util.TreeSet;

public class TestTreeSet {
    public static void main(String[] args){
        Set<String> set = new TreeSet<>();

        set.add("happy");
        set.add("life");
        set.add("aurora");
        set.add("luck");
        set.add("111");
        set.add("aurora");

        TreeSet<String> treeSet = new TreeSet<>(set);
        System.out.println(treeSet);

        System.out.println("first: " + treeSet.first());
    }
}
