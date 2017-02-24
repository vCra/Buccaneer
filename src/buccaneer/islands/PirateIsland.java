package buccaneer.islands;

import buccaneer.cards.CrewCard;
import buccaneer.helpers.CardDeck;
import buccaneer.helpers.Position;

/**
 * Created by awalker on 04/02/2017.
 */
public class PirateIsland extends Island
{
	private CardDeck<CrewCard> crewCardDeck;
	
	/**
	 * Constructor.
	 * Calls the super constructor with startPos and endPos.
	 * Creates CardDeck for crewCardDeck.
	 * @param startPos
	 * @param endPos
	 */
	public PirateIsland (Position startPos, Position endPos)
	{
		super(startPos, endPos);
		
		crewCardDeck = new CardDeck<CrewCard>();
	}

	/**
	 * Assigns a Queue of CrewCards to the crewCardDeck.
	 * @param crewCardDeck
	 */
	public void setCrewCardDeck(CardDeck<CrewCard> crewCardDeck)
	{
		this.crewCardDeck = crewCardDeck;
	}

	/**
	 * Returns a CrewCard from the front of the crewCardDeck.
	 * @return crewCard
	 */
	public CrewCard getTopCard()
	{
		return crewCardDeck.removeCard();
	}	
}
