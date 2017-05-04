package buccaneer.GUI;

import buccaneer.cards.ChanceCard;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
/**
 * @ChanceCardUI.java  15/03/2017
 *
 * Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 *
 * Handles all the UI to do with drawing and using Chance cards
 *
 * @author adl24
 * @version 1.0
 */
public class ChanceCardsUI {

    /**
     * Displays the chance card that the user received
     * @param chanceCard - The chance card
     */
    public static void display(ChanceCard chanceCard) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Chance Cards");

        Font pirateFont = GUIHelper.getPirateFont(18);
        Font pirateFontTitle = GUIHelper.getPirateFont(32);

        Label title1 = new Label("Chance Card");
        title1.setFont(pirateFontTitle);
        Label title2 = new Label("Chance Card " + chanceCard.getID());
        title2.setFont(pirateFontTitle);

        ImageView chanceCardText = new ImageView(chanceCard.getTextImage());
        chanceCardText.setFitWidth(300);
        chanceCardText.setFitHeight(400);
        chanceCardText.setSmooth(true);
        chanceCardText.setCache(true);

        Button ok = new Button("Ok");
        ok.setOnAction(e -> {
            window.close();
        });

        Label instruction = new Label("Click the chance card to reveal what you got");
        instruction.setFont(pirateFont);

        ImageView imageView = new ImageView(chanceCard.getImage());
        imageView.setFitWidth(300);
        imageView.setFitHeight(400);
        imageView.setSmooth(true);
        imageView.setCache(true);
        imageView.setMouseTransparent(true);

        VBox layout1 = new VBox(15);
        layout1.getChildren().addAll(title1, imageView);
        layout1.setAlignment(Pos.CENTER);

        VBox layout2 = new VBox(15);
        layout2.getChildren().addAll(title2, chanceCardText, ok);
        layout2.setAlignment(Pos.CENTER);

        Scene scene1 = new Scene(layout1, 600, 600);
        Scene scene2 = new Scene(layout2, 600, 600);

        window.setScene(scene1);

        layout1.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> window.setScene(scene2));
        window.showAndWait();

    }
}
