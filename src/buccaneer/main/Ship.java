package buccaneer.main;

import buccaneer.enumData.Direction;
import buccaneer.gui.ErrorMessage;
import buccaneer.helpers.Position;
import buccaneer.treasure.Treasure;
import javafx.scene.image.Image;

import java.util.ArrayList;

/**
 * @author AAW13
 * @version 1.0
 * @Ship.java 02/02/2017
 * <p>
 * Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 * <p>
 * A ship for a player.
 * Stores buccaneer.treasure
 */


public class Ship implements GameObject {
    private final ArrayList<Treasure> treasures;
    private final Player owner;
    private GameSquare square;
    private Direction direction;
    private Image shipPhoto;
    private Image shipLargePhoto;

    public Ship(Player owner) {
        this.owner = owner;
        this.treasures = new ArrayList<>(0);
    }

    public int freeSpace() {
        return 2 - this.treasures.size();
    }

    /**
     * Adds treasure to the hold
     *
     * @param t - The treasure being added
     */
    public void addTreasure(Treasure t) {
        if (freeSpace() == 2) {
            treasures.add(0, t);
        } else if (freeSpace() == 1) {
            treasures.add(1, t);
        } else {
            ErrorMessage.display("Error with adding treasure");
        }
        //Treasure score is calculated when we deposit treasure at the homeport
        //owner.getScore().addToScore(t.getValue());
    }

    /**
     * Adds a treasure ArrayList to hold
     *
     * @param t - The treasure ArrayList
     */
    public void addTreasures(ArrayList<Treasure> t) {
        for (Treasure i : t) {
            addTreasure(i);
            //Treasure score is calculated when we deposit treasure at the homeport
            //owner.getScore().addToScore(i.getValue());
        }
    }

    /**
     * Removes treasure from the hold
     *
     * @param t - The treasure being removed
     */

    public void removeTreasure(Treasure t) {
        //Treasure score is calculated when we deposit treasure at the homeport
        //owner.getScore().subFromScore(t.getValue());
        this.treasures.remove(t);

    }

    /**
     * Returns the list of treasures
     *
     * @return ArrayList of treasure
     */
    public ArrayList<Treasure> getTreasures() {
        return treasures;
    }

    /**
     * Returns the Position were the ship is located
     *
     * @return Position which contains current ship
     */
    public Position getLocation() {
        return square.getPosition();
    }

    /**
     * Sets the GameSquare of the ship
     *
     * @param square - The GameSquare which contains the ship
     */
    public void setLocation(GameSquare square) {
        this.square = square;
    }

    /**
     * Returns the GameSquare were the ship is located
     *
     * @return GameSquare which contains the ship
     */
    public GameSquare getSquare() {
        return this.square;
    }

    /**
     * Sets the initial location of the ship
     *
     * @param square - GameSquare that the ship is being set to
     */
    void setinitalLocation(GameSquare square) {
        this.square = square;
    }

    /**
     * Return the owner of the ship
     *
     * @return Player who owns the ship
     */
    public Player getOwner() {
        return owner;
    }

    /**
     * Return the direction of the ship
     *
     * @return Ship's direction
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * Sets the direction of the ship
     *
     * @param direction - The direction that is being set
     */
    public void setDirection(Direction direction) {
        if (direction != null) {
            this.direction = direction;
        }
    }

    /**
     * Returns the ship's image
     *
     * @return ship's image
     */
    Image getShipPhoto() {
        return shipPhoto;
    }

    /**
     * Sets the ship's image
     *
     * @param shipPhotoFile - The photo file that is being set
     */
    void setShipPhoto(String shipPhotoFile) {
        this.shipPhoto = new Image(shipPhotoFile);
    }

    /**
     * Return the ship's image that is used for battle
     *
     * @return ship's battle image
     */
    public Image getShipLargePhoto() {
        return shipLargePhoto;
    }

    /**
     * Sets the ship's battle image
     *
     * @param shipLargePhotoFile - The image that is being set
     */
    void setShipLargePhoto(String shipLargePhotoFile) {
        this.shipLargePhoto = new Image(shipLargePhotoFile);
    }

    /**
     * Returns the number of treasures carried by the ship
     *
     * @return number of treasures
     */
    public int getNumOfTreasures() {
        int num = 0;
        for (Treasure i : treasures) {
            num++;
        }
        return num;
    }

    /**
     * Calculates the total value of the treasures in the hold
     *
     * @return value of treasures
     */
    int calculateValue() {
        int score = 0;
        for (Treasure t : treasures) {
            score = score + t.getValue();
        }
        return score;
    }
}
