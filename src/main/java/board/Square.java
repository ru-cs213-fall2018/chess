package board;

import chess.Color;
import piece.Piece;

/**
 * Represents a square on the board
 * @author Ammaar Muhammad Iqbal
 */
public class Square {

    private Color color;
    private Piece piece;

    /**
     * Creates a new square
     * @param color Color of the square
     * @param piece Piece on the square
     */
    Square(Color color, Piece piece) {
        this.color = color;
        this.piece = piece;
    }

    /**
     * Creates a new square without a piece
     * @param color Color of the square
     */
    Square(Color color) {
        this(color,null);
    }

    /**
     * @return Color of this square
     */
    public Color getColor() {
        return color;
    }

    /**
     * @return Piece on this square or null if no piece
     */
    public Piece getPiece() {
        return piece;
    }

    /**
     * Set a piece on this square
     * @param piece The piece to add or null if no piece
     */
    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    /**
     * @return ASCII representation of what the square looks like
     */
    @Override
    public String toString() {
        if (this.piece != null) return this.piece.toString();
        else if (this.color == color.black) return "##";
        else return "  ";
    }
}
