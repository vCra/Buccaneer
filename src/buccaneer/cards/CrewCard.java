package buccaneer.cards;

import buccaneer.enumData.CardColor;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Crew Card
 */
//TODO: Manage storing of buccaneer.cards and card data/methods
//TODO: Javadoc

public class CrewCard implements CardObject {
    private int id;
    private CardColor color;
    private int value;
    private BufferedImage image;

    public CrewCard(int id, CardColor color, int value) {
        this.id = id;
        this.color = color;
        this.value = value;
        this.image = null;
        loadImage();
    }

    private void loadImage() {
        try {
            File file = new File(getClass().getResource("/images/crewcards/CrewCard_" + color + value + ".png").toURI());
            image = ImageIO.read(file);
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

    public int getValue() {
        return value;
    }

    public Image getImage() {
        return SwingFXUtils.toFXImage(image, null);
    }
}
