/*import java.util.Arrays;

public class InsertionSort {
    public static void main(String[] args) {
        int[] ints = insertionSort(new int[]{45,22,55,33,44,66,11,23,54,88,16});
        System.out.println(Arrays.toString(ints));
    }


    public static int[] insertionSort(int[] array){
        //由于默认认为第一个是有序的，则从第二个元素开始比较，因此循环从1开始
        for (int i = 1; i <array.length ; i++) {
            //从当前元素依次循环遍历与前面所有有序的元素挨个比较，找到合适的插入位置交换位置
            for (int j = i-1; j >=0 ; j--) {
                //如果后一个数比前面任何一个数小则交换，一直交换到合适的位置(这里是升序排序)
                //如果想要降序排序，则修改为>即可
                if (array[j+1]<array[j]){
                    int temp=array[j+1];
                    array[j+1]=array[j];
                    array[j]=temp;
                }
            }
        }
        //返回已经排序完毕的数组
        return array;
    }
}

 */
