package main;

import cards.ChanceCard;
import cards.CrewCard;
import helpers.CardDeck;
import helpers.Position;
import helpers.TurnTracker;

/**
 * Created by awalker on 04/02/2017.
 */
class Game {
    private GameBoard board;
    private Player[] players;
    private TurnTracker turns;
    private CardDeck<ChanceCard> chanceCards;
    private CardDeck<CrewCard> crewCards;

    Game() {
        this.board = new GameBoard();
        this.players = new Player[4];
        this.turns = new TurnTracker();
        this.setPlayer(1, new Player(1, "bob"));
        this.getPlayer(1).getPlayerShip().setLocation(new Position(5,5));
    }

    Player getPlayer(int player) {
        return players[player];
    }

    void setPlayer(int playerID, Player player) {
        this.players[playerID] = player;
    }

    public GameBoard getBoard() {
        return board;
    }

    public void setBoard(GameBoard board) {
        this.board = board;
    }


    /**
     * Sets up the game and starts it,
     * then passes to the TurnTracker.
     */
    public void begin(){
        //TODO:
        //Create players
        //Deal cards to players
        //Deal cards to ports
        //Add treasure to ports
        //Assign players to ports
    }

    private void dealCards()
    {
        //TODO: deal cards to players, and then
        // to the ports
    }

    private void dealTreasure()
    {
        //TODO: fill up the value in the trade ports up to 7
    }


}