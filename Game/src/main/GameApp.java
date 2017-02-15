package main;

import java.net.URISyntaxException;
import java.util.ArrayList;
import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

public class GameApp extends Application {
    private Game game = new Game();
    private Label lblDirection = new Label();
    private ArrayList<ImageView> grid = new ArrayList<ImageView>();
    private ArrayList<ImageView> tilesToReset = new ArrayList<ImageView>();

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage window) throws Exception {
        game.getPlayer(1).getPlayerShip().getLocation();
        window.setTitle("Group Project Demo");

        GridPane gridpane = new GridPane();
        grid = new ArrayList<ImageView>();
        updateGrid(gridpane);

        Button move = new Button("Move");
        Button direction = new Button("Direction");
        VBox buttonslayout = new VBox(10);
        buttonslayout.getChildren().addAll(move, direction, lblDirection);
        HBox scenelayout = new HBox(30);
        scenelayout.getChildren().addAll(gridpane, buttonslayout);

        Scene gridScene = new Scene(scenelayout, 1400, 900);
        window.setScene(gridScene);
        window.show();

    }

    private void updateGrid(GridPane gridpane) throws URISyntaxException {
        Image image = null;
        int shipPosX = game.getPlayer(1).getPlayerShip().getLocation().getX();
        int shipPosY = game.getPlayer(1).getPlayerShip().getLocation().getY();
        for (int y = 0; y < 20; y++) {
            for (int x = 0; x < 20; x++) {
                if (((x >= 1 && y >= 15) && (x <= 3 && y <= 18)) || ((x >= 16 && y >= 1) && (x <= 18 && y <= 4))
                        || ((x >= 8 && y >= 8) && (x <= 11 && y <= 11))) {
                    image = new Image(getClass().getResource("/ground.jpg").toURI().toString());
                } else if (x == shipPosX && y == shipPosY) {
                    image = new Image(getClass().getResource("/pirateship.jpeg").toURI().toString());
                } else {
                    image = new Image(getClass().getResource("/tile.jpg").toURI().toString());
                }
                ImageView tile = new ImageView(image);
                tile.setFitWidth(40);
                tile.setFitHeight(40);
                tile.setPreserveRatio(true);
                tile.setSmooth(true);
                tile.setCache(true);
                tile.setMouseTransparent(true);
                GridPane.setRowIndex(tile, y);
                GridPane.setColumnIndex(tile, x);
                grid.add(tile);
                gridpane.getChildren().add(tile);
            }
        }
    }
}
