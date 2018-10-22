package board;

import chess.Color;
import piece.Pawn;

/**
 * Represents a chess board
 * @author Ammaar Muhammad Iqbal
 */
public class Board {

    private static final int numCols = 8;
    private static final int numRows = 8;
    private Square[][] grid;

    /**
     * Creates a new board and fills the grid appropriately
     */
    public Board() {

        // Initialize the grid
        this.grid = new Square[Board.numCols][Board.numRows];

        // Fill the grid
        for (int i = 0; i < Board.numCols; i++) {
            for (int j = 0; j < Board.numRows; j++) {

                // Alternate black and white squares
                Color color;
                if ((i+j)%2 == 0)
                    color = Color.Black;
                else
                    color = Color.White;
                this.grid[i][j] = new Square(new Coordinate(i, j), color);
            }
        }

        // Add white pawns
        for (int i = 0; i < this.numCols; i++) {
            Square square = grid[i][1];
            square.setPiece(new Pawn(this, square, Color.White));
        }

        // Add black pawns
        for (int i = 0; i < this.numCols; i++) {
            Square square = grid[i][6];
            square.setPiece(new Pawn(this, square, Color.Black));
        }
    }

    /**
     * Get coordinate of the grid
     * @param file File or column used to get x value
     * @param row Row used to get y value
     * @return The requested coordinate
     * @throws Exception If file or row doesn't exist
     */
    public static Coordinate getCoordinate(char file, int row) throws Exception {

        // Check if row is valid
        if (row > Board.numRows || row < 1)
            throw new Exception("Row " + row + " doesn't exist");

        // Set the right x and y values for grid
        int y = row - 1;
        int x;
        if (file == 'a')
            x = 0;
        else if (file == 'b')
            x = 1;
        else if (file == 'c')
            x = 2;
        else if (file == 'd')
            x = 3;
        else if (file == 'e')
            x = 4;
        else if (file == 'f')
            x = 5;
        else if (file == 'g')
            x = 6;
        else if (file == 'h')
            x = 7;

        // Check if file is valid
        else
            throw new Exception("File " + file + " doesn't exist");

        // Return the coordinate
        return new Coordinate(x, y);
    }

    /**
     * Get a coordinate of the grids
     * @param fileRank A string that contains the file at the
     *                 first char and rank at the second
     * @return The requested coordinate
     * @throws Exception If the requested coordinate doesn't exist
     */
    public static Coordinate getCoordinate(String fileRank) throws Exception {
        char file = fileRank.charAt(0);
        int rank = Integer.parseInt(String.valueOf(fileRank.charAt(1)));
        return Board.getCoordinate(file, rank);
    }

    /**
     * @param c Coordinate to check it it is in the board
     * @return True if it is in the board, false otherwise
     */
    public static boolean isInBoard(Coordinate c) {
        return c.getX() >= 0 && c.getX() <= 7 && c.getY() >= 0 && c.getY() <= 7;
    }

    /**
     * Get a square from the board
     * @param c The coordinate of the requested square
     * @return The requested square
     */
    public Square getSquare(Coordinate c) {
        return this.grid[c.getX()][c.getY()];
    }

    /**
     * Get a square from the board
     * @param file File or column where the square is located
     * @param row Row where the square is located
     * @return The requested square
     * @throws Exception If file or row doesn't exist
     */
    public Square getSquare(char file, int row) throws Exception {
        Coordinate c = Board.getCoordinate(file, row);
        return this.getSquare(c);
    }

    /**
     * Get a square from the board
     * @param fileRank A string that contains the file at the
     *                 first char and rank at the second
     * @return The requested square
     * @throws Exception If the requested square doesn't exist
     */
    public Square getSquare(String fileRank) throws Exception {
        Coordinate c = Board.getCoordinate(fileRank);
        return this.getSquare(c);
    }

    /**
     * @return ASCII representation of how the board looks
     */
    @Override
    public String toString() {
        String ret = "";
        for (int j = Board.numRows; j > 0; j--) {
            for (int i = 0; i < Board.numCols; i++)
                ret += this.grid[i][j-1] + " ";
            ret += j + "\n";
        }
        return ret + " a  b  c  d  e  f  g  h";
    }
}
