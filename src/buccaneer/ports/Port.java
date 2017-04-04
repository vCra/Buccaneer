package buccaneer.ports;

import buccaneer.cards.CrewCard;
import buccaneer.enumData.Direction;
import buccaneer.helpers.Position;
import buccaneer.main.GameObject;
import buccaneer.main.GameSquare;
import buccaneer.main.Player;
import buccaneer.treasure.Treasure;

import java.util.ArrayList;

/**
 * Port Class - a port that is owned by a player
 */
public class Port implements GameObject {
    private Player owner;
    private String name;
    private ArrayList<Treasure> treasures;
    private ArrayList<CrewCard> crewCards;
    private Position position;

    /**
     * Constructor.
     * Takes a Player object which becomes the owner of the port,
     * allowing him more functionality at this port.
     *
     */
    public Port(String name, GameSquare s) {
        owner = null;
        this.name = name;
        treasures = new ArrayList<>();
        crewCards = new ArrayList<>();
        this.position = s.getPosition();
        s.setPort(this);
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
    public void storeTreasure(ArrayList<Treasure> treasures) {
        this.treasures.addAll(treasures);
        //TODO: store all the buccaneer.treasure owner has in its ship
    }

    public Position getLocation() {
        return position;
    }


    /**
     * Called upon arrival at the port.
     * Allows to trade or use buccaneer.cards if available.
     */
    public void trade() {
        //TODO: Implement trading functionality
    }

    //TODO: Javadoc


    public Direction getWaterFace(){
        switch (name){
            case "London":
                return Direction.E;
            case "Genoa":
                return Direction.N;
            case "Marseilles":
                return Direction.W;
            case "Cadiz":
                return Direction.S;
            default:
                return Direction.N;
        }
    }

    @Override
    public String toString() {
        return
                name + '\'' + " at " +
                        position;
    }

    public String getName() {
        return name;
    }
}
