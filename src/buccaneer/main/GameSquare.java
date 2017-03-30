package buccaneer.main;

import buccaneer.helpers.Position;
import buccaneer.islands.Island;
import buccaneer.ports.Port;

import java.util.ArrayList;

/**
 * A single square on the board
 * Can hold a game object.
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


    void remove(Ship ship) {
        this.squareObjects.removeIf(e -> (e.equals(ship)));
    }

    public boolean containsShip(){
        for (GameObject o : this.squareObjects){
            if (o instanceof Ship){
                return true;
            }
        }

        return false;
    }

    //TODO: Javadoc

    public void add(GameObject o) {
        squareObjects.add(o);
    }

    public void remove(GameObject o) {
        squareObjects.remove(o);
    }

    public GameBoard getBoard() {
        return board;
    }

    public void setBoard(GameBoard board) {
        this.board = board;
    }
}
