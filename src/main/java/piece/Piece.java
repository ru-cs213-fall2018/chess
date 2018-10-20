package piece;

import board.Board;
import board.Coordinate;
import board.Square;
import chess.Color;

/**
 * Represents a chess piece
 * @author Ammaar Muhammad Iqbal
 */
public abstract class Piece {

    protected static final Exception ILLEGAL_MOVE = new Exception("This move is illegal");

    protected Board board;
    protected Square square;
    protected Color color;

    public Piece(Board board, Square square, Color color) {
        this.board = board;
        this.square = square;
        this.color = color;
    }

    public abstract void moveTo(Coordinate c) throws Exception;

    /**
     * Update the square that this piece is on
     * @param square The square that this piece is on
     */
    public void setSquare(Square square) {
        this.square = square;
    }

    @Override
    public String toString() {
        if (this.color == Color.white) return "w";
        else return "b";
    }
}
