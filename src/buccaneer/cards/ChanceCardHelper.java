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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

/**
 * Chance Card Helper 03/05/2017
 *
 * Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 *
 * Implaments the functionality of chance cards
 * Most of the functionality is called by the function
 * chanceCardX(Game g) - where x is the number of the chance card
 * Chance cards that are not executed when they are picked up (e.g.
 * Kidd's chart etc) are placed in the users hand and then executed
 * later when its conditions are right. These cards can be identified
 * as they have public methods, rather than having package private
 * methods like the rest of the cards.
 *
 * @author AAW13
 * @version 1.0
 */
public class ChanceCardHelper {
    static void chanceCard1 (Game game)
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

        game.moveShip(currentPlayer.getPlayerShip(), newPosition.getPosition());

        game.getTurns().setState(GameState.SPIN);

        get4CrewCards(currentPlayer, game.getGameBoard().getPirateIsland());
    }

    static void chanceCard2 (Game game)
    {
        Player currentPlayer = game.getCurrentPlayer();
        Player otherPlayer = chooseOtherPlayer(game);

        ArrayList<CrewCard> cards = loseNumOfCrewCards(otherPlayer, 3);

        for (CrewCard card : cards) {
            currentPlayer.addCrewCard(card);
        }
    }

    static void chanceCard3 (Game game)
    {
        Ship ship = game.getCurrentPlayer().getPlayerShip();
        Bay mudBay = game.getGameBoard().getMudBay();

        game.moveShip(ship, game.getGameBoard().getSquareAt(mudBay.getPosition().getX(), mudBay.getPosition().getY()).getPosition());

        game.getTurns().setState(GameState.SPIN);

        get4CrewCards(game.getCurrentPlayer(), game.getGameBoard().getPirateIsland());
    }

    static void chanceCard4 (Game game)
    {
        Ship ship = game.getCurrentPlayer().getPlayerShip();

        game.moveShip(ship, game.getGameBoard().getCliffCreek().getPosition());

        game.getTurns().setState(GameState.SPIN);

        get4CrewCards(game.getCurrentPlayer(), game.getGameBoard().getPirateIsland());
    }

    static void chanceCard5 (Game game)
    {
        Ship ship = game.getCurrentPlayer().getPlayerShip();
        Port homePort = ship.getOwner().getPort();

        game.moveShip(ship, game.getGameBoard().getSquareAt(homePort.getLocation().getX(), homePort.getLocation().getY()).getPosition());

        get4CrewCards(game.getCurrentPlayer(), game.getGameBoard().getPirateIsland());
    }

    static void chanceCard6 (Game game)
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

        game.moveShip(ship, game.getGameBoard().getSquareAt(newPosition.getX(), newPosition.getY()).getPosition());

        get4CrewCards(game.getCurrentPlayer(), game.getGameBoard().getPirateIsland());
    }

    static void chanceCard7 (Game game)
    {
        Player player = game.getCurrentPlayer();

        Player other = getClosestPlayer(game);
        if (other == null)
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

    static void chanceCard8(Game game) {
        Player player = game.getCurrentPlayer();

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

            sendTreasureToFlatIsland(game, treasure);
        }
        else
        {
            ArrayList<CrewCard> cards = player.getCrewCards();
            for (int i = 0; i < 2; i++)
            {
                CrewCard card = getLowestCard(cards);
                sendCrewCardToFlatIsland(game, card);
            }
        }
    }

    static void chanceCard9(Game game) {
        Player player = game.getCurrentPlayer();

        if (player.getPlayerShip().getNumOfTreasures() != 0) {
            ArrayList<Treasure> treasures = player.getPlayerShip().getTreasures();

            int max = 0;
            Treasure treasure = null;
            for (Treasure t : treasures) {
                if (max < t.getValue()) {
                    treasure = t;
                    max = treasure.getValue();
                }
            }

            sendTreasureToFlatIsland(game, treasure);
        } else {
            ArrayList<CrewCard> cards = player.getCrewCards();

            int max = 0;
            CrewCard card = null;
            for (CrewCard c : cards) {
                if (max < c.getValue()) {
                    card = c;
                }
            }

            if (card!=null){
                sendCrewCardToFlatIsland(game, card);
            }
        }
    }

    static void chanceCard10(Game game) {
        Player player = game.getCurrentPlayer();

        CrewCard card = getHighestCard(player.getCrewCards());

        game.getGameBoard().getPirateIsland().returnCrewCard(card);
    }
    static void chanceCard11(Game g) {
        takeTreasureOrCrew(g,5,2);
    }
    static void chanceCard12(Game g) {
        takeTreasureOrCrew(g,4,2);
    }
    static void chanceCard13(Game g) {
        takeTreasureOrCrew(g,5,2);
    }
    static void chanceCard14(Game g) {
        takeTreasureOrCrew(g,7,3);
    }
    static void chanceCard15(Game g) { //Take 2 chance cards
        ArrayList<Receivable> l = new ArrayList<>();
        l.add(g.getGameBoard().getPirateIsland().getTopCard());
        l.add(g.getGameBoard().getPirateIsland().getTopCard());
        ItemGained.display(l);
    }

    static void chanceCard16(Game g) {
        SelectTreasure.display(7, g.getCurrentPlayer().getPlayerShip().freeSpace(), g.getGameBoard().getTreasureIsland().getTreasures(), g.getCurrentPlayer().getPlayerShip());
        reduceCrewCardToValue(10, g.getCurrentPlayer(), g);
    }

    static void chanceCard17(Game g){
        SelectTreasure.display(6, g.getCurrentPlayer().getPlayerShip().freeSpace(), g.getGameBoard().getTreasureIsland().getTreasures(), g.getCurrentPlayer().getPlayerShip());
        reduceCrewCardToValue(11, g.getCurrentPlayer(), g);
    }
    static void chanceCard18(Game g){
        SelectTreasure.display(4, g.getCurrentPlayer().getPlayerShip().freeSpace(), g.getGameBoard().getTreasureIsland().getTreasures(), g.getCurrentPlayer().getPlayerShip());
        if (g.getCurrentPlayer().getMoveStrength()<7){
            takeCrewCards(g, 2);
        }
    }
    static  void chanceCard19(Game g){
        int noOfCards = g.getCurrentPlayer().getCrewCards().size();

        Iterator<CrewCard> it = g.getCurrentPlayer().getCrewCards().iterator();
        while (it.hasNext()){
            CrewCard c = it.next();
            it.remove();
            g.getGameBoard().getPirateIsland().returnCrewCard(c);
        }

        for (int i = 0; i < noOfCards; i++){
            g.getCurrentPlayer().getCrewCards().add(g.getGameBoard().getPirateIsland().getTopCard());
        }
    }

    static void chanceCard20(Game g){
        Player otherP = getOtherPlayerAtTreasureIsland(g);
        if (otherP==null){
            g.getGameBoard().getPirateIsland().returnCrewCard(g.getCurrentPlayer().removeSingleCrewCard());
            g.getGameBoard().getPirateIsland().returnCrewCard(g.getCurrentPlayer().removeSingleCrewCard());
        } else { //They is another player, and we can trade with them


        }
    }
    public static void chanceCard21(Game g) {

    }

    static void chanceCard22(Game g){
        for (Player p: g.getPlayers()){
            reduceCrewCardToValue(7, p, g);
        }
    }
    public static void chanceCard23(Game g) {

    }
    public static void chanceCard24(Game g) {

    }
    public static void chanceCard25(Game g) {
        takeCrewCards(g, 7);
    }
    public static void chanceCard26(Game g){
        takeCrewCards(g, 7);
    }

    static void chanceCard27(Game g){
        takeTreasureOrCrew(g, 5, 3);
    }

    static void chanceCard28(Game g){
        takeCrewCards(g, 2);
    }

    private static void get4CrewCards(Player player, PirateIsland pirateIsland)
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
    static void gainCrewCards(Game game, int numOfCards) {
        PirateIsland pirateIsland = game.getGameBoard().getPirateIsland();
        Player currentPlayer = game.getCurrentPlayer();

        for (int i = 0; i < numOfCards; i++)
        {
            currentPlayer.addCrewCard(pirateIsland.getTopCard());
        }
    }

    /**
     * This method removes a number of cards from the player and returns a list of them.
     * @param player the player to remove cards from
     * @param numOfCards the number of cards
     * @return the list of cards that has been removed
     */
    private static ArrayList<CrewCard> loseNumOfCrewCards(buccaneer.main.Player player, int numOfCards) {
        ArrayList<CrewCard> cards = new ArrayList<>();

        for (int i = 0; i < numOfCards; i++)
        {
            cards.add(player.removeSingleCrewCard());
        }

        return cards;
    }


    /**
     * THis method returns number of the CrewCards in Player's hand.
     * @param player the player to get the number of CrewCards of
     * @return the number of crew cards
     */
    private static int getNumOfCrewCards(buccaneer.main.Player player) {
        return player.getCrewCards().size();
    }

    /**
     * This method removes the most valuable Treasure from Player's Ship and returns it to the TreasureIsland.
     * @param game the game
     */
    private static void loseTreasure(Game game)
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
     * @param game the game
     * @return the player closest to the active player
     */
    private static buccaneer.main.Player getClosestPlayer(Game game)
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
     * @param game the game
     * @return another player at TreasureIsland
     */
    private static buccaneer.main.Player getOtherPlayerAtTreasureIsland(Game game) {
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
     * @param game the game
     * @return the other player that has been chosen
     */
    private static buccaneer.main.Player chooseOtherPlayer(Game game) {
        return PickAPlayer.display(game.getCurrentPlayer(), game.getPlayers());
    }


    /**
     * This method removes a treasure from the Player's Ship and adds it to the FlatIsland.
     * @param game the game
     * @param treasure the treasure to send
     */
    private static void sendTreasureToFlatIsland(Game game, buccaneer.treasure.Treasure treasure)
    {
        Ship ship = game.getCurrentPlayer().getPlayerShip();
        FlatIsland flatIsland = game.getGameBoard().getFlatIsland();

        ship.removeTreasure(treasure);

        flatIsland.addTreasure(treasure);
    }

    /**
     * This method removes a CrewCard from the Player and adds it to the FlatIsland.
     * @param game the gamne
     * @param card the card to remove from the current player
     */
    private static void sendCrewCardToFlatIsland(Game game, buccaneer.cards.CrewCard card)
    {
        Player player = game.getCurrentPlayer();
        FlatIsland flatIsland = game.getGameBoard().getFlatIsland();
        player.removeCrewCard(card);

        flatIsland.addCrewCard(card);
    }

    private static Boolean treasureORcrew() {
        return TreasureOrCrew.display();
    }

    /**
     * Reduces the crew cards of a player to a value
     * tries to keep the crew cards value has high as possible
     * @param value the value of cards the crewCards should be reduced to
     */
    private static void reduceCrewCardToValue(int value, Player p, Game g){
        int v;
        v = p.getMoveStrength();
        p.getCrewCards().sort(Comparator.comparing(CrewCard::getValue).reversed());
        Iterator<CrewCard> it = p.getCrewCards().iterator();
        while (it.hasNext()){
            CrewCard c= it.next();
            if (v-c.getValue()==value){
                it.remove();
                p.removeCrewCard(c);
                g.getGameBoard().getPirateIsland().returnCrewCard(c);
                break;
            } else if (v-c.getValue()>value){
                it.remove();
                p.removeCrewCard(c);
                g.getGameBoard().getPirateIsland().returnCrewCard(c);
            } else if (value < v){
                break;
            }
        }
    }

    private static void takeCrewCards(Game g, int crew){
        ArrayList<Receivable> r = new ArrayList<>();
        for (int i = 0; i < crew; i++) {
            r.add(g.getGameBoard().getPirateIsland().getTopCard());
        }
        ItemGained.display(r);
    }

    private static void takeTreasureOrCrew(Game g, int treasure, int crew) {
        if (treasureORcrew()) {//Treasure
            SelectTreasure.display(treasure, g.getCurrentPlayer().getPlayerShip().freeSpace(), g.getGameBoard().getTreasureIsland().getTreasures(), g.getCurrentPlayer().getPlayerShip());
        } else { //CrewCards
            takeCrewCards(g, crew);
        }
    }

    private static CrewCard getLowestCard(ArrayList<CrewCard> cards)
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

    private static CrewCard getHighestCard(ArrayList<CrewCard> cards)
    {
        int max = 0;
        CrewCard card = null;
        for (CrewCard crewCard : cards)
        {
            if (max > crewCard.getValue())
            {
                card = crewCard;
                max = card.getValue();
            }
        }

        return card;
    }
}
