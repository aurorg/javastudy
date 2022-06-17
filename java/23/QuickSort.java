public class QuickSort {
    public static void quickSort(int[] list){
        quickSort(list,0,list.length-1);
    }
    public static void quickSort(int[] list,int first,int last){
        if(last > first){
            int pivotIndex =partition(list,first,last);//排好序
            quickSort(list,first,pivotIndex -1);//处理子数组基准元素的前半部分
            quickSort(list,pivotIndex +1,last);//处理子数组基准元素的后半部分
        }
    }
    public static int partition(int[] list,int first,int last){
        int pivot = list[first];
        int low =first +1;
        int high = last;

        while(high >low){
            while(low <=high && list[low]<=pivot)//从左侧找第一个大于基准元素的元素
                low++;

            while(low <=high && list[high] >pivot)//从右侧找第一个小于基准元素的元素
                high--;

            if(high>low){//交换这两个元素的位置
                int temp =list[high];
                list[high] =list[low];
                list[low] =temp;
            }
        }
        while(high > first && list[high] >= pivot)//如果基准元素被移动了，返回将子数组分为两部分的基准元素的新下标
            high--;
        if(pivot > list[high]){
            list[first] = list[high];
            list[high] =pivot;
            return high;
        }
        else{
            return first;//否则返回基准元素的初始下标
        }
    }
    public static void main(String[] args){
        int[] list = {3,6,9,4,1,66,99};
        quickSort(list);
        for(int i =0;i<list.length;i++){
            System.out.print(list[i] + " ");
        }
    }
}


