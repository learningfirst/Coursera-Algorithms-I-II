package stack_and_queue;


public class Mystack<T>
{
    private Node<T> point;
    private int size;
    private class Node<S>
    {
        S data;
        Node<S> next;
    }

    public Mystack()
    {
        point=null;
        size=0;
    }

    public boolean isEmpty()
    {
        return point==null;
    }

    public void push(T data)
    {
        if(isEmpty()) {
            point=new Node<T>();
            point.data=data;
            point.next=null;
        }
        else {
            Node<T> oldNode=point;
            point=new Node<T>();
            point.data=data;
            point.next=oldNode;
        }
        size++;
    }

    public T pop()
    {
        if(isEmpty()) {
            return null;
        }

        T data=point.data;
        point=point.next;
        size--;
        return data;
    }

    public int getSize()
    {
        return size;
    }

    public void printStack()
    {
        if(size==0)
        {
            System.out.println("у╩ря©у");
            return;
        }

        for (Node<T> i = point; i != null; i = i.next)
        {
            System.out.println("stack:  " + i.data + "  ");
        }
    }

    public static void main(String[] args)
    {
        Mystack<Integer> mystack=new Mystack<>();
        mystack.push(1);
        mystack.push(2);
        mystack.push(3);
        System.out.println(mystack.getSize());
        mystack.printStack();
        mystack.pop();
        mystack.printStack();
        mystack.pop();
        mystack.printStack();
        System.out.println(mystack.pop());
        mystack.printStack();
        System.out.println(mystack.pop());
        mystack.printStack();
    }

}
