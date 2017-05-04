package buccaneer.GUI;

import buccaneer.helpers.Receivable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * @ItemGained.java  15/03/2017
 *
 * Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 *
 * Handles all the UI for when items are gained, e.g. Treasure and crew cards
 *
 * @author ADL24
 * @version 1.0
 */



//TODO: Javadoc

public class ItemGainedOrLost {

    //TODO: Gaining crew cards or treasure or anything else GUI
    /**
     * Displays to the user what treasure or crew card they have received
     * @param items - the items that are receivable
     */
    public static void display(ArrayList<Receivable> items, boolean gained) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);

        if (gained)
        {
            window.setTitle("You have gained an item");
        }
        else
        {
            window.setTitle("You have lost an item");
        }


        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        ImageView imageView;

        int x = 0;
        int y = 0;

        for (Receivable i : items){
            imageView = new ImageView(i.getImage());
            imageView.setFitWidth(90);
            imageView.setFitHeight(90);
            imageView.setSmooth(true);
            imageView.setCache(true);
            GridPane.setColumnIndex(imageView, x);
            GridPane.setRowIndex(imageView, y);
            GridPane.setMargin(imageView, new Insets(10, 10, 10, 10));
            gridPane.getChildren().add(imageView);

            x++;
            if (x >= 3) {
                x = 0;
                y++;
            }
        }

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(gridPane);
        scrollPane.setMaxSize(400, 400);

        Font pirateFontTitle = Font.loadFont(ItemGainedOrLost.class.getResource("/fonts/keelhauled-bb.regular.ttf").toExternalForm(), 32);

        Label title;
        if (gained)
        {
            title = new Label("You've Gained:");
        }
        else
        {
            title = new Label(("You've Lost:"));
        }
        title.setFont(pirateFontTitle);

        Button ok = new Button("Ok");
        ok.setOnAction(e -> {window.close();});

        VBox layout = new VBox(10);
        layout.getChildren().addAll(title, scrollPane, ok);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 500, 500);
        window.setScene(scene);
        window.show();
    }
}