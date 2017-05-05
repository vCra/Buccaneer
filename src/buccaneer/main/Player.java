package buccaneer.main;

import buccaneer.cards.ChanceCard;
import buccaneer.cards.CrewCard;
import buccaneer.enumData.CardColor;
import buccaneer.helpers.Score;
import buccaneer.ports.Port;

import java.util.ArrayList;
import java.util.Random;

/**
 * @Player.java 02/02/2017
 *
 * Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 *
 * A entity that is playing the game
 *
 * @author AAW13
 * @version 1.0
 */
//1 - black, 2-green, 3 - red, 4-yellow
public class Player {
    private final Score score;
    private final ArrayList<ChanceCard> chanceCards;
    private final ArrayList<CrewCard> crewCards;
    private int id;
    private String name;
    private Port port;
    private Ship playerShip;

    public Player(int id, String name) {
        this.setId(id);
        this.setName(name);
        score = new Score();
        this.playerShip = new Ship(this);
        this.chanceCards = new ArrayList<>();
        this.crewCards = new ArrayList<>();
    }

    /**
     * Returns the player's ship
     * @return player's ship
     */
    public Ship getPlayerShip() {
        return playerShip;
    }

    /**
     * Sets the player's ship
     * @param playerShip - The ship that is being set to the player
     */
    void setPlayerShip(Ship playerShip) {
        this.playerShip = playerShip;
    }

    /**
     * Returns the player's home port
     * @return player's home port
     */
    public Port getPort() {
        return this.port;
    }

    /**
     * Sets the player's home port
     * @param port - The port that is being set
     */
    public void setPort(Port port) {
        this.port = port;
    }

    /**
     * Return's the player's ID
     * @return player's ID
     */
    int getId() {
        return id;
    }

    /**
     * Sets the player's ID
     * @param id - The ID that is being set
     */
    private void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the player's score
     * @return player's score
     */
    public Score getScore() {
        return score;
    }

    /**
     * Returns the player's name
     * @return player's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the player's name
     * @param name - The name that is being set
     */
    private void setName(String name) {
        this.name = name;
    }

    /**
     * Adds a crew card to the player's hand
     * @param crewCard - The crew card that is being added
     */
    public void addCrewCard(CrewCard crewCard) {
        this.crewCards.add(crewCard);
    }

    /**
     * Returns the crew cards int the player's hand
     * @return The ArrayList of crew cards
     */
    public ArrayList<CrewCard> getCrewCards() {
        return crewCards;
    }

    /**
     * Removes crew card from the player's hand
     * @param crewCard - The crew card being removed
     */
    public void removeCrewCard(CrewCard crewCard) {
        crewCards.remove(crewCard);
    }

    /**
     * Removes a single crew card
     * @return crew card is removed
     */
    public CrewCard removeSingleCrewCard()
    {
        int index = new Random().nextInt(crewCards.size() - 1);
        return crewCards.remove(index);
    }

    /**
     * Adds a chance card to the player's hand
     * @param chanceCard - The chance card being added
     */
    public void addChanceCard(ChanceCard chanceCard) {
        this.chanceCards.add(chanceCard);
    }

    public ChanceCard removeChanceCard (int id)
    {
        for (ChanceCard card : chanceCards)
        {
            if (card.getID() == id)
            {
                chanceCards.remove(card);
                return card;
            }
        }
        return null;
    }

    /**
     * Returns the chance cards in the player's hand
     * @return ArrayList of chance cards
     */
    public ArrayList<ChanceCard> getChanceCards() {
        return chanceCards;
    }

	
    public ChanceCard getLongJohn ()
    {
        for (ChanceCard card : chanceCards)
        {
            if (card.getID() == 21)
            {
                chanceCards.remove(card);
                return card;
            }
        }
        return null;
    }


    
     /**
     * Returns how fair the player can move
     * @return move strength
     */
    public int getMoveStrength() {
        int strength = 0;
        for (CrewCard c: crewCards){
            strength = strength + c.getValue();
        }
        if (strength == 0) {
            strength = 1;
        }
        return strength;
    }

    /**
     * Returns the total attack power of the player
     * @return player's attack strength
     */
    public int getAttackStrength() {
        int redTotal = 0;
        int blackTotal = 0;
        for (CrewCard i : crewCards) {
            if (i.getColor() == CardColor.Red) {
                redTotal += i.getValue();
            } else {
                blackTotal += i.getValue();
            }
        }
        if (redTotal > blackTotal) {
            return redTotal - blackTotal;
        } else {
            return blackTotal - redTotal;
        }
    }

}