import java.util.Random;

public class RandomInsertTest {

	public static void main(String[] args) {

		KdTree tree = new KdTree();
		int count = 1000;
		Random random = new Random();
		for (int i = 0; i < count * count; i++) {
			int x = random.nextInt(count);
			int y = random.nextInt(count);
			Point2D point = new Point2D(1.0 * x / count, 1.0 * y / count);
			tree.insert(point);
			if (!tree.contains(point)) {
				System.out.println("failed on " + point);
				tree.contains(point);
			}
		}
		System.out.println("succ");
	}
}
