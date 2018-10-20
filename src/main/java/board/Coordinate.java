package board;

/**
 * Represents a coordinate for a chess board's grid
 * @author Ammaar Muhammad Iqbal
 */
public class Coordinate {

    private int x;
    private int y;

    /**
     * Create a coordinate for a chess board's grid
     * @param x X index of grid
     * @param y Y index of grid
     */
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return X index of grid
     */
    public int getX() {
        return x;
    }

    /**
     * @return Y index of grid
     */
    public int getY() {
        return y;
    }
}
