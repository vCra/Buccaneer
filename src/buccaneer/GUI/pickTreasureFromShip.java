package buccaneer.GUI;

import buccaneer.main.Ship;
import buccaneer.treasure.Treasure;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URISyntaxException;
import java.util.ArrayList;

/**
 * Created by adam on 05/05/2017.
 */
public class pickTreasureFromShip {

    private static Treasure picked = null;

    public static Treasure display(Ship ship) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        window.setTitle("Pick another player");

        int size = ship.getNumOfTreasures();

        ImageView imageView = null;

        VBox treasure1 = new VBox(20);
        VBox treasure2 = new VBox(20);

        Button button1 = new Button();
        Button button2 = new Button();

        if (size == 1) {
            imageView = new ImageView(ship.getTreasures().get(0).getImage());
            imageView.setFitHeight(100);
            imageView.setFitWidth(100);
            imageView.setSmooth(true);
            imageView.setCache(true);
            treasure1.getChildren().addAll(imageView, button1);
        } else if (size == 2) {
            imageView = new ImageView(ship.getTreasures().get(0).getImage());
            imageView.setFitHeight(100);
            imageView.setFitWidth(100);
            imageView.setSmooth(true);
            imageView.setCache(true);
            treasure1.getChildren().addAll(imageView, button1);

            imageView = new ImageView(ship.getTreasures().get(1).getImage());
            imageView.setFitHeight(100);
            imageView.setFitWidth(100);
            imageView.setSmooth(true);
            imageView.setCache(true);
            treasure2.getChildren().addAll(imageView, button2);
        }

        button1.setOnAction(e -> {
            picked = ship.getTreasures().get(0);
            window.close();
        });

        button2.setOnAction(e -> {
            picked = ship.getTreasures().get(1);
            window.close();
        });

        Font pirateFont = Font.loadFont(PickAPlayer.class.getResource("/fonts/keelhauled-bb.regular.ttf").toExternalForm(), 22);

        Label title = new Label("Pick a treasure");
        title.setFont(pirateFont);

        HBox treasureLayout = new HBox(30);
        if (size == 1) {
            treasureLayout.getChildren().addAll(treasure1);
        } else if (size == 2) {
            treasureLayout.getChildren().addAll(treasure1, treasure2);
        }

        VBox layout = new VBox(20);
        layout.getChildren().addAll(title, treasureLayout);

        Scene scene = new Scene(layout, 400, 400);
        window.setScene(scene);
        window.showAndWait();

        return picked;
    }
}
