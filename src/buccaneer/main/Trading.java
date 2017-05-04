package buccaneer.main;

import buccaneer.cards.CrewCard;
import buccaneer.helpers.Tradeable;
import buccaneer.ports.Port;
import buccaneer.treasure.Treasure;

import java.util.ArrayList;

/**
 * Trading 22/04/2017
 *
 * Helps with the GUI trading, and allows a player to trade Tradables
 * with a port
 *
 * @author aaw13
 * @version 1.0
 * @see buccaneer.GUI.Trading
 */
public class Trading {

    /**
     * Allows the player to trade with another port
     * @param player - The player making the trade
     * @param playerTake - the items the player can trade for
     * @param port - The port that is trading with the player
     * @param portTake - The crew cards or treasure the port receives
     */
    public static void trade(ArrayList<Tradeable> playerTake, ArrayList<Tradeable> portTake, Player player, Port port) {
        for (Tradeable t : playerTake) {
            if (t instanceof Treasure) {
                port.getTreasures().add((Treasure) t);
                player.getPlayerShip().removeTreasure((Treasure) t);
            } else if (t instanceof CrewCard) {
                port.getCrewCards().add((CrewCard) t);
                player.getCrewCards().remove(t);
            }
        }
        for (Tradeable t : portTake) {
            if (t instanceof Treasure) {
                player.getPlayerShip().addTreasure((Treasure) t);
                port.getTreasures().remove(t);
            } else if (t instanceof CrewCard) {
                player.addCrewCard((CrewCard) t);
                port.getCrewCards().remove(t);
            }
        }
    }

    /**
     * Checks if the trade is a valid trade
     * @param ts1 - Item 1 that is being traded
     * @param ts2 - Item 2 that is being traded
     */
    public static boolean tradeIsValid(ArrayList<Tradeable> ts1, ArrayList<Tradeable> ts2) {
        int value = 0;
        for (Tradeable t : ts1) {
            value += t.getValue();
        }
        for (Tradeable t : ts2) {
            value -= t.getValue();
        }
        return value == 0;
    }
}
