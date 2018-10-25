package piece;

import board.Board;
import board.Coordinate;
import board.Square;
import chess.Color;

import java.util.List;

/**
 * Represents a pawn
 * @author Ammaar Muhammad Iqbal
 */
public class Pawn extends Piece {

    private boolean moved;

    /**
     * Creates a new pawn
     * @param board Board the pawn is on
     * @param square Square the pawn is on
     * @param color Color of the pawn
     */
    public Pawn(Board board, Square square, Color color) {
        super(board, square, color, 3);
        this.moved = false;
    }

    @Override
    public String move(Square square) {
        String ret = super.move(square);
        if (ret == null) this.moved = true;
        return ret;
    }

    @Override
    protected Coordinate nextCoordinate(int pathNum, int stepNum, Coordinate c) {
        if (pathNum == 1) return new Coordinate(c.getX(), this.yForward(stepNum));
        else if (pathNum == 2) return new Coordinate(this.xLeft(stepNum), this.yForward(stepNum));
        else return new Coordinate(this.xRight(stepNum), this.yForward(stepNum));
    }

    @Override
    protected boolean stepCondition(int pathNum, int stepNum, Coordinate c) {
        if (pathNum == 1 && !this.moved) return stepNum <= 3;
        else return stepNum <= 2;
    }

    @Override
    protected String canMove(boolean found, int pathNum, List<Square> path) {
        if (!found) return "Pawns can not move there";
        if (!Board.isPathClear(path)) return "The pawn's path is blocked";
        if (pathNum == 1 && path.get(path.size() - 1).hasPiece())
            return "A pawn can not attack in this direction";
        else if (pathNum != 1 && !path.get(path.size() - 1).hasPiece())
            return "A pawn can only attack in that direction";
        return null;
    }

    /**
     * @return ASCII representation of the pawn
     */
    @Override
    public String toString() {
        return super.toString() + "p";
    }
}
