import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

public class LinkedListQueue<E>
        implements Queue<E> , Iterable<E>{

    private static class Node<E>{
        E value;
        Node<E> next;

        public Node(E value, Node<E> next) {
            this.value = value;
            this.next = next;
        }
    }

    private int size;//当前节点数
    private int capacity=Integer.MAX_VALUE;//容量
    Node<E> head=new Node<>(null,null);
    Node<E> tail=head;
    {
        tail.next=head;
    }
    public LinkedListQueue() {

    }
    public LinkedListQueue(int capacity) {
        this.capacity=capacity;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return head==tail;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> current=head.next;
            @Override
            public boolean hasNext() {
                return current!=head;
            }

            @Override
            public E next() {
                E value=current.value;
                current=current.next;
                return null;
            }
        };

    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(E e) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    /**
     * 向队列尾部插入新值
     * @param e the element to add
     * @return
     */
    @Override
    public boolean offer(E e) {
        if(isFull()) {
            return false;
        }
        Node<E> added=new Node<>(e,head);
        tail.next=added;
        tail=added;
        size++;
        return true;
    }
    public boolean isFull() {
        if(size==capacity) {
            return true;
        }
        return false;
    }
    @Override
    public E remove() {
        return null;
    }

    @Override
    public E poll() {
        if(isEmpty()) return null;
        E value=head.next.value;
        if(head.next==tail) tail.next=head;
        head.next=head.next.next;

        return value;
    }

    @Override
    public E element() {
        return null;
    }

    @Override
    public E peek() {
        if(isEmpty()) return null;
        return head.next.value;
    }
}
