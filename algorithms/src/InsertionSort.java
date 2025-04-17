public class InsertionSort {
    public static void sort(int[] a){

    }

    /**
     * 递归插入排序
     * @param a
     * @param low
     */
    private static void insertion(int[] a,int low){
        if(low==a.length){
            return;
        }
        int t=a[low];
        int i=low-1;//已排序区域指针
        while(i>=0 && a[i]>t){

            a[i+1]=a[i];

            i--;
        }
        if(i+1!=low){
            a[i+1]=t;
        }
        insertion(a,low+1);
    }
}
