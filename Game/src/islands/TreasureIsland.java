package islands;

import java.util.ArrayList;
import java.util.Queue;

import cards.ChanceCard;
import cards.CrewCard;
import helpers.CardDeck;
import helpers.Position;
import treasure.Treasure;

/**
 * Created by awalker on 04/02/2017.
 */

public class TreasureIsland extends Island
{
	private CardDeck<ChanceCard> chanceCardDeck;
	private ArrayList<Treasure> treasures;
	
	/**
	 * Constructor.
	 * Calls the super constructor with startPos and endPos.
	 * Creates CardDeck for chanceCardDeck and an ArrayList of treasures.
	 * @param startPos
	 * @param endPos
	 */
	public TreasureIsland (Position startPos, Position endPos)
	{
		super(startPos, endPos);
		
		chanceCardDeck = new CardDeck<ChanceCard>();
		treasures = new ArrayList<Treasure>();
	}

	/**
	 * Assigns ChanceCards to chanceCardDeck.
	 * @param chanceCardDeck
	 */
	public void setChanceCardDeck(CardDeck<ChanceCard> chanceCardDeck) {
		this.chanceCardDeck = chanceCardDeck;
	}

	/**
	 * Assigns treasure objects to treasures.
	 * @param treasures
	 */
	public void setTreasures(ArrayList<Treasure> treasures) {
		this.treasures = treasures;
	}
	
	/**
	 * Returns a ChanceCard from the front of the chanceCardDeck.
	 * @return crewCard
	 */
	public ChanceCard getTopCard()
	{
		return chanceCardDeck.removeCard();
	}
	
	/**
	 * Returns how many Treasures there are on the island.
	 * @return 
	 */
	public int[] getTreasures()
	{
		int[] treasure = new int[5];
		
		return treasure;
	}
}
