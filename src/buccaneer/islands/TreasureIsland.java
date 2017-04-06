package buccaneer.islands;

import buccaneer.cards.CardDeck;
import buccaneer.cards.ChanceCard;
import buccaneer.enumData.TreasureType;
import buccaneer.helpers.Position;
import buccaneer.treasure.Treasure;

import java.util.ArrayList;

/**
 * TreasureIsland.java
 * Stores chance cards and treasures
 */

public class TreasureIsland extends Island {
    private CardDeck<ChanceCard> chanceCardDeck;
    private ArrayList<Treasure> treasures;


    /**
     * Constructor.
     * Creates CardDeck for chanceCardDeck and an ArrayList of treasures.
     *
     */
    public TreasureIsland() {
        super(new Position(9, 9), new Position(12, 12));

        chanceCardDeck = new CardDeck<>();
        chanceCardDeck.importFromFile();
        treasures = new ArrayList<>();
        genTreasures();
    }


    /**
     * Returns a ChanceCard from the front of the chanceCardDeck.
     *
     * @return chanceCard
     */
    public ChanceCard getTopCard() {
        return chanceCardDeck.removeCard();
    }

    /**
     * Adds a card to the card deck
     * @param card the card to add to the card deck
     */
    public void addChanceCard(ChanceCard card){
        chanceCardDeck.addCard(card);
    }

    /**
     * Returns how many Treasures there are on the island.
     *
     * @return the number of treasures
     */
    public int getTreasureQty() {
        return treasures.size();
    }

    /**
     * Generates 20 treasures and stores them in the arrayList of treasures
     * They are 4 treasures for each type of treasure.
     * This should be called when Treasure Island is Created
     */
    private void genTreasures() {
        for (TreasureType t : TreasureType.values()) {
            for (int i = 0; i < 4; i++) {
                treasures.add(new Treasure(t));
            }
        }
    }


    public ArrayList<Treasure> getTreasures() {
        return treasures;
    }

    public void addTreasure(Treasure treasure) {
        treasures.add(treasure);
    }

    public boolean removeTreasure(Treasure treasure) {
        return treasures.remove(treasure);
    }


}


