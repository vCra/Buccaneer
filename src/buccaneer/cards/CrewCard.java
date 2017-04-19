package buccaneer.cards;

import buccaneer.enumData.CardColor;
import buccaneer.helpers.Tradeable;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import static com.sun.corba.se.spi.activation.IIOP_CLEAR_TEXT.value;

/**
 * Crew Card
 */
//TODO: Manage storing of buccaneer.cards and card data/methods
//TODO: Javadoc

public class CrewCard extends Tradeable implements CardObject {
    private int id;
    private CardColor color;
    private BufferedImage image;

    public CrewCard(int id, CardColor color, int value) {
        this.id = id;
        this.color = color;
        super.setValue(value);
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

    public Image getImage() {
        return SwingFXUtils.toFXImage(image, null);
    }
}
