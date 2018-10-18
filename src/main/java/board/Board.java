package board;

/**
 * Represents a chess board
 * @author Ammaar Muhammad Iqbal
 */
public class Board {

    private Square[][] grid;

    public Board() {
        this.grid = new Square[8][8];
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
}
