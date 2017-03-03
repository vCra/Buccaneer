package buccaneer.main;

import buccaneer.helpers.DirectionHelper;
import buccaneer.helpers.Position;
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
        window.setFullScreen(true);

        gridpane.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                for (Node node : gridpane.getChildren()) {
                    if (node.getBoundsInParent().contains(e.getSceneX(), e.getSceneY())) {
                        //TODO: can someone make a function to get the Position of the square that has been clicke
                    }
                }
            }
        });
    }

    /**
     * Plays music on a loop.
     */
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

    /**
     * Changes the direction of the ship in the current location.
     * @param direction
     * @param position
     */
    public void setShipDirection(buccaneer.enumData.Direction direction, buccaneer.helpers.Position position) {
        ImageView toChange = grid.get((position.getY() * 20) + position.getX());
        toChange.setRotate(DirectionHelper.directionToAngle(direction));
    }

    /**
     * Sets a ships position.
     * @param ship
     * @param position
     */
    public void setShipPosition(Ship ship, buccaneer.helpers.Position position) {
        Image shipImage = ship.getShipPhoto();
        ImageView toChange = grid.get((position.getY() * 20) + position.getX());
        ;
        toChange.setImage(shipImage);
    }

    /**
     * Moves a ship from one position to another.
     * @param ship
     * @param moveFrom
     * @param moveTo
     */
    public void moveShip(Ship ship, buccaneer.helpers.Position moveFrom, buccaneer.helpers.Position moveTo) {
        ImageView toChange = grid.get((moveFrom.getY() * 20) + moveFrom.getX());
        toChange.setImage(null);
        setShipPosition(ship, moveTo);
    }


    /**
     * Highlights squares in the ArrayList in positions.
     * @param positions
     */
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

    /**
     * Runs a players turn.
     * @param player
     */
    public void runTurn(Player player) {
        //TODO
    }

}