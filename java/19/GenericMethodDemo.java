public class GenericMethodDemo {
    public static void main(String[] args){
        Integer[] integers = {1,2,3,4,5};
        String[] strings = {"London","Paris","New York","Austin"};

        GenericMethodDemo.<Integer>print(integers);  //调用泛型方法，也可以这样调用print(integers);
        GenericMethodDemo.<String>print(strings);

    }
    public static <E> void print(E[] list){  //声明泛型方法
        for(int i =0;i<list.length;i++)
            System.out.print(list[i] + "");
        System.out.println();
    }
}
