package buccaneer.main;

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

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.net.URISyntaxException;
import java.util.ArrayList;

/**
 * Starts a Game and provides a GUI, while linking them both together
 */
public class GameApp extends Application {
    private Game game = new Game();
    private ArrayList<ImageView> grid = new ArrayList<ImageView>();

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage window) throws Exception {
        game.fakeBegin();
        Ship ships[] = new Ship[4];

        ships[0] = game.getPlayer(1).getPlayerShip();
        window.setTitle("Group Project Demo");

        Image water = new Image(getClass().getResource("/grid-bg.png").toURI().toString());

        ImageView imageview = new ImageView(water);
        imageview.setFitWidth(800);
        imageview.setFitHeight(800);
        imageview.setPreserveRatio(true);
        imageview.setSmooth(true);
        imageview.setCache(true);
        imageview.setMouseTransparent(true);

        GridPane gridpane = new GridPane();
        playSound();

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

        gridpane.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                for (Node node : gridpane.getChildren()) {
                    if (node.getBoundsInParent().contains(e.getSceneX(), e.getSceneY())) {
                        // enter code here to send position clicked
                    }
                }
            }
        });
    }

    private void playSound(){
        String resource = getClass().getResource("/PirateSong.mp3").toString();
        MediaPlayer a =new MediaPlayer(new Media(resource));
        a.setOnEndOfMedia(new Runnable() {
            public void run() {
                a.seek(Duration.ZERO);
            }
        });
        a.play();
    }

    public void setShipDirection(buccaneer.enumData.Direction direction, buccaneer.helpers.Position position) {
        ImageView toChange = grid.get((position.getY() * 20) + position.getX());
        switch (direction) {
            case N:
                toChange.setRotate(0);
                break;
            case NE:
                toChange.setRotate(45);
                break;
            case E:
                toChange.setRotate(90);
                break;
            case SE:
                toChange.setRotate(135);
                break;
            case S:
                toChange.setRotate(180);
                break;
            case SW:
                toChange.setRotate(225);
                break;
            case W:
                toChange.setRotate(270);
                break;
            case NW:
                toChange.setRotate(315);
        }
    }

    public void setShipPosition(Image ship, buccaneer.helpers.Position position) {
        ImageView toChange = grid.get((position.getY() * 20) + position.getX());;
        toChange.setImage(ship);
    }

    public void moveShip(Image ship, buccaneer.helpers.Position moveFrom, buccaneer.helpers.Position moveTo) {
        ImageView toChange = grid.get((moveFrom.getY() * 20) + moveFrom.getX());
        toChange.setImage(null);
        setShipPosition(ship, moveTo);
    }

    public void highlight(ArrayList<buccaneer.helpers.Position> positions) {
        Image highlight = null;
        try {
            highlight = new Image(getClass().getResource("/highlight.jpg").toURI().toString());
        } catch (URISyntaxException e) {
            System.err.println("Problem with highlight image");
        }
        ImageView gridImage;
        for (buccaneer.helpers.Position i : positions) {
            gridImage = grid.get((i.getY() * 20) + i.getX());
            gridImage.setImage(highlight);
        }
    }

    public void runTurn(Player player) {

    }

}

