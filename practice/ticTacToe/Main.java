import java.util.*;
import Game.*;

public class Main {
    public static void main(String[] args) {
        Board board = new Board(3); // 3x3 board
        Player p1 = new Player("Alice", new Piece(PieceType.X));
        Player p2 = new Player("Bob", new Piece(PieceType.O));
        List<Player> players = Arrays.asList(p1, p2);
        Game game = new Game(players, board);
        game.play();
    }
}