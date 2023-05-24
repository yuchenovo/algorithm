public class Stack<E>  {
//extends ArraryList<E> 直接继承会导致栈可以使用数组的其他方法
    List<E> list = new ArraryList<>();
    public void clear(){list.clear();}
    public int size(){
        return list.size();
    }
    public Boolean isEmpty(){return list.isEmpty();}
    public void push(E element){
        list.add(element);
    }
    public E pop(){
        return list.remove(list.size() - 1);
    }
    public E top(){
        return list.get(list.size() - 1);
    }
}
