package buccaneer.islands;

import buccaneer.helpers.Position;
import buccaneer.main.GameObject;

/**
 * @author AAW13
 * @version 1.0
 * @Island.java 02/02/2017
 * <p>
 * Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 * <p>
 * Island interface which contains generic methods for Islands
 */
public class Island implements GameObject {
    private final Position startPos;
    private final Position endPos;

    /**
     * Constructor.
     * Adds an ArrayList and fills it up with positions
     * between the parameters.
     * We really don't need this
     *
     * @param startPos The starting position of the island
     * @param endPos   The end position of the island
     */
    public Island(Position startPos, Position endPos) {
        this.startPos = startPos;
        this.endPos = endPos;
    }

    /**
     * Returns the start position of the island
     *
     * @return island start position
     */
    public Position getStartPos() {
        return startPos;
    }

    /**
     * Returns the end position of the island
     *
     * @return island end position
     */
    public Position getEndPos() {
        return endPos;
    }
}