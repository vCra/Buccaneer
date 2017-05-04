package buccaneer.GUI;

import buccaneer.cards.CrewCard;
import buccaneer.main.Player;
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
 * Created by Jakub Janas on 5/4/2017.
 */
public class SelectCrew
{
    /**
     * Displays to the user what crew cards they can select
     *
     * @param maxValueAllowed - integer
     */

    //TODO: Java Doc
    public static void display(int maxValueAllowed, ArrayList<CrewCard> crewCards, Player player) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Select Crew Cards");

        Font pirateFont = Font.loadFont(SelectTreasure.class.getResource("/fonts/keelhauled-bb.regular.ttf").toExternalForm(), 30);

        Label title = new Label();
        title.setFont(pirateFont);
        title.setText("Select up to" + maxValueAllowed + " value of CrewCards");

        Label crewCardsValue = new Label("The Value of the Crew Cards Selected is: 0");

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setMaxSize(500, 450);

        GridPane crew = new GridPane();
        GridPane highlightGrid = new GridPane();
        ArrayList<ImageView> crewCardImageViews = new ArrayList<>();
        ArrayList<ImageView> highlightImageViews = new ArrayList<>();

        ArrayList<CrewCard> selected = new ArrayList<CrewCard>();

        ImageView crewCardTile;
        ImageView highlightTile;
        int x = 0;
        int y = 0;
        for (CrewCard i : crewCards) {
            crewCardTile = new ImageView(i.getImage());
            crewCardTile.setFitWidth(100);
            crewCardTile.setFitHeight(100);
            crewCardTile.setSmooth(true);
            crewCardTile.setCache(true);
            crewCardTile.setMouseTransparent(true);
            GridPane.setColumnIndex(crewCardTile, x);
            GridPane.setRowIndex(crewCardTile, y);
            GridPane.setMargin(crewCardTile, new Insets(10, 10, 10, 10));
            crew.getChildren().add(crewCardTile);
            crewCardImageViews.add(crewCardTile);

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
        stackPane.getChildren().addAll(highlightGrid, crew);

        scrollPane.setContent(stackPane);

        Button select = new Button("Select");

        select.setOnAction(e -> {
                    int value = 0;
                    for (CrewCard i : selected) {
                        value += i.getValue();
                    }
                    if (value <= maxValueAllowed) {
                        crewCards.removeAll(selected);
                        player.getCrewCards().addAll(selected);
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
        layout.getChildren().addAll(title, crewCardsValue, scrollPane, select);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout, 600, 600);
        window.setScene(scene);

        try {
            final Image highlight = new Image(PlayersTreasureUI.class.getResource("/images/tiles/highlightTreasure.png").toURI().toString());

            crew.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
                for (Node node : crew.getChildren()) {
                    if (node.getBoundsInParent().contains(e.getX(), e.getY())) {
                        boolean found = false;
                        int counter = 0;
                        for (CrewCard i : selected) {
                            if (i.equals(crewCards.get((GridPane.getRowIndex(node) * 4) + GridPane.getColumnIndex(node)))) {
                                selected.remove(counter);
                                ImageView imageView = highlightImageViews.get((GridPane.getRowIndex(node) * 4) + GridPane.getColumnIndex(node));
                                imageView.setImage(null);
                                crewCardsValue.setText("The Value of the Treasure Selected is: " + Integer.toString(getCrewCardValues(selected)));
                                found = true;
                                break;
                            }
                            counter++;
                        }
//                        if (!found && selected.size() < numOfTreasuresAllowed) {
//                            boolean add = selected.add(crewCards.get((GridPane.getRowIndex(node) * 4) + GridPane.getColumnIndex(node)));
//                            ImageView imageView = highlightImageViews.get((GridPane.getRowIndex(node) * 4) + GridPane.getColumnIndex(node));
//                            crewCardsValue.setText("The Value of the Treasure Selected is: " + Integer.toString(getCrewCardValues(selected)));
//                            imageView.setImage(highlight);
//                        }
                    }
                }
            });

        } catch (URISyntaxException e1) {
            ErrorMessage.display("Error with treasureHighlight Tile");
        }

        window.showAndWait();
    }

    private static int getCrewCardValues(ArrayList<CrewCard> cards) {
        int value = 0;
        for (CrewCard i : cards) {
            value += i.getValue();
        }
        return value;
    }
}
