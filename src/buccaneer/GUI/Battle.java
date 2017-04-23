package buccaneer.GUI;

import buccaneer.main.Player;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URISyntaxException;

/**
 * Created by adam on 15/03/2017.
 */
//TODO: Javadoc

public class Battle {

    public static void display(Player player1, Player player2) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Battle");

        Font pirateFont = Font.loadFont(CrewCardsUI.class.getResource("/fonts/keelhauled-bb.regular.ttf").toExternalForm(), 22);
        Font pirateFontTitle = Font.loadFont(CrewCardsUI.class.getResource("/fonts/keelhauled-bb.regular.ttf").toExternalForm(), 32);

        Label title = new Label("Battle");
        title.setFont(pirateFontTitle);
        Label name1 = new Label(player1.getName());
        Label name2 = new Label(player2.getName());
        name1.setFont(pirateFont);
        name2.setFont(pirateFont);

        Label p1Score = new Label("Attack Strength: " + Integer.toString(player1.getAttackStrength()));
        Label p2Score = new Label("Attack Strength: " + Integer.toString(player2.getAttackStrength()));
        p1Score.setFont(pirateFont);
        p2Score.setFont(pirateFont);

        ImageView ship1 = null;
        ImageView ship2 = null;
        try {
            ship1 = new ImageView(Battle.class.getResource("/images/pirateship.jpeg").toURI().toString());
            ship1.setFitWidth(100);
            ship1.setFitHeight(100);
            ship1.setSmooth(true);
            ship1.setCache(true);
            ship2 = new ImageView(Battle.class.getResource("/images/pirateship.jpeg").toURI().toString());
            ship2.setFitWidth(100);
            ship2.setFitHeight(100);
            ship2.setSmooth(true);
            ship2.setCache(true);
        } catch(URISyntaxException e) {

        }

        Label winner = new Label();
        if (player1.getAttackStrength() > player2.getAttackStrength()) {
            winner.setText("Winner is: " + player1.getName());
        } else if (player2.getAttackStrength() > player1.getAttackStrength()) {
            winner.setText("Winner is: " + player2.getName());
        } else {
            winner.setText("Draw");
        }
        winner.setFont(pirateFont);

        VBox p1Layout = new VBox(10);
        p1Layout.getChildren().addAll(name1, p1Score, ship1);
        p1Layout.setAlignment(Pos.CENTER);
        VBox p2Layout = new VBox(10);
        p2Layout.getChildren().addAll(name2, p2Score, ship2);
        p2Layout.setAlignment(Pos.CENTER);

        HBox playersLayout = new HBox(30);
        playersLayout.getChildren().addAll(p1Layout, p2Layout);
        playersLayout.setAlignment(Pos.CENTER);

        VBox layout = new VBox(20);
        layout.getChildren().addAll(title, p1Layout, winner);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 800, 800);
        window.setScene(scene);
        window.show();
    }
}




