package buccaneer.helpers;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import java.awt.image.BufferedImage;

/**
 * Created by awalker on 19/04/2017.
 */
public class Tradeable {

    public BufferedImage image;
    int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Image getImage() {
        return SwingFXUtils.toFXImage(image, null);
    }


}
