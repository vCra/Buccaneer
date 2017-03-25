package buccaneer.helpers;

/**
 * GameState.java
 *
 * @author awalker
 *         Keeps track of the current state of the game.
 *         For turning please use spin, as this can get confused with a turn/round of the game.
 * @version 0.1
 */
public enum GameState {
    SPIN, //A ship has moved and is about to Spin in a direction
    SPINANDMOVE, //A Ship is at a port and can spin and move in a direction
    SPINORMOVE, //A ship can spin in a direction, or move and then spin
    ATTACK,
    TRADE
}
