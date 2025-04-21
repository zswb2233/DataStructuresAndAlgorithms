import java.util.Arrays;

//大顶堆
public class MaxHeap {
    int[] array;
    int size;
    public MaxHeap(int[] array) {
        this.array = array;
        this.size = array.length;
        heapify();
    }
    //建堆
    private void heapify(){
        //1.找到第一个非叶子节点
        //size/2-1
        for(int i = size/2 - 1; i >= 0; i--){
            down(i);
        }
        //2.从后往前，对每个节点执行下潜
    }
    //堆排序
    public void heapSort(){
        while(size > 1){
            swap(0, size - 1);
            size--;
            down(0);
        }
    }
    //将parent 索引处的元素下潜：与两个孩子较大者交换，直至没孩子或孩子没他大
    public void down(int parent){
        int left=parent*2+1;
        int right=left+1;
        int max=parent;
        if(left<size&&array[left]>array[max]){
            max=left;
        }
        if(right<size&&array[right]>array[max]){
            max=right;
        }
        if(max!=parent){
            //孩子比父节点大
            swap(max,parent);
            down(max);
        }
    }
    public void swap(int i, int j){
        int temp=array[i];
        array[i]=array[j];
        array[j]=temp;
    }
    public int peek() {
        if(isEmpty()){
            throw illegalIndexException(0);
        }
        return array[0];
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public boolean isFull() {
        return size == array.length;
    }
    public IllegalArgumentException illegalIndexException(int index){
        throw  new IllegalArgumentException("Index out of bounds");
    }
    //删除堆顶元素
    public int poll(){
        int top=array[0];
        swap(0,size-1);
        size--;
        down(0);//下潜
        return top;
    }
    public int poll(int index){
        int top=array[index];
        swap(index,size-1);
        size--;
        down(index);//下潜
        return top;
    }
    //替换堆顶元素
    public void replace(int replaced){
        array[0]=replaced;
        down(0);
    }
    public boolean offer(int element){
        if(size==array.length){
            return false;
        }
        up(element);
        size++;
        return true;
    }
    //将offered 元素上浮：直至offered小于父元素或堆顶
    public void up(int offered){
        int child=size;
        while(child>0){
            int parent=(child-1)/2;
            if(offered>array[parent]){
                array[child]=array[parent];
            }else{
                break;
            }
            child=parent;
        }
        array[child]=offered;
    }
    public static void main(String[] args) {
        int[] array = {1,2,3,4,5,6,7};
        MaxHeap heap = new MaxHeap(array);

            System.out.println(Arrays.toString(heap.array));
        heap.offer(8);
        System.out.println(Arrays.toString(heap.array));

        int[] array2 = {1,2,3,4,5,6,7,8};
        MaxHeap heap2 = new MaxHeap(array2);

        System.out.println(Arrays.toString(heap2.array));
        heap2.heapSort();
        System.out.println(Arrays.toString(heap2.array));
    }
}
