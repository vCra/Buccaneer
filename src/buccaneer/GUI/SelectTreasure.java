package buccaneer.GUI;

import buccaneer.main.Ship;
import buccaneer.treasure.Treasure;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URISyntaxException;
import java.util.ArrayList;

/**
 * @SelectTreasure.java 31/03/2017
 * <p>
 * Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 * <p>
 * Handles all the UI for selecting a treasure#
 *
 * @author ALD24
 * @version 1.0
 */


public class SelectTreasure {
    /**
     * Displays to the user what treasure they can select
     *
     * @param numOfTreasuresAllowed - integer
     */
    //TODO: Java Doc
    public static void display(int maxValueAllowed, int numOfTreasuresAllowed, ArrayList<Treasure> treasures, Ship playerShip) {
        if (playerShip.freeSpace() == 2) {
            display2(maxValueAllowed, numOfTreasuresAllowed, treasures, playerShip);
        } else {
            ErrorMessage.display("Arr - we can't store any more booty in the hold!");
        }
    }

    public static void display2(int maxValueAllowed, int numOfTreasuresAllowed, ArrayList<Treasure> treasures, Ship playerShip) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Select Treasure");

        Font pirateFont = Font.loadFont(SelectTreasure.class.getResource("/fonts/keelhauled-bb.regular.ttf").toExternalForm(), 30);

        Label title = new Label();
        title.setFont(pirateFont);
        title.setText("Select " + numOfTreasuresAllowed + " Treasure up to Value " + maxValueAllowed);

        Label treasureValue = new Label("The Value of the Treasure Selected is: 0");

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setMaxSize(500, 450);

        GridPane treasure = new GridPane();
        GridPane highlightGrid = new GridPane();
        ArrayList<ImageView> treasureImageViews = new ArrayList<>();
        ArrayList<ImageView> highlightImageViews = new ArrayList<>();

        ArrayList<Treasure> selected = new ArrayList<>();

        ImageView treasureTile;
        ImageView highlightTile;
        int x = 0;
        int y = 0;
        for (Treasure i : treasures) {
            treasureTile = new ImageView(GUIHelper.getImage(i.getType()));
            treasureTile.setFitWidth(100);
            treasureTile.setFitHeight(100);
            treasureTile.setSmooth(true);
            treasureTile.setCache(true);
            treasureTile.setMouseTransparent(true);
            GridPane.setColumnIndex(treasureTile, x);
            GridPane.setRowIndex(treasureTile, y);
            GridPane.setMargin(treasureTile, new Insets(10, 10, 10, 10));
            treasure.getChildren().add(treasureTile);
            treasureImageViews.add(treasureTile);

            highlightTile = new ImageView();
            highlightTile.setFitWidth(100);
            highlightTile.setFitHeight(100);
            highlightTile.setSmooth(true);
            highlightTile.setCache(true);
            GridPane.setColumnIndex(highlightTile, x);
            GridPane.setRowIndex(highlightTile, y);
            GridPane.setMargin(highlightTile, new Insets(10, 10, 10, 10));
            highlightGrid.getChildren().add(highlightTile);
            highlightImageViews.add(highlightTile);

            x++;
            if (x > 3) {
                x = 0;
                y++;
            }
        }
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(highlightGrid, treasure);

        scrollPane.setContent(stackPane);

        Button select = new Button("Select");

        select.setOnAction(e -> {
                    int value = 0;
                    for (Treasure i : selected) {
                        value += i.getValue();
                    }
                    if (value <= maxValueAllowed) {
                        treasures.removeAll(selected);
                        playerShip.addTreasures(selected);
                        window.close();
                    } else {
                        ErrorMessage.display("The value of the treasure you've selected is too high");
                    }
                }
        );

        window.setOnCloseRequest(e -> {
            Boolean confirm;
            confirm = AreYouSure.display();
            if (confirm == true) {
                window.close();
            } else {
                e.consume();
            }
        });

        VBox layout = new VBox(30);
        layout.getChildren().addAll(title, treasureValue, scrollPane, select);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout, 600, 600);
        window.setScene(scene);

        try {
            final Image highlight = new Image(PlayersTreasureUI.class.getResource("/images/tiles/highlightTreasure.png").toURI().toString());

            treasure.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
                for (Node node : treasure.getChildren()) {
                    if (node.getBoundsInParent().contains(e.getX(), e.getY())) {
                        boolean found = false;
                        int counter = 0;
                        for (Treasure i : selected) {
                            if (i.equals(treasures.get((GridPane.getRowIndex(node) * 4) + GridPane.getColumnIndex(node)))) {
                                selected.remove(counter);
                                ImageView imageView = highlightImageViews.get((GridPane.getRowIndex(node) * 4) + GridPane.getColumnIndex(node));
                                imageView.setImage(null);
                                treasureValue.setText("The Value of the Treasure Selected is: " + Integer.toString(getTreasureValues(selected)));
                                found = true;
                                break;
                            }
                            counter++;
                        }
                        if (!found && selected.size() < numOfTreasuresAllowed) {
                            boolean add = selected.add(treasures.get((GridPane.getRowIndex(node) * 4) + GridPane.getColumnIndex(node)));
                            ImageView imageView = highlightImageViews.get((GridPane.getRowIndex(node) * 4) + GridPane.getColumnIndex(node));
                            treasureValue.setText("The Value of the Treasure Selected is: " + Integer.toString(getTreasureValues(selected)));
                            imageView.setImage(highlight);
                        }
                    }
                }
            });

        } catch (URISyntaxException e1) {
            ErrorMessage.display("Error with treasureHighlight Tile");
        }

        window.showAndWait();
    }

    private static int getTreasureValues(ArrayList<Treasure> treasures) {
        int value = 0;
        for (Treasure i : treasures) {
            value += i.getValue();
        }
        return value;
    }
}
