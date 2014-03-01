public class Board {

    private final int[][] blocks;

    /**
     * construct a board from an N-by-N array of blocks (where blocks[i][j] =
     * block in row i, column j)
     * 
     * @param blocks
     */

    public Board(int[][] blocks) {
        this(cloneBlocks(blocks), true);
    }

    private Board(int[][] blocks, boolean shared) {
        this.blocks = blocks;
    }

    static private int[][] cloneBlocks(int[][] blocks) {
        int[][] buffer = new int[blocks.length][];
        for (int i = 0; i < blocks.length; i++) {
            buffer[i] = blocks[i].clone();
        }
        return buffer;
    }

    private static int getHamming(int[][] blocks) {
        int hammingCount = 0;

        int index = 1;
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks.length; j++) {
                if (blocks[i][j] != index) {
                    if (blocks[i][j] != 0) {
                        hammingCount++;
                    }
                }
                index++;
            }
        }
        return hammingCount;
    }

    private static int getManhanttan(int[][] blocks) {
        int manhanttanCount = 0;

        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks.length; j++) {
                int v = blocks[i][j];
                if (v != 0) {
                    int row = (v - 1) / blocks.length;
                    int col = (v - 1) % blocks.length;
                    if (row != i || col != j) {
                        manhanttanCount += Math.abs(row - i);
                        manhanttanCount += Math.abs(col - j);
                    }
                }
            }
        }
        return manhanttanCount;
    }

    /*
     * board dimension N
     */
    public int dimension() {
        return blocks.length;
    }

    /*
     * number of blocks out of place
     */
    public int hamming() {
        return getHamming(blocks);
    }

    /**
     * sum of Manhattan distances between blocks and goal
     * 
     * @return
     */
    public int manhattan() {
        return getManhanttan(blocks);
    }

    /**
     * is this board the goal board?
     * 
     * @return
     */
    public boolean isGoal() {
        return hamming() == 0;
    }

    /**
     * a board obtained by exchanging two adjacent blocks in the same row
     */
    public Board twin() {
        if (blocks.length > 2) {
            if (blocks[0][0] != 0) {
                if (blocks[0][1] != 0) {
                    return swap(0, 0, 0, 1);
                }
                return swap(1, 0, 1, 1);
            }
            return swap(0, 1, 0, 2);
        } else {
            if (blocks[0][0] != 0 && blocks[0][1] != 0) {
                return swap(0, 0, 0, 1);
            }
            return swap(1, 0, 1, 1);
        }
    }

    /**
     * does this board equal y?
     */
    public boolean equals(Object y) {
        if (y instanceof Board) {
            Board b = (Board) y;
            if (this == b) {
                return true;
            }
            for (int i = 0; i < blocks.length; i++) {
                for (int j = 0; j < blocks.length; j++) {
                    if (blocks[i][j] != b.blocks[i][j]) {
                        return false;
                    }
                }
            }
            return true;

        }
        return false;
    }

    /**
     * all neighboring boards
     * 
     * @return
     */
    public Iterable<Board> neighbors() {

        int row = 0;
        int col = 0;
        for (row = 0; row < blocks.length; row++) {
            for (col = 0; col < blocks.length; col++) {
                if (blocks[row][col] == 0) {
                    break;
                }
            }
            if (col != blocks.length) {
                break;
            }
        }
        Stack<Board> boards = new Stack<Board>();
        // change vertical
        if (row > 0) {
            boards.push(swap(row, col, row - 1, col));
        }
        if (row < blocks.length - 1) {
            boards.push(swap(row, col, row + 1, col));
        }
        // change horizon
        if (col > 0) {
            boards.push(swap(row, col, row, col - 1));
        }
        if (col < blocks.length - 1) {
            boards.push(swap(row, col, row, col + 1));
        }
        return boards;
    }

    private Board swap(int r1, int c1, int r2, int c2) {
        int[][] buffer = blocks.clone();
        for (int i = 0; i < blocks.length; i++) {
            if (i == r1 || i == r2) {
                buffer[i] = blocks[i].clone();
            } else {
                buffer[i] = blocks[i];
            }
        }

        int v = buffer[r1][c1];
        buffer[r1][c1] = buffer[r2][c2];
        buffer[r2][c2] = v;
        return new Board(buffer, false);
    }

    /**
     * string representation of the board (in the output format specified below)
     * 
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(blocks.length + "\n");
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks.length; j++) {
                s.append(String.format("%2d ", blocks[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

}
