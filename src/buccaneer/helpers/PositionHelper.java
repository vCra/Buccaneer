package buccaneer.helpers;

import buccaneer.enumData.Direction;
import buccaneer.main.Ship;

import java.util.ArrayList;

import static buccaneer.helpers.DirectionHelper.isSameDirection;

/**
 * Position Helper
 * A colleciton of static methods that can help with positions, such as checking if a position is
 * an island, getting grid IDs from Positions etc...
 * @author awalker
 * @version 0.1
 */
public class PositionHelper {
    //TODO: Please test this because I have no idea if it works - Aaron
    public static ArrayList<Position> getAvailableMoves(Position position, Direction direction) {
        ArrayList<Position> list = new ArrayList<>();
        int x = position.getX();
        int y = position.getY();
        boolean edge = false;
        return list;
    }


    private static boolean isIsland(int x, int y) {
        if (x >= 2 && x <= 4) {
            if (y >= 16 && y <= 19) {
                return true;
            }
        } else if (x >= 17 && x <= 19) {
            if (y >= 2 && y <= 5) {
                return true;
            }
        } else if (x >= 9 && x <= 12) {
            if (y >= 9 && y <= 12) {
                return true;
            }
        }
        return false;
    }

    static boolean isIsland(Position position) {
        return isIsland(position.getX(), position.getY());
    }

    //TODO: Add a method that takes in a location and returns a Ship if it is next to an enemy ship
    //TODO: Add a method that takes in a location and returns a port, or null

    /**
     * check if the clicked position is next to the ship, and if it is, is it facing in the
     * same direction
     * @return true if the ship should turn, else false
     *
     */
    public static boolean shouldTurn(Ship ship, Position pos){
        boolean a = isSameDirection(ship.getLocation(), pos, ship.getDirection());
        boolean b = isNextTo(ship.getLocation(), pos);
        return !a&&b;
    }

    /**
     * Is the move from pos1 to pos2 valid (e.g. it doesn't pass through islands
     * TODO
     * @param ship the ship to move
     * @param pos2 the end pos
     * @return true if the move is valid
     */
    public static boolean moveIsValid(Ship ship, Position pos2) {
        return DirectionHelper.isSameDirection(ship.getLocation(), pos2, ship.getDirection());
    }

    public static int positionToGridID(Position pos){
        return ((pos.getX()-1)+((20-pos.getY())*20));
    }

    public static Position gridChange(int x, int y) {
        return new Position(x + 1, 20 - (y));
    }

    private static boolean isNextTo(Position pos1, Position pos2){
        return isPlusOrMinus(pos1.getX(), pos2.getX()) && isPlusOrMinus(pos1.getY(),pos2.getY());
    }

    private static boolean isPlusOrMinus(int a, int b){
        return a == b || a == (b - 1) || a == (b + 1);
    }
}
