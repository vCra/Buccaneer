package buccaneer.GUI;

import buccaneer.enumData.TreasureType;
import javafx.scene.image.Image;
import javafx.scene.text.Font;

import java.net.URISyntaxException;

/**
 * Created by awalker on 18/04/2017.
 */
public class GUIHelper {

    /**
     * takes the name of the treasure that needs displaying and returns the image of that treasure
     *
     * @param treasure the treasure to display
     * @return the image of the treasure
     */
    static Image getImage(TreasureType treasure) {
        Image treasureImage = null;
        try {
            switch (treasure) {
                case RUM:
                    treasureImage = new Image(PlayersTreasureUI.class.getResource("/images/treasure/barrel.png").toURI().toString());
                    break;
                case DIAMOND:
                    treasureImage = new Image(PlayersTreasureUI.class.getResource("/images/treasure/diamond.png").toURI().toString());
                    break;
                case GOLD:
                    treasureImage = new Image(PlayersTreasureUI.class.getResource("/images/treasure/gold.png").toURI().toString());
                    break;
                case PEARL:
                    treasureImage = new Image(PlayersTreasureUI.class.getResource("/images/treasure/pearl.png").toURI().toString());
                    break;
                case RUBY:
                    treasureImage = new Image(PlayersTreasureUI.class.getResource("/images/treasure/ruby.png").toURI().toString());
                    break;
                default:
                    //does nothing to keep image as blank
            }
        } catch (URISyntaxException e) {
            ErrorMessage.display("Treasure image unable to load");
        }
        return treasureImage;
    }

    static Font getPirateFont(int size) {
        return Font.loadFont(GUIHelper.class.getResource("/fonts/keelhauled-bb.regular.ttf").toExternalForm(), size);
    }
}
