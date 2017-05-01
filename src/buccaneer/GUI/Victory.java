package buccaneer.GUI;

import buccaneer.main.Player;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URISyntaxException;


/**
 * Created by adam on 15/03/2017.
 */
//TODO: Javadoc

public class Victory {

    //TODO: Victory GUI

    public static void display(Player player) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("VICTORY");

        Font pirateFontTitle = Font.loadFont(CrewCardsUI.class.getResource("/fonts/keelhauled-bb.regular.ttf").toExternalForm(), 64);

        Label title1 = new Label("THE WINNER IS");
        title1.setFont(pirateFontTitle);
        title1.setTextFill(Color.WHITE);
        Label title2 = new Label(player.getName());
        title2.setFont(pirateFontTitle);
        title2.setTextFill(Color.WHITE);

        ImageView shipImage = null;
        ImageView fireworks = null;
        try {
            shipImage = new ImageView(Victory.class.getResource("/images/shipsideviews/pirateship.jpeg").toURI().toString());
            shipImage.setFitWidth(400);
            shipImage.setFitHeight(400);
            shipImage.setSmooth(true);
            shipImage.setCache(true);
            fireworks = new ImageView(Victory.class.getResource("/images/endgame/fireworks-gif.gif").toURI().toString());
            fireworks.setFitWidth(1000);
            fireworks.setFitHeight(800);
            fireworks.setSmooth(true);
            fireworks.setCache(true);
        } catch (URISyntaxException e) {
            System.err.println("unable to locate image on victory screen");
        }

        VBox layout = new VBox(25);
        layout.getChildren().addAll(title1, title2, shipImage);
        layout.setAlignment(Pos.CENTER);
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(fireworks, layout);

        Scene scene = new Scene(stackPane, 1000,800);
        window.setScene(scene);
        window.show();
    }
}
