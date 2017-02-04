package helpers;

import main.Player;

/**
 * Created by aaw13 on 02/02/2017.
 * Keeps track of the current turn
 */
//TODO: Review for extra functionality

public class TurnTracker {
    int round;
    int turn;
    Player players[];

    int getCurrentTurn() {
        return turn;
    }

    void setTurn(int turn) {
        this.turn = turn;
    }

    void nextTurn() {
        turn = +1;
    }

    Player getCurrentPlayer(){
        return null;
    }
}
