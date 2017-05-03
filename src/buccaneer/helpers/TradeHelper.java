package buccaneer.helpers;

import buccaneer.cards.CrewCard;
import buccaneer.main.Player;
import buccaneer.ports.Port;
import buccaneer.treasure.Treasure;

import java.util.ArrayList;
/**
 * @Score.java 22/04/2017
 *
 * Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 *
 * Helps with all the trade
 *
 * @author AAW13
 * @version
 */

public class TradeHelper {
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

    public static void trade(ArrayList<Tradeable> playerTake, ArrayList<Tradeable> portTake, Player player, Player port) {
        for (Tradeable t : playerTake) {
            if (t instanceof Treasure) {
                port.getPlayerShip().getTreasures().add((Treasure) t);
                player.getPlayerShip().removeTreasure((Treasure) t);
            } else if (t instanceof CrewCard) {
                port.getCrewCards().add((CrewCard) t);
                player.getCrewCards().remove(t);
            }
        }
        for (Tradeable t : portTake) {
            if (t instanceof Treasure) {
                player.getPlayerShip().addTreasure((Treasure) t);
                port.getPlayerShip().getTreasures().remove(t);
            } else if (t instanceof CrewCard) {
                player.addCrewCard((CrewCard) t);
                port.getCrewCards().remove(t);
            }
        }
    }

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
