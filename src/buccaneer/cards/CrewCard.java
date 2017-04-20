package buccaneer.cards;

import buccaneer.enumData.CardColor;
import buccaneer.helpers.Tradeable;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Crew Card
 */
//TODO: Manage storing of buccaneer.cards and card data/methods
//TODO: Javadoc

public class CrewCard extends Tradeable implements CardObject {
    private int id;
    private CardColor color;

    public CrewCard(int id, CardColor color, int value) {
        this.id = id;
        this.color = color;
        super.setValue(value);
        super.image = null;
        loadImage();
    }

    private void loadImage() {
        try {
            File file = new File(getClass().getResource("/images/crewcards/CrewCard_" + color + super.getValue() + ".png").toURI());
            super.image = ImageIO.read(file);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public int getID() {
        return id;
    }

    public CardColor getColor() {
        return color;
    }

}
