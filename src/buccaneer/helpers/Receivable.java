package buccaneer.helpers;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import java.awt.image.BufferedImage;
/**
 * @Receivable.java 23/04/2017
 *
 * Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 *
 * Gets the image for the receivable treasures
 *
 * @author AAW13
 * @version
 */

public class Receivable {
    public BufferedImage image;

    public Image getImage() {
        return SwingFXUtils.toFXImage(image, null);
    }
}
