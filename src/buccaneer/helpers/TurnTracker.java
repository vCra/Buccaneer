package buccaneer.helpers;

import buccaneer.main.Player;

/**
 * Created by aaw13 on 02/02/2017.
 * Keeps track of the current turn
 */
//TODO: Review for extra functionality

public class TurnTracker {
    private int round;
    private int turn;
    private Player players[];
    private int[] playerOrder;

    public TurnTracker(Player[] players) {
        this.players = players;
        this.turn = 1;
        //We need a way of getting london to go first, and then go in a specific order around the game board
    }

    public int getCurrentTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public void nextTurn() {
        if (turn % 4 == 0) {
            round = +1;
            turn = 1;
        }
        turn = +1;

    }


    public Player getCurrentPlayer() {
        return players[1];
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }
}
