package main;

import java.net.URISyntaxException;
import java.util.ArrayList;
import javafx.application.Application;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GameApp extends Application {
    private Game game = new Game();
    private ArrayList<ImageView> grid = new ArrayList<ImageView>();

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage window) throws Exception {
        game.getPlayer(1).getPlayerShip().getLocation();
        window.setTitle("Group Project Demo");

        Image water = new Image(getClass().getResource("/movingwater.gif").toURI().toString());

        ImageView imageview = new ImageView(water);
        imageview.setFitWidth(800);
        imageview.setFitHeight(800);
        imageview.setPreserveRatio(true);
        imageview.setSmooth(true);
        imageview.setCache(true);
        imageview.setMouseTransparent(true);

        GridPane gridpane = new GridPane();
        for (int y = 0; y < 20; y++) {
            for (int x = 0; x < 20; x++) {
                ImageView tile = new ImageView();
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

        gridpane.setAlignment(Pos.CENTER);

        StackPane stack = new StackPane();
        stack.getChildren().addAll(imageview, gridpane);

        Scene scene = new Scene(stack, 1400, 800);
        window.setScene(scene);
        window.show();

        Image highlight = new Image(getClass().getResource("/highlight.jpg").toURI().toString());

        gridpane.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                for (Node node : gridpane.getChildren()) {
                    if (node.getBoundsInParent().contains(e.getSceneX(), e.getSceneY())) {
                        ImageView change = grid.get((GridPane.getRowIndex(node) * 20) + GridPane.getColumnIndex(node));
                        change.setImage(highlight);
                    }
                }
            }
        });
    }

}

