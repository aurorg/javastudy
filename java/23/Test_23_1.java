import java.util.Arrays;

public class Test_23_1 {
    public static void main(String[] args) {
        int[] ints = insertionSort(new int[]{9, 5, 2, 1, 44, 22, 99, 66});
        System.out.println(Arrays.toString(ints));
    }

    public static int[] insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        return array;


    }
}

