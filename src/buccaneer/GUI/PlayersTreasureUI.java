package buccaneer.GUI;

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

/**
 * Created by adam on 15/03/2017.
 */
//TODO: Javadoc

public class PlayersTreasureUI {

    //TODO: the treasure that the player has GUI

    public static void display(buccaneer.main.Player player) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Treasure");

        Font pirateFont = Font.loadFont(PlayersTreasureUI.class.getResource("/fonts/keelhauled-bb.regular.ttf").toExternalForm(), 30);

        Label title = new Label("Treasure");
        title.setFont(pirateFont);

        Treasure[] treasures = player.getPlayerShip().getTreasures();

        ImageView treasure1 = new ImageView();
        ImageView treasure2 = new ImageView();
        Label treasure1Name = new Label();
        Label treasure2Name = new Label();
        Boolean noTreasure = true;

        if (treasures[0] != null) {
            treasure1.setImage(getImage(treasures[0].getType().getName()));
            treasure1Name.setText(treasures[0].getType().getName());
            noTreasure = false;
        }
        if (treasures[0] != null) {
            treasure2.setImage(getImage(treasures[1].getType().getName()));
            treasure2Name.setText(treasures[1].getType().getName());
            noTreasure = false;
        }

        VBox treasure1Layout = new VBox(10);
        treasure1Layout.getChildren().addAll(treasure1, treasure1Name);
        VBox treasure2Layout = new VBox(10);
        treasure2Layout.getChildren().addAll(treasure2, treasure2Name);
        HBox images = new HBox(50);
        images.getChildren().addAll(treasure1Layout, treasure2Layout);
        images.setAlignment(Pos.CENTER);
        VBox layout = new VBox(30);
        if (noTreasure == true) {
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

    static Image getImage(String treasure) {
        Image treasureImage = null;
        try {
            switch (treasure) {
                case "Barrel of rum":
                    treasureImage = new Image(PlayersTreasureUI.class.getResource("/images/treasure/barrel.jpg").toURI().toString());
                    break;
                case "Diamond":
                    treasureImage = new Image(PlayersTreasureUI.class.getResource("/images/treasure/diamond.png").toURI().toString());
                    break;
                case "Bar of gold":
                    treasureImage = new Image(PlayersTreasureUI.class.getResource("/images/treasure/gold.jpg").toURI().toString());
                    break;
                case "Pearl":
                    treasureImage = new Image(PlayersTreasureUI.class.getResource("/images/treasure/pearl.png").toURI().toString());
                    break;
                case "Rubie":
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
