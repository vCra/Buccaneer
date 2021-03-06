package buccaneer.cards;

/**
 * CardDeck.java 04/04/2017
 * <p>
 * Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 * <p>
 * The interface for each of the cards
 *
 * @author aaw13
 * @version 1.0
 *          <p>
 *          A card object is an interface that all cards within the game should implament
 *          Card objects are primeraly are designed so that cards can be stored within a CardDeck
 *          which contains an ArrayList of CardObjects, as well as supporting methods
 *          <p>
 *          All Card Objects must have a uniquely identifying ID, which should be used to identify
 *          that specific card.
 */
interface CardObject {
    int getID();
}
