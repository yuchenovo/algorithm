public class DoubleLinkedList<E> extends AbstractList<E> {
    private Node<E> first;
    private Node last;
    public static class Node<E> {
        E data;
        Node<E> next;
        Node<E> prev;
        public Node(E data, Node<E> prev , Node<E> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }

    /**
     * 根据索引查找节点
     *
     * @param index 指数
     * @return {@link Node}<{@link E}>
     */
    private Node<E> node(int index) {
        rangeCheck(index);
        if (index < (size >> 1)){
            Node<E> node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node;
        }else {
            Node<E> node = last;
            for (int i = size; i > index; i--) {
                node = node.prev;
            }
            return node;
        }
    }

    @Override
    public void clear() {
        size = 0;
        first = null;
        last = null;
    }

    @Override
    public E get(int index) {
        Node<E> node = node(index);
        return node.data;
    }

    @Override
    public E set(int index, E element) {
        Node<E> node = node(index);
        E oldValue = node.data;
        node.data = element;
        return oldValue;
    }

    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);
        if (index == size){
            Node oldlast = last;
            last = new Node<>(element, oldlast, null);
            if (oldlast == null){
                first = last;
            }else {
                oldlast.next = last;
            }
        }else {
            Node<E> next = node(index);
            Node<E> prev = next.prev;
            Node<E> node = new Node<>(element, prev, next);
            next.prev = node;
            if (prev == null){
                first = node;
            }else {
                prev.next = node;
            }
        }
        size++;
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);
        Node<E> node = node(index);
        Node<E> prev = node.prev;
        Node<E> next = node.next;
        if (prev == null){
            first = next;
        }else {
            next.prev = prev;
        }
        if (next == null){
            last = prev;
        }else {
            prev.next = next;
        }
        size--;
        return node.data;
    }


    @Override
    public int indexOf(E element) {
        if (element == null){
            Node<E> node = first;
            for (int i = 0; i < size; i++) {
                if (node.data == null) {
                    return i;
                }
                node = node.next;
            }
        }else {
            Node<E> node = first;
            for (int i = 0; i < size; i++) {
                if (node.data.equals(element)) {
                    return i;
                }
                node = node.next;
            }
        }
        return -1;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            if (i != 0){
                builder.append(",");
            }
            builder.append("{").append(node(i).data).append(",").append(node(i).next).append("}");
        }
        return builder.toString();
    }
}
