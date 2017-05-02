package buccaneer.GUI;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
/**
 * @AreYouSure.java  23/04/2017
 *
 * Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 *
 * Handles all the UI for when the user quits off a screen that they may not want to leave
 *
 * @author ADL24
 * @version
 */


public class AreYouSure {

    private static boolean bool;

    /**
     * A GUI box to let user change there mind used for when more perminent actions happen so the user has chance to
     * undo a mistake
     * @return bool. True for yes, false for no.
     */
    public static boolean display() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        window.setTitle("Are you sure?");

        Label text = new Label("Are you sure?");
        Button yes = new Button("Yes");
        Button no = new Button("No");

        window.setOnCloseRequest(e -> {
            e.consume();
        });

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
