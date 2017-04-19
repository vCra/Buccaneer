package buccaneer.GUI;

import buccaneer.enumData.TreasureType;
import buccaneer.treasure.Treasure;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import java.net.URISyntaxException;
import java.util.ArrayList;

/**
 * Created by adam on 15/03/2017.
 */
//TODO: Javadoc

public class PlayersTreasureUI {

    //TODO: needs testing

    /**
     * Displays to the user what treasure they have in their ship at the moment does this via displaying anew window in front of the main board
     * @param player the current player
     */
    public static void display(buccaneer.main.Player player) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Treasure");

        Font pirateFont = Font.loadFont(PlayersTreasureUI.class.getResource("/fonts/keelhauled-bb.regular.ttf").toExternalForm(), 30);

        Label title = new Label("Treasure");
        title.setFont(pirateFont);

        ArrayList<Treasure> treasures = player.getPlayerShip().getTreasures();


        ImageView treasure1 = new ImageView();
        treasure1.setFitWidth(100);
        treasure1.setFitHeight(100);
        treasure1.setSmooth(true);
        treasure1.setCache(true);
        ImageView treasure2 = new ImageView();
        treasure2.setFitWidth(100);
        treasure2.setFitHeight(100);
        treasure2.setSmooth(true);
        treasure2.setCache(true);
        Label treasure1Name = new Label();
        Label treasure2Name = new Label();
        Boolean noTreasure = true;

        if (treasures.get(0) != null) {
            treasure1.setImage(getImage(treasures.get(0).getType()));
            treasure1Name.setText(treasures.get(0).getType().getName());
            noTreasure = false;
        }
        if (treasures.get(1) != null) {
            treasure2.setImage(getImage(treasures.get(1).getType()));
            treasure2Name.setText(treasures.get(1).getType().getName());
            noTreasure = false;
        }

        VBox treasure1Layout = new VBox(10);
        treasure1Layout.getChildren().addAll(treasure1, treasure1Name);
        treasure1Layout.setAlignment(Pos.CENTER);
        VBox treasure2Layout = new VBox(10);
        treasure2Layout.getChildren().addAll(treasure2, treasure2Name);
        treasure2Layout.setAlignment(Pos.CENTER);
        HBox images = new HBox(50);
        images.getChildren().addAll(treasure1Layout, treasure2Layout);
        images.setAlignment(Pos.CENTER);
        VBox layout = new VBox(30);
        if (noTreasure) {
            Label nullTreasure = new Label("No Treasure");
            nullTreasure.setFont(pirateFont);
            layout.getChildren().add(nullTreasure);
        } else {
            layout.getChildren().addAll(title, images);
        }
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout, 600, 600);
        window.setScene(scene);
        window.show();
    }

    /**
     * takes the name of the treasure that needs displaying and returns the image of that treasure
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
                case RUBIE:
                    treasureImage = new Image(PlayersTreasureUI.class.getResource("/images/treasure/ruby.png").toURI().toString());
                    break;
                default:
                    //does nothing to keep image as blank
            }
        }
        catch (URISyntaxException e) {
                System.err.println("Error: " + e);
        }
        return treasureImage;
    }

}
