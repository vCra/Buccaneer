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

    public Treasure[] getTreasures() {
        return treasures;
    }

    public void setTreasures(Treasure[] treasures) {
        this.treasures = treasures;
    }

    public Position getLocation() {
        return square.getPosition();
    }

    public void setLocation(Position location) {
        this.square.remove(this);
        this.square = this.square.getBoard().moveShip(this, this.square.getPosition(), location);
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
        this.direction = direction;
    }

    public Image getShipPhoto() {
        return shipPhoto;
    }

    public void setShipPhoto(Image shipPhoto) {
        this.shipPhoto = shipPhoto;
    }
}
