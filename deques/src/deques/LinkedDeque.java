package deques;

/**
 * @see Deque
 */
public class LinkedDeque<T> extends AbstractDeque<T> {
    private int size;
    // IMPORTANT: Do not rename these fields or change their visibility.
    // We access these during grading to test your code.
    Node<T> front;
    Node<T> back;
    // Feel free to add any additional fields you may need, though.

    public LinkedDeque() {
        size = 0;
        front = new Node<>(null, null, null);
        back = new Node<>(null, null, null);
        front.next = back;
        back.prev = front;
    }

    public void addFirst(T item) {
        Node<T> b = new Node<>(item, front, front.next);
        front.next.prev = b;
        front.next = b;
        size += 1;
    }

    public void addLast(T item) {
        Node<T> b = new Node<>(item, back.prev, back);
        back.prev.next = b;
        back.prev = b;
        size += 1;
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T result = front.next.value;
        front.next.next.prev = front;
        front.next = front.next.next;
        size -= 1;
        return result;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T result = back.prev.value;
        back.prev.prev.next = back;
        back.prev = back.prev.prev;
        size -= 1;
        return result;
    }

    public T get(int index) {
        if ((index >= size) || (index < 0)) {
            return null;
        }
        if (index < size / 2) {
            Node<T> curr = front.next;
            for (int i = 0; i < index; i++) {
                curr = curr.next;
            }
            return curr.value;
        } else {
            Node<T> curr = back.prev;
            for (int i = size - 1; i > index; i--) {
                curr = curr.prev;
            }
            return curr.value;
        }
    }

    public int size() {
        return size;
    }
}
