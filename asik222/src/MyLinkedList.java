import java.util.Iterator;

public class MyLinkedList<T> implements MyList<T> {
    private static class MyNode<T> {
        T data;
        MyNode<T> prev;
        MyNode<T> next;

        public MyNode(T data, MyNode<T> prev, MyNode<T> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }

    private MyNode<T> head;
    private MyNode<T> tail;
    private int size;

    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public void add(T item) {
        addLast(item);
    }

    @Override
    public void set(int index, T item) {
        MyNode<T> node = getNodeAtIndex(index);
        node.data = item;
    }

    @Override
    public void add(int index, T item) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        if (index == size) {
            addLast(item);
            return;
        }
        MyNode<T> nodeAtIndex = getNodeAtIndex(index);
        MyNode<T> newNode = new MyNode<>(item, nodeAtIndex.prev, nodeAtIndex);
        if (nodeAtIndex.prev != null)
            nodeAtIndex.prev.next = newNode;
        else
            head = newNode;
        nodeAtIndex.prev = newNode;
        size++;
    }

    @Override
    public void addFirst(T item) {
        if (size == 0)
            addLast(item);
        else {
            MyNode<T> newNode = new MyNode<>(item, null, head);
            head.prev = newNode;
            head = newNode;
            size++;
        }
    }

    @Override
    public void addLast(T item) {
        MyNode<T> newNode = new MyNode<>(item, tail, null);
        if (tail != null)
            tail.next = newNode;
        else
            head = newNode;
        tail = newNode;
        size++;
    }

    @Override
    public T get(int index) {
        return getNodeAtIndex(index).data;
    }

    @Override
    public T getFirst() {
        if (head == null)
            throw new IndexOutOfBoundsException("List is empty");
        return head.data;
    }

    @Override
    public T getLast() {
        if (tail == null)
            throw new IndexOutOfBoundsException("List is empty");
        return tail.data;
    }

    @Override
    public void remove(int index) {
        MyNode<T> nodeToRemove = getNodeAtIndex(index);
        if (nodeToRemove.prev != null)
            nodeToRemove.prev.next = nodeToRemove.next;
        else
            head = nodeToRemove.next;
        if (nodeToRemove.next != null)
            nodeToRemove.next.prev = nodeToRemove.prev;
        else
            tail = nodeToRemove.prev;
        nodeToRemove.prev = null;
        nodeToRemove.next = null;
        nodeToRemove.data = null;
        size--;
    }

    @Override
    public void removeFirst() {
        if (head == null)
            throw new IndexOutOfBoundsException("List is empty");
        remove(0);
    }

    @Override
    public void removeLast() {
        if (tail == null)
            throw new IndexOutOfBoundsException("List is empty");
        remove(size - 1);
    }

    @Override
    public void sort() {
    }

    @Override
    public int indexOf(Object object) {
        int index = 0;
        MyNode<T> current = head;
        while (current != null) {
            if (current.data.equals(object))
                return index;
            current = current.next;
            index++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object object) {
        int index = size - 1;
        MyNode<T> current = tail;
        while (current != null) {
            if (current.data.equals(object))
                return index;
            current = current.prev;
            index--;
        }
        return -1;
    }

    @Override
    public boolean exists(Object object) {
        return indexOf(object) != -1;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        MyNode<T> current = head;
        int index = 0;
        while (current != null) {
            array[index++] = current.data;
            current = current.next;
        }
        return array;
    }

    @Override
    public void clear() {
        MyNode<T> current = head;
        while (current != null) {
            MyNode<T> next = current.next;
            current.prev = null;
            current.next = null;
            current.data = null;
            current = next;
        }
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    private MyNode<T> getNodeAtIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        MyNode<T> current;
        if (index < size / 2) {
            current = head;
            for (int i = 0; i < index; i++)
                current = current.next;
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--)
                current = current.prev;
        }
        return current;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private MyNode<T> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext())
                    throw new IndexOutOfBoundsException("No more elements");
                T data = current.data;
                current = current.next;
                return data;
            }
        };
    }
}
