/*public class MergeSort {
    public static void main(String[] args){
        int [] list = {9,6,4,2,11,55,88,99};
        mergeSort(list);
        for(int i =0;i<list.length;i++){
            System.out.print(list[i] + " ");
        }

    }

    public static void mergeSort(int[] list){
        if(list.length>1){
            //创建firstHalf数组，该数组是list的前半部分的一个copy
            int [] firstHalf = new int[list.length/2];
            System.arraycopy(list,0,firstHalf,0,list.length/2);
            mergeSort(firstHalf);//递归调用

            int secondHalfLength = list.length -list.length/2;
            //创建secondHalf数组，包含最初数组的后一半的元素
            int[] secondHalf = new int[secondHalfLength];
            System.arraycopy(list,list.length/2,secondHalf,0,secondHalfLength);
            mergeSort(secondHalf);

            merge(firstHalf,secondHalf,list);//排好序之后，合并为一个数组

        }
    }
    public static void merge(int[] list1,int [] list2,int[] temp){
        int current1 =0;
        int current2 =0;
        int current3 =0;

        //分三种情况讨论进行排序合并

        while(current1 <list1.length && current2 <list2.length){
            if(list1[current1] < list2[current2])
                temp[current3++] = list1[current1++];
            else
                temp[current3++] = list2[current2++];
        }

        while(current1 < list1.length)
            temp[current3++] = list1[current1++];

        while(current2 < list2.length)
            temp[current3++] = list2[current2++];
    }
}

 */
