public class Solution {
    public void solveSudoku(char[][] board) {
        if(board == null || board.length == 0) {
            return;
        }
        solve(board);
    }

    public boolean solve(char[][] board) {
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board.length; j++) {
                if(board[i][j] == '.') {
                    for(char c = '1'; c <= '9'; c++) {
                        if(isValid(board, i, j, c)) {
                            board[i][j] = c;
                            if(solve(board))
                                return true;
                            else
                            //this number is wrong, try the next one
                                board[i][j] = '.';
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    } 

    private boolean isValid(char[][] board, int row, int col, char c) {
        for(int i = 0; i < 9; i++) {
            if(board[i][col] != '.' && board[i][col] == c) return false;
            if(board[row][i] != '.' && board[row][i] == c) return false;
            //check whether c has existed in the square
            int diagrow = 3*(row/3) +i/3;
            int diagcol = 3*(col/3) +i%3;
            if(board[diagrow][diagcol] != '.' && board[diagrow][diagcol] == c) return false;
        }
        return true;
    }
}