package main;

import islands.FlatIsland;
import islands.PirateIsland;
import islands.TreasureIsland;
import ports.Port;
import ports.HomePort;

/**
 * Created by aaw13 on 02/02/2017.
 */
//TODO: Implement Basic GameBoard with arrays
//TODO: Add ports to GameBoard
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
        pirateIsland = new PirateIsland();
        flatIsland = new FlatIsland();
        treasureIsland = new TreasureIsland();
        
        addSquares();
        addPorts();
        addIslands();
    }
    
    /**
     * Adds 400 squares to the array of GameSquares.
     */
    private void addSquares(){
        //TODO: Add squares to gameboard
    }
    
    /**
     * Adds Ports to the array.
     * There are two generic ports only for trading
     * and 4 HomePorts for players to store their treasure
     * but also for everyone to trade.
     */
    private void addPorts(){
        //TODO: Add ports to gameboard
    }
    
    /**
     * Adds three island:
     * PirateIsland,
     * FlatIsland,
     * TreasureIsland.
     */
    private void addIslands(){
    	pirateIsland = new PirateIsland();
    	flatIsland = new FlatIsland();
    	treasureIsland = new TreasureIsland();
    };
}
