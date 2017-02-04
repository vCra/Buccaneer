package main;

import helpers.Position;

/**
 * Created by aaw13 on 02/02/2017.
 */
//TODO: Add properties for player, as well as methods for interacting with it.
public class Player {
    private int id;
    private String name;
    private Position location;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
