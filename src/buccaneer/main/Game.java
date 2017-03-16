package buccaneer.main;

import buccaneer.helpers.DirectionHelper;
import buccaneer.helpers.Position;
import buccaneer.helpers.PositionHelper;
import buccaneer.helpers.TurnTracker;
import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

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
        //Something to make ports plz
        for (Player p : players){
            //Ports have not been made yet...
            //p.setPort((HomePort) board.getUnownedPort());
        }
    }

    /**
     * Sets up the game and starts it,
     * then passes to the TurnTracker.
     */
    void begin() {
        //TODO:
        //MakePorts
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
        int i = 1;
        for (Player p : players) {
            try {
                Ship s = new Ship(p);
                ClassLoader classLoader = getClass().getClassLoader(); //allows us to use resources
                File file = new File(classLoader.getResource("data/ships.csv").getFile());
                FileReader csvFile = new FileReader(file);
                CSVReader csvReader = new CSVReader(csvFile); //Uses the file reader in lib/opencsv-x.x.jar
                String[] nextLine;
                nextLine = csvReader.readNext();
                s.setShipPhoto(nextLine[1]);
                Position pos = new Position(1, 1);
                s.setinitalLocation(board.getSquareAt(pos));
                p.setPlayerShip(s);
            } catch (IOException e) {
                e.printStackTrace();
            }
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
        System.out.println("Current Location - " + currentPos);
        if (PositionHelper.shouldTurn(ship, pos)){
            ship.setDirection(DirectionHelper.positionToDirection(currentPos, pos));
        }
        else{
            if (PositionHelper.moveIsValid(currentPos, pos)){
                parent.moveShip(ship, currentPos, pos);
                board.moveShip(ship, pos);


                parent.highlight(PositionHelper.getAvailableMoves(ship.getLocation(), ship.getDirection()));
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

    void addPorts() {
    }


}