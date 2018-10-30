package game;

import board.Board;
import board.Square;
import chess.BadInputException;
import chess.Color;
import chess.IllegalMoveException;
import piece.King;

import java.util.Scanner;

/**
 * Represents a game
 * @author Ammaar Muhammad Iqbal
 */
public class Game {

    private Board board;
    private Player currentPlayer;
    private Player otherPlayer;
    private boolean drawRequested;

    /**
     * Create a new game
     */
    public Game() throws BadInputException {
        this.board = new Board();
        this.currentPlayer = new Player(Color.White, (King) this.board.getSquare('e', 1).getPiece());
        this.otherPlayer = new Player(Color.Black, (King) this.board.getSquare('e', 8).getPiece());
        this.drawRequested = false;
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

                if (move.length == 1) {

                    if (move[0].trim().equals("draw")) {
                        if (!this.drawRequested)
                            throw new IllegalMoveException(this.otherPlayer + " did not offer a draw");
                        System.out.println("\ndraw");
                        break;
                    } else throw new IllegalMoveException("Not a valid option: " + move[0]);

                } else if (move.length == 2 || move.length == 3) {

                    boolean drawRequested = move.length == 3 && move[2].trim().equals("draw?");
                    if (move.length == 3 && !drawRequested)
                        throw new IllegalMoveException("Not a valid option: " + move[2]);

                    Square from = this.board.getSquare(move[0]);
                    Square to = this.board.getSquare(move[1]);
                    this.movePiece(from, to);

                    if (this.currentPlayer.getKing().isInCheck()) {
                        to.getPiece().goBack();
                        throw new IllegalMoveException("You cannot be in check");
                    }

                    this.swapPlayers();

                    if (this.currentPlayer.getKing().isInCheckMate()) {
                        System.out.println("\n" + this.board + "\n\n" + this.otherPlayer + " wins");
                        break;
                    }

                    if (this.currentPlayer.getKing().isInCheck())
                        System.out.println(("\n" + this.currentPlayer + " is in check"));

                    else this.drawRequested = drawRequested;

                } else throw new BadInputException("A move only accepts 1, 2, or 3 arguments");

            } catch (BadInputException e) {
                System.out.println("\nBad input: " + e.getMessage());
            } catch (IllegalMoveException e) {
                System.out.println("\nIllegal move, try again: " + e.getMessage());
            }

            System.out.println();
        }
    }

    /**
     * Moves the piece on from to to
     * @param from Square where the piece is
     * @param to Square where the piece should go
     * @throws IllegalMoveException If cannot make the move
     */
    private void movePiece(Square from, Square to) throws IllegalMoveException {

        // Check if the you are moving your own piece
        if (!from.hasPiece() || from.getPiece().getColor() != this.currentPlayer.getColor())
            throw new IllegalMoveException("You can only move your own piece");

        // Move the piece
        String error = from.getPiece().move(to);
        if (error != null) throw new IllegalMoveException(error);
    }

    /**
     * Switch the current player
     */
    private void swapPlayers() {
        Player temp = this.currentPlayer;
        this.currentPlayer = this.otherPlayer;
        this.otherPlayer = temp;
    }
}
