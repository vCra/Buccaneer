package islands;

import java.util.ArrayDeque;
import java.util.Queue;

import cards.CrewCard;
import helpers.Position;

/**
 * Created by awalker on 04/02/2017.
 */
public class PirateIsland extends Island
{
	private Queue<CrewCard> crewCardDeck;
	
	/**
	 * Constructor.
	 * Calls the super constructor with startPos and endPos.
	 * Creates ArrayList for crewCardDeck.
	 * @param startPos
	 * @param endPos
	 */
	public PirateIsland (Position startPos, Position endPos)
	{
		super(startPos, endPos);
		
		crewCardDeck = new ArrayDeque<CrewCard>();
	}

	/**
	 * Assigns a Queue of CrewCards to the crewCardDeck.
	 * @param crewCardDeck
	 */
	public void setCrewCardDeck(Queue<CrewCard> crewCardDeck)
	{
		this.crewCardDeck = crewCardDeck;
	}

	/**
	 * Returns a CrewCard from the front of the crewCardDeck.
	 * @return crewCard
	 */
	public CrewCard getTopCard()
	{
		return crewCardDeck.poll();
	}
	
	
}
