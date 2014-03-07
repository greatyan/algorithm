import java.util.ArrayList;

public class KdTree {

	private static class Node {
		private Point2D point;
		private Node leftNode;
		private Node rightNode;
	}

	private int size;
	private Node root;

	public KdTree() {

	}

	// is the set empty?
	public boolean isEmpty() {
		return root == null;
	}

	// number of points in the set
	public int size() {
		return size;
	}

	// add the point p to the set (if it is not already in the set)
	public void insert(Point2D p) {
		if (root == null) {
			root = new Node();
			root.point = p;
			size++;
			return;
		}
		insertX(root, p);
	}

	private void insertX(Node node, Point2D p) {
		// compare x
		if (node.point.equals(p)) {
			return;
		}
		if (p.x() < node.point.x()) {
			// insert to left panel
			if (node.leftNode == null) {
				node.leftNode = new Node();
				node.leftNode.point = p;
				size++;
				return;
			}
			insertY(node.leftNode, p);
			return;
		}
		if (node.rightNode == null) {
			node.rightNode = new Node();
			node.rightNode.point = p;
			size++;
			return;
		}
		insertY(node.rightNode, p);
	}

	private void insertY(Node node, Point2D p) {
		if (node.point.equals(p)) {
			return;
		}

		if (p.y() < node.point.y()) {
			// insert into low panel
			if (node.leftNode == null) {
				node.leftNode = new Node();
				node.leftNode.point = p;
				size++;
				return;
			}
			insertX(node.leftNode, p);
			return;
		}
		if (node.rightNode == null) {
			node.rightNode = new Node();
			node.rightNode.point = p;
			size++;
			return;
		}
		insertX(node.rightNode, p);
	}

	// does the set contain the point p?

	public boolean contains(Point2D p) {
		if (root == null) {
			return false;
		}
		return searchX(root, p);
	}

	private boolean searchX(Node node, Point2D p) {
		if (node.point.equals(p)) {
			return true;
		}
		if (p.x() < node.point.x()) {
			if (node.leftNode == null) {
				return false;
			}
			return searchY(node.leftNode, p);
		}
		if (node.rightNode == null) {
			return false;
		}
		return searchY(node.rightNode, p);

	}

	private boolean searchY(Node node, Point2D p) {
		if (node.point.equals(p)) {
			return true;
		}
		if (p.y() < node.point.y()) {
			if (node.leftNode == null) {
				return false;
			}
			return searchX(node.leftNode, p);
		}
		if (node.rightNode == null) {
			return false;
		}
		return searchX(node.rightNode, p);
	}

	// draw all of the points to standard draw
	public void draw() {
		StdDraw.clear();
		if (root != null) {
			drawNodeX(root, 0, 1, 0, 1);
		} else {
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.setPenRadius(.001);
			StdDraw.line(0, 0, 0, 1);
			StdDraw.line(0, 1, 1, 1);
			StdDraw.line(1, 1, 1, 0);
			StdDraw.line(1, 0, 0, 0);

		}
	}

	private void drawNodeX(Node node, double minX, double maxX, double minY,
			double maxY) {
		StdDraw.setPenColor(StdDraw.BLUE);
		StdDraw.setPenRadius(.001);
		StdDraw.line(node.point.x(), minY, node.point.x(), maxY);

		StdDraw.setPenRadius(.03);
		StdDraw.setPenColor(StdDraw.RED);
		node.point.draw();

		if (node.leftNode != null) {
			drawNodeY(node.leftNode, minX, node.point.x(), minY, maxY);
		}
		if (node.rightNode != null) {
			drawNodeY(node.rightNode, node.point.x(), maxX, minY, maxY);
		}
	}

	private void drawNodeY(Node node, double minX, double maxX, double minY,
			double maxY) {
		StdDraw.setPenColor(StdDraw.GREEN);
		StdDraw.setPenRadius(.001);
		StdDraw.line(minX, node.point.y(), maxX, node.point.y());

		StdDraw.setPenRadius(.03);
		StdDraw.setPenColor(StdDraw.RED);
		node.point.draw();

		if (node.leftNode != null) {
			drawNodeX(node.leftNode, minX, maxX, minY, node.point.y());
		}
		if (node.rightNode != null) {
			drawNodeX(node.rightNode, minX, maxX, node.point.y(), maxY);
		}
	}

	// all points in the set that are inside the rectangle
	public Iterable<Point2D> range(RectHV rect) {
		if (root == null) {
			return null;
		}
		ArrayList<Point2D> points = new ArrayList<Point2D>();
		rangeX(root, rect, points);
		return points;
	}

	private void rangeX(Node node, RectHV rect, ArrayList<Point2D> points) {
		if (rect.contains(node.point)) {
			points.add(node.point);
		}
		if (node.point.x() >= rect.xmin()) {
			// search left panel
			if (node.leftNode != null) {
				rangeY(node.leftNode, rect, points);
			}
		}
		if (node.point.x() <= rect.xmax()) {
			// search right panel
			if (node.rightNode != null) {
				rangeY(node.rightNode, rect, points);
			}
		}
	}

	private void rangeY(Node node, RectHV rect, ArrayList<Point2D> points) {
		if (rect.contains(node.point)) {
			points.add(node.point);
		}

		if (node.point.y() >= rect.ymin()) {
			// search left panel
			if (node.leftNode != null) {
				rangeX(node.leftNode, rect, points);
			}
		}
		if (node.point.y() <= rect.ymax()) {
			// search right panel
			if (node.rightNode != null) {
				rangeX(node.rightNode, rect, points);
			}
		}
	}

	private static class NearestStatus {
		private Point2D point;
		private double distance;
	}

	// a nearest neighbor in the set to p; null if set is empty
	public Point2D nearest(Point2D p) {
		if (root == null) {
			return null;
		}
		NearestStatus status = new NearestStatus();
		status.distance = p.distanceTo(root.point);
		status.point = root.point;
		nearstX(root, p, status);
		return status.point;
	}

	private void nearstX(Node node, Point2D point, NearestStatus status) {

		double distance = node.point.distanceTo(point);
		if (distance < status.distance) {
			status.distance = distance;
			status.point = node.point;
		}
		if (point.x() < node.point.x()) {
			// search left part first
			if (node.leftNode != null) {
				nearstY(node.leftNode, point, status);
			}
			if (node.rightNode != null) {
				// should search the right part?
				if (point.x() + status.distance >= node.point.x()) {
					nearstY(node.rightNode, point, status);
				}
			}
		} else {
			// search right part first
			if (node.rightNode != null) {
				nearstY(node.rightNode, point, status);
			}
			if (node.leftNode != null) {
				if (point.x() - status.distance < node.point.x()) {
					nearstY(node.leftNode, point, status);
				}
			}
		}
	}

	private void nearstY(Node node, Point2D point, NearestStatus status) {
		double distance = node.point.distanceTo(point);
		if (distance < status.distance) {
			status.distance = distance;
			status.point = node.point;
		}
		if (point.y() < node.point.y()) {
			// search upper part first
			if (node.leftNode != null) {
				nearstX(node.leftNode, point, status);
			}
			if (node.rightNode != null) {
				// should search the right part?
				if (point.y() + status.distance >= node.point.y()) {
					nearstX(node.rightNode, point, status);
				}
			}
		} else {
			// search right part first
			if (node.rightNode != null) {
				nearstX(node.rightNode, point, status);
			}
			if (node.leftNode != null) {
				if (point.y() - status.distance < node.point.y()) {
					nearstX(node.leftNode, point, status);
				}
			}
		}
	}

}
