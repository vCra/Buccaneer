package buccaneer.islands;

import buccaneer.cards.CardDeck;
import buccaneer.cards.CrewCard;
import buccaneer.helpers.Position;
/**
 * @PirateIsland.java 02/02/2017
 *
 * Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 *
 * Handles all the Pirate Island functionality, which stores a deck of crew cards
 *
 * @author AAW13
 * @version
 * @see Island
 */

public class PirateIsland extends Island {
    private CardDeck<CrewCard> crewCardDeck;

    /**
     * Constructor.
     * Calls the super constructor with startPos and endPos.
     * Creates CardDeck for crewCardDeck.
     *
     */
    public PirateIsland() {
        super(new Position(17,2), new Position(19,5));
        crewCardDeck = new CardDeck<>();
        crewCardDeck.genCrewCards();
        crewCardDeck.shuffle();
    }

    /**
     * Returns a CrewCard from the front of the crewCardDeck.
     *
     * @return crewCard
     */
    public CrewCard getTopCard() {
        return crewCardDeck.removeCard();
    }

    public void returnCard (CrewCard card)
    {
        crewCardDeck.addCard(card);
    }
}
