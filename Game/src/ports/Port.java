package ports;

import cards.CrewCard;
import main.GameObject;
import treasure.Treasure;

import java.util.ArrayList;

/**
 * Created by aaw13 on 02/02/2017.
 */
//TODO: Add port methods and properties
public class Port implements GameObject {
	private ArrayList<Treasure> treasures;
	private ArrayList<CrewCard> crewCards;
	
	/**
	 * Constructor.
	 * Creates two ArrayList objects for
	 * holding Treasures and CrewCards.
	 */
	public Port()
	{
		treasures = new ArrayList<Treasure>();
		crewCards = new ArrayList<CrewCard>();
	}
	
	/**
	 * Called upon arrival at the port.
	 * Allows to trade or use cards if available.
	 */
	public void Trade()
	{
	    //TODO: Implement trading functionality		
	}
}
