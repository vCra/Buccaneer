package buccaneer.helpers;

import javafx.scene.image.Image;

import java.net.URISyntaxException;

/**
 * @author ADL13
 * @version 1.0
 * @PortImageHelper.java 03/05/2017
 * <p>
 * Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 * <p>
 * Helps with getting the right colour for the player's homeport
 */

public class PortImageHelper {
    /**
     * Returns the home port image based on the which player it is
     *
     * @param id - The id of the player
     * @return the colour of his home port
     */
    public static Image getPortImage(int id) {
        try {
            switch (id) {
                case 1:
                    return new Image(PortImageHelper.class.getResource("/images/bg/Town/BlackHouse.png").toURI().toString());
                case 2:
                    return new Image(PortImageHelper.class.getResource("/images/bg/Town/GreenHouse.png").toURI().toString());
                case 3:
                    return new Image(PortImageHelper.class.getResource("/images/bg/Town/RedHouse.png").toURI().toString());
                case 4:
                    return new Image(PortImageHelper.class.getResource("/images/bg/Town/YellowHouse.png").toURI().toString());
            }
        } catch (URISyntaxException e) {
            return null;
        }
        return null;
    }
}
