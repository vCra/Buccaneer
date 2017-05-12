package buccaneer.gui.Display;

import buccaneer.islands.PirateIsland;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * @author adl24
 * @version 1.0
 * @DisplayTreasureIsland.java 03/05/2017
 * <p>
 * Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 * <p>
 * Handles all the Display TreasureIsland UI
 */

public class DisplayPirateIsland {
    /**
     * Displays the contents of Pirate
     *
     * @param island - Pirate Island
     */
    public static void display(PirateIsland island) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        Font pirateFont = Font.loadFont(DisplayPirateIsland.class.getResource("/fonts/keelhauled-bb.regular.ttf").toExternalForm(), 18);
        Font titlePirateFont = Font.loadFont(DisplayPirateIsland.class.getResource("/fonts/keelhauled-bb.regular.ttf").toExternalForm(), 30);

        window.setTitle("Pirate Island");
        Label title = new Label("Pirate Island");
        title.setFont(titlePirateFont);


        GridPane gridPane = new GridPane();
        int x = 0;
        int y = 0;

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(gridPane);
        scrollPane.setMaxSize(400, 400);

        HBox info = new HBox(20);
        info.setAlignment(Pos.CENTER);

        Label items = new Label("I spy a total of " + (island.getCrewCardDeck().getSize()) + " crew!");
        items.setFont(pirateFont);

        Label noItems = new Label("Arr! All the crew have run away!");
        noItems.setFont(pirateFont);

        VBox layout = new VBox(20);
        if (island.getCrewCardDeck().getSize() == 0) {
            layout.getChildren().addAll(title, info, noItems);
        } else {
            layout.getChildren().addAll(title, info, items);
        }
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 500, 500);
        window.setScene(scene);
        window.show();
    }

}
