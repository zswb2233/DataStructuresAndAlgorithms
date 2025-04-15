public class BubbleSort {
    /**
     * 递归冒泡
     * @param arr
     * @param j
     */
    private static void bubbleSort(int[] arr,int j) {
        if(j==0) return;
        for(int i=1;i<j;i++) {
            if(arr[i]>arr[i+1]) {
                int temp = arr[i];
                arr[i] = arr[i+1];
                arr[i+1] = temp;
            }
        }
    }

    /**
     * 非递归冒泡
     * @param arr
     */
    private static void bubbleSort2(int[] arr) {
        for(int i=1;i<arr.length;i++) {
            for(int j=0;j<i;j++) {
                if(arr[j]>arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }
}
