package buccaneer.cards;

import buccaneer.enumData.CardColor;
import buccaneer.helpers.Tradeable;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * CardDeck.java 04/02/2017
 * <p>
 * Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 * <p>
 * Handles all the Crew card functionality.
 *
 * @author aaw13
 * @version 1.0
 * @see CardObject
 * @see Tradeable
 */
public class CrewCard extends Tradeable implements CardObject {
    private final int id;
    private final CardColor color;

    public CrewCard(int id, CardColor color, int value) {
        this.id = id;
        this.color = color;
        super.setValue(value);
        super.image = null;
        loadImage();
    }

    /**
     * Loads the Crew card image
     */
    private void loadImage() {
        try {
            File file = new File(getClass().getResource("/images/cards/crewcards/CrewCard_" + color + super.getValue() + ".png").toURI());
            super.image = ImageIO.read(file);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the ID of the crew card
     *
     * @return
     */
    public int getID() {
        return id;
    }

    /**
     * Returns the colour of the crew card
     *
     * @return crew card colour
     */
    public CardColor getColor() {
        return color;
    }

}
