package piece;

import board.Board;
import board.Coordinate;
import board.Square;
import chess.Color;

import java.util.ArrayList;

/**
 * Represents a pawn
 * @author Ammaar Muhammad Iqbal
 */
public class Pawn extends Piece {

    private boolean moved;

    public Pawn(Board board, Square square, Color color) {
        super(board, square, color);
        this.moved = false;
    }

    @Override
    public void moveTo(Square square) throws Exception {

        // Get the coordinates
        Coordinate start = this.square.getCoordinate();
        Coordinate end = square.getCoordinate();

        // Create a lists of allowed movements
        ArrayList<Coordinate> forward = new ArrayList<>();
        forward.add(new Coordinate(start.getX(), this.yForward(1)));
        if (!this.moved) forward.add(new Coordinate(start.getX(), this.yForward(2)));
        ArrayList<Coordinate> diagnals = new ArrayList<>();
        diagnals.add(new Coordinate(this.xLeft(1), this.yForward(1)));
        diagnals.add(new Coordinate(this.xRight(1), this.yForward(1)));

        // Verify destination with lists
        if (diagnals.contains(end)) {
            if (!square.hasPiece())
                throw new Exception("A pawn can only attack there");
        } else if (forward.contains(end)) {
            for (Coordinate c : forward) {
                if (this.board.getSquare(c).hasPiece())
                    throw new Exception("Your path is blocked");
            }
        } else throw new Exception("A Pawn can't go there");

        // Move the pawn
        this.goTo(square);
        this.moved = true;
    }

    /**
     * @return ASCII representation of the pawn
     */
    @Override
    public String toString() {
        return super.toString() + "p";
    }
}
