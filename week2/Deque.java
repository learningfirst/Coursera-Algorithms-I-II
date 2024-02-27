package stack_and_queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node<Item> first;
    private Node<Item> last;
    private int size;


    private class Node<S>
    {
        S data;
        Node<S> next;
        Node<S> previous;
    }

    // construct an empty deque
    public Deque()
    {
        first = null;
        last = first;
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty()
    {
        return size == 0;
    }

    // return the number of items on the deque
    public int size()
    {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item)
    {
        if(item == null)
        {
            throw new IllegalArgumentException("数据为空？？");
        }

        Node<Item> newNode = new Node<>();
        newNode.data = item;
        newNode.next = null;
        if(isEmpty()) {
            newNode.previous = null;
            last = newNode;
            first = newNode;
        }
        else {
            first.next = newNode;
            newNode.previous = first;
            first = newNode;
        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item){
        if(item == null)
        {
            throw new IllegalArgumentException("数据为空？？");
        }

        Node<Item> newNode = new Node<>();
        newNode.data = item;
        newNode.previous = null;
        if(isEmpty()) {
            newNode.next = null;
            last = newNode;
            first = newNode;
        }
        else {
            newNode.next = last;
            last.previous = newNode;
            last = newNode;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst(){
        if(isEmpty()) {
            throw new NoSuchElementException("deque为空，无法删除");
        }
        Item data= first.data;
        if(size == 1)
        {
            first = null;
            last = null;
            size--;
            return data;
        }
        first.previous.next = null;
        first = first.previous;
        size--;
        return data;
    }

    // remove and return the item from the back
    public Item removeLast(){
        if(isEmpty()) {
            throw new NoSuchElementException("deque为空，无法删除");
        }
        Item data= last.data;
        if(size == 1)
        {
            last = null;
            first = null;
            size--;
            return data;
        }
        last.next.previous = null;
        last = last.next;
        size--;
        return data;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator(){
        return new dequeIterator();
    }

    private class dequeIterator implements Iterator<Item>
    {
        private Node<Item> Current = first;

        @Override
        public boolean hasNext()
        {
            return Current != null;
        }

        @Override
        public Item next()
        {
            if(Current == null)
            {
                throw new NoSuchElementException("deque为空，无法迭代");
            }
            Item data = Current.data;
            Current = Current.previous;
            return data;
        }

        @Override
        public void remove()
        {
            throw new UnsupportedOperationException("不支持该操作");
        }
    }

    // unit testing (required)
    public static void main(String[] args)
    {
        System.out.println(-1%8);
        Deque<Integer> deque=new Deque<>();

        //deque.removeLast();

        //deque.addFirst(null);
        deque.addFirst(1);
        deque.addLast(2);
        deque.addFirst(3);
        deque.addLast(4);
        deque.addFirst(5);
        deque.addLast(6);
        
        //Iterator<Integer> i = deque.iterator();
        for (Integer n : deque)
        {
            System.out.println(n);
        }

        deque.removeFirst();
        deque.removeLast();
        deque.removeFirst();
        deque.removeLast();

        for (Integer n : deque)
        {
            System.out.println(n);
        }
    }

}