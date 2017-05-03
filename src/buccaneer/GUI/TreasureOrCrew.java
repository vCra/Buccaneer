package buccaneer.GUI;

import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * TreasureOrCrew.java  23/04/2017
 * <p>
 * Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 * <p>
 * Asks if a user wants to select treasure or crew after landing at treasure island
 *
 * @author aaw13
 * @version 1.0
 */


public class TreasureOrCrew {

    private static boolean bool;

    /**
     * Displays the dialog box
     *
     * @return bool. True for Treasure, no for CrewCards
     */
    public static boolean display() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        window.setTitle("Treasure or crew cards?");

        Label text = new Label("Would you like to Select Treasure or CrewCards?");
        Button treasure = new Button("Treasure");
        Button crew = new Button("Crew Cards");

        window.setOnCloseRequest(Event::consume);

        treasure.setOnAction(e -> {
            bool = true;
            window.close();
        });

        crew.setOnAction(e -> {
            bool = false;
            window.close();
        });

        HBox buttons = new HBox(20);
        buttons.getChildren().addAll(treasure, crew);
        buttons.setAlignment(Pos.CENTER);
        VBox layout = new VBox(20);
        layout.getChildren().addAll(text, buttons);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 400, 200);
        window.setScene(scene);
        window.showAndWait();

        return bool;
    }

}
