/**
 * 
 * @author wyan
 * 
 */
public class Percolation {

    /**
     * site size.
     */
    private int siteSize;
    /**
     * two dimension arrays to save site data.
     */
    private byte[][] sites;
    /**
     * virtual root for top row.
     */
    private int topRoot;
    /**
     * virtu root for bottom row.
     */
    private int bottomRoot;
    /**
     * used to test if a botom is full
     */
    private WeightedQuickUnionUF ufFull;
    /**
     * weighted quick union find used to test connection.
     */
    private WeightedQuickUnionUF ufPercolate;

    /**
     * create N-by-N grid, with all sites blocked.
     * 
     * @param size
     *            site size
     */

    public Percolation(final int size) {
        if (size <= 0) {
            throw new IllegalArgumentException();
        }
        sites = new byte[size][];
        for (int i = 0; i < size; i++) {
            sites[i] = new byte[size];
        }
        this.siteSize = size;
        ufPercolate = new WeightedQuickUnionUF(size * size + 2);
        topRoot = size * size;
        bottomRoot = topRoot + 1;
        ufFull = new WeightedQuickUnionUF(size * size + 1);
    }

    /**
     * use UF to link two cells.
     * 
     * @param row1
     *            cell1 row
     * @param col1
     *            cell2 column
     * @param row2
     *            cell2 row
     * @param col2
     *            cell2 column
     */
    private void link(final int row1, final int col1, final int row2,
            final int col2) {
        int id1 = getUFIndex(row1, col1);
        int id2 = getUFIndex(row2, col2);
        ufFull.union(id1, id2);
        ufPercolate.union(id1, id2);
    }

    /**
     * return the corresponding index in UF of site (row, col).
     * 
     * @param row
     *            row index
     * @param col
     *            col index
     * @return index in the UF
     */
    private int getUFIndex(final int row, final int col) {
        return (row - 1) * siteSize + col - 1;
    }

    /**
     * open site (row i, column j) if it is not already.
     * 
     * @param i
     *            row
     * @param j
     *            column
     */
    public void open(final int i, final int j) {
        checkRowIndex(i);
        checkColIndex(j);

        if (isOpen(i, j)) {
            return;
        }
        sites[i - 1][j - 1] = 1;
        if (i > 1) {
            if (isOpen(i - 1, j)) {
                link(i, j, i - 1, j);
            }
        }
        if (i < siteSize) {
            if (isOpen(i + 1, j)) {
                link(i, j, i + 1, j);
            }
        }
        if (j > 1) {
            if (isOpen(i, j - 1)) {
                link(i, j, i, j - 1);
            }
        }
        if (j < siteSize) {
            if (isOpen(i, j + 1)) {
                link(i, j, i, j + 1);
            }
        }
        if (i == 1) {
            int index = getUFIndex(i, j);
            // link with the virtual top root.
            ufFull.union(topRoot, index);
            ufPercolate.union(topRoot, index);
        }
        if (i == siteSize) {
            int index = getUFIndex(i, j);
            // link with bottom root
            ufPercolate.union(bottomRoot, index);
        }
    }

    /**
     * test if site (row i, column j) open.
     * 
     * @param i
     *            row
     * @param j
     *            col
     * @return true open, false close.
     */
    public boolean isOpen(final int i, final int j) {
        checkRowIndex(i);
        checkColIndex(j);

        return sites[i - 1][j - 1] == 1;
    }

    /**
     * check if the row index exceed site size.
     * 
     * @param index
     *            row index.
     */
    private void checkRowIndex(final int index) {
        if (index < 1 || index > siteSize) {
            throw new java.lang.IndexOutOfBoundsException();
        }
    }

    /**
     * check if the column index exceed site size.
     * 
     * @param index
     *            column index.
     */
    private void checkColIndex(final int index) {
        if (index < 1 || index > siteSize) {
            throw new java.lang.IndexOutOfBoundsException();
        }
    }

    /**
     * is site (row i, column j) full?
     * 
     * @param i
     *            row
     * @param j
     *            column
     * @return true full(close), false open.
     */
    public boolean isFull(final int i, final int j) {
        checkRowIndex(i);
        checkColIndex(j);
        int index = getUFIndex(i, j);
        return ufFull.connected(topRoot, index);
    }

    /**
     * does the system percolate?
     * 
     * @return true percolates
     */
    public boolean percolates() {

        // merge the bottom vertexes
        return ufPercolate.connected(topRoot, bottomRoot);
    }
}
