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
 * @ChanceCardsInHand.java  02/05/2017
 *
 * Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 * <p>
 * Handles the chance card UI when in the player's hand
 *
 * @author adl24
 * @version 1.0
 */


public class ChanceCardsInHand {
    /**
     * Displays the chance cards in the player's hand
     *
     * @param player - The current player
     */

    static private int counter;
    static private ImageView largeChanceCard;

    public static void display(Player player) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Chance Cards");

        Font titlePirateFont = GUIHelper.getPirateFont(32);
        Font pirateFont = GUIHelper.getPirateFont(18);

        Label title = new Label(player.getName() + "'s Chance Cards");
        title.setFont(titlePirateFont);

        Label note = new Label("Black hooks are chance cards you have");
        note.setFont(pirateFont);

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

        largeChanceCard = new ImageView();
        largeChanceCard.setFitHeight(400);
        largeChanceCard.setFitWidth(300);
        largeChanceCard.setSmooth(true);
        largeChanceCard.setCache(true);
        largeChanceCard.setMouseTransparent(true);

        ArrayList<ChanceCard> playerChanceCards = new ArrayList<>();
        playerChanceCards.addAll(player.getChanceCards());

        GridPane gridPane = new GridPane();
        int x = 0;
        ImageView blankImageView;

        for (counter = 0; counter < 5; counter++) {
            imageView = new ImageView(greyHookImage);
            imageView.setFitHeight(100);
            imageView.setFitWidth(75);
            imageView.setSmooth(true);
            imageView.setCache(true);
            imageView.addEventFilter(MouseEvent.MOUSE_EXITED_TARGET, e -> {
                largeChanceCard.setImage(null);
            });
            hooks.add(imageView);
            if (counter <= 1) {
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
            }   else if (counter == 2) {
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
                hooks.get(0).addEventFilter(MouseEvent.MOUSE_ENTERED_TARGET, e -> {
                    try {
                        largeChanceCard.setImage(new Image(ChanceCardsInHand.class.getResource("/images/cards/chanceCards/21CC.png").toURI().toString()));
                    } catch (URISyntaxException e2) {
                        ErrorMessage.display("Error Loading Chance Card");
                    }
                });
            } else if (i.getID() == 23) {
                hooks.get(1).setImage(blackHookImage);
                hooks.get(1).addEventFilter(MouseEvent.MOUSE_ENTERED_TARGET, e -> {
                    try {
                        largeChanceCard.setImage(new Image(ChanceCardsInHand.class.getResource("/images/cards/chanceCards/23CC.png").toURI().toString()));
                    } catch (URISyntaxException e3) {
                        ErrorMessage.display("Error Loading Chance Card");
                    }
                });
            } else if (i.getID() == 24) {
                hooks.get(2).setImage(blackHookImage);
                hooks.get(2).addEventFilter(MouseEvent.MOUSE_ENTERED_TARGET, e -> {
                    try {
                        largeChanceCard.setImage(new Image(ChanceCardsInHand.class.getResource("/images/cards/chanceCards/24CC.png").toURI().toString()));
                    } catch (URISyntaxException e4) {
                        ErrorMessage.display("Error Loading Chance Card");
                    }
                });
            } else if (i.getID() == 25) {
                hooks.get(3).setImage(blackHookImage);
                hooks.get(3).addEventFilter(MouseEvent.MOUSE_ENTERED_TARGET, e -> {
                    try {
                        largeChanceCard.setImage(new Image(ChanceCardsInHand.class.getResource("/images/cards/chanceCards/25CC.png").toURI().toString()));
                    } catch (URISyntaxException e5) {
                        ErrorMessage.display("Error Loading Chance Card");
                    }
                });
            } else if (i.getID() == 26) {
                hooks.get(4).setImage(blackHookImage);
                hooks.get(4).addEventFilter(MouseEvent.MOUSE_ENTERED_TARGET, e -> {
                    try {
                        largeChanceCard.setImage(new Image(ChanceCardsInHand.class.getResource("/images/cards/chanceCards/26CC.png").toURI().toString()));
                    } catch (URISyntaxException e6) {
                        ErrorMessage.display("Error Loading Chance Card");
                    }
                });
            }
        }

        VBox noteAndGrid = new VBox(40);
        noteAndGrid.getChildren().addAll(note, gridPane);
        noteAndGrid.setAlignment(Pos.CENTER);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(noteAndGrid, largeChanceCard);

        gridPane.setAlignment(Pos.CENTER);
        VBox layout = new VBox(20);
        layout.getChildren().addAll(title, stackPane);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout, 600, 600);
        window.setScene(scene);
        window.show();
    }
}
