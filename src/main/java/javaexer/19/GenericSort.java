public class GenericSort {
    public static void main(String[] args){
        Integer[] intArray = {Integer.valueOf(2),Integer.valueOf(4),Integer.valueOf(3)};

        Double[] doubleArray = {Double.valueOf(3.4),Double.valueOf(1.3),Double.valueOf(-22.1)};

        Character[] charArray = {Character.valueOf('a'),Character.valueOf('J'),Character.valueOf('r')};

        String[] stringArray = {"Tom","Susan","Kim"};

        sort(intArray);
        sort(doubleArray);
        sort(charArray);
        sort(stringArray);

        System.out.print("integer的排序：");
        printList(intArray);

        System.out.print("double的排序：");
        printList(doubleArray);

        System.out.print("charArray的排序：");
        printList(charArray);

        System.out.print("stringArray的排序：");
        printList(stringArray);
    }


    //使用Comparable接口，方面后面使用compareTo
    public static <E extends Comparable<E>> void sort(E[] list){
        E currentMin;
        int currentMinIndex;

        for(int i=0;i<list.length-1;i++){
            currentMin = list[i];
            currentMinIndex = i;

        for(int j = i+1;j<list.length;j++){
            if(currentMin.compareTo(list[j])> 0){
                currentMinIndex=j;
            }
        }
        if(currentMinIndex!=i){
            list[currentMinIndex] = list[i];
            list[i] =currentMin;
          }
        }
    }
    public static void printList(Object[] list){
        for(int i =0;i<list.length;i++)
            System.out.print(list[i] + "");
        System.out.println();
    }
}
