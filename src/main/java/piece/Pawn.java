package piece;

import board.Board;
import board.Coordinate;
import board.Path;
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

        // Check if x value good
        Coordinate start = this.square.getCoordinate();
        Coordinate end = square.getCoordinate();
        if (start.getX() != end.getX())
            throw new Exception("Cannot move there");

        // Check if y movement is good
        boolean isWhite = this.color == Color.White;
        int maxYDiff = this.moved ? 1 : 2;
        int displacement = end.getY() - start.getY();
        int forwardDisplacement = isWhite ? displacement : -displacement;
        if (forwardDisplacement < 1 || forwardDisplacement > maxYDiff)
            throw new Exception("Cannot move there");

        // Check if path is clear
        ArrayList<Square> path = new ArrayList<>();
        for (int i = isWhite ? start.getY() : end.getY();
             i <= (isWhite ? end.getY() : start.getY()); i++)
            path.add(this.board.getSquare(new Coordinate(start.getX(), i)));
        if (!new Path(path).isClear())
            throw new Exception("There is something blocking your way");

        // Move the piece
        this.goTo(square);
        this.moved = true;
    }

    @Override
    public String toString() {
        return super.toString() + "p";
    }
}
