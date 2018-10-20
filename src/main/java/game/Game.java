package game;

import board.Board;
import board.Coordinate;
import board.Square;
import chess.Color;

import java.util.Scanner;

/**
 * Represents a game
 * @author Ammaar Muhammad Iqbal
 */
public class Game {

    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Board board;

    /**
     * Create a new game
     */
    public Game() {
        this.player1 = new Player(Color.White);
        this.player2 = new Player(Color.Black);
        this.currentPlayer = this.player1;
        this.board = new Board();
    }

    /**
     * Start playing the game
     */
    public void start() {

        Scanner input = new Scanner(System.in);

        while (true) {

            System.out.println(this.board);
            System.out.print("\n" + currentPlayer + "'s move: ");
            String in = input.nextLine();
            String[] move = in.split(" ");

            try {

                String fromString = move[0];
                String toString = move[1];

                try {
                    Square from = this.board.getSquare(fromString);
                    Square to = this.board.getSquare(toString);
                    this.movePiece(from, to);

                } catch (Exception e) {
                    System.out.println("Illegal move, try again: " + e.getMessage());
                }

            } catch (IndexOutOfBoundsException e) {
                System.out.println("Bad input");
            }

            System.out.println();
        }
    }

    /**
     * Moves the piece on from to to
     * @param from Square where the piece is
     * @param to Square where the piece should go
     * @throws Exception If the from or to is not allowed
     */
    public void movePiece(Square from, Square to) throws Exception {

        // Check if the you are moving your own piece
        if (!from.hasPiece() || from.getPiece().getColor() != this.currentPlayer.getColor())
            throw new Exception("You can only move your own piece");

        // Check if end has same color piece
        if (to.hasPiece() && to.getPiece().getColor() == this.currentPlayer.getColor())
            throw new Exception("Your have already have a piece at your destination");

        // Move the piece
        from.getPiece().moveTo(to);
        this.finishTurn();
    }

    /**
     * Switch the current player
     */
    private void finishTurn() {
        if (this.currentPlayer == this.player1)
            this.currentPlayer = this.player2;
        else
            this.currentPlayer = this.player1;
    }
}
