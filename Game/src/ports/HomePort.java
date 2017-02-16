package ports;

import main.Player;

/**
 * Created by awalker on 04/02/2017.
 */
public class HomePort extends Port {
	private Player owner;
	
	/**
	 * Constructor.
	 * Takes a Player object which becomes the owner of the port,
	 * allowing him more functionality at this port.
	 * @param player becomes owner
	 */
	public HomePort (Player player)
	{
		super();
		owner = player;
	}
	
	/**
	 * Returns the owner of the port.
	 * @return owner
	 */
	public Player getOwner()
	{
		return owner;
	}

	/**
	 * Upon arrival at the port stores all the treasure
	 * if the player is the owner of the port.
	 */
	public void storeTreasure()
	{
		//TODO: store all the treasure owner has in its ship
	}
}
