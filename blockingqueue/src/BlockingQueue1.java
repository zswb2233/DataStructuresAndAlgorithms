import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueue1<E> implements Blockingqueue<E> {
    private final E[] array;
    private int head;
    private int tail;
    private int size;
    public BlockingQueue1(int capacity) {
        array = (E[]) new Object[capacity];
    }
    ReentrantLock lock = new ReentrantLock();
    private Condition headWaits = lock.newCondition();
    private Condition tailWaits = lock.newCondition();

    @Override
    public void offer(E e) throws InterruptedException {
        lock.lockInterruptibly();
        try{
            while (isFull()){
                tailWaits.await(); //等多久
            }
            array[tail] = e;
            if(++tail == array.length){
                tail = 0;
            }
            size++;
            headWaits.signal();
        }finally {
            lock.unlock();
        }
    }

    /**
     * 时间到了会自己打开
     * @param e
     * @param timeout
     * @return
     * @throws InterruptedException
     */
    @Override
    public boolean offer(E e, long timeout) throws InterruptedException {// 毫秒
        lock.lockInterruptibly();
        try{
            long nt=TimeUnit.MILLISECONDS.toNanos(timeout);
            while (isFull()){
                if(nt<=0){
                    return false;
                }
                nt=tailWaits.awaitNanos(nt); //等多久纳秒 以及返回剩余时间
            }
            array[tail] = e;
            if(++tail == array.length){
                tail = 0;
            }
            size++;
            headWaits.signal();
            return true;
        }finally {
            lock.unlock();
        }
    }

    @Override
    public E poll() throws InterruptedException {
        lock.lockInterruptibly();
        try{
            while(isEmpty()){
                headWaits.await();
            }
            E e = array[head];
            array[head] = null;//help GC 垃圾回收
            if(++head == array.length){
                head = 0;
            }
            size--;
            tailWaits.signal();
            return e;
        }finally {
            lock.unlock();
        }
    }
    public boolean isFull(){
        return size == array.length;
    }
    public boolean isEmpty(){
        return head == tail;
    }
}
