package buccaneer.main;

import buccaneer.helpers.Position;
import buccaneer.islands.Island;
import buccaneer.ports.Port;

import java.util.ArrayList;

/**
 * @GameSquare.java 02/02/2017
 *
 * Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 *
 * A single square on the board
 * Can hold a game object.
 *
 * @author AAW13
 * @version 1.0
 */
/**
 *
 */
public class GameSquare {

    private Position position;
    private ArrayList<GameObject> squareObjects;
    private GameBoard board;

    /**
     * Constructor.
     * Takes two parameters which are used to create
     * an object of class Position.
     *
     * @param x The x coordinate
     * @param y the Y coordinate
     */
    public GameSquare(int x, int y, GameBoard board) {
        this(new Position(x, y));
        this.board = board;
        squareObjects = new ArrayList<>();
    }

    /**
     * Constructor.
     * Takes one parameters Position which is used to
     * set the position of the Gamequare
     *
     * @param position the Location of the gameSquare
     */
    public GameSquare(Position position) {
        this.position = position;
    }

    /**
     * Getter for position.
     *
     * @return position
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Getter for port.
     *
     * @return port
     */
    public Port getPort() {
        for (GameObject o : this.squareObjects) {
            if (o instanceof Port) {
                return (Port) o;
            }
        }
        return null;
    }

    /**
     * Setter for port.
     *
     * @param port becomes this.port
     */
    public void setPort(Port port) {
        if (getPort() == null) {
            squareObjects.add(port);
        } else {
            System.err.println("Tried to add an additional port to a gameSquare.");
        }
    }

    /**
     * Getter for island.
     *
     * @return island
     */
    public Island getIsland() {
        for (GameObject o : this.squareObjects) {
            if (o instanceof Island) {
                return (Island) o;
            }
        }
        return null;
    }

    /**
     * Setter for island
     *
     * @param island becomes this.island
     */
    public void setIsland(Island island) {
        if (getIsland() == null) {
            squareObjects.add(island);
        } else {
            System.err.println("Tried to add an additional island to a gameSquare.");
        }
    }


    /**
     * Removes the inputted ship from the game square
     * @param ship - Current ship
     */
    void remove(Ship ship) {
        this.squareObjects.removeIf(e -> (e.equals(ship)));
    }

    /**
     * Checks if the square contains a ship
     * @return true if there is a ship, false if not
     */
    public boolean containsShip(){
        for (GameObject o : this.squareObjects){
            if (o instanceof Ship){
                return true;
            }
        }
        return false;
    }

    //TODO: Javadoc
    /**
     * Returns player that owns the ship
     * @return player if there is a ship in the game square and null if not
     */
    public Player getPlayer() {
        for (GameObject o : this.squareObjects){
            if (o instanceof Ship){
                return ((Ship) o).getOwner();
            }
        }
        return null;
    }
    /**
     * Adds a game object to the square
     * @param o - the game object being added
     */
    public void add(GameObject o) {
        squareObjects.add(o);
    }
    /**
     * Removes the game object from the game square
     * @param o - the game object being removed
     */
    public void remove(GameObject o) {
        squareObjects.remove(o);
    }

    /**
     * Returns the game board
     * @return the game board
     */
    public GameBoard getBoard() {
        return board;
    }

    /**
     * Sets the game board
     * @param board - The game board being set
     */
    public void setBoard(GameBoard board) {
        this.board = board;
    }
}
