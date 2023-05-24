public class ArraryList<E> extends AbstractList<E>{
    private static final int DEFAULT_CAPACITY = 10;
    private E[] data;
    private final Object[] EMPTY_ELEMENTDATA = {};

    public ArraryList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.data = (E[])new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.data = (E[])EMPTY_ELEMENTDATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: "+
                    initialCapacity);
        }
    }
    public ArraryList(){
        this(DEFAULT_CAPACITY);
    }
    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            data[i] = null;
        }
        size = 0;
    }

    @Override
    public E get(int index) {
        rangeCheck(index);
        return data[index];
    }

    @Override
    public E set(int index, E element) {
        rangeCheck(index);
        E e = data[index];
        data[index] = element;
        return e;
    }

    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);
        if (size - index >= 0) {
            System.arraycopy(data, index, data, index + 1, size - index);
        }
        data[index] = element;
        size++;
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);
        E e = data[index];
        if (size - index - 1 > 0) {
            System.arraycopy(data, index + 1, data, index, size - index - 1);
        }
        data[--size] = null;
        return e;
    }

    @Override
    public int indexOf(E element) {
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (data[i]==null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(data[i])) {
                    return i;
                }
            }
        }
        return -1;
    }
}
