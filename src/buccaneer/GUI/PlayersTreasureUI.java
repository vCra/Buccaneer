package buccaneer.GUI;

import buccaneer.treasure.Treasure;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
/**
 * @PlayersTreasureUI.java  15/03/2017
 *
 * Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 *
 * Handles all the UI for the treasure in the player's hold
 *
 * @author ALD24
 * @version
 */
public class PlayersTreasureUI {

    /**
     * Displays to the user what treasure they have in their ship at the moment does this via displaying
     * a new window in front of the main board
     * @param player the current player
     */
    public static void display(buccaneer.main.Player player) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Treasure");

        Font pirateFont = GUIHelper.getPirateFont(30);

        Label title = new Label(player.getName() + "'s Treasure");
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

        if (treasures.size() > 0) {
            treasure1.setImage(GUIHelper.getImage(treasures.get(0).getType()));
            treasure1Name.setText(treasures.get(0).getType().getName());
            noTreasure = false;
        }
        if (treasures.size() > 1) {
            treasure2.setImage(GUIHelper.getImage(treasures.get(1).getType()));
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


}
