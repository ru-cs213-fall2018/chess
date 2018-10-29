package piece;

import board.Board;
import board.Coordinate;
import board.Square;
import chess.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a chess piece
 * @author Ammaar Muhammad Iqbal
 */
public abstract class Piece {

    protected Board board;
    protected Square square;
    protected Color color;
    protected int numPaths;

    /**
     * Initializes a piece
     * @param board Board the piece is on
     * @param square Square the piece is on
     * @param color Color of the piece
     * @param numPaths Number of paths this piece can take when moving
     */
    public Piece(Board board, Square square, Color color, int numPaths) {
        this.board = board;
        this.square = square;
        this.color = color;
        this.numPaths = numPaths;
    }

    /**
     * Moves the piece if allowed
     * @param square The square to move the piece to.
     *               Must be empty or have opponents piece.
     * @return Null if piece moved, message otherwise
     */
    public String move(Square square) {
        String ret = this.canMove(square);
        if (ret == null) {
            this.square.removePiece();
            square.setPiece(this);
        }
        return ret;
    }

    /**
     * Checks if this piece can move to square
     * @param square The destination
     * @return Null if this piece can move to square, otherwise a message
     */
    public String canMove(Square square) {

        // Initialize path and found
        ArrayList<Square> path = new ArrayList<>();
        boolean found = false;
        int pathNum = 0;

        // Run for each path
        out: for (int j = 1; j <= this.numPaths; j++) {

            // Clear the path
            path.clear();
            Coordinate c = this.square.getCoordinate();

            // Look in path as long as the coordinate is in the board and condition holds
            for (int i = 1; Board.isInBoard(c) && this.stepCondition(j, i, c); i++) {

                // Add corresponding square to the path
                Square s = this.board.getSquare(c);
                path.add(s);

                // If the square is the destination break and set found
                if (square.equals(s)) {
                    found = true;
                    pathNum = j;
                    break out;
                }

                // Updated the coordinate based on the out's j
                c = this.nextCoordinate(j, i, c);
            }
        }

        // Check if can move
        return this.canMove(found, pathNum, path);
    }

    /**
     * @param square Square to check if this piece can attack
     * @return True if this piece can move to square, otherwise false
     */
    public boolean canAttack(Square square) {
        return this.canMove(square) == null;
    }

    /**
     * Implement this to get the next coordinate when building the path to the
     * destination square
     * @param pathNum The number of the current path (1 to numPaths)
     * @param stepNum The number of the current step in the path (starts at 1)
     * @param c The current coordinate
     * @return The next coordinate
     */
    protected abstract Coordinate nextCoordinate(int pathNum, int stepNum, Coordinate c);

    /**
     * Implement this to see if the piece should take the next step
     * when building the path to the destination
     * @param pathNum The number of the current path (1 to numPaths)
     * @param stepNum The number of the current step in the path
     *                (starts at 1 representing the starting position)
     * @param c The current coordinate, it is in the board
     * @return True if should take step, otherwise false
     */
    protected abstract boolean stepCondition(int pathNum, int stepNum, Coordinate c);

    /**
     * Implement this to check if the piece can move
     * @param found If the destination was found
     * @param pathNum Number of the path the destination was found in (1 to numPaths)
     * @param path The path where the destination was found
     * @return Null if can move, otherwise a message
     */
    protected abstract String canMove(boolean found, int pathNum, List<Square> path);

    /**
     * @param n Number of spaces forward
     * @return Y coordinate of this piece if it were to go forward n spaces
     */
    protected int yForward(int n) {
        Coordinate start = this.square.getCoordinate();
        boolean white = this.color == Color.White;
        return start.getY() + (white ? n : -n);
    }

    /**
     * @param n Number of spaces to go back
     * @return Y coordinate of this piece if it were to go back n spaces
     */
    protected int yBack(int n) {
        Coordinate start = this.square.getCoordinate();
        boolean white = this.color == Color.White;
        return start.getY() + (white ? -n : n);
    }

    /**
     * @param n Number of spaces to go right
     * @return X coordinate of this piece if it were to go right n spaces
     */
    protected int xRight(int n) {
        Coordinate start = this.square.getCoordinate();
        boolean white = this.color == Color.White;
        return start.getX() + (white ? n : -n);
    }

    /**
     * @param n Number of spaces to go left
     * @return X coordinate of this piece if it were to go left n spaces
     */
    protected int xLeft(int n) {
        Coordinate start = this.square.getCoordinate();
        boolean white = this.color == Color.White;
        return start.getX() + (white ? -n : n);
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

    /**
     * @return ASCII representation of color
     */
    @Override
    public String toString() {
        if (this.color == Color.White) return "w";
        else return "b";
    }
}
