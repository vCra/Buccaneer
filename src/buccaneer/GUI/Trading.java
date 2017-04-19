package buccaneer.GUI;

import buccaneer.cards.CrewCard;
import buccaneer.enumData.TreasureType;
import buccaneer.helpers.Tradeable;
import buccaneer.main.Player;
import buccaneer.ports.Port;
import buccaneer.treasure.Treasure;
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

    private static ArrayList<ImageView> playerHighlight;
    private static ArrayList<ImageView> portHighlight;
    private static int amountOfPlayerTreasure = 0;
    private static int amountOfPortTreasure = 0;
    private static int playerTotal = 0;
    private static int portTotal = 0;

    public static void display(Player player, Port port) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Trading");

        ArrayList<Treasure> playerTreasure;
        ArrayList<CrewCard> playerCrewCards;
        ArrayList<Treasure> portTreasure;
        ArrayList<CrewCard> portCrewCards;

        playerTreasure = player.getPlayerShip().getTreasures();
        playerCrewCards = player.getCrewCards();
        portTreasure = port.getTreasures();
        portCrewCards = port.getCrewCards();

        playerHighlight = new ArrayList<>();
        portHighlight = new ArrayList<>();

        GridPane playerGrid = new GridPane();
        GridPane portGrid = new GridPane();
        GridPane playerHighlightGrid = new GridPane();
        GridPane portHighlightGrid = new GridPane();
        ImageView imageView;

        int x = 0;
        int y = 0;

        try {
            for (Treasure i : playerTreasure) {
                imageView = new ImageView(getImage(i.getType()));
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
        }


        for (CrewCard i : playerCrewCards) {
            imageView = new ImageView(i.getImage());
            imageView.setFitWidth(90);
            imageView.setFitHeight(100);
            imageView.setSmooth(true);
            imageView.setCache(true);
            imageView.setMouseTransparent(true);
            GridPane.setColumnIndex(imageView, x);
            GridPane.setRowIndex(imageView, y);
            GridPane.setMargin(imageView, new Insets(5, 10, 5, 10));
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

        }

        x = 0;
        y = 0;

        for (Treasure i : portTreasure) {
            imageView = new ImageView(getImage(i.getType()));
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
            amountOfPortTreasure++;
        }

        for (CrewCard i : portCrewCards) {
            imageView = new ImageView(i.getImage());
            imageView.setFitWidth(90);
            imageView.setFitHeight(100);
            imageView.setSmooth(true);
            imageView.setCache(true);
            imageView.setMouseTransparent(true);
            GridPane.setColumnIndex(imageView, x);
            GridPane.setRowIndex(imageView, y);
            GridPane.setMargin(imageView, new Insets(5, 10, 5, 10));
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

        Button confirm = new Button("Trade");
        confirm.setOnAction(e -> {
            if (playerTotal == portTotal) {
                //TODO: something useful to do with trading
            } else {
                //TODO: some message telling the user values need to be the same
            }
        });

        Font pirateFontSmall = Font.loadFont(CrewCardsUI.class.getResource("/fonts/keelhauled-bb.regular.ttf").toExternalForm(), 16);
        Label playerTradeValue = new Label("0");
        Label portTradeValue = new Label("0");
        playerTradeValue.setFont(pirateFontSmall);
        portTradeValue.setFont(pirateFontSmall);
        Label playerTitle = new Label("Player");
        Label portTitle = new Label("Port");
        playerTitle.setFont(pirateFontSmall);
        portTitle.setFont(pirateFontSmall);

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

        ArrayList<Treasure> playerTreasureSelected = new ArrayList<>();
        ArrayList<CrewCard> playerCrewCardsSelected = new ArrayList<>();
        ArrayList<Treasure> portTreasureSelected = new ArrayList<>();
        ArrayList<CrewCard> portCrewCardsSelected = new ArrayList<>();

        try {
            final Image highlight = new Image(Trading.class.getResource("/images/tiles/highlightTreasure.png").toURI().toString());

            playerGrid.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    for (Node node : playerGrid.getChildren()) {
                        if (node.getBoundsInParent().contains(e.getX(), e.getY())) {
                            boolean found = false;
                            int counter = 0;
                            if (amountOfPlayerTreasure > ((GridPane.getRowIndex(node) * 3) + GridPane.getColumnIndex(node))) {
                                for (Treasure i : playerTreasureSelected) {
                                    if (i.equals(playerTreasure.get((GridPane.getRowIndex(node) * 3) + GridPane.getColumnIndex(node)))) {
                                        playerTreasureSelected.remove(counter);
                                        ImageView imageView = playerHighlight.get((GridPane.getRowIndex(node) * 3) + GridPane.getColumnIndex(node));
                                        imageView.setImage(null);
                                        found = true;
                                        playerTotal -= i.getValue();
                                        break;
                                    }
                                    counter++;
                            }
                            } else {
                                for (CrewCard i : playerCrewCardsSelected) {
                                    if (i.equals(playerCrewCards.get((GridPane.getRowIndex(node) * 3) + GridPane.getColumnIndex(node) - amountOfPlayerTreasure))) {
                                        playerCrewCardsSelected.remove(counter);
                                        ImageView imageView = playerHighlight.get((GridPane.getRowIndex(node) * 3) + GridPane.getColumnIndex(node) - amountOfPlayerTreasure);
                                        imageView.setImage(null);
                                        found = true;
                                        playerTotal -= i.getValue();
                                        break;
                                    }
                                }
                                counter++;

                            }
                            if (!found) {
                                if (amountOfPlayerTreasure > ((GridPane.getRowIndex(node) * 3) + GridPane.getColumnIndex(node))) {
                                    boolean add = playerTreasureSelected.add(playerTreasure.get((GridPane.getRowIndex(node) * 3) + GridPane.getColumnIndex(node)));
                                    playerTotal += playerTreasure.get((GridPane.getRowIndex(node) * 3) + GridPane.getColumnIndex(node)).getValue();
                                } else {
                                    boolean add = playerCrewCardsSelected.add(playerCrewCards.get((GridPane.getRowIndex(node) * 3) + GridPane.getColumnIndex(node) - amountOfPlayerTreasure));
                                    playerTotal += playerCrewCards.get((GridPane.getRowIndex(node) * 3) + GridPane.getColumnIndex(node) - amountOfPlayerTreasure).getValue();
                                }
                                ImageView imageView = playerHighlight.get((GridPane.getRowIndex(node) * 3) + GridPane.getColumnIndex(node));
                                imageView.setImage(highlight);
                            }
                            playerTradeValue.setText(Integer.toString(playerTotal));
                        }
                    }
                }
            });

            portGrid.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    for (Node node : portGrid.getChildren()) {
                        if (node.getBoundsInParent().contains(e.getX(), e.getY())) {
                            boolean found = false;
                            int counter = 0;
                            if (amountOfPortTreasure > ((GridPane.getRowIndex(node) * 3) + GridPane.getColumnIndex(node))) {
                                for (Treasure i : portTreasureSelected) {
                                    if (i.equals(portTreasure.get((GridPane.getRowIndex(node) * 3) + GridPane.getColumnIndex(node)))) {
                                        portTreasureSelected.remove(counter);
                                        ImageView imageView = portHighlight.get((GridPane.getRowIndex(node) * 3) + GridPane.getColumnIndex(node));
                                        imageView.setImage(null);
                                        found = true;
                                        portTotal -= i.getValue();
                                        break;
                                    }
                                    counter++;
                                }
                            } else {
                                for (CrewCard i : portCrewCardsSelected) {
                                    if (i.equals(portCrewCards.get((GridPane.getRowIndex(node) * 3) + GridPane.getColumnIndex(node) - amountOfPortTreasure))) {
                                        portCrewCardsSelected.remove(counter);
                                        ImageView imageView = portHighlight.get((GridPane.getRowIndex(node) * 3) + GridPane.getColumnIndex(node) - amountOfPortTreasure);
                                        imageView.setImage(null);
                                        found = true;
                                        portTotal -= i.getValue();
                                        break;
                                    }
                                    counter++;
                                }
                            }
                            if (!found) {
                                if (amountOfPortTreasure > ((GridPane.getRowIndex(node) * 3) + GridPane.getColumnIndex(node))) {
                                    boolean add = portTreasureSelected.add(portTreasure.get((GridPane.getRowIndex(node) * 3) + GridPane.getColumnIndex(node)));
                                    portTotal += portTreasure.get((GridPane.getRowIndex(node) * 3) + GridPane.getColumnIndex(node)).getValue();
                                } else {
                                    boolean add = portCrewCardsSelected.add(portCrewCards.get((GridPane.getRowIndex(node) * 3) + GridPane.getColumnIndex(node) - amountOfPortTreasure));
                                    portTotal += portCrewCards.get((GridPane.getRowIndex(node) * 3) + GridPane.getColumnIndex(node) - amountOfPortTreasure).getValue();
                                }
                                ImageView imageView = portHighlight.get((GridPane.getRowIndex(node) * 3) + GridPane.getColumnIndex(node));
                                imageView.setImage(highlight);
                            }
                            portTradeValue.setText(Integer.toString(portTotal));
                        }
                    }
                }
            });
        }
        catch (URISyntaxException e1) {
            System.err.println("Error with treasureHighlight Tile");
        }
    }


    static Image getImage(TreasureType treasure) {
        Image treasureImage = null;
        try {
            switch (treasure) {
                case RUM:
                    treasureImage = new Image(PlayersTreasureUI.class.getResource("/images/treasure/barrel.png").toURI().toString());
                    break;
                case DIAMOND:
                    treasureImage = new Image(PlayersTreasureUI.class.getResource("/images/treasure/diamond.png").toURI().toString());
                    break;
                case GOLD:
                    treasureImage = new Image(PlayersTreasureUI.class.getResource("/images/treasure/gold.png").toURI().toString());
                    break;
                case PEARL:
                    treasureImage = new Image(PlayersTreasureUI.class.getResource("/images/treasure/pearl.png").toURI().toString());
                    break;
                case RUBIE:
                    treasureImage = new Image(PlayersTreasureUI.class.getResource("/images/treasure/ruby.png").toURI().toString());
                    break;
                default:
                    //does nothing to keep image as blank
            }
        } catch (URISyntaxException e) {
            System.err.println("Error: " + e);
        }
        return treasureImage;
    }

    int genTotal(ArrayList<Tradeable> list) {
        int value = 0;
        for (Tradeable i : list) {
            value = value + i.getValue();
        }
        return value;
    }
}
