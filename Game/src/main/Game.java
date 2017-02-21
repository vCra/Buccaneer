package main;

import cards.ChanceCard;
import cards.CrewCard;
import helpers.CardDeck;
import helpers.Position;
import helpers.TurnTracker;
import ports.Port;

import java.util.ArrayList;

/**
 * Handles all elements of a game, including gameboards, players and turn trackers.
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
    }

    Player getPlayer(int player) {
        return players[player];
    }

    private void setPlayer(Player player) {
        this.players[player.getId()-1] = player;
    }

    public GameBoard getBoard() {
        return board;
    }

    public void setBoard(GameBoard board) {
        this.board = board;
    }

    public void fakeBegin(){
        onUserNameInput("Alan", "Bob", "Charlie", "Dave");
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
    }

    private void createPlayers()
    {
        //TODO: ask users for their usernames
        // create Player objects
        //  assign players to ports
        //   by getting the array of ports from the gameboard
        //    and assigning the players to indexes 0-3
    }

    private void dealCards()
    {
        //TODO: deal cards to players, and then
        // to the ports
    }

    private void assignUsersPort(){

    }

    private void dealTreasure()
    {
        //TODO: fill up the value in the trade ports up to 7
    }

    void onLoad(){

    }

    void onUserNameInput(String name1, String name2, String name3, String name4){
        setPlayer(new Player(1,name1));
        setPlayer(new Player(2,name2));
        setPlayer(new Player(3,name3));
        setPlayer(new Player(4,name4));
    }

    void onGameBegin(){
        //Start taking turns, starting with london.
        turns = new TurnTracker(players);

    }

    ArrayList<GameSquare> getValidMoves(){
        return null;
    }

    boolean goneThroughOtherPlayer(GameSquare start, GameSquare end){
        return false;
    }

    boolean canAttack(Player player){
        return false;
    }
    boolean isNextToTreasureIsland(Player player){
        return false;
    }
    boolean isNextToFlatIsland(Player player){
        return false;
    }
    Port isNextToPort(Player player){
        return null;
    }
    boolean isNextToHomePort(Player player){
        return false;
    }




}