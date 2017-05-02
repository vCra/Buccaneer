package buccaneer.GUI;

import buccaneer.cards.ChanceCard;
import buccaneer.main.Player;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * ChanceCardsInHand.java  02/05/2017
 *
 * Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 *
 * Handles the chance card UI when in the player's hand
 *
 * @author adl24
 * @version 1.0
 */


public class ChanceCardsInHand {
    /**
     * Displays the chance cards in the player's hand
     * @param player - The current player
     */

    public static void display(Player player) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Chance Cards");

        ArrayList<VBox> cards = new ArrayList<>();

        Font pirateFont = Font.loadFont(ChanceCardsInHand.class.getResource("/fonts/keelhauled-bb.regular.ttf").toExternalForm(), 20);

        Label cardText;
        Label cardNumber;
        VBox chanceCard;
        for (ChanceCard i : player.getChanceCards()) {
            cardNumber = new Label(Integer.toString(i.getID()));
            cardText = new Label(i.getText());
            cardNumber.setFont(pirateFont);
            cardText.setFont(pirateFont);
            cardText.setMaxWidth(80);
            cardText.setWrapText(true);
            chanceCard = new VBox(10);
            chanceCard.getChildren().addAll(cardNumber, cardText);
            cards.add(chanceCard);
        }

        HBox cardsLayout = new HBox(20);
        for (VBox i : cards) {
            cardsLayout.getChildren().add(i);
        }

        Label title = new Label(player.getName() + " Chance Cards");
        Label noChance = new Label("No Chance Cards");
        title.setFont(pirateFont);
        noChance.setFont(pirateFont);

        VBox layout = new VBox(15);
        if (cards.size() == 0) {
            layout.getChildren().addAll(title, noChance);
        } else {
            layout.getChildren().addAll(title, cardsLayout);
        }

        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 600, 600);
        window.setScene(scene);
        window.show();
    }

}
