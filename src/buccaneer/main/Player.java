package buccaneer.main;

import buccaneer.cards.ChanceCard;
import buccaneer.cards.CrewCard;
import buccaneer.helpers.Score;
import buccaneer.ports.Port;

import java.util.ArrayList;

/**
 * A entity that is playing the game
 */

//TODO: Add JavaDoc
//1 - black, 2-green, 3 - red, 4-yellow
public class Player {
    private int id;
    private String name;
    private Port port;
    private Score score;
    private ArrayList<ChanceCard> chanceCards;
    private ArrayList<CrewCard> crewCards;
    private Ship playerShip;

    public Player(int id, String name) {
        this.setId(id);
        this.setName(name);
        score = new Score(0);
        this.playerShip = new Ship(this);
        this.chanceCards = new ArrayList<>();
        this.crewCards = new ArrayList<>();
    }

    public Ship getPlayerShip() {
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

    int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public void addCrewCard(CrewCard crewCard) {
        this.crewCards.add(crewCard);
    }

    public ArrayList<CrewCard> getCrewCards() {
        return crewCards;
    }

    public void addChanceCard(ChanceCard chanceCard) {
        this.chanceCards.add(chanceCard);
    }

    public ArrayList<ChanceCard> getChanceCards() {
        return chanceCards;
    }

    //TODO: Add a function to calculate the move strength based on crew cards;
    public int getMoveStrength() {
        int strength = 0;
        for (CrewCard c: crewCards){
            strength = strength + c.getValue();
        }
        return strength;
    }

    //TODO: Add a function to calculate the Attack Strength based on crew cards;
    public int getAttackStrength() {
        return 5;
    }

}