package buccaneer.helpers;
/**
 * @Tradeable.java 19/04/2017
 *
 * Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 *
 * Helps all the tradable items
 *
 * @author AAW13
 * @version 1.0
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
