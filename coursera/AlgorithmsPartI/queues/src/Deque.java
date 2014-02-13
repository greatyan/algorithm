import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private class Node {
        Item value;
        Node prev;
        Node next;
    }

    private Node head;
    private Node tail;
    private int count;

    /**
     * construct an empty deque
     */
    public Deque() {
        head = null;
        tail = null;
        count = 0;
    }

    /**
     * is the deque empty?
     * 
     * @return
     */
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * return the number of items on the deque.
     * 
     * @return
     */
    public int size() {
        return count;
    }

    /**
     * insert the item at the front
     */

    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        Node node = new Node();
        node.value = item;
        if (head == null) {
            head = node;
            tail = node;
        } else {
            head.prev = node;
            node.next = head;
            head = node;
        }
        count++;
    }

    /**
     * insert the item at the end
     * 
     * @param item
     */
    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        Node node = new Node();
        node.value = item;
        if (tail == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
        count++;
    }

    /**
     * delete and return the item at the front
     * 
     * @return
     */
    public Item removeFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        Node node = head;
        head = head.next;
        if (head == null) {
            tail = null;
        } else {
            head.prev = null;
        }
        node.next = null;
        count--;
        return node.value;
    }

    /**
     * delete and return the item at the end
     * 
     * @return
     */
    public Item removeLast() {
        if (tail == null) {
            throw new NoSuchElementException();
        }
        Node node = tail;
        tail = tail.prev;
        if (tail == null) {
            head = null;
        } else {
            tail.next = null;
        }
        node.prev = null;
        count--;
        return node.value;
    }

    /**
     * return an iterator over items in order from front to end
     * 
     * @return
     */

    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            Node current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Item next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Item value = current.value;
                current = current.next;
                return value;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    /**
     * unit testing
     * 
     * @param args
     */
    /**
     * 
     * @param args
     */
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<Integer>();

        for (int i = 0; i < 8; i++) {
            deque.addFirst(i);
        }

        int index = 0;
        Iterator<Integer> iter = deque.iterator();
        while (iter.hasNext()) {
            System.out.println(index + ":" + iter.next());
            index++;
        }

        index = 0;
        while (!deque.isEmpty()) {
            System.out.println(index + ":" + deque.removeFirst());
            index++;
        }

        for (int i = 0; i < 8; i++) {
            deque.addLast(i);
        }

        index = 0;
        while (!deque.isEmpty()) {
            System.out.println(index + ":" + deque.removeLast());
            index++;
        }
    }

}
