package buccaneer.helpers;

import buccaneer.islands.Island;
import buccaneer.main.GameBoard;
import buccaneer.ports.Bay;

import java.util.ArrayList;

/**
 * @author AAW13
 * @version 1.0
 * @DirectionHelper.java Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 * <p>
 * Stores a position of an object, using X and Y coordinates as integers.
 */
public class Position {
    private int x;
    private int y;


    public Position(int x, int y) {
        set(x, y);
    }

    /**
     * Returns y coordinate
     *
     * @return y
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the y coordinate
     *
     * @param y - The y coordinate that is being set
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Sets both the y and x
     *
     * @param x - The x coordinate that is being set
     * @param y - The y coordinate that is being set
     */
    public void set(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Return x coordinate
     *
     * @return x
     */
    public int getX() {
        return x;
    }

    /**
     * Sets the x coordinate
     *
     * @param x - The x coordinate being set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Checks if the position is an Island
     *
     * @return true if next to or an Island, false if not
     */
    public boolean isIsland() {
        return PositionHelper.isIsland(this);
    }

    public boolean isBay(Bay b) {
        return b.getPosition().equals(this);
    }

    public boolean isNextToOrOnIsland(Island island) {
        if (this.getX() >= island.getStartPos().getX() - 1 && this.getX() <= island.getEndPos().getX() + 1) {
            if (this.getY() >= island.getStartPos().getY() - 1 && this.getY() <= island.getEndPos().getY() + 1) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the position is next to or an Island for all the Islands
     *
     * @param is - ArrayList of Islands
     * @return - true if next to or an Island, false if not
     */
    public boolean isNextToOrOnAnyIsland(ArrayList<Island> is) {
        for (Island i : is) {
            if (isNextToOrOnIsland(i)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the position is a port
     *
     * @param board - The game board
     * @return PositionHelper method isPort
     * @see PositionHelper
     */
    public boolean isPort(GameBoard board) {
        return PositionHelper.isPort(this, board);
    }

    /**
     * Checks if the position is part of the edge
     *
     * @return PositionHelper method isEdge
     * @see PositionHelper
     */

    public boolean isEdge() {
        return PositionHelper.isEdge(this);
    }

    /**
     * Checks if the position contains a ship
     *
     * @param board - The game board
     * @return PostitionHelper method isShip
     */
    public boolean containsShip(GameBoard board) {
        return PositionHelper.isShip(this, board);
    }

    @Override
    /**
     * Checks if the positions are the same
     * @param o - Generic Object
     */
    public boolean equals(Object o) {
        boolean result = false;
        if (o instanceof Position) {
            Position o2 = (Position) o;
            result = (this.getX() == o2.getX() && this.getY() == o2.getY());
        }
        return result;
    }

    @Override
    /**
     * A hash method
     */
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    /**
     * To string method
     * @return x and coordinate
     */
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

}
