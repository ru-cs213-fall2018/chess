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
     * @param n Number of spaces forward
     * @return Y coordinate of this piece if it were to go forward n spaces
     */
    protected int yForward(int n) {
        Coordinate start = this.square.getCoordinate();
        boolean white = this.color == Color.White;
        int forward = start.getY() + (white ? 1 : -1);
        return forward * n;
    }

    /**
     * @param n Number of spaces to go back
     * @return Y coordinate of this piece if it were to go back n spaces
     */
    protected int yBack(int n) {
        Coordinate start = this.square.getCoordinate();
        boolean white = this.color == Color.White;
        int back = start.getY() + (white ? -1 : 1);
        return back * n;
    }

    /**
     * @param n Number of spaces to go right
     * @return X coordinate of this piece if it were to go right n spaces
     */
    protected int xRight(int n) {
        Coordinate start = this.square.getCoordinate();
        boolean white = this.color == Color.White;
        int right = start.getX() + (white ? 1 : -1);
        return right * n;
    }

    /**
     * @param n Number of spaces to go left
     * @return X coordinate of this piece if it were to go left n spaces
     */
    protected int xLeft(int n) {
        Coordinate start = this.square.getCoordinate();
        boolean white = this.color == Color.White;
        int left = start.getX() + (white ? -1 : 1);
        return left * n;
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
