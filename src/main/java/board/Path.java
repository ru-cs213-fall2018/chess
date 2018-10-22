package board;

import java.util.List;

/**
 * Represents a path taken by a piece
 * @author Ammaar Muhammad Iqbal
 */
public class Path {

    List<Square> path;

    public Path(List<Square> path) {
        this.path = path;
    }

    public boolean isClear() {
        for (int i = 1; i < path.size() - 1; i++) {
            if (path.get(i).hasPiece())
                return false;
        }
        return true;
    }
}
