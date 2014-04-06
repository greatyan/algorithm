package com.actuate.wyan;

import org.junit.Assert;
import org.junit.Test;

public class ValidSudoku {

    @Test
    public void test() {
        Assert.assertTrue(new Solution()
                .isValidSudoku(new String[] { ".87654321", "2........",
                        "3........", "4........", "5........", "6........",
                        "7........", "8........", "9........" }));
    }

    public class Solution {

        public boolean isValidSudoku(String[] board) {
            char[][] cb = new char[9][];
            for (int i = 0; i < 9; i++) {
                cb[i] = board[i].toCharArray();
            }
            return isValidSudoku(cb);
        }

        public boolean isValidSudoku(char[][] board) {

            // valid row
            for (int i = 0; i < 9; i++) {
                if (validRow(board, i) == false) {
                    return false;
                }
            }

            // valid column
            for (int i = 0; i < 9; i++) {
                if (validColumn(board, i) == false) {
                    return false;
                }
            }
            // valid area

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (validArea(board, i * 3, j * 3) == false) {
                        return false;
                    }
                }
            }
            return true;
        }

        public boolean validRow(char[][] board, int row) {
            boolean[] used = new boolean[9];

            for (int i = 0; i < 9; i++) {
                char v = board[row][i];
                if (v == '.') {
                    continue;
                }
                int index = v - '0';
                if (index >= 1 && index <= 9) {
                    if (used[index - 1] == true) {
                        return false;
                    }
                    used[index - 1] = true;
                } else {
                    return false;
                }
            }
            return true;
        }

        public boolean validColumn(char[][] board, int col) {
            boolean[] used = new boolean[9];

            for (int i = 0; i < 9; i++) {
                char v = board[i][col];
                if (v == '.') {
                    continue;
                }
                int index = v - '0';
                if (index >= 1 && index <= 9) {
                    if (used[index - 1] == true) {
                        return false;
                    }
                    used[index - 1] = true;
                } else {
                    return false;
                }
            }
            return true;
        }

        public boolean validArea(char[][] board, int row, int col) {
            boolean[] used = new boolean[9];

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    char v = board[row + i][col + j];
                    if (v == '.') {
                        continue;
                    }
                    int index = v - '0';
                    if (index >= 1 && index <= 9) {
                        if (used[index - 1] == true) {
                            return false;
                        }
                        used[index - 1] = true;
                    } else {
                        return false;
                    }
                }
            }
            return true;
        }

    }
}
