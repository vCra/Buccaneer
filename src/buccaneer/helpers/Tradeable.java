package buccaneer.helpers;

/**
 * @author AAW13
 * @version 1.0
 * @Tradeable.java 19/04/2017
 * <p>
 * Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 * <p>
 * Helps all the tradable items
 * @see buccaneer.helpers.Receivable
 */
public class Tradeable extends Receivable {
    int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
