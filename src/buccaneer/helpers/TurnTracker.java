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

    /**
     * The order of ports (with the first one assinged to the first playing player)
     * London, Genoa, Marsiellis Candiz
     */
    public TurnTracker() {
        this.players = new Player[4];
        this.turn = 0;
        //We need a way of getting london to go first, and then go in a specific order around the game board
    }

    public int getCurrentTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public void nextTurn() {
        turn = turn + 1;
        System.out.println(turn);
        System.out.println(getCurrentPlayer());
    }


    public Player getCurrentPlayer() {
        return players[turn % 4];
    }

    /**
     * Add the players in the corrrect order so that London goes first and Cadiz goes last
     *
     * @param p the player to be added into the turn tracker.
     */
    public void addPlayer(Player p) {
        switch (p.getPort().getName()) {
            case "London":
                players[0] = p;
                break;
            case "Genoa":
                players[1] = p;
                break;
            case "Marseilles":
                players[2] = p;
                break;
            case "Cadiz":
                players[3] = p;
                break;
        }
    }
}
