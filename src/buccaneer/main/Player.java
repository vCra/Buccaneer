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
    /**
     * Returns the player's ship
     * @return the player's ship
     */
    public Ship getPlayerShip() {
        return playerShip;
    }

    /**
     * Sets the player's ship
     * @param playerShip - the ship that is assigned to the player
     */

    void setPlayerShip(Ship playerShip) {
        this.playerShip = playerShip;
    }

    /**
     * Returns the port
     * @return the port assigned to the player
     */
    public Port getPort() {
        return this.port;
    }

    /**
     * Set's the port of the player
     * @param port - the port that is assigned to the player
     */
    public void setPort(Port port) {
        this.port = port;
    }

    /**
     * Returns the player ID
     * @return the id of the player
     */
    int getId() {
        return id;
    }

    /**
     * Sets the score of the player
     * @param score - the score of the player
     */
    public void setScore(Score score) {
        this.score = score;
    }

    /**
     * Returns the score of of the player
     * @return player's score
     */
    public Score getScore() {
        return score;
    }

    /**
     * Sets the score of the player
     * @param id - the score of the player
     */
    private void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the player ID
     * @return the name of the player
     */
    public String getName() {
        return name;
    }
    /**
     * Sets the name of the player
     * @param name - the name of the player
     */
    private void setName(String name) {
        this.name = name;
    }

    /**
     * Adds crew card to the player's hand
     * @param crewCard - The crew card being added to the player's hand
     */
    public void addCrewCard(CrewCard crewCard) {
        this.crewCards.add(crewCard);
    }

    /**
     * Sets the score of the player
     * @return the arraylist of player's crew cards
     */
    public ArrayList<CrewCard> getCrewCards() {
        return crewCards;
    }

    /**
     * Removes the inputted crew card
     * @param crewCard - the Crew card being removed
     */
    public void removeCrewCard(CrewCard crewCard) {
        crewCards.remove(crewCard);
    }

    /**
     * Adds the inputted chance card to the player's hand
     * @param chanceCard -  The chance card being added to the player's hand
     */
    public void addChanceCard(ChanceCard chanceCard) {
        this.chanceCards.add(chanceCard);
    }

    /**
     * Returns the player's chance cards
     * @return the arraylist of chance cards
     */
    public ArrayList<ChanceCard> getChanceCards() {
        return chanceCards;
    }

    /**
     * Returns how far the user can move from the total value of their crew cards
     * @return how far the user can move
     */
    public int getMoveStrength() {
        int strength = 0;
        for (CrewCard c: crewCards){
            strength = strength + c.getValue();
        }
        return strength;
    }

    /**
     * Returns the attack strength of the player calculate from the crew cards
     * @return attack strength
     */
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