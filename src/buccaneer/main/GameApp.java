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
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

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
        Image water = new Image(getClass().getResource("/images/bg/grid-bg.png").toURI().toString());

        ImageView imageview = new ImageView(water);
        imageview.setFitWidth(800);
        imageview.setFitHeight(800);
        imageview.setPreserveRatio(true);
        imageview.setSmooth(true);
        imageview.setCache(true);
        imageview.setMouseTransparent(true);

        GridPane centerGrid = new GridPane();
        GridPane leftGrid = new GridPane();
        GridPane rightGrid = new GridPane();

        //Toggle this if you want Sound on the game
        //It should probably have a UI control at some point
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

                GridPane.setConstraints(tile,x,y);

                grid.add(tile);
                centerGrid.getChildren().add(tile);
            }
        }

        centerGrid.setAlignment(Pos.CENTER);
        leftGrid.setAlignment(Pos.CENTER_LEFT);
        rightGrid.setAlignment(Pos.CENTER_RIGHT);

        StackPane stack = new StackPane();
        stack.getChildren().addAll(imageview, centerGrid);

        Scene scene = new Scene(stack, 1400, 800);
        window.setScene(scene);
        window.show();

        //Uncomment this if you want a Fullscreen Game.
        window.setFullScreen(true);

        centerGrid.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                for (Node node : centerGrid.getChildren()) {
                    if (node.getBoundsInParent().contains(e.getSceneX(), e.getSceneY())) {
                        Position pos = new Position(1,1); //Replace with actual x/y
                        game.onSquareClick(pos);
                    }
                }
            }
        });
    }

    /**
     * Plays music on a loop.
     */
    private void playSound(){
        AudioClip pirateSong = new AudioClip(getClass().getResource("/sound/PirateSong.mp3").toString());
        pirateSong.play();
        pirateSong.setCycleCount(AudioClip.INDEFINITE);
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
        game.getBoard().moveShip(ship, moveFrom, moveTo);
        setShipPosition(ship, moveTo);
    }


    /**
     * Highlights squares in the ArrayList in positions.
     * @param positions
     */
    public void highlight(ArrayList<buccaneer.helpers.Position> positions) {
        Image highlight = null;
        //Can we try and put this into a method that does the catch/try thing for lost resources please -Aaron
        try {
            highlight = new Image(getClass().getResource("/images/tiles/highlight.jpg").toURI().toString());
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

    /**
     * Updates the sidebar with the information in Game
     * including elements such as the current player and score.
     */
    public void updateSidebar(){

    }
}