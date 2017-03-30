package buccaneer.helpers;

import buccaneer.enumData.Direction;
import buccaneer.main.GameBoard;
import buccaneer.main.Ship;
import buccaneer.ports.Port;

import java.util.ArrayList;

import static buccaneer.helpers.DirectionHelper.isSameDirection;

/**
 * Position Helper
 * A colleciton of static methods that can help with positions, such as checking if a position is
 * an island, getting grid IDs from Positions etc...
 * @author awalker
 * @version 0.1
 */
//TODO: Javadoc
public class PositionHelper {
    /**
     *  Given the ship returns an ArrayList of possible moves
     *  that a ship can take.
     * @param s
     * @return
     */
    public static ArrayList<Position> getAvailableMoves(Ship s) {
        ArrayList<Position> list = new ArrayList<Position>();
        int moves = 0;
        Position currentPos = s.getLocation();

        while (s.getOwner().getMoveStrength() > moves) {
            currentPos = DirectionHelper.getNextPos(currentPos, s.getDirection());
            if (currentPos.isIsland() || currentPos.isEdge()) {
                break;
            } else {
                list.add(currentPos);
                moves++;
            }
        }
        return list;
    }

    public static ArrayList<Position> getAvailablePortMoves(Ship s) {
        ArrayList<Position> list = new ArrayList<Position>();
        for (Direction d : Direction.values()) {
            int moves = 0;
            Position currentPos = s.getLocation();
            while (s.getOwner().getMoveStrength() > moves) {
                currentPos = DirectionHelper.getNextPos(currentPos, d);
                if (currentPos.isIsland() || currentPos.isEdge()) {
                    break;
                } else {
                    list.add(currentPos);
                    moves++;
                }
            }
        }

        return list;
    }

    /**
     *
     */
     static boolean isPort(Position pos, GameBoard board){
        for (Port p : board.getPorts()){
            if (p.getLocation() == pos){
                return true;
            }
        }
        return false;
    }
    /**
     *  Given the coordinates x and y checks if the position is on the edge of the board
     *  and returns true or false accordingly.
     * @param x the x value to check
     * @param y the y value to check
     * @return true if it is an edge
     */
    private static boolean isEdge(int x, int y) {
        return (x < 1 || x > 20) || (y < 1 || y > 20);
    }

    /**
     *  Given coordinates x and y checks if the position is an island and
     *  returns true or false accordingly.
     *  TODO: Combine if statements
     *  FIXME: Reduce Cyclomatic Complexity
     * @param x the x value to check
     * @param y the y value to check
     * @return true if it is an island
     */
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

    /**
     *  Given the position and a board to check against,
     *  checks if the position contains another ship
     *
     */
    static boolean isShip(Position position, GameBoard board){
        return board.getSquareAt(position.getX(), position.getY()).containsShip();
    }

    /**
     *  Given position objects checks if it's an island and
     *  returns true or false accordingly.
     * @param position
     * @return
     */
    static boolean isIsland(Position position) {
        return isIsland(position.getX(), position.getY());
    }

    /**
     *  Given the position checks if it's on the edge of the board
     *  and returns true or false accordingly.
     * @param position
     * @return
     */
    static boolean isEdge(Position position) {
        return isEdge(position.getX(), position.getY());
    }

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
     * @param ship the ship to move
     * @param pos2 the end pos
     * @return true if the move is valid
     */
    public static boolean moveIsValid(Ship ship, Position pos2) {
        return PositionHelper.getAvailableMoves(ship).contains(pos2);
    }
    public static boolean moveFromPortIsValid(Ship ship, Position pos2){
        return PositionHelper.getAvailablePortMoves(ship).contains(pos2);
    }

    public static boolean moveIsValid(Position pos1, Position pos2) {
        return !pos2.isIsland() && !pos2.isEdge();
    }
    /**
     *  Given position returns an ID of the GameSquare in the Grid.
     * @param pos
     * @return
     */
    public static int positionToGridID(Position pos){
        return ((pos.getX()-1)+((20-pos.getY())*20));
    }

    /**
     *  Given coordinates x and y creates a Position object.
     * @param x
     * @param y
     * @return
     */
    public static Position gridChange(int x, int y) {
        return new Position(x + 1, 20 - (y));
    }

    /**
     *  Checks if given positions are next to each other.
     * @param pos1
     * @param pos2
     * @return
     */
    public static boolean isNextTo(Position pos1, Position pos2) {
        return isPlusOrMinus(pos1.getX(), pos2.getX()) && isPlusOrMinus(pos1.getY(),pos2.getY());
    }

    /**
     *
     * @param a
     * @param b
     * @return
     */
    private static boolean isPlusOrMinus(int a, int b){
        return a == b || a == (b - 1) || a == (b + 1);
    }

    //TODO: Check if a ship has moved through a player
    public static boolean moveThroughPlayer(Ship s, Position endPos) { return false; }

    private static int distanceTraveled(Position pos1, Position pos2){
        return Math.max(Math.abs(pos1.getX() - pos2.getX()), Math.abs(pos1.getY() - pos2.getY()));
    }
}
