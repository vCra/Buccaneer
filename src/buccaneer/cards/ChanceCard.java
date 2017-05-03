package buccaneer.cards;

import buccaneer.GUI.ItemGained;
import buccaneer.GUI.PickAPlayer;
import buccaneer.GUI.SelectTreasure;
import buccaneer.GUI.TreasureOrCrew;
import buccaneer.enumData.Direction;
import buccaneer.helpers.GameState;
import buccaneer.helpers.Position;
import buccaneer.helpers.PositionHelper;
import buccaneer.helpers.Receivable;
import buccaneer.islands.FlatIsland;
import buccaneer.islands.PirateIsland;
import buccaneer.islands.TreasureIsland;
import buccaneer.main.Game;
import buccaneer.main.GameSquare;
import buccaneer.main.Player;
import buccaneer.main.Ship;
import buccaneer.ports.Bay;
import buccaneer.ports.Port;
import buccaneer.treasure.Treasure;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * ChanceCard.java
 *
 * Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 *
 * Handles all the chance card functionality
 *
 * @author aaw13
 * @author jaj48
 * @version 1.0
 * @see CardObject
 * @see Receivable
 *
 */
//TODO: Javadoc

public class ChanceCard extends Receivable implements CardObject {
    private final int id;
    private final String text;

    public ChanceCard(int id, String text) {
        this.id = id;
        this.text = text;
        super.image = null;
        loadImage();

    }

    public int getID() {
        return id;
    }

    public String getText() {
        return text;
    }

    private void loadImage() {
        try {
            //Using this while we don't have a chance card picture
            File file = new File(getClass().getResource("/images/crewcards/CrewCard_Black1.png").toURI());
            super.image = ImageIO.read(file);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
    public void executeChanceCard(Game g) {
        //TODO: add in method calls to perform correct functionality, some more methods may need to be written
        switch(id) {
            case 1:        //Move ship 5 squares away, choose direction at end
                chanceCard1(g);
                break;
            case 2:        //Pick other player, they give over 3 crew cards
                chanceCard2(g);
                break;
            case 3:        //Move to mud bay, if crew cards < 3 then gain 4 crew cards
                chanceCard3(g);
                break;
            case 4:        //Move to cliff creak, if crew cards < 3 then gain 4 crew cards
                g.moveShip(g.getCurrentPlayer().getPlayerShip(), g.getGameBoard().getCliffCreek().getPosition());
                break;
            case 5:        //Move to home port, if crew cards < 3 then gain 4 crew cards
                g.moveShip(g.getCurrentPlayer().getPlayerShip(), g.getCurrentPlayer().getPort().getLocation());
                break;
            case 6:        //Move to port in direction facing, if crew cards < 3 then gain 4 crew cards
                break;
            case 7:        //1 treasure OR 2 crew cards are given to nearest ship
                break;
            case 8:        //1 treasure OR 2 crew cards are sent to flat island
                break;
            case 9:        //Most valuable treasure (if null highest crew card) sent to flat island
                break;
            case 10:       //Highest crew card sent to pirate island
                break;
            case 11:       //Take treasure up to 5 in value OR 2 crew cards
                takeTreasureOrCrew(g, 5, 2);
                break;
            case 12:       //Take treasure up to 4 in value OR 2 crew cards
                takeTreasureOrCrew(g, 4, 2);
                break;
            case 13:       //Take treasure up to 5 in value OR 2 crew cards
                takeTreasureOrCrew(g, 5, 2);
                break;
            case 14:       //Take treasure up to 7 in value OR 3 crew cards
                takeTreasureOrCrew(g, 7, 3);
                break;
            case 15:       //Take 2 crew cards
                chanceCard15(g);
                break;
            case 16:       //Take treasure up to 7 in value AND reduce crew to 10
                chanceCard16(g);
                break;
            case 17:       //Take treasure up to 6 in value AND reduce crew to 11
                break;
            case 18:       //Take treasure up to 4 in value AND if crew total < 7 take 2 crew cards
                break;
            case 19:       //Replace crew cards in hand for same amount of crew cards
                break;
            case 20:       //if other player at treasure island {exchange 2 crew cards with player} else {lose 2 crew cards}
                break;
            case 21:       //Long John Silver
                break;
            case 22:       //All players if amount of crew cards > 7 then lose crew cards until = 7
                break;
            case 23:       //Keep this card, can be traded at port for (treasure OR crew) up to 5 value
                g.getCurrentPlayer().addChanceCard(this);
                break;
            case 24:       //Keep this card, can be traded at port for (treasure OR crew) up to 4 value
                g.getCurrentPlayer().addChanceCard(this);
            case 25:       //This is identical to 26 so remove break as it has the same functionality
                break;
            case 26:       //Keep this card, if at pirate island then take treasure up to 7 in value
                g.getCurrentPlayer().addChanceCard(this);
                break;
            case 27:       //Take treasure up to 5 in value OR 3 crew cards
                break;
            case 28:       //Take 2 crew cards
                break;
        }
    }


    private void chanceCard1 (Game game)
    {
        Player currentPlayer = game.getCurrentPlayer();

        // Move the Player's Ship 5 squares away from the TreasureIsland
        Position playerPosition = currentPlayer.getPlayerShip().getLocation();
        GameSquare newPosition = game.getGameBoard().getSquareAt(playerPosition.getX(), playerPosition.getY());
        if (playerPosition.getX() >= 9 && playerPosition.getX() <= 12)
        {
            if (playerPosition.getY() == 8)
            {
                newPosition = game.getGameBoard().getSquareAt(playerPosition.getX(), playerPosition.getY() - 5);
            }
            else if (playerPosition.getY() == 13)
            {
                newPosition = game.getGameBoard().getSquareAt(playerPosition.getX(), playerPosition.getY() + 5);
            }
        }
        else if (playerPosition.getY() >= 9 && playerPosition.getY() <= 12)
        {
            if (playerPosition.getX() == 8)
            {
                newPosition = game.getGameBoard().getSquareAt(playerPosition.getX(), playerPosition.getY() - 5);
            }
            else if (playerPosition.getX() == 13)
            {
                newPosition = game.getGameBoard().getSquareAt(playerPosition.getX(), playerPosition.getY() + 5);
            }
        }
        else if (playerPosition.getX() == 8)
        {
            if (playerPosition.getY() == 8)
            {
                newPosition = game.getGameBoard().getSquareAt(playerPosition.getX() - 5, playerPosition.getY() - 5);
            }
            else if (playerPosition.getY() == 13)
            {
                newPosition = game.getGameBoard().getSquareAt(playerPosition.getX() - 5, playerPosition.getY() + 5);
            }
        }
        else if (playerPosition.getX() == 13)
        {
            if (playerPosition.getY() == 8)
            {
                newPosition = game.getGameBoard().getSquareAt(playerPosition.getX() + 5, playerPosition.getY() + 5);
            }
            else if (playerPosition.getY() == 13)
            {
                newPosition = game.getGameBoard().getSquareAt(playerPosition.getX() + 5, playerPosition.getY() - 5);
            }
        }

        game.getGameBoard().moveShip(currentPlayer.getPlayerShip(), newPosition);

        game.getTurns().setState(GameState.SPIN);

        get4CrewCards(currentPlayer, game.getGameBoard().getPirateIsland());
    }

    private void chanceCard2 (Game game)
    {
        Player currentPlayer = game.getCurrentPlayer();
        Player otherPlayer = chooseOtherPlayer(game);

        ArrayList<CrewCard> cards = loseNumOfCrewCards(otherPlayer, 3);

        for (CrewCard card : cards) {
            currentPlayer.addCrewCard(card);
        }
    }

    private void chanceCard3 (Game game)
    {
        Ship ship = game.getCurrentPlayer().getPlayerShip();
        Bay mudBay = game.getGameBoard().getMudBay();

        game.getGameBoard().moveShip(ship, game.getGameBoard().getSquareAt(mudBay.getPosition().getX(), mudBay.getPosition().getY()));

        game.getTurns().setState(GameState.SPIN);

        get4CrewCards(game.getCurrentPlayer(), game.getGameBoard().getPirateIsland());
    }

    private void chanceCard4 (Game game)
    {
        Ship ship = game.getCurrentPlayer().getPlayerShip();
        Bay cliffCreek = game.getGameBoard().getCliffCreek();

        game.getGameBoard().moveShip(ship, game.getGameBoard().getSquareAt(cliffCreek.getPosition().getX(), cliffCreek.getPosition().getY()));

        game.getTurns().setState(GameState.SPIN);

        get4CrewCards(game.getCurrentPlayer(), game.getGameBoard().getPirateIsland());
    }

    private void chanceCard5 (Game game)
    {
        Ship ship = game.getCurrentPlayer().getPlayerShip();
        Port homePort = ship.getOwner().getPort();

        game.getGameBoard().moveShip(ship, game.getGameBoard().getSquareAt(homePort.getLocation().getX(), homePort.getLocation().getY()));

        get4CrewCards(game.getCurrentPlayer(), game.getGameBoard().getPirateIsland());
    }

    private void chanceCard6 (Game game)
    {
        Ship ship = game.getCurrentPlayer().getPlayerShip();

        Direction direction = ship.getDirection();
        ArrayList<Port> ports = game.getGameBoard().getPorts();

        Position shipPosition = ship.getLocation();
        Position newPosition = shipPosition;
        if (direction == Direction.W)
        {
            Position port1 = ports.get(0).getLocation();
            Position port2 = ports.get(1).getLocation();

            if (PositionHelper.distanceTraveled(port1, shipPosition) < PositionHelper.distanceTraveled(port2, shipPosition))
            {
                newPosition = port1;
            }
            else
            {
                newPosition = port2;
            }
        }
        else if (direction == Direction.NW)
        {
            newPosition = ports.get(1).getLocation();
        }
        else if (direction == Direction.N)
        {
            newPosition = ports.get(3).getLocation();
        }
        else if (direction == Direction.NE)
        {
            Position port1 = ports.get(3).getLocation();
            Position port2 = ports.get(4).getLocation();

            if (PositionHelper.distanceTraveled(port1, shipPosition) < PositionHelper.distanceTraveled(port2, shipPosition))
            {
                newPosition = port1;
            }
            else
            {
                newPosition = port2;
            }
        }
        else if (direction == Direction.E)
        {
            Position port1 = ports.get(4).getLocation();
            Position port2 = ports.get(5).getLocation();

            if (PositionHelper.distanceTraveled(port1, shipPosition) < PositionHelper.distanceTraveled(port2, shipPosition))
            {
                newPosition = port1;
            }
            else
            {
                newPosition = port2;
            }
        }
        else if (direction == Direction.SE)
        {
            newPosition = ports.get(5).getLocation();
        }
        else if (direction == Direction.S)
        {
            newPosition = ports.get(2).getLocation();
        }
        else if (direction == Direction.SW)
        {
            Position port1 = ports.get(0).getLocation();
            Position port2 = ports.get(2).getLocation();

            if (PositionHelper.distanceTraveled(port1, shipPosition) < PositionHelper.distanceTraveled(port2, shipPosition))
            {
                newPosition = port1;
            }
            else
            {
                newPosition = port2;
            }
        }

        game.getGameBoard().moveShip(ship, game.getGameBoard().getSquareAt(newPosition.getX(), newPosition.getY()));

        get4CrewCards(game.getCurrentPlayer(), game.getGameBoard().getPirateIsland());
    }

    private void chanceCard7 (Game game)
    {
        Player player = game.getCurrentPlayer();

        Player other = getClosestPlayer(game);
        if (other.equals(null))
        {
            return;
        }

        if (player.getPlayerShip().getNumOfTreasures() != 0 && other.getPlayerShip().freeSpace() != 0)
        {
            ArrayList<Treasure> treasures = player.getPlayerShip().getTreasures();

            int min = 6;
            Treasure treasure = null;
            for (Treasure t : treasures)
            {
                if (min > t.getValue())
                {
                    treasure = t;
                    min = treasure.getValue();
                }
            }

            player.getPlayerShip().removeTreasure(treasure);
            other.getPlayerShip().addTreasure(treasure);
        }
        else
        {
            ArrayList<CrewCard> cards = player.getCrewCards();
            for (int i = 0; i < 2; i++)
            {
                CrewCard card = getLowestCard(cards);
                player.removeCrewCard(card);
                other.addCrewCard(card);
            }
        }
    }

    private void chanceCard8 (Game game)
    {
        Player player = game.getCurrentPlayer();
        FlatIsland flatIsland = game.getGameBoard().getFlatIsland();

        if (player.getPlayerShip().getNumOfTreasures() != 0)
        {
            ArrayList<Treasure> treasures = player.getPlayerShip().getTreasures();

            int min = 6;
            Treasure treasure = null;
            for (Treasure t : treasures)
            {
                if (min > t.getValue())
                {
                    treasure = t;
                    min = treasure.getValue();
                }
            }

            player.getPlayerShip().removeTreasure(treasure);
            flatIsland.addTreasure(treasure);
        }
        else
        {
            ArrayList<CrewCard> cards = player.getCrewCards();
            for (int i = 0; i < 2; i++)
            {
                CrewCard card = getLowestCard(cards);
                player.removeCrewCard(card);
                flatIsland.addCrewCard(card);
            }
        }
    }

    private CrewCard getLowestCard (ArrayList<CrewCard> cards)
    {
        int min = 4;
        CrewCard card = null;
        for (CrewCard crewCard : cards)
        {
            if (min > crewCard.getValue())
            {
                card = crewCard;
                min = card.getValue();
            }
        }

        return card;
    }

    private void chanceCard15(Game g) { //Take 2 chance cards
        ArrayList<Receivable> l = new ArrayList<>();
        l.add(g.getGameBoard().getPirateIsland().getTopCard());
        l.add(g.getGameBoard().getPirateIsland().getTopCard());
        ItemGained.display(l);
    }

    /**
     * Take treasure up to 7 in value and reduce crew cards to 10
     *
     * @param g
     */
    private void chanceCard16(Game g) {

    }

    private void get4CrewCards (Player player, PirateIsland pirateIsland)
    {
        // If the Player has 3 CrewCards or draw 4 CrewCards from the PirateIsland
        if (getNumOfCrewCards(player) <= 3)
        {
            for (int i = 0; i < 4; i++)
            {
                player.addCrewCard(pirateIsland.getTopCard());
            }
        }
    }

    /**
     * This method deals a number of CrewCards to the current Player.
     * @param game the game to get crew cards from
     * @param numOfCards the number of cards to gain
     */
    private void gainCrewCards(Game game, int numOfCards) {
        PirateIsland pirateIsland = game.getGameBoard().getPirateIsland();
        Player currentPlayer = game.getCurrentPlayer();

        for (int i = 0; i < numOfCards; i++)
        {
            currentPlayer.addCrewCard(pirateIsland.getTopCard());
        }
    }

    /**
     * This method removes a number of cards from the player and returns a list of them.
     * @param player
     * @param numOfCards
     * @return
     */
    private ArrayList<CrewCard> loseNumOfCrewCards(buccaneer.main.Player player, int numOfCards) {
        ArrayList<CrewCard> cards = new ArrayList<>();

        for (int i = 0; i < numOfCards; i++)
        {
            cards.add(player.removeSingleCrewCard());
        }

        return cards;
    }

    /**
     * This method returns a move strength (sum of the CrewCards values) of the Player's hand.
     * @param player
     * @return
     */
    private int getValueOfCrewCards(buccaneer.main.Player player) {
        return player.getMoveStrength();
    }

    /**
     * THis method returns number of the CrewCards in Player's hand.
     * @param player
     * @return
     */
    private int getNumOfCrewCards(buccaneer.main.Player player) {
        return player.getCrewCards().size();
    }

    //TODO: player chooses treasure and then gives treasure to player
    private void gainTreasure(buccaneer.main.Player player, int valueOfTreasure, int numOfTreasures)
    {

    }

    /**
     * This method removes the most valuable Treasure from Player's Ship and returns it to the TreasureIsland.
     * @param game
     */
    private void loseTreasure(Game game)
    {
        Ship ship = game.getCurrentPlayer().getPlayerShip();
        ArrayList<Treasure> treasures = ship.getTreasures();

        Treasure treasure = null;
        int max = 0;
        for (Treasure treasure1 : treasures) {
            if (max < treasure1.getValue()) {
                treasure = treasure1;
            }
        }

        ship.removeTreasure(treasure);
        game.getGameBoard().getTreasureIsland().addTreasure(treasure);
    }

    /**
     * This method returns a Player closest to the active Player.
     * Returns null if there are at least two Players at the same distance.
     * @param game
     * @return
     */
    private buccaneer.main.Player getClosestPlayer(Game game)
    {
        Player currentPlayer = game.getCurrentPlayer();
        ArrayList<Player> players = new ArrayList<>(Arrays.asList(game.getPlayers()));

        players.remove(currentPlayer);

        Position currentPlayerPos = currentPlayer.getPlayerShip().getLocation();
        Player closestPlayer = null;
        int min = 1000;
        for (int i = 0; i < 3; i++)
        {
            Position pos = players.get(i).getPlayerShip().getLocation();
            if (min > PositionHelper.distanceTraveled(currentPlayerPos, pos))
            {
                min = PositionHelper.distanceTraveled(currentPlayerPos, pos);
                closestPlayer = players.get(i);
            }
        }

        players.remove(closestPlayer);
        for (int i = 0; i < 2; i++)
        {
            Position pos = players.get(i).getPlayerShip().getLocation();
            if (min == PositionHelper.distanceTraveled(currentPlayerPos, pos))
            {
                return null;
            }
        }

        return closestPlayer;
    }

    /**
     * This method gets another Player at the TreasureIsland.
     * Returns null if none found.
     * @param game
     * @return
     */
    private buccaneer.main.Player getOtherPlayerAtTreasureIsland(Game game) {
        TreasureIsland treasureIsland = game.getGameBoard().getTreasureIsland();
        Player currentPlayer = game.getCurrentPlayer();
        ArrayList<Player> players = new ArrayList<>(Arrays.asList(game.getPlayers()));

        players.remove(currentPlayer);

        Player other = null;
        for (int i = 0; i < 3; i++)
        {
            Player player = players.get(i);
            if (player.getPlayerShip().getLocation().isNextToOrOnIsland(treasureIsland))
            {
                other = player;
            }
        }

        return other;
    }

    /**
     * This method lets the Player choose other Players.
     * @param game
     * @return
     */
    private buccaneer.main.Player chooseOtherPlayer(Game game) {
        return PickAPlayer.display(game.getCurrentPlayer(), game.getTurns());
    }

    /**
     * This method adds a ChanceCard to the Player.
     * @param player
     */
    private void keepCard(buccaneer.main.Player player)
    {
        player.addChanceCard(this);
    }

    /**
     * This method removes a treasure from the Player's Ship and adds it to the FlatIsland.
     * @param game
     * @param treasure
     */
    private void sendTreasureToFlatIsland(Game game, buccaneer.treasure.Treasure treasure)
    {
        Ship ship = game.getCurrentPlayer().getPlayerShip();
        FlatIsland flatIsland = game.getGameBoard().getFlatIsland();

        ship.removeTreasure(treasure);

        flatIsland.addTreasure(treasure);
    }

    /**
     * This method removes a CrewCard from the Player and adds it to the FlatIsland.
     * @param game
     * @param card
     */
    private void sendCrewCardToFlatIsland(Game game, buccaneer.cards.CrewCard card)
    {
        Player player = game.getCurrentPlayer();
        FlatIsland flatIsland = game.getGameBoard().getFlatIsland();
        player.removeCrewCard(card);

        flatIsland.addCrewCard(card);
    }

    //TODO: player makes a choice treasure or crew
    private Boolean treasureORcrew() {
        return TreasureOrCrew.display();
    }

    private void takeTreasureOrCrew(Game g, int treasure, int crew) {
        if (treasureORcrew()) {//Treasure
            SelectTreasure.display(treasure, g.getCurrentPlayer().getPlayerShip().freeSpace(), g.getGameBoard().getTreasureIsland().getTreasures(), g.getCurrentPlayer().getPlayerShip());
        } else { //CrewCards
            ArrayList<Receivable> r = new ArrayList<>();
            for (int i = 0; i < crew; i++) {
                r.add(g.getGameBoard().getPirateIsland().getTopCard());
            }
            ItemGained.display(r);
        }
    }
}
