package buccaneer.helpers;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import java.awt.image.BufferedImage;

/**
 * Created by awalker on 23/04/2017.
 */
public class Receivable {
    public BufferedImage image;

    public Image getImage() {
        return SwingFXUtils.toFXImage(image, null);
    }
}
