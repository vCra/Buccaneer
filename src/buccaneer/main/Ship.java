package buccaneer.main;

import buccaneer.enumData.Direction;
import buccaneer.helpers.Position;
import buccaneer.treasure.Treasure;
import javafx.scene.image.Image;

import java.util.ArrayList;


/**
 * A ship for a player.
 * Stores buccaneer.treasure, and
 */
public class Ship implements GameObject {
    private ArrayList<Treasure> treasures;
    private Player owner;
    private GameSquare square;
    private Direction direction;
    private Image shipPhoto;

    public Ship(Player owner) {
        this.owner = owner;
        this.treasures = new ArrayList<>(0);
    }

    public int freeSpace() {
        return 2 - this.treasures.size();
    }

    //TODO: Javadoc

    /**
     * Adds treasure to the player's hold
     * @param t - treasure being added
     */
    public void addTreasure(Treasure t) {
        if (freeSpace() == 2) {
            treasures.add(0, t);
        } else if (freeSpace() == 1) {
            treasures.add(1, t);
        } else {
            System.err.print("ahh fuck");
        }
    }

    /**
     * Adds more than one treasure to the ship hold
     */
    public void addTreasures(ArrayList<Treasure> t) {
        for (Treasure i : t) {
            addTreasure(i);
        }
    }

    /**
     * Remove inputted treasure from the ship's hold
     * @param t - treasure being remove
     */
    public void removeTreasure(Treasure t) {
        this.treasures.remove(t);
    }

    /**
     * Returns treasure in the ship's hold
     * @return treasures ArrayList
     */
    public ArrayList<Treasure> getTreasures() {
        return treasures;
    }

    /**
     * Removes all treasure from the ship's hold
     */
    public void clearTreasure(){
        this.treasures.clear();
    }

    /**
     * Returns the ship's position
     * @return position of the game square that ship is on
     */
    public Position getLocation() {
        return square.getPosition();
    }

    /**
     * Sets the ship's position
     * @param square - the game square the ship is being set to
     */
    public void setLocation(GameSquare square) {
        this.square = square;
    }

    /**
     * Returns the game square the ship is on
     * @return the game square the ship is on
     */
    public GameSquare getSquare() {
        return this.square;
    }

    /**
     * Sets the start location of the ship
     * @param square - the start game square for the current ship
     */
    void setinitalLocation(GameSquare square) {
        this.square = square;
    }

    /**
     * Returns the player that owns the ship
     * @return player
     */
    public Player getOwner() {
        return owner;
    }

    /**
     * Sets the ship's owner
     * @param owner - the player who owns the ship
     */
    public void setOwner(Player owner) {
        this.owner = owner;
    }

    /**
     * Returns the direction the ship is facing
     * @return direction of the ship
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * Sets the direction of the ship
     * @param direction - the direction the ship is being set to
     */
    public void setDirection(Direction direction) {
        if (direction != null) {
            this.direction = direction;
        }
    }

    /**
     * Returns the image of the ship
     * @return ship image
     */
    Image getShipPhoto() {
        return shipPhoto;
    }

    /**
     * Return the number of treasures in the hold
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
     * Sets the ship photo
     * @param shipPhotoFile - the link of the photo file
     */
    void setShipPhoto(String shipPhotoFile) {
        this.shipPhoto = new Image(shipPhotoFile);
    }


    int calculateValue() {
        int score = 0;
        for (Treasure t : treasures) {
            score = score + t.getValue();
        }
        return score;
    }
}
