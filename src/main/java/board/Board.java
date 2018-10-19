package board;

import chess.Color;

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
                    color = Color.black;
                else
                    color = Color.white;
                this.grid[i][j] = new Square(color);
            }
        }
    }

    /**
     * Get a square from the board
     * @param file File or column where the square is located
     * @param row Row where the square is located
     * @return The requested square
     * @throws Exception If file or row doesn't exist
     */
    public Square getSquare(char file, int row) throws Exception {

        // Check if row is valid
        if (row > 8 || row < 1)
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

        // Return the appropriate square
        return this.grid[x][y];
    }

    /**
     * Get a square from the board
     * @param fileRank A string that contains the file at the
     *                 first char and rank at the second
     * @return The requested square
     * @throws Exception If the requested square doesn't exist
     */
    public Square getSquare(String fileRank) throws Exception {
        char file = fileRank.charAt(0);
        int rank = Integer.parseInt(String.valueOf(fileRank.charAt(1)));
        return this.getSquare(file, rank);
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
