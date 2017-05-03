package buccaneer.GUI;

import buccaneer.helpers.Tradeable;
import buccaneer.ports.Port;
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

import java.security.acl.Owner;
import java.util.ArrayList;
/**
 * @Display.java  03/05/2017
 *
 * Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 *
 * Handles all the Display Port card UI
 *
 * @author adl24
 * @version 1.0
 */

public class DisplayPort {
    /**
     * Displays the contents of the ports
     * @param port - The port that is being displayed
     */
    public static void display(Port port) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        Font pirateFont = Font.loadFont(DisplayPort.class.getResource("/fonts/keelhauled-bb.regular.ttf").toExternalForm(), 18);
        Font titlePirateFont = Font.loadFont(DisplayPort.class.getResource("/fonts/keelhauled-bb.regular.ttf").toExternalForm(), 30);

        window.setTitle(port.getName());
        Label title = new Label(port.getName());
        title.setFont(titlePirateFont);

        ImageView imageView;

        Label owner = new Label();
        owner.setFont(pirateFont);
        if (port.getOwner() != null) {
            owner.setText("Owner: " + port.getOwner().getName());
        }

        Label valueOfTreasure = new Label("Value Of Treasure: " + Integer.toString(port.getTreasureValue()));
        valueOfTreasure.setFont(pirateFont);

        GridPane gridPane = new GridPane();
        int x = 0;
        int y = 0;
        ArrayList<Tradeable> tradable = new ArrayList<>();
        tradable.addAll(port.getTreasures());
        tradable.addAll(port.getCrewCards());

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
        info.getChildren().addAll(owner, valueOfTreasure);
        info.setAlignment(Pos.CENTER);

        Label noItems = new Label("There is no treasure");
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
