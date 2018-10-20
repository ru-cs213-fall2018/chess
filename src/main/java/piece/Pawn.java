package piece;

import board.Board;
import board.Coordinate;
import board.Path;
import board.Square;
import chess.Color;

import java.util.ArrayList;

public class Pawn extends Piece {

    private boolean moved;

    public Pawn(Board board, Square square, Color color) {
        super(board, square, color);
        this.moved = false;
    }

    @Override
    public void moveTo(Coordinate c) throws Exception {

        // Check if end has same color piece
        Square end = this.board.getSquare(c);
        if (end.hasPiece() && end.getPiece().color == this.color)
            throw Piece.ILLEGAL_MOVE;

        // Check if x value good
        Coordinate start = this.square.getCoordinate();
        if (start.getX() != c.getX()) throw Piece.ILLEGAL_MOVE;

        // Check if y movement is good
        boolean isWhite = this.color == Color.white;
        int maxYDiff = this.moved ? 1 : 2;
        int displacement = c.getY() - start.getY();
        int forwardDisplacement = isWhite ? displacement : -displacement;
        if (forwardDisplacement < 1 || forwardDisplacement > maxYDiff)
            throw Piece.ILLEGAL_MOVE;

        // Check if path is clear
        ArrayList<Square> path = new ArrayList<>();
        for (int i = isWhite ? start.getY() : c.getY();
             i <= (isWhite ? c.getY() : start.getY()); i++)
            path.add(this.board.getSquare(new Coordinate(start.getX(), i)));
        if (!new Path(path).isClear()) throw Piece.ILLEGAL_MOVE;

        // Move the piece
        end.setPiece(this);
        this.square.removePiece();
        this.moved = true;
    }

    @Override
    public String toString() {
        return super.toString() + "p";
    }
}
