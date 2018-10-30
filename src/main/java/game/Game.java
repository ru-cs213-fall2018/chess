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
    private boolean drawOffered;

    /**
     * Create a new game
     */
    public Game() throws BadInputException {
        this.board = new Board();
        this.currentPlayer = new Player(Color.White, (King) this.board.getSquare('e', 1).getPiece());
        this.otherPlayer = new Player(Color.Black, (King) this.board.getSquare('e', 8).getPiece());
        this.drawOffered = false;
    }

    /**
     * Start playing the game
     */
    public void start() {
        Scanner input = new Scanner(System.in);
        while (true) {

            // Get input
            System.out.println(this.board);
            System.out.print("\n" + currentPlayer + "'s move: ");
            String in = input.nextLine();
            String[] move = in.split(" ");
            try {
                if (move.length == 1) {

                    // Handle accepting draw offer
                    if (move[0].trim().equals("draw")) {
                        if (!this.drawOffered)
                            throw new IllegalMoveException(this.otherPlayer + " did not offer a draw");
                        System.out.println("\ndraw");
                        break;
                    }

                    // Handle resigning
                    else if (move[0].trim().equals("resign")) {
                        System.out.println("\n" + this.otherPlayer + " wins");
                        break;
                    }

                    // Handle erroneous input
                    else throw new IllegalMoveException("Not a valid option: " + move[0]);
                }

                // Handle regular moves and draw offers
                else if (move.length == 2 || move.length == 3) {

                    // Check if valid draw offer
                    boolean drawOffered = move.length == 3 && move[2].trim().equals("draw?");
                    if (move.length == 3 && !drawOffered)
                        throw new IllegalMoveException("Not a valid option: " + move[2]);

                    // Move the piece
                    Square from = this.board.getSquare(move[0]);
                    Square to = this.board.getSquare(move[1]);
                    if (!from.hasPiece() || from.getPiece().getColor() != this.currentPlayer.getColor())
                        throw new IllegalMoveException("You can only move your own piece");
                    String error = from.getPiece().move(to);
                    if (error != null) throw new IllegalMoveException(error);

                    // Check if current player is in check
                    if (this.currentPlayer.getKing().isInCheck()) {
                        to.getPiece().goBack();
                        throw new IllegalMoveException("You cannot be in check");
                    }

                    // Current player wins if other player is in checkmate
                    if (this.otherPlayer.getKing().isInCheckMate()) {
                        System.out.println("\n" + this.board + "\n\n" + this.currentPlayer + " wins");
                        break;
                    }

                    // Let other player know if s/he is in check
                    if (this.otherPlayer.getKing().isInCheck())
                        System.out.println(("\n" + this.otherPlayer + " is in check"));

                    // Swap the playes
                    Player temp = this.currentPlayer;
                    this.currentPlayer = this.otherPlayer;
                    this.otherPlayer = temp;

                    // Set draw offered
                    this.drawOffered = drawOffered;
                }

                // Handle erroneous number of move arguments
                else throw new BadInputException("A move only accepts 1, 2, or 3 arguments");
            }

            // Handle Exceptions
            catch (BadInputException e) {
                System.out.println("\nBad input: " + e.getMessage());
            } catch (IllegalMoveException e) {
                System.out.println("\nIllegal move, try again: " + e.getMessage());
            }
            System.out.println();
        }
    }
}
