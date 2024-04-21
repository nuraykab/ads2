public class MyMinHeap<T extends Comparable<T>> {
    private MyArrayList<T> list;

    public MyMinHeap() {
        this.list = new MyArrayList<>();
    }

    public void insert(T item) {
        list.add(item);
        heapifyUp(list.size() - 1);
    }

    public T extractMin() {
        if (isEmpty())
            throw new IllegalStateException("Heap is empty");
        T min = list.get(0);
        int lastIndex = list.size() - 1;
        list.set(0, list.get(lastIndex));
        list.remove(lastIndex);
        heapifyDown(0);
        return min;
    }

    public T peekMin() {
        if (isEmpty())
            throw new IllegalStateException("Heap is empty");
        return list.get(0);
    }

    public boolean isEmpty() {
        return list.size() == 0;
    }

    public int size() {
        return list.size();
    }

    private void heapifyUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (list.get(index).compareTo(list.get(parentIndex)) >= 0)
                break;
            swap(index, parentIndex);
            index = parentIndex;
        }
    }

    private void heapifyDown(int index) {
        int leftChildIndex, rightChildIndex, minIndex;
        while (true) {
            leftChildIndex = 2 * index + 1;
            rightChildIndex = 2 * index + 2;
            minIndex = index;
            if (leftChildIndex < list.size() && list.get(leftChildIndex).compareTo(list.get(minIndex)) < 0)
                minIndex = leftChildIndex;
            if (rightChildIndex < list.size() && list.get(rightChildIndex).compareTo(list.get(minIndex)) < 0)
                minIndex = rightChildIndex;
            if (minIndex == index)
                break;
            swap(index, minIndex);
            index = minIndex;
        }
    }

    private void swap(int i, int j) {
        T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
}
