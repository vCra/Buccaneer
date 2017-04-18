package buccaneer.main;

import buccaneer.GUI.SelectTreasure;
import buccaneer.helpers.*;
import buccaneer.ports.Port;
import buccaneer.treasure.Treasure;
import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

//TODO: Javadoc


/**
 * Handles all elements of a game, including gameboard, players and turn trackers.
 */
public class Game {
    private GameBoard board;
    private Player[] players;
    private TurnTracker turns;
    private GameApp gui;

    //private CardDeck<ChanceCard> chanceCards;
    // chanceCards are now stored on Treasure Island
    //private CardDeck<CrewCard> crewCards;
    // Crew cards now are stored on pirate island
    Game(GameApp app) {
        this.board = new GameBoard();
        this.players = new Player[4];
        this.gui = app;
    }

    Player getPlayer(int player) {
        return players[player - 1];
    }

    private void setPlayer(Player player) {
        this.players[player.getId() - 1] = player;
    }

    public GameBoard getGameBoard() {
        return board;
    }

    /**
     * Sets up the game and starts it,
     * then passes to the TurnTracker.
     */

    private void createPlayers() {
        assignUsersPort();
        CSVReader csvReader;
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
                for (int j = 0; j<6; j++){
                    p.addCrewCard(board.getPirateIsland().getTopCard());
                }
                turns.addPlayer(p);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    }

    private void dealChanceCard() {
        getCurrentPlayer().addChanceCard(board.getTreasureIsland().getTopCard());
    }


    private void nextTurn() {
        gui.dehighlight();
        turns.nextTurn();
        this.setInitialGameState();
        if (turns.getState() == GameState.SPINORMOVE) { //We are not at a port, and can move normally
            //We need to combine the move highlighting and the spinning highligting
            DirectionHelper.highlightTurns(turns.getCurrentPlayer().getPlayerShip(), gui);
            gui.highlight(PositionHelper.getAvailableMoves(turns.getCurrentPlayer().getPlayerShip()));

        } else { //We are at a port, and hence can move in all directions
            gui.highlight(PositionHelper.getAvailablePortMoves(turns.getCurrentPlayer().getPlayerShip()));

        }
        gui.updatePlayersTurn();
    }

    private void checkPosition() {
        Ship playerShip = turns.getCurrentPlayer().getPlayerShip();
        if (playerShip.getLocation().isNextToOrOnIsland(board.getTreasureIsland())) {
            dealChanceCard();
            ArrayList<Treasure> shipTreasure = new ArrayList<>();
            SelectTreasure.display(playerShip.freeSpace(), board.getTreasureIsland().getTreasures(), playerShip);
            System.out.println(turns.getCurrentPlayer().getName() + " has landed at treasure island!");
        }
        if (playerShip.getLocation().isPort(board)) {
            buccaneer.GUI.Trading.display(getCurrentPlayer(), board.getSquareAt(playerShip.getLocation()).getPort());
        }
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
            if (turns.getState() == GameState.SPINORMOVE) { //Move normally
            if (PositionHelper.shouldTurn(ship, pos)) {
                ship.setDirection(DirectionHelper.positionToDirection(currentPos, pos));
                turnShip(ship);
                System.out.println("The ship should turn");
                nextTurn();
            } else {
                if (PositionHelper.moveIsValid(ship, pos)) {
                    this.moveShip(ship, pos);
                    System.out.println("The move is valid");
                    if (! pos.equals(currentPos)){
                        checkPosition();
                    }
                    //gui.highlight(PositionHelper.highlightTurns(ship.getLocation(), ship.getDirection()));
                    gui.dehighlight();

                    DirectionHelper.highlightTurns(ship, gui);
                    turns.setState(GameState.SPIN);
                } else {
                    //return a message saying that the current move is not valid
                    System.out.println("The Move is not valid");
                }
            }
        } else if (turns.getState() == GameState.SPIN) {
            ship.setDirection(DirectionHelper.positionToDirection(currentPos, pos));
            turnShip(ship);
            System.out.println("The ship should turn");

            nextTurn();
        } else { //Move from a port
            if (PositionHelper.moveFromPortIsValid(ship, pos)){
                ship.setDirection(DirectionHelper.positionToDirection(ship.getLocation(),pos));
                this.moveShip(ship, pos);

                this.nextTurn();
                this.turnShip(ship);

            }
            //Move to new location
            //Turn ship to face away from port.
        }
    }

    public void moveShip(Ship s, Position pos) {
        if (pos.containsShip(board)) {
            System.out.println("Attack");
        } else {
            gui.moveShip(s, pos);
            board.moveShip(s, pos);
    }
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
        //dealCards();
        dealTreasure();
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

    public Player getCurrentPlayer() {
        return turns.getCurrentPlayer();
    }

    private void turnShip(Ship s){
        gui.setShipDirection(s.getDirection(), s.getLocation());
    }

    private void setInitialGameState() {
        if (turns.getCurrentPlayer().getPlayerShip().getLocation().isPort(board)) {
            turns.setState(GameState.SPINANDMOVE);
        } else {
            turns.setState(GameState.SPINORMOVE);
        }
    }

    int getTurnNum() {
        return turns.getCurrentTurn();
    }
}