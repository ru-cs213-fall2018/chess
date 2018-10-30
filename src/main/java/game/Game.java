package game;

import board.Board;
import board.Square;
import chess.Color;
import piece.King;

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
        this.board = new Board();
        this.player1 = new Player(Color.White, (King) this.board.getSquare('e', 1).getPiece());
        this.player2 = new Player(Color.Black, (King) this.board.getSquare('e', 8).getPiece());
        this.currentPlayer = this.player1;
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
                Square from = this.board.getSquare(fromString);
                Square to = this.board.getSquare(toString);

                String msg = this.movePiece(from, to);

                if (msg != null) System.out.println("Illegal move, try again: " + msg);

            } catch (IndexOutOfBoundsException e) {
                System.out.println("Bad input: " + e.getMessage());
            }

            System.out.println();
        }
    }

    /**
     * Moves the piece on from to to
     * @param from Square where the piece is
     * @param to Square where the piece should go
     * @return Null if piece was moved, else returns message
     */
    public String movePiece(Square from, Square to) {

        // Check if the you are moving your own piece
        if (!from.hasPiece() || from.getPiece().getColor() != this.currentPlayer.getColor())
            return "You can only move your own piece";

        // Check if end has same color piece
        if (to.hasPiece() && to.getPiece().getColor() == this.currentPlayer.getColor())
            return "Your have already have a piece at your destination";

        // Move the piece
        String ret = from.getPiece().move(to);
        if (ret == null) this.finishTurn();
        return ret;
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
