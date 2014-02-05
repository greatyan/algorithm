/**
 * 
 * @author wyan
 * 
 */
public class PercolationStats {

    /**
     * constant used to evaluate 95% confidence.
     */
    private static final double CONFIDENCE_95 = 1.96D;
    /**
     * mean value.
     */
    private double mean;
    /**
     * stddev.
     */
    private double stddev;
    /**
     * 95% confidence low bound.
     */
    private double confidenceLow;
    /**
     * 95% confidence high bound.
     */
    private double confidenceHigh;

    /**
     * perform T independent computational experiments on an N-by-N grid.
     * 
     * @param n
     *            size of the site
     * @param t
     *            test numbers
     */
    public PercolationStats(final int n, final int t) {

        if (n <= 0 || t <= 0) {
            throw new IllegalArgumentException();
        }

        double[] values = new double[t];
        int totalValues = n * n;

        for (int i = 0; i < t; i++) {
            Percolation percolation = new Percolation(n);
            int count = 0;
            while (!percolation.percolates()) {
                int x = StdRandom.uniform(n) + 1;
                int y = StdRandom.uniform(n) + 1;
                if (!percolation.isOpen(x, y)) {
                    count++;
                    percolation.open(x, y);
                }
            }
            // add count to calculation
            values[i] = count * 1.0 / totalValues;
        }

        mean = StdStats.mean(values);
        stddev = StdStats.stddev(values);
        double sqrtt = Math.sqrt(t);
        confidenceLow = mean - CONFIDENCE_95 * stddev / sqrtt;
        confidenceHigh = mean + CONFIDENCE_95 * stddev / sqrtt;
    }

    /**
     * sample mean of percolation threshold.
     * 
     * @return mean
     */
    public double mean() {
        return mean;
    }

    /**
     * sample standard deviation of percolation threshold.
     * 
     * @return stddev
     */
    public double stddev() {
        return stddev;
    }

    /**
     * returns lower bound of the 95% confidence interval.
     * 
     * @return 95% confidence low
     */
    public double confidenceLo() {
        return confidenceLow;
    }

    /**
     * returns upper bound of the 95% confidence interval.
     * 
     * @return 95% confidence high
     */
    public double confidenceHi() {
        return confidenceHigh;
    }

    /**
     * 
     * @param args
     *            arguments with two integer
     */
    public static void main(final String[] args) {
        if (args.length != 2) {
            System.out.println("java PercolationStats <N> <T>");
            return;
        }

        int n = Integer.valueOf(args[0]);
        int t = Integer.valueOf(args[1]);
        PercolationStats stats = new PercolationStats(n, t);
        System.out.println("mean                    = " + stats.mean());
        System.out.println("stddev                  = " + stats.stddev());
        System.out.println("95% confidence interval = " + stats.confidenceLo()
                + ", " + stats.confidenceHi());
    }
}
