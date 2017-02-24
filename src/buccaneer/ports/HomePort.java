package buccaneer.ports;

import buccaneer.main.Player;

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
	public HomePort (String name, Player player)
	{
		super(name);
		owner = null;
	}

	/**
	 *
	 * @param owner
	 */
	public void setOwner(Player owner) {
		this.owner = owner;
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
	 * Upon arrival at the port stores all the buccaneer.treasure
	 * if the player is the owner of the port.
	 */
	public void storeTreasure()
	{
		//TODO: store all the buccaneer.treasure owner has in its ship
	}
}
