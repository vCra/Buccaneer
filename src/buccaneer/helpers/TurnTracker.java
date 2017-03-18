package buccaneer.helpers;

import buccaneer.main.Player;

/**
 * Created by aaw13 on 02/02/2017.
 * Keeps track of the current turn
 * @author awalker
 * @version 1
 */
public class TurnTracker {
    private int turn;
    private Player players[];

    /**
     * The order of ports (with the first one assinged to the first playing player)
     * London, Genoa, Marsiellis Candiz
     */
    public TurnTracker() {
        this.players = new Player[4];
        this.turn = 0;
        //We need a way of getting london to go first, and then go in a specific order around the game board
    }

    /**
     * Gets an integer of the current turn of the game
     * @return the current round number
     */
    public int getCurrentTurn() {
        return turn;
    }

    /**
     * Progresses the game onto the next round, and displays a message in the console
     */
    public void nextTurn() {
        turn = turn + 1;
        System.out.println("It is turn number "+turn);
        System.out.println("It is now " + getCurrentPlayer().getName()+"'s turn");
    }

    /**
     * Gets the player of who's turn it currently is
     * @return the current player
     */
    public Player getCurrentPlayer() {
        //return players[0]; //Use this for debugging
        return players[turn % 4]; //Use this for normal use
    }

    /**
     * Add the players in the correct order so that London goes first and Cadiz goes last
     *
     * @param p the player to be added into the turn tracker.
     */
    public void addPlayer(Player p) {
        switch (p.getPort().getName()) {
            case "London":
                players[1] = p;
                break;
            case "Genoa":
                players[2] = p;
                break;
            case "Marseilles":
                players[3] = p;
                break;
            case "Cadiz":
                players[0] = p;
                break;
        }
    }
}
