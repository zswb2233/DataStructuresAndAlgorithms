import java.util.Iterator;

public class DoublyLinkedListSentinel implements Iterable<Integer> {
    private IllegalArgumentException illegalIndex(int index){
        throw new IllegalArgumentException(String.format("Index %d 不合法", index));
    }

    @Override
    public Iterator<Integer> iterator() {
        //匿名内部类
        return new Iterator<Integer>() {
            Node p = head.next;
            @Override
            public boolean hasNext() {//是否有下一个元素
                return p!= tail;
            }
            @Override
            public Integer next() {//返回当前值，并指向下一个元素
                int v=p.value;
                p = p.next;
                return v;
            }
        };
    }


    static class Node {
        Node prev;
        int value;
        Node next;
        public Node(Node prev, int value,Node next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }
    }
    private Node head;//头哨兵
    private Node tail;//尾哨兵

    public DoublyLinkedListSentinel() {
        head=new Node(null,0,null);
        tail=new Node(null,0,null);
        head.next=tail;
        tail.prev=head;
    }
    private Node findNode(int index) {
        int i=-1;
        for(Node p=head;p!=tail;p=p.next) {
            if(index==i){
                return p;
            }
        }
        return null;
    }
    public void insert(int index,int value) {
        Node prev = findNode(index - 1);
        if(prev==null) {
            throw illegalIndex(index);
        }
        Node next=prev.next;
        Node inserted=new Node(prev,value,next);
        prev.next=inserted;
        next.prev=inserted;
    }
    public void remove(int index) {
        Node prev = findNode(index - 1);
        if(prev==null) {
            throw illegalIndex(index);
        }

        Node removed=prev.next;
        if(removed==tail) {
            throw illegalIndex(index);
        }
        Node next=removed.next;
        prev.next=next;
        next.prev=prev;
    }
    public void addLast(int value) {
        Node prev=tail.prev;
        Node addlist=new Node(prev,value,tail);
        prev.next=addlist;
        tail.prev=addlist;
    }
    public void removeLast() {
        Node removed=tail.prev;
        if(removed==head) {
            throw illegalIndex(0);
        }
        removed.prev.next=tail;
        tail.prev=removed.prev;
    }

}
