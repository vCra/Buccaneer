package main;

import ports.HomePort;

/**
 * Created by aaw13 on 02/02/2017.
 */
//TODO: Implement Basic GameBoard with arrays
//TODO: Add ports to GameBoard
 class GameBoard {
    private GameSquare[][] gameSquares;
    private HomePort[] ports;
    GameBoard() {
        gameSquares = new GameSquare[20][20];
        addSquares();
        addPorts();
        addIslands();
    }
    private void addPorts(){
        //TODO: Add ports to gameboard
    }
    private void addSquares(){
        //TODO: Add squares to gameboard
    }
    private void addIslands(){
        //TODO: Add Islands to gameboard
    };

}
