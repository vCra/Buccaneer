package buccaneer.islands;

import buccaneer.cards.CardDeck;
import buccaneer.cards.ChanceCard;
import buccaneer.enumData.TreasureType;
import buccaneer.helpers.Position;
import buccaneer.treasure.Treasure;

import java.util.ArrayList;


/**
 * @author AAW13
 * @version 1.0
 * @TreasureIsland.java 02/02/2017
 * <p>
 * Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 * <p>
 * Handles all the Treasure Island functionality, which stores chance cards and treasures
 * @see Island
 */
public class TreasureIsland extends Island {
    private final CardDeck<ChanceCard> chanceCardDeck;
    private final ArrayList<Treasure> treasures;


    /**
     * Constructor.
     * Creates CardDeck for chanceCardDeck and an ArrayList of treasures.
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
     *
     * @param card the card to add to the card deck
     */
    public void addChanceCard(ChanceCard card) {
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


    /**
     * Get an array list of the treasures on the island
     *
     * @return An arraylist of treasures
     */
    public ArrayList<Treasure> getTreasures() {
        return treasures;
    }

    /**
     * Add a treasure
     *
     * @param treasure the treausre to add
     */
    public void addTreasure(Treasure treasure) {
        treasures.add(treasure);
    }

    /**
     * Removes a treasure
     *
     * @param treasure the treasure to remove
     * @return true if removed
     */
    public boolean removeTreasure(Treasure treasure) {
        return treasures.remove(treasure);
    }

    /**
     * Gets treasure of the type specified
     *
     * @param tt the type of treasure
     * @return A treasure (note it is not removed from the array)
     */
    public Treasure getTreasureOfType(TreasureType tt) {
        for (Treasure t : treasures) {
            if (t.getType().equals(tt)) {
                return t;
            }
        }
        return null;
    }

    /**
     * Gets a treasure of the value specified
     *
     * @param value the value of the treasure
     * @return A treasure (it does not remove it from the array)
     */
    public Treasure getTreasureOfValue(int value) {
        for (Treasure t : treasures) {
            if (t.getValue() == value) {
                treasures.remove(t);
                return t;
            }
        }
        return null;
    }

    /**
     * Returns the number of treasure on treasure
     *
     * @param value - Value of the treasure
     * @return The quantity of the treasure on treasure
     */
    public int qtyOfValue(int value) {
        int qty = 0;
        for (Treasure t : treasures) {
            if (t.getValue() == value) {
                qty = qty + 1;
            }
        }
        return qty;
    }

    /**
     * Adds treasure to Treasure Island
     *
     * @param value -
     * @return the treasure ArrayList
     */
    public ArrayList treasuresOfValue(int value) {
        ArrayList<Treasure> t = new ArrayList<>();

        switch (value) {
            case 2: //Need treasure adding up to 6
                if (qtyOfValue(3) >= 2) {
                    t.add(getTreasureOfValue(3));
                    t.add(getTreasureOfValue(3));
                } else {
                    t.add(getTreasureOfValue(2));
                    t.add(getTreasureOfValue(4));
                }
                break;
            case 3: //Need treasure adding up to 5
                t.add(getTreasureOfValue(5));
                break;
            case 4: //Need treasure adding up to 4
                if (qtyOfValue(4) > 0) {
                    t.add(getTreasureOfValue(4));
                } else {
                    t.add(getTreasureOfValue(2));
                    t.add(getTreasureOfValue(2));
                }
                break;

            case 5: //Need treasure adding up to 3;
                t.add(getTreasureOfValue(3));
                break;
            case 6: //Need treasure adding up to 2;
                t.add(getTreasureOfValue(2));
                break;
        }
        return t;
    }
}


