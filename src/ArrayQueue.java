import java.util.EmptyStackException;
import java.util.Stack;

public class ArrayQueue<E extends Comparable<E>> implements FIFOQueue<E>{
    private E[] dataArray;
    private int front;
    private int rear;
    private int numElements;

    public ArrayQueue() {
        dataArray = (E[])new Object[1];
        front = 0;
        rear = 0;
        numElements = 0;
    }
    public ArrayQueue(int capacity) {
        dataArray = (E[])new Comparable[capacity];
        front = 0;
        rear = 0;
        numElements = 0;
    }

    @Override
    public void enqueue(E element) {

        if (numElements == dataArray.length) // OR if (numElements == dataArray.length) throw new FullQueueException();
            reallocate();
        dataArray[rear] = element;
        rear = (rear+1) % dataArray.length;
        numElements++;
    }

    private void reallocate() {
        E[] newArray =
                (E[])new Object[numElements*2];
        int j = front;
        for (int i=0; i<numElements; i++) {
            newArray[i] = dataArray[j];
            j ++ ;
        }
        front = 0;
        rear = numElements+1;
        dataArray = newArray;
    }

    @Override
    public boolean isEmpty() {
        return (numElements == 0);
    }

    @Override
    public E dequeue() {
        if (numElements == 0) throw new EmptyStackException();
        E item = dataArray[front];
        dataArray[front] = null;
        front = (front + 1) % dataArray.length;
        if (front==rear) {
            System.out.println("the queue is now empty");
        }
        numElements --;
        return item;
    }

    @Override
    public E peek(){
        if (numElements == 0){
            throw new EmptyStackException();
        }
        return dataArray[front];
    }

    public static void reverse(FIFOQueue<String> q) {
        Stack<String> stack = new Stack<>();
        while (!q.isEmpty()) {
            stack.add(q.peek());
            q.dequeue();
        }
        while (!stack.isEmpty()) {
            q.enqueue(stack.peek());
            stack.pop();
        }
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        return numElements;
    }
    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < dataArray.length; i++) {
            s += dataArray[i] +" ";
        }
        return s;
    }

    public E dequeuePriority(){
        if (numElements == 0) throw new EmptyStackException();

        int index=front;
        int index_next=front+1;
        int max_index=index;;
        E max=dataArray[index];
        for (int i=0;i<numElements;i++){
            if (dataArray.length<=index || dataArray.length<=index_next){
                index=index % dataArray.length;
                index_next=index_next % dataArray.length;
            }
            if (dataArray[index].compareTo(dataArray[index_next]) < 0){
                max_index=index_next;
                max=dataArray[index_next];
            }
            index++;
            index_next++;
        }


        if (max_index==front){
            dataArray[front] = null;
            front = (front + 1) % dataArray.length;
            numElements--;
            return max;
        }


        int rear_prev=rear-1;
        if (rear_prev<0){
            rear_prev+=dataArray.length;
        }
        if (max_index == rear_prev){
            rear=rear_prev;
            dataArray[rear_prev] = null;
            numElements--;
            return max;
        }

        
        dataArray[max_index] = null;
        for (int i=max_index;i+1<=rear_prev;i = i+1 % dataArray.length){
            dataArray[i] = dataArray[i+1];
            dataArray[i+1]=null;
        }
        rear=rear_prev;
        numElements--;
        return max;
    }


    public static void main(String[] args) {
        FIFOQueue<String> q=new ArrayQueue<>(3);
        q.enqueue("a");
        q.enqueue("b");
        q.enqueue("c");

        System.out.println(q);
        System.out.println();

        ArrayQueue.reverse(q);

        System.out.println(q);

        ArrayQueue<Integer> q2=new ArrayQueue<>(8);
        q2.enqueue(1);
        q2.enqueue(2);
        q2.enqueue(3);
        q2.enqueue(4);
        q2.enqueue(5);
        q2.enqueue(78);
        q2.enqueue(15);
        q2.enqueue(11);

        System.out.println();
        System.out.println(q2);
        System.out.println(q2.dequeuePriority());
        System.out.println(q2);

        System.out.println();
        q2=new ArrayQueue<>(8);
        q2.enqueue(1);
        q2.enqueue(2);
        q2.enqueue(3);
        q2.enqueue(4);
        q2.enqueue(5);
        q2.enqueue(6);
        q2.enqueue(7);
        q2.enqueue(8);
        System.out.println(q2);
        System.out.println(q2.dequeuePriority());
        System.out.println(q2);

        System.out.println();
        ArrayQueue<Integer> q3=new ArrayQueue<>(4);
        q3.enqueue(78);
        q3.enqueue(2);
        q3.enqueue(3);
        q3.enqueue(4);
        System.out.println(q3);
        System.out.println(q3.dequeuePriority());
        System.out.println(q3);
    }

}
