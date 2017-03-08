package buccaneer.main;

import buccaneer.helpers.DirectionHelper;
import buccaneer.helpers.Position;
import buccaneer.helpers.PositionHelper;
import buccaneer.helpers.TurnTracker;
import buccaneer.ports.Port;
import javafx.geometry.Pos;

import java.util.ArrayList;

/**
 * Handles all elements of a game, including gameboards, players and turn trackers.
 */
class Game {
    private GameBoard board;
    private Player[] players;
    private TurnTracker turns;
    private GameApp parent;

    //private CardDeck<ChanceCard> chanceCards;
    // chanceCards are now stored on Treasure Island
    //private CardDeck<CrewCard> crewCards;
    // Crew cards now are stored on pirate island
    Game(GameApp app) {
        this.board = new GameBoard();
        this.players = new Player[4];
        this.parent = app;
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

    private void fakeBegin() {
        onUserNameInput("Alan", "Bob", "Charlie", "Dave");
        for (Player p : players){
            p.setPort((HomePort) board.getUnownedPort());
        }
    }

    /**
     * Sets up the game and starts it,
     * then passes to the TurnTracker.
     */
    public void begin() {
        //TODO:
        fakeBegin();
        createPlayers();
        //Create players
        //Deal buccaneer.cards to players
        //Deal buccaneer.cards to buccaneer.ports
        //Add buccaneer.treasure to buccaneer.ports
    }

    private void createPlayers() {
        //TODO: ask users for their usernames
        //assignUsersPort();
        for (Player p : players){
            Ship s = new Ship(p);
            Position pos = new Position(1,1);
            s.setinitalLocation(board.getSquareAt(pos));
            p.setPlayerShip(s );
        }
    }

    private void dealCards() {
        //TODO: deal buccaneer.cards to players, and then
        // to the buccaneer.ports
    }

    private void assignUsersPort() {
        for (Player p : players){
            //p.setPort();
        }
    }

    private void dealTreasure() {
        //TODO: fill up the value in the trade buccaneer.ports up to 7
    }


    /**
     * When the gui has a square clicked (usually when its a players turn)
     * Move or Rotate the ship.
     * @param pos the Position that was clicked.
     */
    void onSquareClick(Position pos){
        //Possibly have some form of state which checks if clicking on squares at this point in time is valid.
        //Ship ship = turns.getCurrentPlayer().getPlayerShip();
        Ship ship = players[0].getPlayerShip();
        Position currentPos = ship.getLocation();
        if (PositionHelper.shouldTurn(ship, pos)){
            ship.setDirection(DirectionHelper.positionToDirection(currentPos, pos));
        }
        else{
            if (PositionHelper.moveIsValid(currentPos, pos)){
                parent.moveShip(ship, currentPos, pos);
            }
            else{
                //return a message saying that the current move is not valid
            }
        }
    }

    private void onUserNameInput(String name1, String name2, String name3, String name4) {
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