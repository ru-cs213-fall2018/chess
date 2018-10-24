package piece;

import board.Board;
import board.Coordinate;
import board.Square;
import chess.Color;

import java.util.List;

/**
 * Represents a king
 * @author Ammaar Muhammad Iqbal
 */
public class King extends Piece {

    /**
     * Creates a king
     * @param board Board the king is on
     * @param square Square the king is on
     * @param color Color of the king
     */
    public King(Board board, Square square, Color color) {
        super(board, square, color, 8);
    }

    @Override
    protected Coordinate nextCoordinate(int pathNum, int stepNum, Coordinate c) {
        if (pathNum == 1) return new Coordinate(c.getX(), this.yForward(stepNum));
        else if (pathNum == 2) return new Coordinate(this.xRight(stepNum), this.yForward(stepNum));
        else if (pathNum == 3) return new Coordinate(this.xRight(stepNum), c.getY());
        else if (pathNum == 4) return new Coordinate(this.xRight(stepNum), this.yBack(stepNum));
        else if (pathNum == 5) return new Coordinate(c.getX(), this.yBack(stepNum));
        else if (pathNum == 6) return new Coordinate(this.xLeft(stepNum), this.yBack(stepNum));
        else if (pathNum == 7) return new Coordinate(this.xLeft(stepNum), c.getY());
        else return new Coordinate(this.xLeft(stepNum), this.yForward(stepNum));
    }

    @Override
    protected boolean stepCondition(int pathNum, int stepNum, Coordinate c) {
        return stepNum <= 2;
    }

    @Override
    protected void checkCanMove(boolean found, int pathNum, List<Square> path) throws Exception {
        if (!found) throw new Exception("A king can't move there");
    }

    /**
     * @return ASCII representation of the kning
     */
    @Override
    public String toString() {
        return super.toString() + "K";
    }
}
