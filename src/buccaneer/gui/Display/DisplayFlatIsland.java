package buccaneer.gui.Display;

import buccaneer.helpers.Tradeable;
import buccaneer.islands.FlatIsland;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * @author adl24
 * @version 1.0
 * @DisplayFlatIsland.java 03/05/2017
 * <p>
 * Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 * <p>
 * Handles all the Display FlatIsland  UI
 */

public class DisplayFlatIsland {
    /**
     * Displays the contents of flatIsland
     *
     * @param island - Flat Island
     */
    public static void display(FlatIsland island) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        Font pirateFont = Font.loadFont(DisplayFlatIsland.class.getResource("/fonts/keelhauled-bb.regular.ttf").toExternalForm(), 18);
        Font titlePirateFont = Font.loadFont(DisplayFlatIsland.class.getResource("/fonts/keelhauled-bb.regular.ttf").toExternalForm(), 30);

        window.setTitle("Flat Island");
        Label title = new Label("Flat Island");
        title.setFont(titlePirateFont);

        ImageView imageView;

        GridPane gridPane = new GridPane();
        int x = 0;
        int y = 0;
        ArrayList<Tradeable> tradable = new ArrayList<>();
        tradable.addAll(island.getTreasures());
        tradable.addAll(island.getCrewCards());

        for (Tradeable i : tradable) {
            imageView = new ImageView(i.getImage());
            imageView.setFitWidth(90);
            imageView.setFitHeight(90);
            imageView.setSmooth(true);
            imageView.setCache(true);
            imageView.setMouseTransparent(true);
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

        HBox info = new HBox(20);
        info.setAlignment(Pos.CENTER);

        Label noItems = new Label("Arr! All the boot has gone me matey!");
        noItems.setFont(pirateFont);

        VBox layout = new VBox(20);
        if (tradable.size() == 0) {
            layout.getChildren().addAll(title, info, noItems);
        } else {
            layout.getChildren().addAll(title, info, scrollPane);
        }
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 500, 500);
        window.setScene(scene);
        window.show();
    }

}
