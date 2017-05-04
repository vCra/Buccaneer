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
 * @version 1.0
 */

public class Receivable {
    public BufferedImage image;

    /**
     * Returns the image of the receivable
     * @return image of receivable
     */
    public Image getImage() {
        return SwingFXUtils.toFXImage(image, null);
    }
}
