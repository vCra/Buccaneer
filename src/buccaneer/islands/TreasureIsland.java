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
     * @param startPos the upper left pos of the island
     * @param endPos   the lower right location of the island
     */
    public TreasureIsland(Position startPos, Position endPos) {
        super(startPos, endPos);

        chanceCardDeck = new CardDeck<>();
        chanceCardDeck.importFromFile();
        treasures = new ArrayList<>();
        genTreasures();
    }


    /**
     * Returns a ChanceCard from the front of the chanceCardDeck.
     *
     * @return crewCard
     */
    public ChanceCard getTopCard() {
        return chanceCardDeck.removeCard();
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
    private void genTreasures(){
        for (TreasureType t : TreasureType.values()){
            for (int i = 0;i<4;i++){
                treasures.add(new Treasure(t));
            }
        }
    }
}


