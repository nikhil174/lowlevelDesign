import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class TicTacToe {
    Deque<Player> players;
    Board game;

    public void initialize() {
        players = new LinkedList<>();

        PlayingPiece X = new PlayingPiece(PieceType.X);
        Player player1 = new Player("Player 1", X);

        PlayingPiece O = new PlayingPiece(PieceType.O);
        Player player2 = new Player("Player 2", O);

        players.add(player1);
        players.add(player2);

        game = new Board(3);
    }

    public String startGame() {
        boolean noWinner = true;
        while (noWinner) {
            Player playerTurn = players.removeFirst();
            game.printBoard();

            List<Pair<Integer, Integer>> freeCells = game.getFreeCells();
            if (freeCells.isEmpty()) {
                noWinner = false;
                continue;
            }

            System.out.println("Player : " + playerTurn.name + " Enter row, column: ");
            Scanner sc = new Scanner(System.in);
            String st = sc.nextLine();
            String[] values = st.split(",");
            int row = Integer.parseInt(values[0]);
            int col = Integer.parseInt(values[1]);

            boolean pieceAdded = game.addPiece(row, col, playerTurn.getPlayingPiece());

            if (!pieceAdded) {
                System.out.println("Incorrect Position, Try Again!");
                players.addFirst(playerTurn);
                continue;
            }

            players.addLast(playerTurn);

            boolean winner = isThereWinner(row, col, playerTurn.getPlayingPiece().pieceType);

            if (winner)
                return playerTurn.getName();
        }

        return "tie";
    }

    boolean isThereWinner(int row, int col, PieceType piece) {
        boolean rowWinner = true;
        boolean colWinner = true;
        boolean diagonalWinner = true;
        boolean antiDiagonalWinner = true;

        for (int i = 0; i < game.size; i++) {
            if (game.board[row][i] == null || game.board[row][i].pieceType != piece)
                rowWinner = false;
        }

        for (int i = 0; i < game.size; i++) {
            if (game.board[i][col] == null || game.board[i][col].pieceType != piece)
                colWinner = false;
        }

        for (int i = 0, j = 0; i < game.size; i++, j++) {
            if (game.board[i][j] == null || game.board[i][j].pieceType != piece)
                diagonalWinner = false;
        }

        for (int i = game.size - 1, j = 0; i >= 0; i--, j++) {
            if (game.board[i][j] == null || game.board[i][j].pieceType != piece)
                antiDiagonalWinner = false;
        }

        return rowWinner || colWinner || diagonalWinner || antiDiagonalWinner;
    }
}
