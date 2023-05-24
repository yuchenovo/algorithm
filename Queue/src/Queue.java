

public class Queue<E> {
    private final LinkedList<E> list = new LinkedList<E>();
    public int size(){
        return list.size();
    }
    public Boolean isEmpty(){
        return list.isEmpty();
    }
    public void enQueue(E element){
        list.add(element);
    }
    public E deQueue(){
        return list.remove(0);
    }
    public E front(){
        return list.get(0);
    }
    public void clear(){
        list.clear();
    }

}
