package buccaneer.islands;

import buccaneer.cards.CardDeck;
import buccaneer.cards.CrewCard;
import buccaneer.enumData.CardColor;
import buccaneer.helpers.Position;

/**
 * Pirate Island
 * Stores a deck of crew cards
 */
public class PirateIsland extends Island {
    private CardDeck<CrewCard> crewCardDeck;

    /**
     * Constructor.
     * Calls the super constructor with startPos and endPos.
     * Creates CardDeck for crewCardDeck.
     *
     * @param startPos the top left corner of the island
     * @param endPos the bottom right corner of the island
     */
    public PirateIsland(Position startPos, Position endPos) {
        super(startPos, endPos);
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


}
