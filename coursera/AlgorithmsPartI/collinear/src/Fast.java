import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Fast {
    public static void main(String[] args) {
        if (args.length != 1) {
            StdOut.println("java Fast <input file>");
            return;
        }
        new Fast().run(args[0]);
    }

    private void run(String file) {
        Point[] points = readPoints(file);
        ArrayList<Point[]> results = searchColinear(points);
        printResults(results);
        drawResults(points, results);

    }

    private Point[] readPoints(String file) {
        In in = new In(file);
        int count = in.readInt();
        Point[] points = new Point[count];
        for (int i = 0; i < count; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }
        return points;
    }

    private void addColinearSegment(ArrayList<Point[]> segments, Point[] segment) {
        if (segment == null) {
            return;
        }
        int index = Collections.binarySearch(segments, segment,
                new SegmentComparator());
        if (index < 0) {
            int insertPoint = -(index + 1);
            segments.add(insertPoint, segment);
        }
    }

    private ArrayList<Point[]> searchColinear(Point[] points) {

        Point[] targets = new Point[points.length];
        for (int i = 0; i < points.length; i++) {
            targets[i] = points[i];
        }
        ArrayList<Point[]> results = new ArrayList<Point[]>();
        for (int i = 0; i < points.length - 3; i++) {
            Arrays.sort(targets, points[i].SLOPE_ORDER);
            int start = 0;
            int end = 0;
            double slop = points[i].slopeTo(targets[0]);
            for (int j = 1; j < targets.length; j++) {
                double newSlop = points[i].slopeTo(targets[j]);
                if (newSlop == slop) {
                    end++;
                } else {
                    addColinearSegment(results,
                            createSegment(points[i], targets, start, end));
                    start = j;
                    end = j;
                    slop = newSlop;
                }
            }
            addColinearSegment(results,
                    createSegment(points[i], targets, start, end));
        }
        return results;
    }

    private Point[] createSegment(Point point, Point[] points, int start,
            int end) {
        int size = end - start + 1;
        if (size >= 3) {
            Point[] result = new Point[size + 1];
            result[0] = point;
            System.arraycopy(points, start, result, 1, size);
            Arrays.sort(result);
            return result;
        }
        return null;
    }

    private void printResults(ArrayList<Point[]> results) {
        for (Point[] points : results) {
            StdOut.print(points[0].toString());
            for (int i = 1; i < points.length; i++) {
                StdOut.print(" -> ");
                StdOut.print(points[i].toString());
            }
            StdOut.println();
        }
    }

    private void drawResults(Point[] points, ArrayList<Point[]> results) {
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);

        for (Point point : points) {
            point.draw();
        }
        for (Point[] segment : results) {
            segment[0].drawTo(segment[segment.length - 1]);
        }
    }

    private static class SegmentComparator implements Comparator<Point[]> {

        @Override
        public int compare(Point[] seg0, Point[] seg1) {
            // compare the first two segments
            int r1 = seg0[0].compareTo(seg1[0]);
            if (r1 == 0) {
                return seg0[1].compareTo(seg1[1]);

            }
            return r1;
        }
    }
}
