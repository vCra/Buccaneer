package main;

import cards.ChanceCard;
import cards.CrewCard;
import helpers.CardDeck;
import helpers.TurnTracker;

/**
 * Created by awalker on 04/02/2017.
 */
class Game {
    GameBoard board = new GameBoard();
    Player[] players = new Player[4];
    private TurnTracker turns;
    private CardDeck<ChanceCard> chanceCards;
    private CardDeck<CrewCard> crewCards;

    public void begin(){
        //Create board
        //Create players
        //Deal cards to players
        //Deal cards to ports
        //Add treasure to ports
        //Assign players to ports
    }

}
