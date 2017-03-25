package buccaneer.islands;

import buccaneer.helpers.Position;
import buccaneer.main.GameObject;

import java.util.ArrayList;

/**
 * Island Base Class
 *
 * @author outca_000
 */
public class Island implements GameObject {
    private ArrayList<Position> spaceOccupied;

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
        spaceOccupied = new ArrayList<>();

        int startX = startPos.getX(), startY = startPos.getY();
        int endX = endPos.getX(), endY = endPos.getY();

        for (int i = startX; i < endX; i++) {
            for (int j = startY; j < endY; j++) {
                spaceOccupied.add(new Position(i, j));
            }
        }
    }

    /**
     * Returns the spaceOccupied
     *
     * @return spaceOccupied
     */
    public ArrayList<Position> getSpaceOccupied() {
        return spaceOccupied;
    }

    @Override
    public Position getLocation() {
        return spaceOccupied.get(0);
    }
}