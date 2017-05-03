package buccaneer.main;

import buccaneer.cards.ChanceCard;
import buccaneer.cards.CrewCard;
import buccaneer.enumData.CardColor;
import buccaneer.helpers.Score;
import buccaneer.ports.Port;

import java.util.ArrayList;

/**
 * A entity that is playing the game
 */

//TODO: Add JavaDoc
//1 - black, 2-green, 3 - red, 4-yellow
public class Player {
    private final Score score;
    private final ArrayList<ChanceCard> chanceCards;
    private final ArrayList<CrewCard> crewCards;
    private int id;
    private String name;
    private Port port;
    private Ship playerShip;

    public Player(int id, String name) {
        this.setId(id);
        this.setName(name);
        score = new Score();
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

    public Score getScore() {
        return score;
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

    public void removeCrewCard(CrewCard crewCard) {
        crewCards.remove(crewCard);
    }

    public CrewCard removeSingleCrewCard()
    {
        return crewCards.remove(0);
    }

    public void addChanceCard(ChanceCard chanceCard) {
        this.chanceCards.add(chanceCard);
    }

    public ArrayList<ChanceCard> getChanceCards() {
        return chanceCards;
    }

    public int getMoveStrength() {
        int strength = 0;
        for (CrewCard c: crewCards){
            strength = strength + c.getValue();
        }
        if (strength == 0) {
            strength = 1;
        }
        return strength;
    }

    public int getAttackStrength() {
        int redTotal = 0;
        int blackTotal = 0;
        for (CrewCard i : crewCards) {
            if (i.getColor() == CardColor.Red) {
                redTotal += i.getValue();
            } else {
                blackTotal += i.getValue();
            }
        }
        if (redTotal > blackTotal) {
            return redTotal - blackTotal;
        } else {
            return blackTotal - redTotal;
        }
    }

}