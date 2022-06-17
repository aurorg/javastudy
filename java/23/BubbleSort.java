public class BubbleSort {
    public static void main(String[] args) {
        int[] list = {2, 5, 1, 4, -8, 9, 10};
        bubbleSort(list);
        for (int i = 0; i < list.length; i++) {
            System.out.print(list[i] + " ");
        }
    }

    public static void bubbleSort(int[] list) {
        boolean needNextPass = true;
        for (int i = 1; i < list.length && needNextPass; i++) {
            needNextPass = false;
            for (int j = 0; j < list.length - i; j++) {
                if (list[i] > list[i + 1]) {
                    int temp = list[i];
                    list[i] = list[i + 1];
                    list[i + 1] = temp;

                    needNextPass = true;
                }
            }
        }
    }
}
