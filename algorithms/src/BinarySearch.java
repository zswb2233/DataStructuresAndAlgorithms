public class BinarySearch {
    /**
     * 二分查找基础版
     * 要求：有序序列
     */

    public static int binarySearchBasic(int[] a, int target) {
        int i=0;
        int j=a.length-1;//记录指针和初值
        //范围内有东西
        while(i<=j) {
            int m=(i+j)>>1;
            //不是(i+j)/2，因为可能超过最大值。
            //32为，int最大值为2^31-1，最高位是符号位
            if(target<a[m]){
                j=m-1;
            }else if(a[m]<target) {
                i = m + 1;
            }else{
                //找到了
                return m;
            }
        }
        return -1;//表示找不到
    }

    /**
     * j不作为边界,少一次减法运算。
     * @param a
     * @param target
     * @return
     */
    public static int binarySearchUpper(int[] a, int target) {
        int i=0;
        int j=a.length;//作为边界值
        //范围内有东西
        while(i<j) {
            int m=(i+j)>>1;
            //不是(i+j)/2，因为可能超过最大值。
            //32为，int最大值为2^31-1，最高位是符号位
            if(target<a[m]){
                j=m;
            }else if(a[m]<target) {
                i = m + 1;
            }else{
                //找到了
                return m;
            }
        }
        return -1;//表示找不到
    }

    /**
     * 缩小边界
     * @param a
     * @param target
     * @return
     */
    public static int binarySearch3(int[] a, int target) {
        int i=0, j=a.length;
        while(j-i>1){
            int m=(i+j)>>1;
            if(target<a[m]){
                j=m;
            }
            else{
                i=m;
            }
        }
        if(a[i]==target){
            return i;
        }else {
            return -1;
        }
    }

    /**
     * 当有多个位置的有序数组满足条件，要求最左边的情况，右边是同理。
     * @param a
     * @param target
     * @return
     */
    public static int binarySearchLeftmost1(int[] a, int target) {
        int i=0;
        int j=a.length-1;//记录指针和初值
        int candidate=-1;//候选者
        //范围内有东西
        while(i<=j) {
            int m=(i+j)>>1;
            //不是(i+j)/2，因为可能超过最大值。
            //32为，int最大值为2^31-1，最高位是符号位
            if(target<a[m]){
                j=m-1;
            }else if(a[m]<target) {
                i = m + 1;
            }else{
                //找到了
                //记录候选位置
                candidate = m;
                j=m-1;
            }
        }
        return candidate;//表示找不到
    }

    /**
     * 返回<=target 的最靠右的索引
     * @param a
     * @param target
     * @return
     */
    public static int binarySearchRightmost2(int[] a, int target) {
        int i=0,j=a.length-1;
        while(i<=j){
            int m=(i+j)>>1;
            if(target<a[m]){
                j=m-1;
            }else{
                i=m+1;
            }
        }
        return i-1;
    }
}