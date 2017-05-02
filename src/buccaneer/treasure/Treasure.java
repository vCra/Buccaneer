package buccaneer.treasure;

import buccaneer.GUI.ErrorMessage;
import buccaneer.enumData.TreasureType;
import buccaneer.helpers.Tradeable;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

//TODO: Javadoc


/**
 * Treasure object
 */
public class Treasure extends Tradeable {
    private TreasureType type;
    //TODO: getters and setters for treasure properties

    public Treasure(TreasureType t){
        type = t;
        super.setValue(t.getValue());
        super.image = null;
        loadImage();
    }

    /**
     * Returns the type of treasure
     * @return the treasure type
     */
    public TreasureType getType() {
        return type;
    }

    /**
     * Set the type of the treasure
     * @param type - the type that is being set
     */
    public void setType(TreasureType type) {
        this.type = type;
    }

    /**
     * Returns the name of the treasure e.g. Diamond
     * @return the string name of the treasure
     */
    public String getFriendlyName() {
        return this.type.getName();
    }


    /**
     * Loads a Java AWT image into super.image based on the treasure type.
     * If the type of treasure is not defined then this won't work, so make sure to call this after the treasure
     * type has been set
     * Access the image via super.getImage(), which will return a javaSwing image.
     */
    private void loadImage() {
        try {
            File file;
            switch (this.getType()) {
                case RUM:
                    file = new File(getClass().getResource("/images/treasure/barrel.png").toURI());
                    super.image = ImageIO.read(file);
                    break;
                case DIAMOND:
                    file = new File(getClass().getResource("/images/treasure/diamond.png").toURI());
                    super.image = ImageIO.read(file);
                    break;
                case GOLD:
                    file = new File(getClass().getResource("/images/treasure/gold.png").toURI());
                    super.image = ImageIO.read(file);
                    break;
                case PEARL:
                    file = new File(getClass().getResource("/images/treasure/pearl.png").toURI());
                    super.image = ImageIO.read(file);
                    break;
                case RUBY:
                    file = new File(getClass().getResource("/images/treasure/ruby.png").toURI());
                    super.image = ImageIO.read(file);
                    break;
                default:
                    //does nothing to keep image as blank
            }
        } catch (IOException | URISyntaxException e) {
            ErrorMessage.display("Unable to load treasure image");
        }
    }
}
