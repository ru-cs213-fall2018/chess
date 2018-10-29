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
    protected String canMove(boolean found, int pathNum, List<Square> path) {
        if (!found) return "A king can't move there";
        return null;
    }

    /**
     * Checks if the king is in danger
     * @return True if the king can be attacked, false otherwise
     */
    public boolean isInCheck() {
        return this.isInCheck(this.square);
    }

    /**
     * Checks if this king is in check at square
     * @param square The hypothetical square the king
     *               will check its check status from
     * @return True if king can be attacked at square, else false
     */
    public boolean isInCheck(Square square) {
        return !this.isSafe(square);
    }

    /**
     * Checks if king is in checkmate
     * @return True if the kink can't move anywhere safe
     * and is currently under attack
     */
    public boolean isInCheckMate() {
        return this.board.isEverySquare(s -> {
            if (this.canMove(s) == null)
                return this.isInCheck(s);
            else return true;
        });
    }

    /**
     * @return ASCII representation of the kning
     */
    @Override
    public String toString() {
        return super.toString() + "K";
    }
}
