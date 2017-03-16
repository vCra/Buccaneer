package buccaneer.main;

import buccaneer.cards.ChanceCard;
import buccaneer.cards.CrewCard;
import buccaneer.helpers.Score;
import buccaneer.ports.Port;

/**
 * A entity that is playing the game
 */

//TODO: Add JavaDoc plz
//TODO: Add properties for player, as well as methods for interacting with it.
public class Player {
    private int id;
    private String name;
    private Port port;
    private Score score;
    private ChanceCard[] chanceCards; //Should be arrayLists
    private CrewCard[] crewCards; //^^
    private Ship playerShip;

    public Player(int id, String name) {
        this.id = id;
        this.name = name;
        score = new Score(0);
        this.playerShip = new Ship(this);
    }

    Ship getPlayerShip() {
        return playerShip;
    }

    void setPlayerShip(Ship playerShip) {
        this.playerShip = playerShip;
    }

    public Port getPort() {
        return this.port;
    }

    public void setPort(Port port) {
        this.port = port;
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