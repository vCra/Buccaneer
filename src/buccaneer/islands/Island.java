package buccaneer.islands;

import buccaneer.helpers.Position;
import buccaneer.main.GameObject;

/**
 * Island Base Class
 *
 * @author outca_000
 */
public class Island implements GameObject {
    private Position startPos;
    private Position endPos;
    /**
     * Constructor.
     * Adds an ArrayList and fills it up with positions
     * between the parameters.
     * We really don't need this
     *
     * @param startPos The starting position of the island
     * @param endPos The end position of the island
     */
    public Island(Position startPos, Position endPos) {
        this.startPos = startPos;
        this.endPos = endPos;
    }

    public Position getStartPos() {
        return startPos;
    }

    public Position getEndPos() {
        return endPos;
    }
}