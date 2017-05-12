package buccaneer.enumData;

/**
 * @author AAW13
 * @version 1.0
 * @GameState.java 25/03/2017
 * <p>
 * Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 * <p>
 * Keeps track of the current state of the game.
 * For turning please use spin, as this can get confused with a turn/round of the game.
 */

public enum GameState {
    SPIN, //A ship has moved and is about to Spin in a direction
    SPINANDMOVE, //A Ship is at a port and can spin and move in a direction
    SPINORMOVE, //A ship can spin in a direction, or move and then spin
    ATTACK,
    TRADE
}
