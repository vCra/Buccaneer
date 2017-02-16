package islands;

import java.util.ArrayList;

import cards.CrewCard;
import helpers.Position;
import treasure.Treasure;

/**
 * Created by awalker on 04/02/2017.
 */
public class FlatIsland extends Island
{
	private ArrayList<Treasure> treasures;
	private ArrayList<CrewCard> crewCards;

	/**
	 * Constructor.
	 * Calls the super constructor with startPos and endPos.
	 * Creates ArrayLists for treasure and crewCards.
	 * @param startPos
	 * @param endPos
	 */
	public FlatIsland (Position startPos, Position endPos)
	{
		super (startPos, endPos);
		
		treasures = new ArrayList<Treasure>();
		crewCards = new ArrayList<CrewCard>();
	}

	/**
	 * Returns treasures on the island.
	 * @return treasures
	 */
	public int[] getTreasures()
	{
		int[] treasure = new int[5];
		
		return treasure;
	}

	/**
	 * Returns crewCards on the island.
	 * @return crewCards
	 */
	public ArrayList<CrewCard> getCrewCards()
	{
		return crewCards;
	}
	
	/**
	 * Adds a treasure to the island.
	 * @param treasure
	 */
	public void addTreasure (Treasure treasure)
	{
		treasures.add(treasure);		
	}
	
	/**
	 * Adds crewCard to the island.
	 * @param crewCard
	 */
	public void addCrewCard (CrewCard crewCard)
	{
		crewCards.add(crewCard);
	}
}
