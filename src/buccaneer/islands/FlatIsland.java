package buccaneer.islands;

import buccaneer.GUI.ErrorMessage;
import buccaneer.GUI.ItemGained;
import buccaneer.cards.CrewCard;
import buccaneer.helpers.Position;
import buccaneer.helpers.Receivable;
import buccaneer.main.Player;
import buccaneer.treasure.Treasure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
        ArrayList<Treasure> treasure = new ArrayList<>(treasures);
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

    public void trade(Player currentPlayer) {
        ArrayList<Receivable> l = new ArrayList<>();
        l.addAll(getCrewCards());

        sortTreasure();

        for (int freeSpace = currentPlayer.getPlayerShip().freeSpace(); freeSpace != 0; freeSpace--) {
            if (treasures.size() >= 1) {
                l.add(treasures.get(1));
            }
        }
        currentPlayer.getCrewCards().addAll(getCrewCards());
        if (l.size() == 0) {
            ErrorMessage.display("You landed at flat island, but they it turns out its deserted. Arrrr!!!");
        } else {
            ItemGained.display(l);
        }
    }

    private void sortTreasure() {
        Collections.sort(treasures, new Comparator<Treasure>() {
            @Override
            public int compare(Treasure t1, Treasure t2) {
                return t1.getValue() - t2.getValue();
            }
        });
    }
}
