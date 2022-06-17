public class TestMethodsInCollection {
    public static void main(String[] args){
        java.util.Set<String> set1 = new java.util.HashSet<>();//创建规则集set1
        set1.add("happy");
        set1.add("aurora");
        set1.add("fairy");
        set1.add("life");

        System.out.println("set1 is " + set1);
        System.out.println(set1.size()  +  "  elements in set1");

        java.util.Set<String> set2 = new java.util.HashSet<>();//创建规则集set2
        set2.add("111");
        set2.add("222");
        set2.add("333");
        System.out.println("\nset2 is " + set2);
        System.out.println(set2.size() + "elements in set2");

        System.out.println("\nIs 555 in set2  " + set2.contains("555"));
        set1.addAll(set2);//将2添加到1中
        System.out.println("\nAfter adding set2 to set1,set1 is " + set1);

        set1.removeAll(set2);//从1中删除2
        System.out.println("\nAfter removing set2 from set1,set1 is " + set1);

        set1.retainAll(set2);//保留1，2共有的元素
        System.out.println("After retaining set2 from set1 ,set1 is " + set1);


    }
}
