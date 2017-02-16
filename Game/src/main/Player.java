package main;

import cards.ChanceCard;
import cards.CrewCard;
import helpers.Score;
import ports.HomePort;

/**
 * Created by aaw13 on 02/02/2017.
 */
//TODO: Add properties for player, as well as methods for interacting with it.
public class Player {
    private int id;
    private String name;
    private HomePort port;
    private Score score;
    private ChanceCard[] chanceCards;
    private CrewCard[] crewCards;
    private Ship playerShip;

    public Player(int id, String name) {
        this.id = id;
        score = new Score();
        this.playerShip = new Ship(this);

    }

    public Ship getPlayerShip() {
        return playerShip;
    }


    public void setPort(HomePort port) {
        this.port = port;
    }

    public void setPlayerShip(Ship playerShip) {
        this.playerShip = playerShip;
    }


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