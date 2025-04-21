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
        int c;//添加前元素个数
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
            c=size.getAndIncrement();
            if(c+1<array.length){
                //offer 当前唤醒后面等待的offer线程
                tailWaits.signal();
            }



            //通知,告诉他们可以用了 headWaits.signal();
        }finally {
            tailLock.unlock();
        }
        if(c==0){
            //当c==0,说明目前是从0到1的过程，需要唤醒非空的poll线程
            headLock.lock();
            try{
                headWaits.signal();
            }finally {
                headLock.unlock();
            }
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

        }finally {
            tailLock.unlock();
        }
        //通知，你们不用再等了，ok了
        headLock.lock();
        try{
            headWaits.signal();
        }finally {
            headLock.unlock();
        }
        return true;
    }

    @Override
    public E poll() throws InterruptedException {
        E e;
        int c;//取走前的元素个数
        headLock.lockInterruptibly();
        try{
            //队列如果是空
            while(isEmpty()){
                headWaits.await();
            }
            //2.非空则出队
            e = array[head];
            array[head] = null;//help GC 垃圾回收
            if(++head == array.length){
                head = 0;
            }
            //3. 修改size
            c=size.getAndDecrement();
            if(c>1){
                //有富裕的元素，唤醒其他poll进程
                headWaits.signal();
            }

        }finally {
            headLock.unlock();
        }
        //当队列从满到不满，才会由poll唤醒等待不满的offer线程
        if(c==array.length){
            tailLock.lock();
            try{
                tailWaits.signal();
            }finally {
                tailLock.unlock();
            }
        }

        return e;
    }
    public boolean isFull(){
        return size.get() == array.length;
    }
    public boolean isEmpty(){
        return size.get()==0;
    }
}
