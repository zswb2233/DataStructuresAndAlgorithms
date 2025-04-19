import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueue2<E> implements Blockingqueue<E> {
    private final E[] array;
    private int head;
    private int tail;
    private AtomicInteger size;//原子整数类
    private ReentrantLock tailLock = new ReentrantLock();
    private Condition tailWaits = tailLock.newCondition();
    private ReentrantLock headLock = new ReentrantLock();
    private Condition headWaits = headLock.newCondition();

    public BlockingQueue2(int capacity) {
        array = (E[]) new Object[capacity];
    }



    @Override
    public void offer(E e) throws InterruptedException {
        tailLock.lockInterruptibly();
        try{
            //1.队列满则等待
            while (isFull()){
                tailWaits.await(); //等多久
            }
            //2.不满则入队
            array[tail] = e;
            if(++tail == array.length){
                tail = 0;
            }
            size.getAndIncrement();
            //通知,告诉他们可以用了 headWaits.signal();
        }finally {
            tailLock.unlock();
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
        tailLock.lockInterruptibly();
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
            size.getAndIncrement();
            //通知，你们不用再等了，ok了
            headWaits.signal();
            return true;
        }finally {
            tailLock.unlock();
        }
    }

    @Override
    public E poll() throws InterruptedException {
        headLock.lockInterruptibly();
        try{
            //队列如果是空
            while(isEmpty()){
                headWaits.await();
            }
            //2.非空则出队
            E e = array[head];
            array[head] = null;//help GC 垃圾回收
            if(++head == array.length){
                head = 0;
            }
            //3. 修改size
            size.getAndDecrement();
            tailWaits.signal();
            return e;
        }finally {
            headLock.unlock();
        }
    }
    public boolean isFull(){
        return size.get() == array.length;
    }
    public boolean isEmpty(){
        return size.get()==0;
    }
}
