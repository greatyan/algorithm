import java.util.Comparator;
import java.util.HashMap;

public class Solver {

    private static final boolean USE_MANHATTAN = true;

    private boolean isSolvable;
    private int moveCount;
    private Stack<Board> solution;

    /**
     * find a solution to the initial board (using the A* algorithm)
     * 
     * @param initial
     */
    public Solver(Board initial) {
        solution = solve(initial);
        if (solution == null) {
            isSolvable = false;
        } else {
            isSolvable = true;
            moveCount = solution.size() - 1;
        }
    }

    private static class BoardResolver {

        private static class BoardMove {
            private BoardMove parent;
            private int moveCount;
            private int manhattan;
            private int hamming;
            private Board board;

            private BoardMove(Board board) {
                this(null, board);
            }

            private BoardMove(BoardMove parent, Board board) {
                this.parent = parent;
                this.board = board;
                if (parent != null) {
                    this.moveCount = parent.moveCount + 1;
                }
                this.manhattan = board.manhattan() + moveCount;
                this.hamming = board.hamming() + moveCount;
            }
        }

        private static final Comparator<BoardMove> MANHATTAN_COMPARATOR = new Comparator<BoardMove>() {

            @Override
            public int compare(BoardMove b1, BoardMove b2) {
                return b1.manhattan - b2.manhattan;
            }
        };

        private static final Comparator<BoardMove> HAMMING_COMPARATOR = new Comparator<BoardMove>() {

            @Override
            public int compare(BoardMove b1, BoardMove b2) {
                return b1.hamming - b2.hamming;
            }
        };

        private Comparator<BoardMove> comparator;
        private HashMap<String, Board> testedBoards;
        private MinPQ<BoardMove> remainBoards;
        private BoardMove result;
        private boolean isResolved;

        public BoardResolver(Board start) {
            this.comparator = USE_MANHATTAN ? MANHATTAN_COMPARATOR
                    : HAMMING_COMPARATOR;
            this.remainBoards = new MinPQ<BoardMove>(comparator);
            this.remainBoards.insert(new BoardMove(start));
            this.testedBoards = new HashMap<String, Board>();
            this.isResolved = false;
        }

        public boolean isResolved() {
            return isResolved;
        }

        public Stack<Board> getResolvePath() {
            Stack<Board> boards = new Stack<Board>();
            BoardMove move = result;
            while (move != null) {
                boards.push(move.board);
                move = move.parent;
            }
            return boards;
        }

        public void resolveNext() {
            if (!remainBoards.isEmpty()) {
                BoardMove move = remainBoards.delMin();
                if (move.board.isGoal()) {
                    isResolved = true;
                    result = move;
                    return;
                }
                String boardKey = move.board.toString();
                // System.out.println(move.moveCount + move.board.manhattan());
                // System.out.println("move " + move.moveCount + " manhan:"
                // + move.board.manhattan());
                // System.out.println(boardKey);
                testedBoards.put(boardKey, move.board);
                Iterable<Board> children = move.board.neighbors();
                for (Board nextBoard : children) {
                    if (!nextBoard.equals(move.board)) {
                        if (!testedBoards.containsKey(nextBoard.toString())) {
                            remainBoards.insert(new BoardMove(move, nextBoard));
                        }
                    }
                }
            }
        }
    }

    private Stack<Board> solve(Board start) {
        BoardResolver resolver1 = new BoardResolver(start);
        BoardResolver resolver2 = new BoardResolver(start.twin());

        while (!resolver1.isResolved() && !resolver2.isResolved()) {
            resolver1.resolveNext();
            resolver2.resolveNext();
        }

        if (resolver1.isResolved()) {
            return resolver1.getResolvePath();
        }
        return null;
    }

    /**
     * // is the initial board solvable?
     * 
     * @return
     */
    public boolean isSolvable() {
        return isSolvable;
    }

    /**
     * // min number of moves to solve initial board; -1 if no solution
     * 
     * @return
     */
    public int moves() {
        return moveCount;
    }

    /**
     * 
     * @return
     */
    public Iterable<Board> solution() {
        return solution;
    }

    /**
     * // solve a slider puzzle (given below)
     * 
     * @param args
     */
    public static void main(String[] args) {

        if (args.length != 1) {
            StdOut.println("java Solver <inputfile>");
            return;
        }
        solve(args[0]);
    }

    private static void solve(String file) {
        System.out.println(file);
        long start = System.currentTimeMillis();
        // create initial board from file
        In in = new In(file);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // StdOut.println(initial);
        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            //
            // for (Board board : solver.solution())
            // StdOut.println(board);
        }
        long end = System.currentTimeMillis();
        System.out.println(" time:" + (end - start) + "ms");
    }

}
