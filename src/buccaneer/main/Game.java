package buccaneer.main;

import buccaneer.helpers.Position;
import buccaneer.helpers.TurnTracker;
import buccaneer.ports.Port;

import java.util.ArrayList;

/**
 * Handles all elements of a game, including gameboards, players and turn trackers.
 */
class Game {
    private GameBoard board;
    private Player[] players;
    private TurnTracker turns;

    //private CardDeck<ChanceCard> chanceCards;
    // chanceCards are now stored on Treasure Island
    //private CardDeck<CrewCard> crewCards;
    // Crew cards now are stored on pirate island
    Game() {
        this.board = new GameBoard();
        this.players = new Player[4];
    }

    Player getPlayer(int player) {
        return players[player];
    }

    private void setPlayer(Player player) {
        this.players[player.getId() - 1] = player;
    }

    public GameBoard getBoard() {
        return board;
    }

    public void setBoard(GameBoard board) {
        this.board = board;
    }

    public void fakeBegin() {
        onUserNameInput("Alan", "Bob", "Charlie", "Dave");
    }

    /**
     * Sets up the game and starts it,
     * then passes to the TurnTracker.
     */
    public void begin() {
        //TODO:
        //Create players
        //Deal buccaneer.cards to players
        //Deal buccaneer.cards to buccaneer.ports
        //Add buccaneer.treasure to buccaneer.ports
    }

    private void createPlayers() {
        //TODO: ask users for their usernames
        // create Player objects
        //  assign players to buccaneer.ports
        //   by getting the array of buccaneer.ports from the gameboard
        //    and assigning the players to indexes 0-3
    }

    private void dealCards() {
        //TODO: deal buccaneer.cards to players, and then
        // to the buccaneer.ports
    }

    private void assignUsersPort() {

    }

    private void dealTreasure() {
        //TODO: fill up the value in the trade buccaneer.ports up to 7
    }

    void onLoad() {

    }

    void onSquareClick(Position pos){

    }

    void onUserNameInput(String name1, String name2, String name3, String name4) {
        setPlayer(new Player(1, name1));
        setPlayer(new Player(2, name2));
        setPlayer(new Player(3, name3));
        setPlayer(new Player(4, name4));
    }

    void onGameBegin() {
        //Start taking turns, starting with london.
        turns = new TurnTracker(players);

    }



}