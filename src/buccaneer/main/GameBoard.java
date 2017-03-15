package buccaneer.main;

import buccaneer.helpers.Position;
import buccaneer.islands.FlatIsland;
import buccaneer.islands.PirateIsland;
import buccaneer.islands.TreasureIsland;
import buccaneer.ports.HomePort;
import buccaneer.ports.Port;

import java.util.Random;

/**
 * The board for the game
 * Provides a 20 by 20 gameSquare array, as well as storing an array of buccaneer.ports, and links to each of
 * the Islands
 */
//5
//TODO: Add buccaneer.ports to GameBoard
class GameBoard {
    private GameSquare[][] gameSquares;
    private Port[] ports;
    private PirateIsland pirateIsland;
    private FlatIsland flatIsland;
    private TreasureIsland treasureIsland;

    /**
     * Constructor. Creates an array of squares (20x20).
     * Calls addSquares(), addPorts(), addIslands().
     */
    public GameBoard() {
        gameSquares = new GameSquare[20][20];
        ports = new Port[6];

        addSquares();
        addPorts();
        addIslands();
    }


    /**
     * Moves a ship from one square to a new one;
     *
     * @return the Ships new GameSquare
     * TODO: implement moving ships
     */
    private GameSquare moveShip(Ship ship, GameSquare newSquare) {
        ship.getSquare().remove(ship);
        ship.setLocation(newSquare);

        return null;
    }

    GameSquare moveShip(Ship ship, Position newPos) {
        return moveShip(ship, getSquareAt(newPos));
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
        //TODO: Add buccaneer.ports to gameboard,
        // first four are the homeports,
        // currently without owners
    }

    /**
     * Adds three island:
     * PirateIsland,
     * FlatIsland,
     * TreasureIsland.
     */
    private void addIslands() {
        //TODO: Add Islands to the board
        pirateIsland = new PirateIsland(new Position(17, 2), new Position(19, 5));
        treasureIsland = new TreasureIsland(new Position(9, 9), new Position(12, 12));
        flatIsland = new FlatIsland(new Position(2, 16), new Position(4, 19));
        for (int pIx = 17; pIx <= 19; pIx++) {
            for (int pIy = 2; pIy <= 5; pIy++) {

            }
        }
    }

    Port getUnownedPort() {
        Random randomizer = new Random();
        while (true) {
            int rnd = new Random().nextInt(ports.length);
            Port port = ports[rnd];
            HomePort port2 = (HomePort) port;
            if (port2 != null){
                return port2;
            }
        }
    }
    public Port getPorts(int portID) {
        return ports[portID];
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
    GameSquare getSquareAt(Position pos) {
        int x = pos.getX() - 1;
        int y = pos.getY() - 1;
        return gameSquares[x][y];
    }
}
