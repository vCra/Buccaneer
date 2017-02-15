package main;

import enumData.Direction;
import helpers.Position;
import javafx.scene.image.Image;
import treasure.Treasure;


/**
 * Created by aaw13 on 02/02/2017.
 */
public class Ship implements GameObject {
    private Treasure[] treasures;
    private Player owner;
    private Position location;
    private Direction direction;
    private Image image;
    public Ship(Player owner) {
        this.owner = owner;
        this.treasures = new Treasure[2];
        this.setImage();

    }

    private void setImage(){
        try{
            image = new Image(getClass().getResource("/pirateship.jpeg").toURI().toString());
        } catch (java.net.URISyntaxException e){
            System.err.println("Unable to find file");
        }
    }
    public Treasure[] getTreasures() {
        return treasures;
    }

    public void setTreasures(Treasure[] treasures) {
        this.treasures = treasures;
    }

    public Position getLocation() {
        return location;
    }

    public void setLocation(Position location) {
        this.location = location;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }
}
