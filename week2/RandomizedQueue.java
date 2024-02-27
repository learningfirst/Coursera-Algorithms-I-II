package stack_and_queue;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] array;
    private int size;
    //private int head;
    private int tail;

    // construct an empty randomized queue
    public RandomizedQueue(){
        array = (Item[]) new Object[1];
        //head = 0;
        tail = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty(){
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size(){
        return size;
    }

    // add the item
    public void enqueue(Item item){
        if(item == null)
        {
            throw new IllegalArgumentException("datas are empty？？");
        }
        if(size == array.length) {
            Item[] temp = array;
            array =(Item[]) new Object[array.length*2];
            for (int i = 0; i < size ; i++)
            {
                array[i] = temp[i];
            }
            tail = size ;
        }
        array[tail] = item;
        tail = tail+1;
        size++;
    }

    // remove and return a random item
    public Item dequeue() {
        if(isEmpty()) {
            throw new NoSuchElementException("deque");
        }
        int index = StdRandom.uniformInt(tail);
        Item res = array[index];
        array[index] = null;
        swap(index, tail-1 );
        tail = tail-1;
        size--;
        if(size == array.length/4 && size != 0){
            Item[] temp = array;
            array =(Item[]) new Object[array.length/2];
            for (int i = 0; i < size; i++)
            {
                array[i] = temp[i];
            }
            //tail = mod(tail, array.length);
        }
        return res;
    }

    private void swap(int a, int b)
    {
        Item temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    // return a random item (but do not remove it)
    public Item sample(){
        if(isEmpty()) {
            throw new NoSuchElementException("deque");
        }
        int index = StdRandom.uniformInt(tail);
        Item res = array[index];
        return res;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator(){
        return new RandomIterator();
    }

    private class RandomIterator implements Iterator<Item>
    {
        int current = tail - 1;
        Item[] data;

        public RandomIterator(){
            data = (Item[]) new Object[size];
            for (int i = 0; i < size; i++)
            {
                data[i] = array[i];
            }
        }

        @Override
        public boolean hasNext()
        {
            return current > -1;
        }

        @Override
        public Item next()
        {
            if(current <= -1)
            {
                throw new NoSuchElementException("deque");
            }

            int index = StdRandom.uniformInt(current+1);
            Item temp = data[index];
            data[index] = data[current];
            data[current] = null;
            current--;
            return temp;
        }

        @Override
        public void remove()
        {
            throw new UnsupportedOperationException("不支持该操作");
        }
    }


    // unit testing (required)
    public static void main(String[] args){
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<>();
        randomizedQueue.enqueue(1);
        randomizedQueue.dequeue();
        randomizedQueue.enqueue(3);



    }

}