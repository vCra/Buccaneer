package buccaneer.helpers;

import javafx.scene.image.Image;

import java.net.URISyntaxException;

/**
 * Created by adam on 03/05/2017.
 */
public class PortImageHelper {

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
