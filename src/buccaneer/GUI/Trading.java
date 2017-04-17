package buccaneer.GUI;

import buccaneer.cards.CrewCard;
import buccaneer.enumData.TreasureType;
import buccaneer.main.Player;
import buccaneer.ports.Port;
import buccaneer.treasure.Treasure;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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

        GridPane playerGrid = new GridPane();
        GridPane portGrid = new GridPane();
        ImageView imageView;

        int x = 0;
        int y = 0;

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

            x++;
            if (x >= 3) {
                x = 0;
                y++;
            }
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

            x++;
            if (x >= 3) {
                x = 0;
                y++;
            }
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

            x++;
            if (x >= 3) {
                x = 0;
                y++;
            }
        }

        ScrollPane playerScroll = new ScrollPane();
        ScrollPane portScroll = new ScrollPane();
        playerScroll.setMaxSize(330, 450);
        portScroll.setMaxSize(330, 450);
        playerScroll.setContent(playerGrid);
        portScroll.setContent(portGrid);

        Font pirateFont = Font.loadFont(CrewCardsUI.class.getResource("/fonts/keelhauled-bb.regular.ttf").toExternalForm(), 30);

        Label title = new Label();
        title.setFont(pirateFont);
        title.setText("Trading");

        Button confirm = new Button("Trade");
        confirm.setOnAction(e -> {

        });

        Font pirateFontSmall = Font.loadFont(CrewCardsUI.class.getResource("/fonts/keelhauled-bb.regular.ttf").toExternalForm(), 11);
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
        VBox mainLayout = new VBox(10);
        mainLayout.getChildren().addAll(title, scrollPaneLayout);
        mainLayout.setAlignment(Pos.CENTER);

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
}
