package buccaneer.islands;

import buccaneer.GUI.ErrorMessage;
import buccaneer.GUI.ItemGained;
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
 * @version
 * @see Island
 *
 */

public class FlatIsland extends Island {
    private ArrayList<Treasure> treasures;
    private ArrayList<CrewCard> crewCards;
    private ArrayList<Position> positions;

    /**
     * Constructor.
     * Creates ArrayLists for buccaneer.treasure and crewCards.
     *
     * @param startPos the top left hand corner of the island
     * @param endPos the bottom right corner of the island
     */
    public FlatIsland(Position startPos, Position endPos) {
        super(startPos, endPos);
        positions = new ArrayList<>();
        setPositions();
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

    public ArrayList<Position> getPositions() {
        return positions;
    }

    private void setPositions() {
        positions.add(new Position(2, 16));
        positions.add(new Position(2, 17));
        positions.add(new Position(2, 18));
        positions.add(new Position(2, 19));
        positions.add(new Position(3, 16));
        positions.add(new Position(3, 17));
        positions.add(new Position(3, 18));
        positions.add(new Position(3, 19));
        positions.add(new Position(4, 16));
        positions.add(new Position(4, 17));
        positions.add(new Position(4, 18));
        positions.add(new Position(4, 19));
    }

    private void sortTreasure() {
        treasures.sort(Comparator.comparingInt(Tradeable::getValue));
    }
}
