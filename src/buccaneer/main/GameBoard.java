package buccaneer.main;

import buccaneer.helpers.Position;
import buccaneer.helpers.PositionHelper;
import buccaneer.islands.FlatIsland;
import buccaneer.islands.PirateIsland;
import buccaneer.islands.TreasureIsland;
import buccaneer.ports.Bay;
import buccaneer.ports.Port;

import java.util.ArrayList;
import java.util.Random;

/**
 * The board for the game
 * Provides a 20 by 20 gameSquare array, as well as storing an array of buccaneer.ports, and links to each of
 * the Islands
 */
//5
public class GameBoard {
    private GameSquare[][] gameSquares;
    private ArrayList<Port> ports;
    private PirateIsland pirateIsland;
    private FlatIsland flatIsland;
    private TreasureIsland treasureIsland;
    private Bay mudBay;
    private Bay anchorBay;
    private Bay cliffCreek;

    /**
     * Constructor. Creates an array of squares (20x20).
     * Calls addSquares(), addPorts(), addIslands().
     */
    public GameBoard() {
        gameSquares = new GameSquare[20][20];
        ports = new ArrayList<>();
        addSquares();
        addPorts();
        addIslands();
        addBays();
    }

    private void addBays() {
        anchorBay = new Bay("Anchor Bay", new Position(20,1));
        mudBay = new Bay("Mud Bay", new Position(1,1));
        cliffCreek = new Bay("Cliff Creek", new Position(20,20));
    }


    /**
     * Moves a ship from one square to a new one;
     *
     */
    private void moveShip(Ship ship, GameSquare newSquare) {
        boolean attack = false;
        Position otherPlayer = PositionHelper.moveThroughPlayer(ship, newSquare.getPosition(), this);
        if (otherPlayer.equals(null));
            //Call GUI to ask if player wants to attack
            // If they do then change newSquare to the position of the other player

        if (newSquare.containsShip());
            attack = true;

        ship.setLocation(newSquare);
        newSquare.add(ship);

        ship.getSquare().remove(ship);

        if (attack == true);
            //Call attack method
    }

    void moveShip(Ship ship, Position newPos) {
        moveShip(ship, getSquareAt(newPos));
    }

    //Add to Game

    /**
     * Adds 400 squares to the array of GameSquares.
     */
    private void addSquares() {
        for (int x=0; x<20; x++){
            for (int y=0; y<20; y++){
                gameSquares[x][y] = new GameSquare(x + 1, y + 1, this);
            }
        }
    }

    /**
     * Adds Ports to the array.
     * There are two generic buccaneer.ports only for trading
     * and 4 HomePorts for players to store their buccaneer.treasure
     * but also for everyone to trade.
     */
    private void addPorts() {
        ports.add(new Port("Venice", getSquareAt(1, 7)));
        ports.add(new Port("London", getSquareAt(1, 14)));
        ports.add(new Port("Cadiz", getSquareAt(14, 20)));
        ports.add(new Port("Amsterdam", getSquareAt(20, 14)));
        ports.add(new Port("Marseilles", getSquareAt(20, 7)));
        ports.add(new Port("Genoa", getSquareAt(7, 1)));
    }

    /**
     * Adds three island:
     * PirateIsland,
     * FlatIsland,
     * TreasureIsland.
     */
    private void addIslands() {
        //TODO: Add Islands to the board
        pirateIsland = new PirateIsland();
        treasureIsland = new TreasureIsland();
        flatIsland = new FlatIsland(new Position(2, 16), new Position(4, 19));
        for (int pIx = 17; pIx <= 19; pIx++) {
            for (int pIy = 2; pIy <= 5; pIy++) {
                //Add the islands to gameSquares
            }
        }
    }

    Port getUnownedPort() {
        while (true) {
            //Note that we can only assigned the ports of London, Genoa, Marsellis and Candiz
            //The ports of Venice and amsterdam can not be owned
            int rnd = new Random().nextInt(ports.size());
            Port port = ports.get(rnd);
            if (port.getOwner() == null && !(port.getName().equals("Venice") || port.getName().equals("Amsterdam"))) {
                return port;
            }
        }
    }

    public ArrayList<Port> getPorts() {
        return ports;
    }
    public Port getPort(int portID) {
        return ports.get(portID);
    }

    public PirateIsland getPirateIsland() {
        return pirateIsland;
    }

    public FlatIsland getFlatIsland() {
        return flatIsland;
    }

    public TreasureIsland getTreasureIsland() {
        return treasureIsland;
    }

    /**
     * Gets the gameSquare at the specified position
     *
     * @return gameSquare
     */

    public GameSquare getSquareAt(int x, int y) {
        return getSquareAt(new Position(x, y));
    }
    GameSquare getSquareAt(Position pos) {
        int x = pos.getX() - 1;
        int y = pos.getY() - 1;
        return gameSquares[x][y];
    }

    public Bay getMudBay() {
        return mudBay;
    }

    public Bay getAnchorBay() {
        return anchorBay;
    }

    public Bay getCliffCreek() {
        return cliffCreek;
    }
}
