package buccaneer.GUI;

import buccaneer.helpers.Tradeable;
import buccaneer.main.Player;
import buccaneer.ports.Port;
import javafx.event.EventHandler;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URISyntaxException;
import java.util.ArrayList;

/**
 * Created by adam on 15/03/2017.
 */
//TODO: Javadoc

public class Trading {

    // These are the image views for the highlighing
    private static ArrayList<ImageView> playerHighlight;
    private static ArrayList<ImageView> portHighlight;

    // The amount of treasure each is trading
    private static int amountOfPlayerTreasure = 0;

    // The total value of what the player has been selected to trade
    private static int playerTotal = 0;
    private static int portTotal = 0;

    public static void display(Player player, Port port) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Trading");

        // The lists of the treasure and crew cards that the player and port has these are what can be traded
        ArrayList<Tradeable> playerTradables = new ArrayList<>();
        ArrayList<Tradeable> portTradables = new ArrayList<>();

        playerTradables.addAll(player.getPlayerShip().getTreasures());
        playerTradables.addAll(player.getCrewCards());
        portTradables.addAll(port.getTreasures());
        portTradables.addAll(port.getCrewCards());

        playerHighlight = new ArrayList<>();
        portHighlight = new ArrayList<>();

        GridPane playerGrid = new GridPane();
        GridPane portGrid = new GridPane();
        GridPane playerHighlightGrid = new GridPane();
        GridPane portHighlightGrid = new GridPane();
        ImageView imageView;

        int x = 0;
        int y = 0;

        // Display the players tradables in the player tradables grid
        try {
            for (Tradeable i : playerTradables) {
                imageView = new ImageView(i.getImage());
                imageView.setFitWidth(100);
                imageView.setFitHeight(100);
                imageView.setSmooth(true);
                imageView.setCache(true);
                imageView.setMouseTransparent(true);
                GridPane.setColumnIndex(imageView, x);
                GridPane.setRowIndex(imageView, y);
                GridPane.setMargin(imageView, new Insets(5, 5, 5, 5));
                playerGrid.getChildren().add(imageView);

                imageView = new ImageView();
                imageView.setFitWidth(100);
                imageView.setFitHeight(100);
                imageView.setSmooth(true);
                imageView.setCache(true);
                GridPane.setColumnIndex(imageView, x);
                GridPane.setRowIndex(imageView, y);
                GridPane.setMargin(imageView, new Insets(5, 5, 5, 5));
                playerHighlightGrid.getChildren().add(imageView);
                playerHighlight.add(imageView);

                x++;
                if (x >= 3) {
                    x = 0;
                    y++;
                }
                amountOfPlayerTreasure++;
            }
        } catch (NullPointerException e) {
            //do nothing
            //I can tell by the fact they isn't any code here, but thanks for clarifying
        }

        x = 0;
        y = 0;

        // Displaying the tradables in the port tradable grid
        for (Tradeable i : portTradables) {
            imageView = new ImageView(i.getImage());
            imageView.setFitWidth(100);
            imageView.setFitHeight(100);
            imageView.setSmooth(true);
            imageView.setCache(true);
            imageView.setMouseTransparent(true);
            GridPane.setColumnIndex(imageView, x);
            GridPane.setRowIndex(imageView, y);
            GridPane.setMargin(imageView, new Insets(10, 10, 10, 10));
            portGrid.getChildren().add(imageView);

            imageView = new ImageView();
            imageView.setFitWidth(100);
            imageView.setFitHeight(100);
            imageView.setSmooth(true);
            imageView.setCache(true);
            GridPane.setColumnIndex(imageView, x);
            GridPane.setRowIndex(imageView, y);
            GridPane.setMargin(imageView, new Insets(5, 5, 5, 5));
            portHighlightGrid.getChildren().add(imageView);
            portHighlight.add(imageView);

            x++;
            if (x >= 3) {
                x = 0;
                y++;
            }
        }

        // Layout for the the two scroll panes and trade button
        StackPane playerStack = new StackPane();
        playerStack.getChildren().addAll(playerHighlightGrid, playerGrid);
        StackPane portStack = new StackPane();
        portStack.getChildren().addAll(portHighlightGrid, portGrid);
        ScrollPane playerScroll = new ScrollPane();
        ScrollPane portScroll = new ScrollPane();
        playerScroll.setMaxSize(500, 450);
        portScroll.setMaxSize(500, 450);
        playerScroll.setContent(playerStack);
        portScroll.setContent(portStack);

        Font pirateFont = Font.loadFont(CrewCardsUI.class.getResource("/fonts/keelhauled-bb.regular.ttf").toExternalForm(), 32);

        Label title = new Label();
        title.setFont(pirateFont);
        title.setText("Trading");

        // These contain all of the items the player has decided to trade
        // Aaron these are what you need to do the trade with
        ArrayList<Tradeable> playerSelected = new ArrayList<>();
        ArrayList<Tradeable> portSelected = new ArrayList<>();

        // Confirms the trade and calls the method to do the trade
        Button confirm = new Button("Trade");
        confirm.setOnAction(e -> {
            if (playerTotal == portTotal) {
                //TODO: something useful to do with trading
            } else {
                //TODO: some message telling the user values need to be the same
            }
        });

        Font pirateFontSmall = GUIHelper.getPirateFont(16);
        Label playerTradeValue = new Label("0");
        Label portTradeValue = new Label("0");
        playerTradeValue.setFont(pirateFontSmall);
        portTradeValue.setFont(pirateFontSmall);
        Label playerTitle = new Label("Player");
        Label portTitle = new Label("Port");
        playerTitle.setFont(pirateFontSmall);
        portTitle.setFont(pirateFontSmall);

        //Layout of the window
        VBox playerLayout = new VBox(10);
        playerLayout.getChildren().addAll(playerTitle, playerScroll, playerTradeValue);
        playerLayout.setAlignment(Pos.CENTER);
        VBox portLayout = new VBox(10);
        portLayout.getChildren().addAll(portTitle, portScroll, portTradeValue);
        portLayout.setAlignment(Pos.CENTER);
        HBox scrollPaneLayout = new HBox(20);
        scrollPaneLayout.getChildren().addAll(playerLayout, confirm, portLayout);
        scrollPaneLayout.setAlignment(Pos.CENTER);
        VBox mainLayout = new VBox(50);
        mainLayout.getChildren().addAll(title, scrollPaneLayout);
        mainLayout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(mainLayout, 1200, 800);
        window.setScene(scene);
        window.show();

        //Please ignore this code - it is awful I know
        final Image highlight;
        Image highlight1 = null;
        try {
            highlight1 = new Image(Trading.class.getResource("/images/tiles/highlightTreasure.png").toURI().toString());
        } catch (URISyntaxException e1) {
            System.err.println("Error with treasure Highlight Tile");
        }
        highlight = highlight1;


        // Event handler for the players items scroll pane
        playerGrid.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                for (Node node : playerGrid.getChildren()) {
                    boolean found = false;

                    if (node.getBoundsInParent().contains(e.getX(), e.getY())) {
                        int counter = 0;
                        // Try to find the item that has been clicked in the selected array list if it is contained in there it removes it and unhighlights its image finally deducts its value form the trade value
                        for (Tradeable i : playerSelected) {
                            if (i.equals(playerTradables.get((GridPane.getRowIndex(node) * 3) + GridPane.getColumnIndex(node) - amountOfPlayerTreasure))) {
                                playerSelected.remove(counter);
                                ImageView imageView = playerHighlight.get((GridPane.getRowIndex(node) * 3) + GridPane.getColumnIndex(node) - amountOfPlayerTreasure);
                                imageView.setImage(null);
                                found = true;
                                playerTotal -= i.getValue();
                                break;
                            }
                        }
                    }
                    // If the item has not been found in the selected array list then add it to the selected array list, highlight the image and add the value to the total value being traded
                    if (!found) {
                        boolean add = playerSelected.add(playerTradables.get((GridPane.getRowIndex(node) * 3) + GridPane.getColumnIndex(node)));
                        playerTotal += playerTradables.get((GridPane.getRowIndex(node) * 3) + GridPane.getColumnIndex(node)).getValue();
                        ImageView imageView = playerHighlight.get((GridPane.getRowIndex(node) * 3) + GridPane.getColumnIndex(node));
                        imageView.setImage(highlight);

                    }
                    playerTradeValue.setText(Integer.toString(playerTotal));
                }
            }
        });

        // Event handler for the ports items scroll pane

        portGrid.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                for (Node node : portGrid.getChildren()) {
                    if (node.getBoundsInParent().contains(e.getX(), e.getY())) {
                        boolean found = false;
                        int counter = 0;

                        // Try to find an item that has been clicked in the selected array list
                        // If present it removes it and unhighlights
                        // Image finally deducts its value form the trade value
                        for (Tradeable i : portSelected) {
                            if (i.equals(portTradables.get((GridPane.getRowIndex(node) * 3) + GridPane.getColumnIndex(node)))) {
                                portSelected.remove(counter);
                                ImageView imageView = portHighlight.get((GridPane.getRowIndex(node) * 3) + GridPane.getColumnIndex(node));
                                imageView.setImage(null);
                                found = true;
                                portTotal -= i.getValue();
                                break;
                            }
                            counter++;
                        }

                        // If the item has not been found in the selected array list then add it to the selected array list, highlight the image and add the value to the total value being traded
                        if (!found) {
                            boolean add = portSelected.add(portTradables.get((GridPane.getRowIndex(node) * 3) + GridPane.getColumnIndex(node)));
                            portTotal += portTradables.get((GridPane.getRowIndex(node) * 3) + GridPane.getColumnIndex(node)).getValue();
                            ImageView imageView = portHighlight.get((GridPane.getRowIndex(node) * 3) + GridPane.getColumnIndex(node));
                            imageView.setImage(highlight);
                        }
                        portTradeValue.setText(Integer.toString(portTotal));
                    }
                }
            }
        });
    }
}
