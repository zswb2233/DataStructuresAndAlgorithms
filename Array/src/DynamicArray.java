/*
动态数组
 */
public class DynamicArray {

    private int size;
    private int capacity=8;
    private int[] array={};

    public void addLast(int element) {
//        array[size]=element;
//        size++;
        add(size, element);
    }
    private void checkAndGrow(){
        if(size==0){
            array=new int[capacity];
        }
        //容量检查
        if(size==capacity) {
            //进行扩容，1.5倍
            capacity+=capacity>>1;
            int[] newArray = new int[capacity];
            System.arraycopy(array, 0, newArray, 0, size);
            array=newArray;

        }
    }
    public void add(int index, int element) {
        checkAndGrow();
        if(index>=0&&index<size){
            System.arraycopy(array,index,array,index+1,size-index);

        }
        if(index<0||index>size) return;//说明错误
        array[index]=element;
        size++;

    }
    public int remove(int index){
        int removed=array[index];
        System.arraycopy(array,index+1,array,index,size-index);
        size--;
        return removed;
    }
    /**
     * 查询元素
     */
    public int get(int index){
        return array[index];
    }
    /*
    在遍历中，先╭(╯^╰)╮横在竖效率更高。
    局部性原理，会把该数附件的数都缓存
     */
}
