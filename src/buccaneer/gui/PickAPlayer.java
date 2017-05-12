package buccaneer.gui;

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

import java.util.ArrayList;

/**
 * @author ADL24
 * @PickAPlayer.java 01/05/2017
 * <p>
 * Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 * <p>
 * Handles all the UI when a player picks another player
 */


public class PickAPlayer {

    private static int playerPicked = 0;

    /**
     * Displays when a player has to choose someone due to chance card effect
     *
     * @param player  - The player with the chance card
     * @param players - an array of players
     * @return the player picked
     */
    public static Player display(Player player, Player[] players) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        window.setTitle("Pick another player");

        ArrayList<Player> playerList = new ArrayList<>();
        Label[] names = new Label[3];
        ImageView[] ships = new ImageView[3];

        Font pirateFont = Font.loadFont(PickAPlayer.class.getResource("/fonts/keelhauled-bb.regular.ttf").toExternalForm(), 22);

        int c = 0;
        for (Player p : players) {
            if (!player.equals(p)) {
                playerList.add(p);
                names[c] = new Label(p.getName());
                names[c].setFont(pirateFont);
                ships[c] = new ImageView(p.getPlayerShip().getShipLargePhoto());
                ships[c].setFitWidth(100);
                ships[c].setFitHeight(100);
                ships[c].setSmooth(true);
                ships[c].setCache(true);
                c++;
            }

        }

        Button player1 = new Button("Pick This Player");
        player1.setOnAction(e -> {
            playerPicked = 0;
            window.close();
        });
        Button player2 = new Button("Pick This Player");
        player2.setOnAction(e -> {
            playerPicked = 1;
            window.close();
        });
        Button player3 = new Button("Pick This Player");
        player3.setOnAction(e -> {
            playerPicked = 2;
            window.close();
        });

        window.setOnCloseRequest(e -> {
            e.consume();
        });

        VBox player1Layout = new VBox(15);
        player1Layout.getChildren().addAll(names[0], ships[0], player1);
        player1Layout.setAlignment(Pos.CENTER);
        VBox player2Layout = new VBox(15);
        player2Layout.getChildren().addAll(names[1], ships[1], player2);
        player2Layout.setAlignment(Pos.CENTER);
        VBox player3Layout = null;
        if (players.length > 2) {
            player3Layout = new VBox(15);
            player3Layout.getChildren().addAll(names[2], ships[2], player3);
            player3Layout.setAlignment(Pos.CENTER);
        }


        HBox playersLayout = new HBox(15);
        playersLayout.setAlignment(Pos.CENTER);
        if (players.length > 2) {
            playersLayout.getChildren().addAll(player1Layout, player2Layout, player3Layout);
        } else {
            playersLayout.getChildren().addAll(player1Layout, player2Layout);
        }

        Label title = new Label("Pick a Player");
        title.setFont(pirateFont);

        VBox layout = new VBox(20);
        layout.getChildren().addAll(title, playersLayout);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 400, 400);
        window.setScene(scene);
        window.showAndWait();

        return playerList.get(playerPicked);
    }

}
