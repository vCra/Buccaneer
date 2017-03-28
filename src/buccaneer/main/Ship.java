package buccaneer.main;

import buccaneer.enumData.Direction;
import buccaneer.helpers.Position;
import buccaneer.treasure.Treasure;
import javafx.scene.image.Image;


/**
 * A ship for a player.
 * Stores buccaneer.treasure, and
 */
public class Ship implements GameObject {
    private Treasure[] treasures;
    private Player owner;
    private GameSquare square;
    private Direction direction;
    private Image shipPhoto;

    public Ship(Player owner) {
        this.owner = owner;
        this.treasures = new Treasure[2];
    }


    //TODO: Javadoc

    public Treasure[] getTreasures() {
        return treasures;
    }

    public void addTreasures(Treasure[] treasures) {
        this.treasures = treasures;
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
