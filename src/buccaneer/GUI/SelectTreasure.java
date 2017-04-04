package buccaneer.GUI;

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
 * Created by adam on 31/03/2017.
 */
public class SelectTreasure {

    public static void display(int numOfTreasuresAllowed, ArrayList<Treasure> treasures) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Selection");

        Font pirateFont = Font.loadFont(CrewCardsUI.class.getResource("/fonts/keelhauled-bb.regular.ttf").toExternalForm(), 30);

        Label title = new Label();
        title.setFont(pirateFont);
        title.setText("Select " + numOfTreasuresAllowed + " Treasure");

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setMaxSize(500, 450);

        GridPane treasure = new GridPane();
        GridPane highlightGrid = new GridPane();
        ArrayList<ImageView> treasureImageViews = new ArrayList<>();
        ArrayList<ImageView> highlightImageViews = new ArrayList<>();

        ImageView treasureTile;
        ImageView highlightTile;
        int x = 0;
        int y = 0;

        for (Treasure i : treasures) {
            treasureTile = new ImageView(getImage(i.getType().getName()));
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

        ArrayList<Treasure> selected = new ArrayList<>();

        Button select = new Button("Select");
        select.setOnAction(e -> {
            //TODO: send the array list "selected" to somewhere useful
        });

        VBox layout = new VBox(30);
        layout.getChildren().addAll(title, scrollPane, select);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout, 600, 600);
        window.setScene(scene);
        window.show();

        try {
            final Image highlight = new Image(PlayersTreasureUI.class.getResource("/images/tiles/highlightTreasure.png").toURI().toString());

            treasure.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    for (Node node : treasure.getChildren()) {
                        if (node.getBoundsInParent().contains(e.getX(), e.getY())) {
                            boolean found = false;
                            int counter = 0;
                            for (Treasure i : selected) {
                                if (i.equals(treasures.get((GridPane.getRowIndex(node) * 4) + GridPane.getColumnIndex(node)))) {
                                    selected.remove(counter);
                                    ImageView imageView = highlightImageViews.get((GridPane.getRowIndex(node) * 4) + GridPane.getColumnIndex(node));
                                    imageView.setImage(null);
                                    found = true;
                                    break;
                                }
                                counter++;
                            }
                            if (found == false && selected.size() < numOfTreasuresAllowed) {
                                selected.add(treasures.get((GridPane.getRowIndex(node) * 4) + GridPane.getColumnIndex(node)));
                                ImageView imageView = highlightImageViews.get((GridPane.getRowIndex(node) * 4) + GridPane.getColumnIndex(node));
                                imageView.setImage(highlight);
                            }
                        }
                    }
                }
            });

        } catch (URISyntaxException e1) {
            System.err.println("Error with treasureHighlight Tile");
        }

    }

    /**
     * takes the name of the treasure that needs displaying and returns the image of that treasure
     *
     * @param treasure the treasure to display
     * @return the image of the treasure
     */
    static Image getImage(String treasure) {
        Image treasureImage = null;
        try {
            switch (treasure) {
                case "Barrel of rum":
                    treasureImage = new Image(PlayersTreasureUI.class.getResource("/images/treasure/barrel.png").toURI().toString());
                    break;
                case "Diamond":
                    treasureImage = new Image(PlayersTreasureUI.class.getResource("/images/treasure/diamond.png").toURI().toString());
                    break;
                case "Bar of gold":
                    treasureImage = new Image(PlayersTreasureUI.class.getResource("/images/treasure/gold.png").toURI().toString());
                    break;
                case "Pearl":
                    treasureImage = new Image(PlayersTreasureUI.class.getResource("/images/treasure/pearl.png").toURI().toString());
                    break;
                case "Rubie":
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
