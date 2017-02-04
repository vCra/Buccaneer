package main;

import treasure.Treasure;
import helpers.Position;


/**
 * Created by aaw13 on 02/02/2017.
 */
public class Ship implements GameObject {
    private Treasure[] treasures;
    private Player owner;
    private Position location;

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

    public Ship(Player owner) {
        this.owner = owner;
        this.treasures = new Treasure[2];
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }
}