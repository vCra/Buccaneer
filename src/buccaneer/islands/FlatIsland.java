package buccaneer.islands;

import buccaneer.cards.CrewCard;
import buccaneer.helpers.Position;
import buccaneer.treasure.Treasure;

import java.util.ArrayList;

/**
 * FlatIsland.java
 * Stores treasures and crew cards that people have deposited
 */
public class FlatIsland extends Island {
    private ArrayList<Treasure> treasures;
    private ArrayList<CrewCard> crewCards;

    /**
     * Constructor.
     * Creates ArrayLists for buccaneer.treasure and crewCards.
     *
     * @param startPos the top left hand corner of the island
     * @param endPos the bottom right corner of the island
     */
    public FlatIsland(Position startPos, Position endPos) {
        super(startPos, endPos);

        treasures = new ArrayList<>();
        crewCards = new ArrayList<>();
    }

    /**
     * Returns treasures on the island.
     *
     * @return treasures
     */
    public ArrayList<Treasure> getTreasures() {
        return this.treasures;
    }

    public ArrayList<Treasure> getAndRemoveTreasure ()
    {
        ArrayList<Treasure> treasure = new ArrayList<Treasure>(treasures);
        treasures.clear();
        return treasure;
    }

    /**
     * Returns crewCards on the island.
     *
     * @return crewCards
     */
    public ArrayList<CrewCard> getCrewCards() {
        ArrayList<CrewCard> cards = new ArrayList<CrewCard>(crewCards);
        crewCards.clear();
        return cards;
    }

    /**
     * Adds a buccaneer.treasure to the island.
     *
     * @param treasure the treasure to add
     */
    public void addTreasure(Treasure treasure) {
        treasures.add(treasure);
    }

    /**
     * Adds crewCard to the island.
     *
     * @param crewCard the crew card to add
     */
    public void addCrewCard(CrewCard crewCard) {
        crewCards.add(crewCard);
    }
}
