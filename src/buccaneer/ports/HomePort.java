package buccaneer.ports;

import buccaneer.main.GameSquare;
import buccaneer.main.Player;

/**
 * HomePort Class - a port that is owned by a player
 */
public class HomePort extends Port {
    private Player owner;

    /**
     * Constructor.
     * Takes a Player object which becomes the owner of the port,
     * allowing him more functionality at this port.
     *
     * @param player becomes owner
     */
    public HomePort(String name, Player player, GameSquare s) {
        super(name, s);
        owner = player;
    }

    /**
     * Returns the owner of the port.
     *
     * @return owner
     */
    public Player getOwner() {
        return owner;
    }

    /**
     * @param owner the owner of the port
     */
    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public Boolean isOwned(){
        return owner==null;
    }

    /**
     * Upon arrival at the port stores all the buccaneer.treasure
     * if the player is the owner of the port.
     */
    public void storeTreasure() {
        //TODO: store all the buccaneer.treasure owner has in its ship
    }
}
