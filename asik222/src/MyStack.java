public class MyStack<T> {
    private MyLinkedList<T> list;

    public MyStack() {
        this.list = new MyLinkedList<>();
    }

    public void push(T item) {
        list.addLast(item);
    }

    public T pop() {
        if (isEmpty())
            throw new IllegalStateException("Stack is empty");
        // Remove the item and return it
        T item = list.getLast();
        list.removeLast();
        return item;
    }

    public T peek() {
        if (isEmpty())
            throw new IllegalStateException("Stack is empty");
        return list.getLast();
    }

    public boolean isEmpty() {
        return list.size() == 0;
    }

    public int size() {
        return list.size();
    }
}
