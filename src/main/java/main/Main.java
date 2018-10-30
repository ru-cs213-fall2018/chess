package main;

import chess.BadInputException;
import game.Game;

public class Main {
    public static void main(String[] args) throws BadInputException {
        Game game = new Game();
        game.start();
    }
}
