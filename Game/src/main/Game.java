package main;

import cards.ChanceCard;
import cards.CrewCard;
import helpers.CardDeck;
import helpers.TurnTracker;

/**
 * Created by awalker on 04/02/2017.
 */
class Game {
    private GameBoard board = new GameBoard();
    private Player[] players = new Player[4];
    private TurnTracker turns;
    private CardDeck<ChanceCard> chanceCards;
    private CardDeck<CrewCard> crewCards;


    public Player getPlayer(int player) {
        return players[player];
    }

    public void setPlayer(int playerID, Player player) {
        this.players[playerID] = player;
    }

    public GameBoard getBoard() {
        return board;
    }

    public void setBoard(GameBoard board) {
        this.board = board;
    }




    public void begin(){
        //Create board
        //Create players
        //Deal cards to players
        //Deal cards to ports
        //Add treasure to ports
        //Assign players to ports
    }

}
