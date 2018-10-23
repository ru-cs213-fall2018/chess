package piece;

import board.Board;
import board.Coordinate;
import board.Path;
import board.Square;
import chess.Color;

import java.util.ArrayList;

public class Rook extends Piece {

    public Rook(Board board, Square square, Color color) {
        super(board, square, color);
    }

    @Override
    public void moveTo(Square square) throws Exception {

        ArrayList<Square> path = new ArrayList<>();
        boolean found = false;

        out: for (int j = 0; j < 4; j++) {
            path.clear();
            Coordinate c = this.square.getCoordinate();
            for (int i = 1; Board.isInBoard(c); i++) {
                Square s = this.board.getSquare(c);
                path.add(s);
                if (square.equals(s)) {
                    found = true;
                    break out;
                }
                if (j == 0) c = new Coordinate(c.getX(), this.yForward(i));
                else if (j == 1) c = new Coordinate(c.getX(), this.yBack(i));
                else if (j == 2) c = new Coordinate(this.xRight(i), c.getY());
                else c = new Coordinate(this.xLeft(i), c.getY());
            }
        }

        if (!found) throw new Exception("A rook can't move there");
        boolean clear = new Path(path).isClear();
        if (!clear) throw new Exception("The rook's path is blocked");

        this.goTo(square);
    }

    @Override
    public String toString() {
        return super.toString() + "R";
    }
}
