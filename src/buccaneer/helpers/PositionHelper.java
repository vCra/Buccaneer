package buccaneer.helpers;

import buccaneer.cards.CrewCard;
import buccaneer.enumData.Direction;
import buccaneer.main.GameBoard;
import buccaneer.main.GameSquare;
import buccaneer.main.Player;
import buccaneer.main.Ship;
import buccaneer.ports.Port;
import org.junit.Test;

import java.util.ArrayList;

import static buccaneer.enumData.CardColor.Black;
import static buccaneer.enumData.CardColor.Red;
import static buccaneer.enumData.Direction.*;
import static buccaneer.helpers.DirectionHelper.isSameDirection;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @PositionHelper.java
 *
 * Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 *
 * A collection of static methods that can help with positions, such as checking if a position is
 * an island, getting grid IDs from Positions etc...
 * @author aaw13
 * @version 1.0
 */
public class PositionHelper {
    /**
     *  Given the ship returns an ArrayList of possible moves
     *  that a ship can take.
     * @param s - Current ship
     * @return the ArrayList of possible moves
     */
    public static ArrayList<Position> getAvailableMoves(Ship s) {
        ArrayList<Position> list = new ArrayList<>();
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
    /**
     * Returns the available moves from a port
     * @param s - The current ship
     */
    public static ArrayList<Position> getAvailablePortMoves(Ship s) {
        ArrayList<Position> list = new ArrayList<>();
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
     * Returns if the location contains a port
     * @param pos - Current position
     * @param board - The game board
     * @return true if the location is a port
     */
     static boolean isPort(Position pos, GameBoard board){
        for (Port p : board.getPorts()){
            if (p.getLocation().equals(pos)) {
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
     *
     * @param x the x value to check
     * @param y the y value to check
     * @return true if it is an island
     */
    private static boolean isIsland(int x, int y) {
        return (isFlatIsland(x,y)||isPirateIsland(x,y)||isTreasureIsland(x,y));
    }

    public static boolean isFlatIsland(int x, int y){
        if (x >= 2 && x <= 4) {
            if (y >= 16 && y <= 19) {
                return true;
            }
        }
        return false;
    }

    public static boolean isPirateIsland(int x, int y){
        if (x >= 17 && x <= 19) {
            if (y >= 2 && y <= 5) {
                return true;
            }
        }
        return false;
    }

    public static boolean isTreasureIsland(int x, int y) {
        if (x >= 9 && x <= 12) {
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
    public static boolean isShip(Position position, GameBoard board){
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
     * @param pos the end pos
     * @return true if the move is valid
     */
    public static boolean moveIsValid(Ship ship, Position pos) {
        return PositionHelper.getAvailableMoves(ship).contains(pos);
    }

    /**
     * Checks if the move from the port is valid
     * @param ship - The current ship
     * @param pos2 - The position the ship is moving to
     * @return
     */
    public static boolean moveFromPortIsValid(Ship ship, Position pos2){
        return PositionHelper.getAvailablePortMoves(ship).contains(pos2);
    }

    public static boolean moveIsValid(Position pos, GameBoard gameBoard) {
        return !isIsland(pos) && !isEdge(pos) && !isShip(pos, gameBoard);
    }
    /**
     *  Given position returns a GridID of the GameSquare in the Grid.
     * @param pos - The position that is
     * @return
     */
    public static int positionToGridID(Position pos){
        return ((pos.getX()-1)+((20-pos.getY())*20));
    }

    /**
     * Given coordinates x and y creates a Position object.
     * @param x - x coordinate
     * @param y - y coordinate
     * @return new Position
     */
    public static Position gridChange(int x, int y) {
        return new Position(x + 1, 20 - (y));
    }

    /**
     * Checks if given positions are next to each other.
     * @param pos1 - 1st position
     * @param pos2 - 2nd position
     * @return isPlusOrMinus method
     */
    public static boolean isNextTo(Position pos1, Position pos2) {
        return isPlusOrMinus(pos1.getX(), pos2.getX()) && isPlusOrMinus(pos1.getY(),pos2.getY());
    }

    /**
     * Checks if the inputted integers are plus or minus each other
     * @param a - 1st integer
     * @param b - 2nd integer
     * @return a==b || a==(b-1) || a==(b+1)
     */
    private static boolean isPlusOrMinus(int a, int b){
        return a == b || a == (b - 1) || a == (b + 1);
    }

    /**
     * If the ship has passed though another player then returns the position of that other player
     * @param s The Ship moving
     * @param endPos The ships intended location
     * @param board The game board
     * @return Returns the position of the other ship that has been moved through
     */
    public static ArrayList<Position> moveThroughPlayer(Ship s, Position endPos, GameBoard board) {
        Position currentPos = s.getLocation();
        ArrayList<Position> postions = new ArrayList<>();

        while (!currentPos.equals(endPos)) {
            currentPos = DirectionHelper.getNextPos(currentPos, s.getDirection());
            if (currentPos.equals(endPos)) {

            } else {
                if (currentPos.isIsland() || currentPos.isEdge()) {
                    break;
                } else if (currentPos.containsShip(board)) {
                    postions.add(currentPos);
                }
            }
        }
        return postions;
    }

    /**
     * The total distance traveled from one position to another
     * @param pos1 - First position
     * @param pos2 - Second position
     * @return the distance of pos1 to pos2
     * @see Math
     */
    public static int distanceTraveled(Position pos1, Position pos2){
        return Math.max(Math.abs(pos1.getX() - pos2.getX()), Math.abs(pos1.getY() - pos2.getY()));
    }
}
