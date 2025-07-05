package Game;

public class Board {
    private int size;
    private Piece[][] board;
    public Board(int size) {
        this.size = size;
        this.board = new Piece[size][size];
    }
    public boolean isCellEmpty(int row, int col) {
        return board[row][col] == null;
    }
    public boolean placePiece(int row, int col, Piece piece) {
        if (row < 0 || row >= size || col < 0 || col >= size) return false;
        if (!isCellEmpty(row, col)) return false;
        board[row][col] = piece;
        return true;
    }
    public boolean checkWin(Piece piece) {
        PieceType type = piece.getType();
        // Check rows and columns
        for (int i = 0; i < size; i++) {
            boolean rowWin = true, colWin = true;
            for (int j = 0; j < size; j++) {
                if (board[i][j] == null || board[i][j].getType() != type) rowWin = false;
                if (board[j][i] == null || board[j][i].getType() != type) colWin = false;
            }
            if (rowWin || colWin) return true;
        }
        // Check diagonals
        boolean diag1 = true, diag2 = true;
        for (int i = 0; i < size; i++) {
            if (board[i][i] == null || board[i][i].getType() != type) diag1 = false;
            if (board[i][size - 1 - i] == null || board[i][size - 1 - i].getType() != type) diag2 = false;
        }
        return diag1 || diag2;
    }
    public boolean isFull() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == null) return false;
            }
        }
        return true;
    }
    public void printBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == null) System.out.print(".");
                else System.out.print(board[i][j].getType());
                if (j < size - 1) System.out.print(" ");
            }
            System.out.println();
        }
    }
}
