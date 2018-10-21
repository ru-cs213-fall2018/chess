package game;

import chess.Color;

/**
 * Represents a player
 * @author Ammaar Muhammad Iqbal
 */
public class Player {

    private Color color;

    /**
     * Creates a new player
     * @param color Color of the player
     */
    public Player(Color color) {
        this.color = color;
    }

    /**
     * @return Color of the player
     */
    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return this.color.toString();
    }
}
