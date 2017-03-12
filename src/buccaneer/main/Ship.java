package buccaneer.main;

import buccaneer.enumData.Direction;
import buccaneer.helpers.Position;
import buccaneer.treasure.Treasure;
import javafx.scene.image.Image;

import java.net.URISyntaxException;


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
        try {
            this.shipPhoto = new Image(getClass().getResource("/images/ships/BlackShip.png").toURI().toString());
        } catch (URISyntaxException e) {
            System.err.println("Problem with highlight image");
        }    }

    public Treasure[] getTreasures() {
        return treasures;
    }

    public void setTreasures(Treasure[] treasures) {
        this.treasures = treasures;
    }

    public Position getLocation() {
        return square.getPosition();
    }

    public void setLocation(GameSquare square) {
        this.square = square;
    }

    GameSquare getSquare() {
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
        this.direction = direction;
    }

    Image getShipPhoto() {
        return shipPhoto;
    }

    public void setShipPhoto(Image shipPhoto) {
        this.shipPhoto = shipPhoto;
    }

    //TODO: add a method that checks if a ship can attack (aka they is another ship next to them)

    //TODO: Check if a ship has moved through another player

}
