public interface FIFOQueue<E> {
    public void enqueue(E element);
    // Insert element at rear of queue
    public boolean isEmpty();
    // Is the queue empty?
    public E dequeue();
    // Remove element from front of queue

    public int size() ;

    //need it for reverse
    public E peek();
}
