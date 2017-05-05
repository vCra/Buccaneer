package buccaneer.islands;

import buccaneer.GUI.ErrorMessage;
import buccaneer.GUI.ItemGainedOrLost;
import buccaneer.cards.CrewCard;
import buccaneer.helpers.Position;
import buccaneer.helpers.Receivable;
import buccaneer.helpers.Tradeable;
import buccaneer.main.Player;
import buccaneer.treasure.Treasure;

import java.util.ArrayList;
import java.util.Comparator;


/**
 * @FlatIsland.java 02/02/2017
 *
 * Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 *
 * Handles all the Flat Island functionality, which stores treasures and crew cards that people have deposited
 *
 * @author AAW13
 * @version 1.0
 * @see Island
 *
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
        ArrayList<CrewCard> cards = new ArrayList<>(crewCards);
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

    /**
     * Handles trading on Flat Island
     * @param currentPlayer - The player at Flat Island
     */
    public void trade(Player currentPlayer) {
        ArrayList<Receivable> l = new ArrayList<>();
        l.addAll(getCrewCards());
        currentPlayer.getCrewCards().addAll(getCrewCards());

        sortTreasure();

        while (currentPlayer.getPlayerShip().freeSpace() != 0) {
            if (treasures.size() > 0) {
                l.add(treasures.get(0));
                currentPlayer.getPlayerShip().getTreasures().add(treasures.get(0));
                treasures.remove(0);
            } else {
                break;
            }
        }


        if (l.size() == 0) {
            ErrorMessage.display("You landed at flat island, but they it turns out its deserted. Arrrr!!!");
        } else {
            ItemGainedOrLost.display(l, true, currentPlayer.getName());
        }
    }

    private void sortTreasure() {
        treasures.sort(Comparator.comparingInt(Tradeable::getValue));
    }
}
