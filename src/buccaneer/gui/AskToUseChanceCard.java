package buccaneer.gui;

import buccaneer.cards.ChanceCard;
import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * @author adl24
 * @version 1.0
 * @AsktoUseChanceCard.java 02/05/2017
 * <p>
 * Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 * <p>
 * Handles all the UI for when asked to use a chance card in a port
 */


public class AskToUseChanceCard {

    private static boolean bool = false;

    /**
     * Displays when the user uses a chance card in port
     *
     * @param chanceCard - The chance card being used
     * @return bool
     */
    public static boolean display(ChanceCard chanceCard, String name) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        window.setTitle("Use The Chance Card?");

        Label title = new Label("Would you like to use " + name + "?");
        ImageView chanceCardText = new ImageView(chanceCard.getTextImage());
        chanceCardText.setFitHeight(400);
        chanceCardText.setFitWidth(300);
        Button yes = new Button("Yes");
        Button no = new Button("No");

        window.setOnCloseRequest(Event::consume);

        yes.setOnAction(e -> {
            bool = true;
            window.close();
        });

        no.setOnAction(e -> {
            bool = false;
            window.close();
        });

        HBox buttons = new HBox(50);
        buttons.getChildren().addAll(yes, no);
        buttons.setAlignment(Pos.CENTER);
        VBox layout = new VBox(20);
        layout.getChildren().addAll(title, chanceCardText, buttons);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 600, 600);
        window.setScene(scene);
        window.showAndWait();

        return bool;
    }

}
