package buccaneer.cards;

import buccaneer.enumData.CardColor;
import javafx.scene.image.Image;

import java.net.URISyntaxException;

/**
 * Crew Card
 */
//TODO: Manage storing of buccaneer.cards and card data/methods
//TODO: Implement card methods on board
//TODO: Implement card score calculator
//TODO: Javadoc

public class CrewCard implements CardObject {
    private int id;
    private CardColor color;
    private int value;
    private Image image;

    public CrewCard(int id, CardColor color, int value) {
        this.id = id;
        this.color = color;
        this.value = value;

        loadImage();
    }

    private void loadImage()
    {
        image = null;
        try
        {
            image = new Image(getClass().getResource("/images/crewcards/CrewCard_" + color + value + ".png").toURI().toString());
        }
        catch (URISyntaxException ioe)
        {
            System.out.println("Error " + ioe);
        }
    }

    public int getID() {
        return id;
    }

    public CardColor getColor() {
        return color;
    }

    public int getValue() {
        return value;
    }

    public Image getImage() { return image; }
}
