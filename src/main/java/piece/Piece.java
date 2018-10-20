package piece;

import board.Board;
import board.Square;
import chess.Color;

/**
 * Represents a chess piece
 * @author Ammaar Muhammad Iqbal
 */
public abstract class Piece {

    protected Board board;
    protected Square square;
    protected Color color;

    public Piece(Board board, Square square, Color color) {
        this.board = board;
        this.square = square;
        this.color = color;
    }

    /**
     * Moves the piece if allowed
     * @param square The square to move the piece to.
     *               Must be empty or have opponents piece.
     * @throws Exception If you cannot move the piece to square
     */
    public abstract void moveTo(Square square) throws Exception;

    /**
     * Moves the piece without any check
     * @param square Square to go to
     */
    protected void goTo(Square square) {
        this.square.removePiece();
        square.setPiece(this);
    }

    /**
     * Update the square that this piece is on
     * @param square The square that this piece is on
     */
    public void setSquare(Square square) {
        this.square = square;
    }

    /**
     * @return Color of the piece
     */
    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        if (this.color == Color.White) return "w";
        else return "b";
    }
}
