package buccaneer.GUI;

import buccaneer.cards.ChanceCard;
import buccaneer.main.Player;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URISyntaxException;
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

        Font pirateFont = GUIHelper.getPirateFont(32);
        Label title = new Label(player.getName() + "'s Chance Cards");
        title.setFont(pirateFont);

        ImageView imageView;
        ArrayList<ImageView> hooks = new ArrayList<>();
        Image greyHookImage = null;
        Image blackHookImage = null;
        try {
            greyHookImage = new Image(ChanceCardsInHand.class.getResource("/images/cards/chanceCards/greyHook.png").toURI().toString());
            blackHookImage = new Image(ChanceCardsInHand.class.getResource("/images/cards/chanceCards/blackHook.png").toURI().toString());
        } catch (URISyntaxException e) {
            ErrorMessage.display("Error loading hook image");
        }

        ArrayList<ChanceCard> playerChanceCards = new ArrayList<>();
        playerChanceCards.addAll(player.getChanceCards());

        GridPane gridPane = new GridPane();
        int x = 0;
        ImageView blankImageView;

        for (int i = 0; i < 5; i++) {
            imageView = new ImageView(greyHookImage);
            imageView.setFitHeight(100);
            imageView.setFitWidth(75);
            imageView.setSmooth(true);
            imageView.setCache(true);
            imageView.setMouseTransparent(true);
            hooks.add(imageView);
            if (i <= 1) {
                GridPane.setMargin(imageView, new Insets(0, 0, 10, 0));
                GridPane.setColumnIndex(imageView, x);
                GridPane.setRowIndex(imageView, 0);
                gridPane.getChildren().add(imageView);
                x++;
                blankImageView = new ImageView();
                blankImageView.setFitHeight(100);
                blankImageView.setFitWidth(75);
                GridPane.setColumnIndex(blankImageView, x);
                GridPane.setRowIndex(blankImageView, 0);
                gridPane.getChildren().add(blankImageView);
                x++;
            } else if (i == 2) {
                GridPane.setMargin(imageView, new Insets(0, 0, 10, 0));
                GridPane.setColumnIndex(imageView, x);
                GridPane.setRowIndex(imageView, 0);
                gridPane.getChildren().add(imageView);
                x++;
            } else {
                GridPane.setMargin(imageView, new Insets(10, 0, 0, 0));
                blankImageView = new ImageView();
                blankImageView.setFitHeight(100);
                blankImageView.setFitWidth(75);
                GridPane.setColumnIndex(blankImageView, x);
                GridPane.setRowIndex(blankImageView, 1);
                gridPane.getChildren().add(blankImageView);
                x++;
                GridPane.setColumnIndex(imageView, x);
                GridPane.setRowIndex(imageView, 1);
                gridPane.getChildren().add(imageView);
                x++;
            }
            if (x >= 5) {
                x = 0;
            }
        }

        for (ChanceCard i : playerChanceCards) {
            if (i.getID() == 21) {
                hooks.get(0).setImage(blackHookImage);
            } else if (i.getID() == 23) {
                hooks.get(1).setImage(blackHookImage);
            }  else if (i.getID() == 24) {
                hooks.get(2).setImage(blackHookImage);
            } else if (i.getID() == 25) {
                hooks.get(3).setImage(blackHookImage);
            } else if (i.getID() == 26) {
                hooks.get(4).setImage(blackHookImage);
            }
        }

        ImageView largeChanceCard = new ImageView();
        largeChanceCard.setFitHeight(400);
        largeChanceCard.setFitWidth(300);
        largeChanceCard.setSmooth(true);
        largeChanceCard.setCache(true);
        largeChanceCard.setMouseTransparent(true);
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(gridPane, largeChanceCard);

        gridPane.setAlignment(Pos.CENTER);
        VBox layout = new VBox(20);
        layout.getChildren().addAll(title, stackPane);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout, 600, 600);
        window.setScene(scene);
        window.show();

        gridPane.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {
            for (Node node : gridPane.getChildren()) {
                if (node.getBoundsInParent().contains(e.getX(), e.getY())) {
                    if (GridPane.getColumnIndex(node) == 0 && GridPane.getRowIndex(node) == 0) {

                    }
                }
            }
        });
        gridPane.addEventHandler(MouseEvent.MOUSE_EXITED, e -> {
            for (Node node : gridPane.getChildren()) {
                if (node.getBoundsInParent().contains(e.getX(), e.getY())) {

                }
            }
        });
    }

}
