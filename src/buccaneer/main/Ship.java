package buccaneer.main;

import buccaneer.enumData.Direction;
import buccaneer.enumData.TreasureType;
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
        this.treasures.add(1, new Treasure(TreasureType.GOLD));
    }


    //TODO: Javadoc

    public Treasure getTreasure1() {
        return treasures.get(0);
    }

    public Treasure getTreasure2() {
        return treasures.get(1);
    }

    public ArrayList<Treasure> getTreasures() {
        return treasures;
    }

    public Treasure setTreasure1(Treasure t) {
        return treasures.set(0, t);
    }

    public Treasure setTreasure2(Treasure t) {
        return treasures.set(1, t);
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
