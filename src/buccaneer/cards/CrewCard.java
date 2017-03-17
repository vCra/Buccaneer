package buccaneer.cards;

import buccaneer.enumData.CardColor;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Crew Card
 */
//TODO: Manage storing of buccaneer.cards and card data/methods
//TODO: Implement card methods on board
//TODO: Implament card score calculator
public class CrewCard implements CardObject {
    private int id;
    private CardColor color;
    private int value;
    private BufferedImage image;

    public CrewCard(int id, CardColor color, int value) {
        this.id = id;
        this.color = color;
        this.value = value;

        loadImage();
    }

    private void loadImage()
    {
        int width = 186;
        int height = 216;

        image = null;
        File f = null;

        try
        {
            f = new File("res\\images\\crewcards\\CrewCard_" + color.toString() + value + ".png");
            image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            image = ImageIO.read(f);
        }
        catch (IOException ioe)
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

    public BufferedImage getImage() { return image; }
}
