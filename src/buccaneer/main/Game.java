package buccaneer.main;

import buccaneer.GUI.*;
import buccaneer.cards.ChanceCard;
import buccaneer.cards.ChanceCardHelper;
import buccaneer.cards.CrewCard;
import buccaneer.enumData.Direction;
import buccaneer.helpers.*;
import buccaneer.ports.Port;
import buccaneer.treasure.Treasure;
import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @Game.java 02/02/2017
 *
 * Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 *
 * Handles all elements of a game, including gameboard, players and turn trackers.
 *
 * @author AAW13
 * @version 1.0
 */
//TODO: Javadoc

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

    /**
     * Returns the player from their id
     * @param player - player id
     * @return Player object
     */
    Player getPlayer(int player) {
        return players[player - 1];
    }

    /**
     * Returns all the players
     * @return
     */
    public Player[] getPlayers() {
        return players;
    }

    /**
     * Sets the player
     * @param player - the player that is being set
     */
    private void setPlayer(Player player) {
        this.players[player.getId() - 1] = player;
    }

    /**
     * Returns the GameBoard
     * @return GameBoard
     */
    public GameBoard getGameBoard() {
        return board;
    }

    /**
     * Returns the number of turns
     * @return number of turns
     */
    public TurnTracker getTurns() {
        return turns;
    }

    /**
     * Sets up the game and starts it,
     * then passes to the TurnTracker.
     */

    private void createPlayers() {
        assignUsersPort();
        CSVReader csvReader;
        CSVReader csvReader2;

        ClassLoader classLoader = getClass().getClassLoader(); //allows us to use resources
        try {
            File file = new File(classLoader.getResource("data/ships.csv").getFile());
            FileReader csvFile = new FileReader(file);
            csvReader = new CSVReader(csvFile); //Uses the file reader in lib/opencsv-x.x.jar
            File file2 = new File(classLoader.getResource("data/shipsL.csv").getFile());
            FileReader csvFile2 = new FileReader(file2);
            csvReader2 = new CSVReader(csvFile2); //Uses the file reader in lib/opencsv-x.x.jar
            for (Player p : players) {
                Ship s = p.getPlayerShip();
                String[] nextLine;
                nextLine = csvReader.readNext();
                s.setShipPhoto(nextLine[1]);
                p.setPlayerShip(s);

                nextLine = csvReader2.readNext();
                s.setShipLargePhoto(nextLine[1]);

                for (int j = 0; j < 5; j++) {
                    p.addCrewCard(board.getPirateIsland().getTopCard());
                }
                turns.addPlayer(p);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Assigns all the players their home port
     */
    private void assignUsersPort() {
        for (Player player : players) {
            Port port = board.getUnownedPort();
            port.setOwner(player);
            player.setPort(port);
            player.getPlayerShip().setDirection(port.getWaterFace());
            gui.setHomePortOnBoarder(player);
        }
    }

    /**
     * Deals out the chance cards on setup
     */
    private void dealChanceCard() {
        ChanceCard c = board.getTreasureIsland().getTopCard();
        ChanceCardsUI.display(c);
        c.executeChanceCard(this);

    }

    /**
     *  Changes the game to the next turn
     */
    private void nextTurn() {
        gui.dehighlight();
        turns.nextTurn();
        this.setInitialGameState();
        if (turns.getState() == GameState.SPINORMOVE) { //We are not at a port, and can move normally
            //We need to combine the move highlighting and the spinning highligting
            DirectionHelper.highlightTurns(turns.getCurrentPlayer().getPlayerShip(), gui);
            gui.setShipPosition(getCurrentPlayer().getPlayerShip(), getCurrentPlayer().getPlayerShip().getLocation());
            gui.highlight(PositionHelper.getAvailableMoves(turns.getCurrentPlayer().getPlayerShip()));

        } else { //We are at a port, and hence can move in all directions
            gui.highlight(PositionHelper.getAvailablePortMoves(turns.getCurrentPlayer().getPlayerShip()));

        }
        gui.updatePlayersTurn();
        gui.updateTurnNumber();
    }

    /**
     * Checks the position of the ship
     */
    private void checkPosition() {
        Ship playerShip = turns.getCurrentPlayer().getPlayerShip();


        //CHECK IF NEXT TO ISLAND
        if (playerShip.getLocation().isNextToOrOnIsland(board.getTreasureIsland())) {
            dealChanceCard();
            //ArrayList<Treasure> shipTreasure = new ArrayList<>();
            //SelectTreasure.display(10, playerShip.freeSpace(), board.getTreasureIsland().getTreasures(), playerShip);
            System.out.println(turns.getCurrentPlayer().getName() + " has landed at treasure island!");
        } else if (playerShip.getLocation().isNextToOrOnIsland(board.getFlatIsland())) {
            board.getFlatIsland().trade(getCurrentPlayer());
        }

        //Check if at Bays
        else if (playerShip.getLocation().isBay(board.getAnchorBay())){
            for (ChanceCard c: getCurrentPlayer().getChanceCards()){
                if (c.getID()==(26)){
                    ChanceCardHelper.chanceCard26(this);
                    getGameBoard().getTreasureIsland().addChanceCard(c);
                } else if (c.getID()==25){
                    ChanceCardHelper.chanceCard25(this);
                    getGameBoard().getTreasureIsland().addChanceCard(c);
                }
            }
        }

        //CHECK IF AT PORT
        else if (playerShip.getLocation().isPort(board)) {
            if (playerShip.getSquare().getPort().equals(getCurrentPlayer().getPort())) {
                getCurrentPlayer().getPort().storeTreasure(playerShip.getTreasures());
                playerShip.getTreasures().clear();
                getCurrentPlayer().getScore().setScore(getCurrentPlayer().getPort().getTreasureValue());
            }
            Port thisPort = board.getSquareAt(playerShip.getLocation()).getPort();
            buccaneer.GUI.Trading.display(getCurrentPlayer(), thisPort);
            if (thisPort.isOwned()) {
                thisPort.getOwner().getScore().setScore(thisPort.getTreasureValue());
            }
//            }
            gui.updateScores();
            for (Player p : players) {
                if (p.getScore().hasWon()) {
                    Victory.display(p);
                }
            }
        }

    }

    /**
     * When the gui has a square clicked (usually when its a players turn)
     * Move or Rotate the ship.
     *
     * @param pos the Position that was clicked.
     */
    void onSquareClick(Position pos) {
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
                    if (!pos.equals(currentPos) && turns.getState() != GameState.ATTACK) {
                        gui.dehighlight();
                        checkPosition();
                    }
                    //gui.highlight(PositionHelper.highlightTurns(ship.getLocation(), ship.getDirection()));
                    if (pos.isPort(board)) {
                        nextTurn();
                    } else if (turns.getState() != GameState.ATTACK) {
                        DirectionHelper.highlightTurns(ship, gui);
                        turns.setState(GameState.SPIN);
                    }
                } else {
                    //return a message saying that the current move is not valid
                    ErrorMessage.display("The move that was selected is not valid");
                }
            }
        } else if (turns.getState() == GameState.SPIN) {
            Direction d = DirectionHelper.positionToDirection(currentPos, pos);
            if (turns.getAttack()) {
                if (DirectionHelper.turnIsValid(turns.getLoser().getPlayerShip(), d)) {
                    ship.setDirection(d);
                    turnShip(ship);
                    System.out.println("The ship should turn");
                    turns.setAttack(false);
                    nextTurn();
                } else {
                    ErrorMessage.display("You can not turn in this direction");
                }
            } else {
                if (DirectionHelper.turnIsValid(ship, d)) {
                    ship.setDirection(d);
                    turnShip(ship);
                    System.out.println("The ship should turn");
                    nextTurn();
                } else {
                    ErrorMessage.display("You can not turn in this direction");
                }
            }

        } else if (turns.getState() == GameState.SPINANDMOVE) { //Move from a port
            if (PositionHelper.moveFromPortIsValid(ship, pos)) {
                ship.setDirection(DirectionHelper.positionToDirection(ship.getLocation(), pos));
                this.moveShip(ship, pos);

                if (!pos.equals(currentPos)) {
                    checkPosition();
                }

                if (turns.getState() != GameState.ATTACK) {
                    gui.dehighlight();
                    DirectionHelper.highlightTurns(ship, gui);
                    turns.setState(GameState.SPIN);
                }

            }
            //Move to new location
            //Turn ship to face away from port.
        } else if (turns.getState() == GameState.ATTACK) {
            if (PositionHelper.moveFromPortIsValid(ship, pos)) {
                if (!pos.equals(currentPos)) {
                    ship.setDirection(DirectionHelper.positionToDirection(ship.getLocation(), pos));
                    this.moveShip(ship, pos);
                    gui.dehighlight();
                    turns.setState(GameState.SPIN);
                    gui.setShipPosition(turns.getOtherPlayerFromAttack().getPlayerShip(), turns.getOtherPlayerFromAttack().getPlayerShip().getLocation());
                    gui.setShipPosition(turns.getCurrentPlayer().getPlayerShip(), turns.getCurrentPlayer().getPlayerShip().getLocation());
                    turns.setAttack(true);
                    DirectionHelper.highlightTurns(turns.getLoser().getPlayerShip(), gui);
                } else {
                    ErrorMessage.display("This is an error message that is being displayed");
                }
            }
        }
    }

    /**
     * Moves the ship
     * @param s - The current ship
     * @param pos - Position it is moving to
     */
    void oldMoveShip(Ship s, Position pos) {
        {
            s.setLocation(board.getSquareAt(pos));
            gui.moveShip(s, pos);
            board.moveShip(s, pos);
        }
    }

    /**
     * Moves the ship and checks if it triggers any game events
     * @param s
     * @param pos
     */
    public void moveShip(Ship s, Position pos) {
        Position oldPosition = s.getLocation();
        ArrayList<Position> otherPlayersPositions = PositionHelper.moveThroughPlayer(s, pos, getGameBoard());
        for (Position i : otherPlayersPositions) {
            if (i.containsShip(board) && (!i.isNextToOrOnAnyIsland(board.getAllIslands())) && (!i.isPort(board))) {
                Player otherPlayer = getGameBoard().getSquareAt(i).getPlayer();
                boolean answer = AskToAttack.display(otherPlayer, s.getOwner());
                if (answer) {
                    pos = i;
                    break;
                }
            }
        }
        if (pos.containsShip(board) && (!pos.isNextToOrOnAnyIsland(board.getAllIslands())) && (!pos.isPort(board))) {
            gui.moveShip(s, pos);
            board.moveShip(s, pos);
            calculateWinner(s.getOwner(), getGameBoard().getSquareAt(pos).getPlayer());
        } else {
            gui.moveShip(s, pos);
            board.moveShip(s, pos);
            if (oldPosition.containsShip(board)) {
                gui.setShipPosition(getGameBoard().getSquareAt(oldPosition).getPlayer().getPlayerShip(), oldPosition);
            }
        }

    }

    /**
     * Calculates the winner of the of the battle
     * @param p1 - One of the players
     * @param p2 - The other player
     */
    private void calculateWinner(Player p1, Player p2) {
        Battle.display(p1, p2);
        turns.setOtherPlayerFromAttack(p2);
        if (p1.getAttackStrength() > p2.getAttackStrength()) {
            attack(p1, p2);
        } else if (p1.getAttackStrength() < p2.getAttackStrength()) {
            attack(p2, p1);
        } else {
            turns.setLoser(p1);
            turns.setState(GameState.ATTACK);
            gui.dehighlight();
            gui.highlight(PositionHelper.getAvailablePortMoves(p1.getPlayerShip()));
        }
    }

    /**
     * Calculates the spoils of the battle
     * @param winner - winner of the battle
     * @param loser - loser of the battle
     */
    private void attack(Player winner, Player loser) {
        int numOfTreasuresWinner = 2;
        int numOfTreasuresLoser = 0;
        numOfTreasuresWinner -= winner.getPlayerShip().getNumOfTreasures();
        numOfTreasuresLoser += loser.getPlayerShip().getNumOfTreasures();
        if (numOfTreasuresWinner != 0 && numOfTreasuresLoser != 0) {
            SelectTreasure.display(10, numOfTreasuresWinner, loser.getPlayerShip().getTreasures(), winner.getPlayerShip());
            if (loser.getPlayerShip().getNumOfTreasures() != 0) {
                playerTreasureToTreasureIsland(loser);
            }
        } else if (numOfTreasuresLoser == 0) {
            giveCrewCardsFromAttack(winner, loser);
        } else {
            playerTreasureToTreasureIsland(loser);
        }
        turns.setLoser(loser);
        turns.setState(GameState.ATTACK);
        gui.setShipPosition(loser.getPlayerShip(), loser.getPlayerShip().getLocation());
        gui.dehighlight();
        gui.highlight(PositionHelper.getAvailablePortMoves(loser.getPlayerShip()));
    }

    /**
     * Sends the player's treasure to Treasure Island
     * @param player
     */
    private void playerTreasureToTreasureIsland(Player player) {
        for (Treasure i : player.getPlayerShip().getTreasures()) {
            player.getPlayerShip().removeTreasure(i);
            board.getTreasureIsland().addTreasure(i);
        }
    }

    /**
     * Gives the crew cards from the loser to the winner
     * @param recipient - The player receiving the crew cards
     * @param giver - The player giving the crew cards
     */

    private void giveCrewCardsFromAttack(Player recipient, Player giver) {
        ArrayList<CrewCard> cards = giver.getCrewCards();
        int numOfCards = cards.size();
        CrewCard least;
        CrewCard secondLeast;
        if (numOfCards >= 2) {
            least = cards.get(0);
            secondLeast = cards.get(1);
            for (CrewCard i : cards) {
                if (i.getValue() < least.getValue()) {
                    secondLeast = least;
                    least = i;
                } else if (i.getValue() < secondLeast.getValue()) {
                    secondLeast = i;
                }
            }
            giver.removeCrewCard(least);
            giver.removeCrewCard(secondLeast);
            recipient.addCrewCard(least);
            recipient.addCrewCard(secondLeast);
        } else if (numOfCards == 1) {
            least = cards.get(0);
            giver.removeCrewCard(least);
            recipient.addCrewCard(least);
        }
    }

    /**
     * Takes the player's names as input and creates the players
     * @param name1 - Player 1's name
     * @param name2 - Player 2's name
     * @param name3 - Player 3's name
     * @param name4 - Player 4's name
     */

    void onUserNameInput(String name1, String name2, String name3, String name4) {
        setPlayer(new Player(1, name1));
        setPlayer(new Player(2, name2));
        setPlayer(new Player(3, name3));
        setPlayer(new Player(4, name4));
    }

    /**
     * Initial set up
     */
    void onGameBegin() {
        //Start taking turns, starting with london.
        turns = new TurnTracker();
        createPlayers();

        setupTradingPorts();
        addShipsToGUI();
        nextTurn();
    }

    /**
     * Sets up the trading ports with treasure and crew cards
     */
    private void setupTradingPorts() {
        for (Port p : board.getPorts()) {
            if (!p.isOwned()) {
                p.getCrewCards().add(board.getPirateIsland().getTopCard());
                p.getCrewCards().add(board.getPirateIsland().getTopCard());

                int value = p.getValue();
                p.getTreasures().addAll(board.getTreasureIsland().treasuresOfValue(value));

            }
        }
    }

    /**
     * Adds the player's ships to the GUI
     */
    private void addShipsToGUI() {
        for (int i = 1; i < 5; i++) {
            Player p = this.getPlayer(i);
            Ship s = p.getPlayerShip();

            oldMoveShip(s, p.getPort().getLocation());
            turnShip(s);
        }
    }

    /**
     * Returns the current player
     * @return Current player
     */
    public Player getCurrentPlayer() {
        return turns.getCurrentPlayer();
    }

    /**
     * Turns the direction of the ship
     * @param s - Current ship
     */
    private void turnShip(Ship s) {
        gui.setShipDirection(s.getDirection(), s.getLocation());
    }

    /**
     * Sets the initial game state
     */
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