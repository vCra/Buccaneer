package buccaneer.GUI;

import buccaneer.helpers.TurnTracker;
import buccaneer.main.Player;
import javafx.geometry.Pos;
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
 * Created by adam on 01/05/2017.
 */
public class PickAPlayer {

    private static int playerPicked = 0;

    public static Player display(Player player, TurnTracker turnTracker) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        window.setTitle("Pick another player");

        ArrayList<Player> players = new ArrayList<Player>();
        Label[] names = new Label[3];
        ImageView[] ships = new ImageView[3];

        Font pirateFont = Font.loadFont(CrewCardsUI.class.getResource("/fonts/keelhauled-bb.regular.ttf").toExternalForm(), 22);

        int c = 0;
        Player playerLoop;
        for (int i = 0; i < 3; i++) {
            playerLoop = turnTracker.getPlayerAtIndex(i);
            if (!player.equals(playerLoop)) {
                players.add(playerLoop);
                names[c] = new Label(playerLoop.getName());
                names[c].setFont(pirateFont);
                ships[c] = new ImageView(playerLoop.getPlayerShip().getShipLargePhoto());
                ships[c].setFitWidth(100);
                ships[c].setFitHeight(100);
                ships[c].setSmooth(true);
                ships[c].setCache(true);
            }
            c++;
        }

        Button player1 = new Button("Pick This Player");
        player1.setOnAction(e -> {
            playerPicked = 0;
        });
        Button player2 = new Button("Pick This Player");
        player2.setOnAction(e -> {
            playerPicked = 1;
        });
        Button player3 = new Button("Pick This Player");
        player3.setOnAction(e -> {
            playerPicked = 2;
        });

        VBox player1Layout = new VBox(15);
        player1Layout.getChildren().addAll(names[0], ships[0], player1);
        VBox player2Layout = new VBox(15);
        player2Layout.getChildren().addAll(names[1], ships[1], player2);
        VBox player3Layout = new VBox(15);
        player3Layout.getChildren().addAll(names[2], ships[2], player3);

        HBox playersLayout = new HBox(15);
        playersLayout.getChildren().addAll(player1Layout, player2Layout, player3Layout);

        Label title = new Label("Pick a Player");
        title.setFont(pirateFont);

        VBox layout = new VBox(20);
        layout.getChildren().addAll(title, playersLayout);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 400, 400);
        window.setScene(scene);
        window.showAndWait();

        return players.get(playerPicked);
    }

}
