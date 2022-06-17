public class Test23_4 {
    private static int[] test = {1,3,2,5,7,9,6,99};
    public static void main(String[] args){
        int [] result = bucketSort(test);
        for(int i:result){
            System.out.print(i + " ");
        }

    }

    private static int[] bucketSort(int[] array){
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for(int i:array){
            min = Math.min(min,i);
            max = Math.max(max,i);
        }
        int [] bucket = new int[max -min +1];

        for(int i=0;i<array.length;i++){
            bucket[array[i] -min]++;
        }

        int j=0;
        for(int i=0;i<bucket.length;i++){
            while(bucket[i]-->0){
                array[j++] =i + min;
            }
        }
        return array;
    }
}
