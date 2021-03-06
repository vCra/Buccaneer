package buccaneer.helpers;

import buccaneer.enumData.Direction;
import buccaneer.main.GameApp;
import buccaneer.main.Ship;

import static java.lang.Math.abs;

/**
 * DirectionHelper.java 03/03/2017
 * <p>
 * Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 * <p>
 * Helps with working out direction related infomation
 *
 * @author AAW13
 * @version 1.0
 */
public class DirectionHelper {

    /**
     * We need to make sure that the turns we highlight are not edges
     * We also need to make sure that the positions we highlight are valid
     * - The player needs to be able to turn at least one square forward
     * after they turn in that direction. As a result, we should disalow
     * turning towards islands if the turn direction is an island.
     *
     * @param ship
     * @param gui  the gui of the game, which has highlightDirection Methods
     */
    public static void highlightTurns(Ship ship, GameApp gui) {
        Position p = ship.getLocation();
        for (Direction d : Direction.values()) {
            if (!(getNextPos(p, d).isEdge() || getNextPos(p, d).isIsland())) {
                gui.highlightDirection(DirectionHelper.getNextPos(p, d), d);
            }
        }
    }

    /**
     * Takes the compass direction and converts it into correct angle
     *
     * @param dir the direction to convert to an angle
     */
    public static int directionToAngle(Direction dir) {
        switch (dir) {
            case N:
                return 0;
            case NE:
                return 45;
            case E:
                return 90;
            case SE:
                return 135;
            case S:
                return 180;
            case SW:
                return 225;
            case W:
                return 270;
            case NW:
                return 315;
            default:
                return 0;
        }
    }

    /**
     * Takes a number as input and returns Direction
     *
     * @param num the number converted to a direction
     */
    public static Direction numToDir(int num) {
        switch (num) {
            case 1:
                return Direction.E;
            case 2:
                return Direction.N;
            case 3:
                return Direction.W;
            case 4:
                return Direction.S;
            default:
                return Direction.N;
        }
    }

    /**
     * Takes 2 directions and returns the direction of the second pos from the first
     *
     * @param pos1 the first reference pos
     * @param pos2 the second pos (to go to)
     * @return a direction from the first to the second.
     */
    public static Direction positionToDirection(Position pos1, Position pos2) {
        if (pos1.getX() < pos2.getX()) {           //X is increasing
            if (pos1.getY() < pos2.getY()) {       //Y is increasing
                return Direction.NE;
            } else if (pos1.getY() > pos2.getY()) {  //Y is decreasing
                return Direction.SE;
            } else {
                return Direction.E;
            }
        } else if (pos1.getX() > pos2.getX()) {      //X is decreasing
            if (pos1.getY() < pos2.getY()) {       //Y is increasing
                return Direction.NW;
            } else if (pos1.getY() > pos2.getY()) {  //Y is decreasing
                return Direction.SW;
            } else {
                return Direction.W;
            }
        } else {
            if (pos1.getY() < pos2.getY()) {      //Y is increasing
                return Direction.N;
            } else if (pos1.getY() > pos2.getY()) {  //Y is decreasing
                return Direction.S;
            } else {
                System.out.print("Tried to find a direction that does not exist!");
                return null;
            }
        }
    }

    /**
     * Returns if the direction is the same
     *
     * @param start - The start position
     * @param end   - The end position
     * @param dir   - The direction
     */
    static boolean isSameDirection(Position start, Position end, Direction dir) {
        switch (dir) {
            case N:
                if (start.getX() == end.getX() && start.getY() < end.getY()) {
                    return true;
                }
                break;
            case E:
                if (start.getX() < end.getX() && start.getY() == end.getY()) {
                    return true;
                }
                break;
            case S:
                if (start.getX() == end.getX() && start.getY() > end.getY()) {
                    return true;
                }
                break;
            case W:
                if (start.getX() > end.getX() && start.getY() == end.getY()) {
                    return true;
                }
                break;
            case NE:
                if (start.getX() < end.getX() && start.getY() < end.getY() && abs(start.getX() - end.getX()) == abs(start.getY() - end.getY())) {
                    return true;
                }
                break;
            case NW:
                if (start.getX() > end.getX() && start.getY() < end.getY() && abs(start.getX() - end.getX()) == abs(start.getY() - end.getY())) {
                    return true;
                }
                break;
            case SE:
                if (start.getX() < end.getX() && start.getY() > end.getY() && abs(start.getX() - end.getX()) == abs(start.getY() - end.getY())) {
                    return true;
                }
                break;
            case SW:
                if (start.getX() > end.getX() && start.getY() > end.getY() && abs(start.getX() - end.getX()) == abs(start.getY() - end.getY())) {
                    return true;
                }
                break;
            default:
                return false;
        }
        return false;
    }

    /**
     * returns the next position
     *
     * @param pos - Current position
     * @param dir - Current direction
     */
    public static Position getNextPos(Position pos, Direction dir) {
        switch (dir) {
            case N:
                return new Position(pos.getX(), pos.getY() + 1);
            case E:
                return new Position(pos.getX() + 1, pos.getY());
            case S:
                return new Position(pos.getX(), pos.getY() - 1);
            case W:
                return new Position(pos.getX() - 1, pos.getY());
            case NE:
                return new Position(pos.getX() + 1, pos.getY() + 1);
            case NW:
                return new Position(pos.getX() - 1, pos.getY() + 1);
            case SE:
                return new Position(pos.getX() + 1, pos.getY() - 1);
            case SW:
                return new Position(pos.getX() - 1, pos.getY() - 1);
            default:
                return pos;
        }

    }

    /**
     * turnIsValid
     * <p>
     * Checks if a turn valid from the current location,
     * mainly by seing if it collides with edges or islands
     * It is not affected by ships, as they could move, and
     * we are able to move onto ships in order to attack
     *
     * @param ship      that is turning
     * @param direction that should be checked
     * @return true if valid, false if invalid
     */
    public static boolean turnIsValid(Ship ship, Direction direction) {
        if (direction == null) { //The center square was probably clicked
            return false;
        }
        Position nextPos = getNextPos(ship.getLocation(), direction);
        boolean a = nextPos.isIsland();
        boolean b = nextPos.isEdge();
        return !(a || b);
    }
}