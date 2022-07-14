/*public class TongSort {
    //待排序数组
    private static int[] test = {1, 3, 5, 0, -2, 9, 33, -20, 0};

    public static void main(String[] args) {
        int[] result = bucketSort(test);//调用
        for (int i : result) {
            System.out.println(i);
        }
    }

    private static int[] bucketSort(int[] array) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        //找出数组中的最大值和最小值并记录下来
        for (int i : array) {
            min = Math.min(min, i);
            max = Math.max(max, i);
        }
        //辅助数组的大小为max-min+1
        int[] bucket = new int[max - min + 1];
        for (int i = 0; i < array.length; i++) {
            //减去min是为了防止array[i]小于0，数组索引可是不能小于0的，相当于做了个偏移。++是为了记录次数
            bucket[array[i] - min]++;
        }
        int j = 0;
        //遍历辅助数组，取出结果
        for (int i = 0; i < bucket.length; i++) {
            while (bucket[i]-- > 0) {
                array[j++] = i + min;
            }
        }
        return array;
    }
}

 */