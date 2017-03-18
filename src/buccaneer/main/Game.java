package buccaneer.main;

import buccaneer.helpers.DirectionHelper;
import buccaneer.helpers.Position;
import buccaneer.helpers.PositionHelper;
import buccaneer.helpers.TurnTracker;
import buccaneer.ports.Port;
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

    private Player getPlayer(int player) {
        return players[player - 1];
    }

    private void setPlayer(Player player) {
        this.players[player.getId() - 1] = player;
    }

    /**
     * Sets up the game and starts it,
     * then passes to the TurnTracker.
     */

    private void createPlayers() {
        //TODO: ask users for their usernames
        assignUsersPort();
        CSVReader csvReader;
        int i = 1;
        ClassLoader classLoader = getClass().getClassLoader(); //allows us to use resources
        try {
            File file = new File(classLoader.getResource("data/ships.csv").getFile());
            FileReader csvFile = new FileReader(file);
            csvReader = new CSVReader(csvFile); //Uses the file reader in lib/opencsv-x.x.jar
            for (Player p : players) {
                Ship s = p.getPlayerShip();
                String[] nextLine;
                nextLine = csvReader.readNext();
                s.setShipPhoto(nextLine[1]);
                Position pos = new Position(1, 1);
                s.setinitalLocation(board.getSquareAt(pos));
                p.setPlayerShip(s);
                turns.addPlayer(p);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void dealCards() {
        //TODO: deal buccaneer.cards to players, and then
        // to the buccaneer.ports
    }

    private void assignUsersPort() {
        for (Player player : players) {
            Port port = board.getUnownedPort();
            port.setOwner(player);
            player.setPort(port);
            player.getPlayerShip().setDirection(port.getWaterFace());
        }
    }

    private void dealTreasure() {
        //TODO: fill up the value in the trade buccaneer.ports up to 7
    }


    private void nextTurn() {
        parent.dehighlight();
        turns.nextTurn();
        parent.highlight(PositionHelper.getAvailableMoves(turns.getCurrentPlayer().getPlayerShip().getLocation(), turns.getCurrentPlayer().getPlayerShip().getDirection()));
    }
    /**
     * When the gui has a square clicked (usually when its a players turn)
     * Move or Rotate the ship.
     * @param pos the Position that was clicked.
     */
    void onSquareClick(Position pos){
        //Possibly have some form of state which checks if clicking on squares at this point in time is valid.
        //Ship ship = turns.getCurrentPlayer().getPlayerShip();
        Ship ship = turns.getCurrentPlayer().getPlayerShip();
        Position currentPos = ship.getLocation();
        if (PositionHelper.shouldTurn(ship, pos)){
            ship.setDirection(DirectionHelper.positionToDirection(currentPos, pos));
            turnShip(ship);
            System.out.println("The ship should turn");
            nextTurn();
        }
        else{
            if (PositionHelper.moveIsValid(ship, pos)) {
                this.moveShip(ship, pos);
                System.out.println("The move is valid");
                //parent.highlight(PositionHelper.getAvailableMoves(ship.getLocation(), ship.getDirection()));
                nextTurn();
            }
            else{
                //return a message saying that the current move is not valid
                System.out.println("The Move is not valid");
            }
        }
    }

    private void moveShip(Ship s, Position pos) {
        parent.moveShip(s, pos);
        board.moveShip(s, pos);
    }

    void onUserNameInput(String name1, String name2, String name3, String name4) {
        setPlayer(new Player(1, name1));
        setPlayer(new Player(2, name2));
        setPlayer(new Player(3, name3));
        setPlayer(new Player(4, name4));
    }

    void onGameBegin() {
        //Start taking turns, starting with london.
        turns = new TurnTracker();
        createPlayers();
        addShipsToGUI();
        nextTurn();
    }

    private void addShipsToGUI() {
        for (int i = 1; i < 5; i++) {
            Player p = this.getPlayer(i);
            Ship s = p.getPlayerShip();

            moveShip(s, p.getPort().getLocation());
            turnShip(s);
        }
    }

    private void turnShip(Ship s){
        parent.setShipDirection(s.getDirection(), s.getLocation());
    }
}