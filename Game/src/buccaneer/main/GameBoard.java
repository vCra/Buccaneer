package buccaneer.main;

import buccaneer.helpers.Position;
import buccaneer.islands.FlatIsland;
import buccaneer.islands.PirateIsland;
import buccaneer.islands.TreasureIsland;
import buccaneer.ports.Port;

/**
 * The board for the game
 * Provides a 20 by 20 gameSquare array, as well as storing an array of buccaneer.ports, and links to each of
 * the Islands
 */
//TODO: Implement Basic GameBoard with arrays
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
     * @return the Ships new GameSquare
     * TODO: implement moving ships
     */
    GameSquare moveShip(Ship ship, GameSquare oldSquare, GameSquare newSquare){
        return null;
    }
    GameSquare moveShip(Ship ship, Position oldPos, Position newPos){
        return moveShip(ship, getSquareAt(oldPos), getSquareAt(newPos));
    }

    /**
     * Adds 400 squares to the array of GameSquares.
     */
    private void addSquares(){
        //TODO: Add squares to gameboard
    }
    
    /**
     * Adds Ports to the array.
     * There are two generic buccaneer.ports only for trading
     * and 4 HomePorts for players to store their buccaneer.treasure
     * but also for everyone to trade.
     */
    private void addPorts(){
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
    private void addIslands(){
        //TODO: Add Islands to the board
    };

    /**
     * Gets the gameSquare at the specified position
     * @return gameSquare
     * TODO: implement getting gameSquares
     */
    private GameSquare getSquareAt(Position pos){
        return null;
    }
}
