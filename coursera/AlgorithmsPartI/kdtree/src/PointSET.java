import java.util.ArrayList;
import java.util.TreeSet;

public class PointSET {

	private TreeSet<Point2D> points;

	// construct an empty set of points
	public PointSET() {
		points = new TreeSet<Point2D>();
	}

	// is the set empty?
	public boolean isEmpty() {
		return points.isEmpty();
	}

	// number of points in the set
	public int size() {
		return points.size();
	}

	// add the point p to the set (if it is not already in the set)
	public void insert(Point2D p) {
		points.add(p);
	}

	// does the set contain the point p?
	public boolean contains(Point2D p) {
		return points.contains(p);
	}

	// draw all of the points to standard draw
	public void draw() {
		for (Point2D p : points) {
			p.draw();
		}
	}

	// all points in the set that are inside the rectangle
	public Iterable<Point2D> range(RectHV rect) {
		ArrayList<Point2D> results = new ArrayList<Point2D>();
		for (Point2D p : points) {
			if (rect.contains(p)) {
				results.add(p);
			}
		}
		return results;
	}

	// a nearest neighbor in the set to p; null if set is empty
	public Point2D nearest(Point2D p) {

		Point2D nearstPoint = null;
		double minDistance = Double.POSITIVE_INFINITY;
		for (Point2D p2 : points) {
			double d = p.distanceTo(p2);
			if (d < minDistance) {
				nearstPoint = p2;
				minDistance = d;
			}
		}
		return nearstPoint;
	}

	public static void main(String[] args) {

	}
}
