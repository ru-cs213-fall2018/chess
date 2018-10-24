package piece;

import board.Board;
import board.Coordinate;
import board.Square;
import chess.Color;

import java.util.ArrayList;

/**
 * Represents a rook
 * @author Ammaar Muhammad Iqbal
 */
public class Rook extends Piece {

    /**
     * Create a new rook
     * @param board Board the rook is on
     * @param square Square the rook is on
     * @param color Color of the rook
     */
    public Rook(Board board, Square square, Color color) {
        super(board, square, color);
    }

    @Override
    public void moveTo(Square square) throws Exception {

        // Initialize path and found
        ArrayList<Square> path = new ArrayList<>();
        boolean found = false;

        // Run 4 times for front, back, right, left
        out: for (int j = 0; j < 4; j++) {

            // Clear the path becuase each direction is alone
            path.clear();
            Coordinate c = this.square.getCoordinate();

            // Look in direction as long as the coordinate is in the board
            for (int i = 1; Board.isInBoard(c); i++) {

                // Add corresponding square to the path
                Square s = this.board.getSquare(c);
                path.add(s);

                // If the square is the destination break and set found
                if (square.equals(s)) {
                    found = true;
                    break out;
                }

                // Updated the coordinate based on the out's j
                if (j == 0) c = new Coordinate(c.getX(), this.yForward(i));
                else if (j == 1) c = new Coordinate(c.getX(), this.yBack(i));
                else if (j == 2) c = new Coordinate(this.xRight(i), c.getY());
                else c = new Coordinate(this.xLeft(i), c.getY());
            }
        }

        // Check if found and path is clear
        if (!found) throw new Exception("A rook can't move there");
        if (!Board.isPathClear(path)) throw new Exception("The rook's path is blocked");

        // Move the piece
        this.goTo(square);
    }

    /**
     * ASCII representation of the rook
     * @return
     */
    @Override
    public String toString() {
        return super.toString() + "R";
    }
}
