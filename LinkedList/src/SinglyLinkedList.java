import java.util.*;
import java.util.function.Consumer;
import org.junit.Test;

public class SinglyLinkedList implements Iterable<Integer> {
    Node head;//头指针
    //可以加个哨兵，减少下面的判断为零的情况（略）
    @Override
    public Iterator<Integer> iterator() {
        //匿名内部类
        return new Iterator<Integer>() {
            Node p = head;
            @Override
            public boolean hasNext() {//是否有下一个元素
                return p!= null;
            }
            @Override
            public Integer next() {//返回当前值，并指向下一个元素
                int v=p.value;
                p = p.next;
                return v;
            }
        };
    }

    private class Node {
        int value;
        Node next;
        public Node(int value,Node next) {
            this.value = value;
            this.next = next;
        }
    }

    /**
     * 头结点增加新链表（头插法）
     * @param value
     */
    public void addFirst(int value){
//        //1.链表为空
//        head=new Node(value,null);
//
//        //2.链表非空
        head=new Node(value,head);
    }

    /**
     * 遍历链表1,2,Interator,递归遍历(略)
     */
    public void loop1(Consumer<Integer> consumer){
        Node p = head;
        while(p!=null){
            consumer.accept(p.value);
            p=p.next;
        }
    }
    public void loop2(Consumer<Integer> consumer){
        for(Node p=head;p!=null;p=p.next){
            consumer.accept(p.value);
        }
    }

    private Node findLast(){
        if(head==null){
            return null;
        }
        Node p;
        for(p=head;p!=null;p=p.next){};
        return p;
    }
    public void addLast(int value){
        Node last=findLast();
        if(last==null){
            addFirst(value);
            return;
        }
        //效率低，log_2(N);
        last.next=new Node(value,null);
    }
    private Node findNode(int index){
        int i=0;
        for(Node p=head;p!=null;p=p.next){
            if(i==index){
                return p;
            }
        }
        return null;
    }
    public int get(int index){
        Node node=findNode(index);
        if(node==null){
            throw new IllegalArgumentException(String.format("Index %d is not found", index));
        }
        return node.value;
    }

    /**
     * 插入节点
     * @param index
     * @param value
     */
    public void insert(int index, int value){
        if(index==0){
            addFirst(value);
            return;
        }
        //先找到index的上一个节点，创建新节点，改变指向

        Node prev=findNode(index-1);
       if(prev==null){
           //找不到
           throw new IllegalArgumentException(String.format("Index %d 不合法", index));

       }
        prev.next=new Node(value,prev.next);
    }

    /**
     * 删除头结点
     */
    private IllegalArgumentException illegalIndex(int index){
        throw new IllegalArgumentException(String.format("Index %d 不合法", index));
    }
    public void removeFirst(){
        if(head==null){
            throw illegalIndex(0);

        }
        head=head.next;

    }
    public void remove(int index){
        if(index==0){
            removeFirst();
            return;
        }
       Node prev= findNode(index-1);
       if(prev==null){
           throw illegalIndex(index);
       }
       Node remove=prev.next;
       if(remove==null){
           throw illegalIndex(index);
       }
       prev.next=prev.next.next;
    }
}
