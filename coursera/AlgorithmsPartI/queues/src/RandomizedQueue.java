import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private final int INIT_QUEUE_SIZE = 4;
    private Random random;
    private Item[] values;
    private int count;

    /**
     * construct an empty randomized queue
     */
    @SuppressWarnings("unchecked")
    public RandomizedQueue() {
        random = new Random();
        count = 0;
        values = (Item[]) new Object[INIT_QUEUE_SIZE];
    }

    /**
     * is the queue empty?
     * 
     * @return
     */
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * return the number of items on the queue
     * 
     * @return
     */
    public int size() {
        return count;
    }

    /**
     * add the item
     * 
     * @param item
     */
    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        resizeArray(count + 1);
        values[count] = item;
        count++;
    }

    /**
     * delete and return a random item
     * 
     * @return
     */
    public Item dequeue() {
        if (count <= 0) {
            throw new NoSuchElementException();
        }
        int index = new Random().nextInt(count);

        Item value = values[index];
        count--;
        values[index] = values[count];
        values[count] = null;
        resizeArray(count);
        return value;
    }

    /**
     * return (but do not delete) a random item
     * 
     * @return
     */
    public Item sample() {
        if (count <= 0) {
            throw new NoSuchElementException();
        }
        int index = random.nextInt(count);
        return values[index];
    }

    /**
     * return an independent iterator over items in random order
     * 
     * @return
     */
    public Iterator<Item> iterator() {
        return new RandomIterator();
    }

    @SuppressWarnings("unchecked")
    private void resizeArray(int newCount) {

        int newLength = values.length;
        if (values.length >= 4 * newCount) {
            newLength = values.length / 2;
            if (newLength < INIT_QUEUE_SIZE) {
                newLength = INIT_QUEUE_SIZE;
            }
        } else if (values.length < newCount) {
            newLength = values.length * 2;
        }

        if (newLength != values.length) {
            Item[] newValues = (Item[]) new Object[newLength];
            for (int i = 0; i < count; i++) {
                newValues[i] = values[i];
            }
            values = newValues;
        }
    }

    private class RandomIterator implements Iterator<Item> {
        int[] indexes;
        int current;

        RandomIterator() {
            indexes = new int[count];
            current = 0;
            Random r = new Random();
            boolean[] used = new boolean[count];
            int assigned = 0;
            while (assigned < count) {
                int index = r.nextInt(count);
                if (used[index] == false) {
                    used[index] = true;
                    indexes[assigned] = index;
                    assigned++;
                }
            }
        }

        @Override
        public boolean hasNext() {
            return current < indexes.length;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return values[indexes[current++]];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    /**
     * 
     * @param args
     */
    public static void main(String[] args) {
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();

        for (int i = 0; i < 16; i++) {
            queue.enqueue(i);
        }

        int index = 0;
        Iterator<Integer> iter = queue.iterator();
        while (iter.hasNext()) {
            System.out.println(index + ":" + iter.next());
            index++;
        }

        index = 0;
        while (!queue.isEmpty()) {
            System.out.println(index + ":" + queue.dequeue());
            index++;
        }

    }

}
