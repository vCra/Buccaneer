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

    public void addTreasure(Treasure t) {
        if (freeSpace() == 2) {
            treasures.add(0, t);
        } else if (freeSpace() == 1) {
            treasures.add(1, t);
        } else {
            System.err.print("ahh fuck");
        }
        owner.getScore().addToScore(t.getValue());
    }

    public void addTreasures(ArrayList<Treasure> t) {
        for (Treasure i : t) {
            addTreasure(i);
            owner.getScore().addToScore(i.getValue());
        }
    }

    public void removeTreasure(Treasure t) {
        owner.getScore().subFromScore(t.getValue());
        this.treasures.remove(t);

    }

    public ArrayList<Treasure> getTreasures() {
        return treasures;
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

    public int getNumOfTreasures() {
        int num = 0;
        for (Treasure i : treasures) {
            num++;
        }
        return num;
    }

    boolean canAttack() {
        return false;
    }

    int calculateValue() {
        int score = 0;
        for (Treasure t : treasures) {
            score = score + t.getValue();
        }
        return score;
    }
}
