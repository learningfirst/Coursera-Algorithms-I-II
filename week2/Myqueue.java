package stack_and_queue;

public class Myqueue<T>
{
    private Mystack<T> inStack;
    private Mystack<T> outStack;

    public Myqueue()
    {
        inStack=new Mystack<>();
        outStack=new Mystack<>();
    }

    public void enQueue(T data)
    {
        inStack.push(data);
    }

    public T deQueue()
    {
        if(outStack.isEmpty())
        {
            for (int i = inStack.getSize(); i >0; i--)
            {
                outStack.push(inStack.pop());
            }
        }

        T temp=outStack.pop();
        return temp;
    }

    public static void main(String[] args)
    {
        Myqueue<Integer> myqueue=new Myqueue<>();
        for (int i = 1; i <4 ; i++)
        {
            myqueue.enQueue(i);
        }
        for (int i=0;i<2;i++)
        {
            System.out.println("dequeue: "+myqueue.deQueue());
        }
    }
}
