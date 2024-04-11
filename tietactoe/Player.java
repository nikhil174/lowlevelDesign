public class Player {
    String name;
    PlayingPiece piece;

    Player(String name, PlayingPiece piece) {
        this.name = name;
        this.piece = piece;
    }

    public String getName() {
        return name;
    }

    public PlayingPiece getPlayingPiece() {
        return piece;
    }

    public void setPlayingPiece(PlayingPiece piece) {
        this.piece = piece;
    }
}
