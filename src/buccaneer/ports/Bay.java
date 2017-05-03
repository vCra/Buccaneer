package buccaneer.ports;

import buccaneer.helpers.Position;
/**
 * Bay.java 30/03/2017
 *
 * Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 *
 * Handles all the functionality of the bays
 *
 * @author aaw13
 * @version 1.0
 */

public class Bay {
    private String name;
    private Position position;
    public Bay(String name, Position position) {
        this.name = name;
        this.position = position;
    }
    /**
     * Returns the name of the bay
     * @return name of bay
     */
    public String getName() {
        return name;
    }

    /**
     * Checks if the trade is a valid trade
     * @param name - The name the port is being set to
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the position of the bay
     * @return the position of the bay
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Checks if the trade is a valid trade
     * @param position - The position that is being set
     */
    public void setPosition(Position position) {
        this.position = position;
    }
}
