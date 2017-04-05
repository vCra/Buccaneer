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
        this.treasures = new ArrayList<>(2);
        this.treasures.add(0, null);
        this.treasures.add(1, null);
    }

    public int freeSpace() {
        int count = 0;
        for (Treasure t : treasures)
            if (t != null)
                ++count;
        return 2 - count;
    }

    //TODO: Javadoc

    public void addTreasure(Treasure t) {
        if (freeSpace() == 2) {
            treasures.add(0, t);
        } else if (freeSpace() == 1) {
            treasures.add(1, t);
        } else {
            System.err.print("ahh fuck");
        }
    }

    public void addTreasures(ArrayList<Treasure> t) {
        for (Treasure i : t) {
            addTreasure(i);
        }
    }
    public ArrayList<Treasure> getTreasures() {
        return treasures;
    }



    public void clearTreasure(){
        this.treasures.set(0, null);
        this.treasures.set(1, null);
    }
    public Position getLocation() {
        return square.getPosition();
    }

    public void setLocation(GameSquare square) {
        this.square = square;
    }

    public GameSquare getSquare() {
        return this.square;
    }

    void setinitalLocation(GameSquare square) {
        this.square = square;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        if (direction != null) {
            this.direction = direction;
        }
    }

    Image getShipPhoto() {
        return shipPhoto;
    }

    void setShipPhoto(String shipPhotoFile) {
        this.shipPhoto = new Image(shipPhotoFile);
    }

    boolean canAttack() {
        return false;
    }
}
