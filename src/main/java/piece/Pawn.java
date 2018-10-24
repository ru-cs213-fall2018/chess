package piece;

import board.Board;
import board.Coordinate;
import board.Square;
import chess.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a pawn
 * @author Ammaar Muhammad Iqbal
 */
public class Pawn extends Piece {

    private boolean moved;

    public Pawn(Board board, Square square, Color color) {
        super(board, square, color, 3);
        this.moved = false;
    }

    @Override
    public void moveTo(Square square) throws Exception {
        super.moveTo(square);
        this.moved = true;
    }

    @Override
    protected Coordinate nextCoordinate(int pathNum, int stepNum, Coordinate c) {
        if (pathNum == 1) return new Coordinate(c.getX(), this.yForward(stepNum));
        else if (pathNum == 2) return new Coordinate(this.xLeft(stepNum), this.yForward(stepNum));
        else return new Coordinate(this.xRight(stepNum), this.yForward(stepNum));
    }

    @Override
    protected boolean stepCondition(int pathNum, int stepNum, Coordinate c) {
        if (pathNum == 1 && !this.moved) return stepNum <= 2;
        else return stepNum <= 1;
    }

    @Override
    protected void checkCanMove(boolean found, int pathNum, List<Square> path) throws Exception {
        if (!found) throw new Exception("Pawns can not move there");
        if (!Board.isPathClear(path)) throw new Exception("The pawn's path is blocked");
        if (pathNum == 1 && path.get(path.size() - 1).hasPiece())
            throw new Exception("A pawn can not attack in this direction");
        else if (pathNum != 1 && !path.get(path.size() - 1).hasPiece())
            throw new Exception("A pawn can only attack in that direction");
    }

    /**
     * @return ASCII representation of the pawn
     */
    @Override
    public String toString() {
        return super.toString() + "p";
    }
}
