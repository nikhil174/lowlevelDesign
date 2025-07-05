package Game;

import java.util.*;

public class Game {
    private List<Player> players;
    private Board board;
    private int currentPlayerIndex;
    private Player winner = null;
    public Game(List<Player> players, Board board) {
        this.players = players;
        this.board = board;
        this.currentPlayerIndex = 0;
    }
    public void play() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            board.printBoard();
            Player current = players.get(currentPlayerIndex);
            System.out.println(current.getName() + "'s turn (" + current.getPiece().getType() + ")");
            System.out.print("Enter row and column (0-based, space separated): ");
            int row = sc.nextInt();
            int col = sc.nextInt();
            if (!makeMove(row, col)) {
                System.out.println("Invalid move. Try again.");
                continue;
            }
            if (board.checkWin(current.getPiece())) {
                board.printBoard();
                winner = current;
                System.out.println(current.getName() + " wins!");
                break;
            }
            if (board.isFull()) {
                board.printBoard();
                System.out.println("It's a draw!");
                break;
            }
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        }
        sc.close();
    }
    public boolean makeMove(int row, int col) {
        Player current = players.get(currentPlayerIndex);
        return board.placePiece(row, col, current.getPiece());
    }
    public Player getWinner() {
        return winner;
    }
    public boolean isDraw() {
        return winner == null && board.isFull();
    }
}
