package buccaneer.gui;

import buccaneer.main.Player;
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
 * @author ADL24
 * @AskToAttack.java 24/04/2017
 * <p>
 * Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 * <p>
 * Handles all the UI for when a player moves over another and is asked if they want ot attack
 */


public class AskToAttack {

    private static boolean bool;

    /**
     * A gui box to ask a player being moved through if they would like to attack
     *
     * @return bool. True for yes, false for no.
     */
    public static boolean display(Player toAsk, Player moving) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        window.setTitle("Attack?");

        Label text = new Label(toAsk.getName() + " would you like to attack " + moving.getName() + "?");
        Button yes = new Button("Yes");
        Button no = new Button("No");

        bool = false;

        window.setOnCloseRequest(Event::consume);

        yes.setOnAction(e -> {
            bool = true;
            window.close();
        });

        no.setOnAction(e -> {
            bool = false;
            window.close();
        });

        HBox buttons = new HBox(20);
        buttons.getChildren().addAll(yes, no);
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
