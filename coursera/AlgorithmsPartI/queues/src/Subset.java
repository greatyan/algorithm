public class Subset {
    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("java Sunset <number>");
            return;
        }

        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        int count = Integer.parseInt(args[0]);
        while (!StdIn.isEmpty()) {
            String word = StdIn.readString();
            queue.enqueue(word);
        }
        for (int i = 0; i < count; i++) {
            System.out.println(queue.dequeue());
        }
    }
}
