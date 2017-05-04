package buccaneer.ports;

import buccaneer.cards.ChanceCard;
import buccaneer.cards.CrewCard;
import buccaneer.enumData.Direction;
import buccaneer.helpers.Position;
import buccaneer.main.GameObject;
import buccaneer.main.GameSquare;
import buccaneer.main.Player;
import buccaneer.treasure.Treasure;

import java.util.ArrayList;
/**
 * @Port.java 02/02/2017
 *
 * Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 *
 * Handles all the Port functionality which is owned by a player
 *
 * @author aw13
 * @version 1.0
 * @see GameObject
 */


public class Port implements GameObject {
    private final ArrayList<Treasure> treasures;
    private final ArrayList<CrewCard> crewCards;
    private Player owner;
    private String name;
    private Position position;
    private ChanceCard longJohn;

    public ChanceCard getLongJohn() {
        ChanceCard silver = longJohn;
        longJohn = null;
        return silver;
    }

    public void addLongJohn(ChanceCard longJohn) {
        this.longJohn = longJohn;
    }

    /**
     * Constructor.
     * Takes a Player object which becomes the owner of the port,
     * allowing him more functionality at this port.
     *
     */
    public Port(String name, GameSquare s) {
        owner = null;
        this.name = name;
        treasures = new ArrayList<>();
        crewCards = new ArrayList<>();
        this.position = s.getPosition();
        s.setPort(this);
    }

    /**
     * Returns the owner of the port.
     * @return owner
     */
    public Player getOwner() {
        return owner;
    }

    /**
     * Set the owner of the port
     * @param owner the owner of the port
     */
    public void setOwner(Player owner) {
        this.owner = owner;
    }

    /**
     * Check if the port is owned
     * @return returns the owner of the port
     */
    public Boolean isOwned(){
        return owner != null;
    }

    /**
     * Returns the port's name
     * @return the name of the port
     */
    public String getName() {
        return name;
    }

    /**
     * Checks if the trade is a valid trade
     * @param name - The name that is being set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the port location
     * @return position of the port
     */
    public Position getLocation() {
        return position;
    }

    /**
     * Sets the location of the port
     * @param position - The position that is being set
     */
    public void setLocation(Position position) {
        this.position = position;
    }

    /**
     * Returns the treasures in the port
     * @return ArrayList of treasures
     */
    public ArrayList<Treasure> getTreasures() {
        return treasures;
    }
    /**
     * Returns the treasures in the port
     * @return ArrayList of crew cards
     */
    public ArrayList<CrewCard> getCrewCards() {
        return crewCards;
    }

    /**
     * Upon arrival at the port stores all the buccaneer.treasure
     * if the player is the owner of the port.
     */
    public void storeTreasure(ArrayList<Treasure> treasures) {
        this.treasures.addAll(treasures);
    }

    //TODO: Javadoc
    /**
     * Calculates which way to point the ship when the game stops
     */
    public Direction getWaterFace(){
        switch (name){
            case "London":
                return Direction.E;
            case "Genoa":
                return Direction.N;
            case "Marseilles":
                return Direction.W;
            case "Cadiz":
                return Direction.S;
            default:
                return Direction.N;
        }
    }

    /**
     * Returns a string with the name at position
     * @return name at position
     */
    @Override
    public String toString() {
        return
                name + " at " +
                        position;
    }

    /**
     * Returns the total CrewCard values
     * @return total CrewCard values
     */
    private int getCardValue() {
        int value = 0;
        for (CrewCard c : crewCards) {
            value = value + c.getValue();
        }
        return value;
    }

    /**
     * Returns the total value of the port
     * @return total value of port
     */
    public int getValue() {
        return getCardValue() + getTreasureValue();
    }

    /**
     * Returns the value of the treasure
     * @return value of treasure
     */
    public int getTreasureValue() {
        int value = 0;
        for (Treasure t : treasures) {
            value = value + t.getValue();
        }
        return value;
    }
}
