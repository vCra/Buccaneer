package buccaneer.ports;

import buccaneer.cards.CrewCard;
import buccaneer.helpers.Position;
import buccaneer.main.GameObject;
import buccaneer.treasure.Treasure;

import java.util.ArrayList;

/**
 * A Basic port that is not owned by a player
 * Can store treasure and crew cards, and also has a position
 *
 */
//TODO: Add port methods and properties
public class Port implements GameObject {
    private String name;
    private ArrayList<Treasure> treasures;
    private ArrayList<CrewCard> crewCards;
    private Position position;

    /**
     * Constructor.
     * Creates two ArrayList objects for
     * holding Treasures and CrewCards.
     */
    public Port(String name) {
        this.name = name;
        treasures = new ArrayList<>();
        crewCards = new ArrayList<>();
    }

    @Override
    public Position getLocation() {
        return position;
    }

    /**
     * Called upon arrival at the port.
     * Allows to trade or use buccaneer.cards if available.
     */
    public void Trade() {
        //TODO: Implement trading functionality
    }
}
