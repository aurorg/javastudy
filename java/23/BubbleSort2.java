
public class BubbleSort2 {
    public static void main(String[] args){
        int[] list = {2,6,4,9,3,22,100};
        bubbleSort(list);
        for(int i =0;i<list.length;i++){
            System.out.print(list[i] + " ");
        }

    }
    public static void bubbleSort(int[] list){
        boolean flag= true;
        for(int i=1;i<list.length && flag;i++){
            flag =false;
            for(int j=0;j<list.length-i;j++){
                if(list[j] > list[j+1]){
                    int temp =list[j];
                    list[j] =list[j+1];
                    list[j+1]= temp;

                    flag =true;
                }
            }
        }
    }
}
