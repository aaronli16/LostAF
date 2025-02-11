package priorityqueues;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @see ExtrinsicMinPQ
 */
public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
    // IMPORTANT: Do not rename these fields or change their visibility.
    // We access these during grading to test your code.
    static final int START_INDEX = 1;
    List<PriorityNode<T>> items;
    HashMap<T, Integer> itemToIndex;
    // can add here

    public ArrayHeapMinPQ() {
        items = new ArrayList<>();
        items.add(null);
        itemToIndex = new HashMap<>();

    }

    // Here's a method stub that may be useful. Feel free to change or remove it, if you wish.
    // You'll probably want to add more helper methods like this one to make your code easier to read.
    /**
     * A helper method for swapping the items at two indices of the array heap.
     */
    private void swap(int a, int b) {

        PriorityNode<T> temp = items.get(a);
        items.set(a, items.get(b));
        items.set(b, temp);
        itemToIndex.put(items.get(a).getItem(), a);
        itemToIndex.put(items.get(b).getItem(), b);
    }

    @Override
    public void add(T item, double priority) {
        if (itemToIndex.containsKey(item)) {
            throw new IllegalArgumentException("Already have item");
        }
        PriorityNode<T> node = new PriorityNode<>(item, priority);
        items.add(node);
        itemToIndex.put(item, items.size() - 1);
        this.percolateUp(items.size() - 1);

    }

    private void percolateUp(int index) {
        while (index > START_INDEX) {
            int parentIndex = (index / 2);
            if (items.get(index).getPriority() < items.get(parentIndex).getPriority()) {
                 swap(index, parentIndex);
                 index = parentIndex;
             } else {
                return;
            }
        }
    }

    @Override
    public boolean contains(T item) {
        return itemToIndex.containsKey(item);
    }

    @Override
    public T peekMin() {
        if (items.size() <= START_INDEX) {  // Need to add this check
            throw new NoSuchElementException();
        }
        return items.get(START_INDEX).getItem();

    }

    @Override
    public T removeMin() {
        if (items.size() <= START_INDEX) {
            throw new NoSuchElementException();
        }

        T result = items.get(START_INDEX).getItem();
        itemToIndex.remove(result);

        if (items.size() == 2) {
            items.remove(START_INDEX);
            return result;
        }


        swap(START_INDEX, items.size() - 1);
        items.remove(items.size() - 1);

        if (items.size() > START_INDEX) {
            percolateDown(START_INDEX);
        }

        return result;
    }

    private void percolateDown(int index) {
        while (2 * index < items.size()) {
            int leftIndex = 2 * index;
            int rightIndex = 2 * index + 1;
            int minChildIndex = leftIndex;


            if (rightIndex < items.size() &&
                items.get(rightIndex).getPriority() < items.get(leftIndex).getPriority()) {
                minChildIndex = rightIndex;
            }


            if (items.get(index).getPriority() <= items.get(minChildIndex).getPriority()) {
                return;
            }
            swap(index, minChildIndex);
            index = minChildIndex;
        }
    }

    @Override
    public void changePriority(T item, double priority) {
        if (!this.contains(item)) {
            throw new NoSuchElementException("Item is not in the Heap");
        }
        int index = itemToIndex.get(item);
        PriorityNode<T> curr = items.get(index);
        double oldPriority = items.get(index).getPriority();
        curr.setPriority(priority);
        if (priority < oldPriority) {
            percolateUp(index);
        } else if (priority > oldPriority) {
            percolateDown(index);
        }


    }

    @Override
    public int size() {

        return items.size() - 1;
    }
}
