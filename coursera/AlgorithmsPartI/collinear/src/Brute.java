import java.util.ArrayList;
import java.util.Arrays;

public class Brute {

    public static void main(String[] args) {
        if (args.length != 1) {
            StdOut.println("java Brute <input file>");
            return;
        }
        new Brute().run(args[0]);
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
        Arrays.sort(points);
        return points;
    }

    private ArrayList<Point[]> searchColinear(Point[] points) {
        ArrayList<Point[]> results = new ArrayList<Point[]>();
        for (int i = 0; i < points.length - 3; i++) {
            for (int j = i + 1; j < points.length - 2; j++) {
                double s1 = points[i].slopeTo(points[j]);
                for (int k = j + 1; k < points.length - 1; k++) {
                    double s2 = points[i].slopeTo(points[k]);
                    if (!(Double.compare(s1, s2) == 0)) {
                        continue;
                    }
                    for (int l = k + 1; l < points.length; l++) {
                        double s3 = points[i].slopeTo(points[l]);
                        if (Double.compare(s1, s3) == 0) {

                            results.add(new Point[] { points[i], points[j],
                                    points[k], points[l] });
                        }
                    }
                }
            }
        }
        return results;
    }

    private void printResults(ArrayList<Point[]> results) {
        for (Point[] points : results) {
            StdOut.print(points[0].toString());
            StdOut.print(" -> ");
            StdOut.print(points[1].toString());
            StdOut.print(" -> ");
            StdOut.print(points[2].toString());
            StdOut.print(" -> ");
            StdOut.print(points[3].toString());
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

    private void run(String file) {
        Point[] points = readPoints(file);
        ArrayList<Point[]> results = searchColinear(points);
        printResults(results);
        drawResults(points, results);

    }
}
