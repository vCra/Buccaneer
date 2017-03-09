package buccaneer.helpers;

import buccaneer.enumData.Direction;
import buccaneer.main.Ship;

import java.util.ArrayList;

/**
 * Created by awalker on 15/02/2017.
 */
public class PositionHelper {
    //TODO: Please test this because I have no idea if it works - Aaron
    public static ArrayList<Position> getAvailableMoves(Position position, Direction direction) {
        ArrayList<Position> list = new ArrayList<>();
        int x = position.getX();
        int y = position.getY();
        boolean edge = false;
        switch (direction) {
            case E:
                while (!edge) {
                    if (x + 1 > 20 || isIsland(x + 1, y)) {
                        edge = true;
                    } else {
                        x++;
                        list.add(new Position(x, y));
                    }
                }
                break;
            case N:
                while (!edge) {
                    if (y + 1 > 20 || isIsland(x, y + 1)) {
                        edge = true;
                    } else {
                        y++;
                        list.add(new Position(x, y));
                    }
                }
                break;
            case W:
                while (!edge) {

                    if (x - 1 < 1 || isIsland(x - 1, y)) {
                        edge = true;
                    } else {
                        x--;
                        list.add(new Position(x, y));
                    }
                }
                break;
            case S:
                while (!edge) {
                    if (y - 1 < 1 || isIsland(x, y - 1)) {
                        edge = true;
                    } else {
                        y--;
                        list.add(new Position(x, y));
                    }
                }
                break;
            case NE:
                while (!edge) {
                    if (x + 1 > 20 || y + 1 > 20 || isIsland(x + 1, y + 1)) {
                        edge = true;
                    } else {
                        x++;
                        y++;
                        list.add(new Position(x, y));
                    }
                }
                break;
            case NW:
                while (!edge) {
                    if (x - 1 < 0 || y + 1 > 20 || isIsland(x - 1, y + 1)) {
                        edge = true;
                    } else {
                        x--;
                        y++;
                        list.add(new Position(x, y));
                    }
                }
                break;
            case SW:
                while (!edge) {
                    if (x - 1 < 0 || y - 1 < 0 || isIsland(x - 1, y - 1)) {
                        edge = true;
                    } else {
                        x--;
                        y--;
                        list.add(new Position(x, y));
                    }
                }
                break;
            case SE:
                while (!edge) {
                    if (x + 1 > 20 || y - 1 < 0 || isIsland(x + 1, y - 1)) {
                        edge = true;
                    } else {
                        x++;
                        y--;
                        list.add(new Position(x, y));
                    }
                }
                break;
        }
        return list;
    }

    public static boolean isIsland(int x, int y) {
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

    public static boolean isIsland(Position position) {
        return isIsland(position.getX(), position.getY());
    }

    //TODO: Add a method that takes in a location and returns a Ship if it is next to an enemy ship
    //TODO: Add a method that takes in a location and returns a port, or null

    /**
     * TODO: Add a method that checks if the clicked position is next to the ship, and if it is, is it facing in the
     * same direction
     * @return true if the ship should turn, else false
     *
     */
    public static boolean shouldTurn(Ship ship, Position pos){
        return false;
    }

    /**
     * Is the move from pos1 to pos2 valid (e.g. it doesn't pass through islands
     * TODO
     * @param pos1
     * @param pos2
     * @return true if the move is valid
     */
    public static boolean moveIsValid(Position pos1, Position pos2){
        return true;
    }

    public static int positionToGridID(Position pos){
        return ((pos.getX()-1)+((20-pos.getY())*20));
    }
}
