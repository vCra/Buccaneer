package buccaneer.main;

import buccaneer.helpers.DirectionHelper;
import buccaneer.helpers.Position;
import buccaneer.helpers.PositionHelper;
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
    private Game game = new Game(this);
    private ArrayList<ImageView> shipgrid = new ArrayList<>();
    private ArrayList<ImageView> highlightgrid = new ArrayList<>();

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage window) throws Exception {
        window.setTitle("Buccaneer Board");
        Image background = new Image(getClass().getResource("/images/bg/grid-bg.png").toURI().toString());

        ImageView imageview = new ImageView(background);
        imageview.setFitWidth(800);
        imageview.setFitHeight(800);
        imageview.setPreserveRatio(true);
        imageview.setSmooth(true);
        imageview.setCache(true);
        imageview.setMouseTransparent(true);

        GridPane shipGridPane = new GridPane();
        GridPane highlightGridPane = new GridPane();
        GridPane leftGrid = new GridPane();
        GridPane rightGrid = new GridPane();

        //Toggle this if you want Sound on the game
        //It should probably have a UI control at some point
        playSound();

        game.begin();

        for (int y = 0; y < 20; y++) {
            for (int x = 0; x < 20; x++) {
                ImageView shiptile = new ImageView();
                ImageView highlighttile = new ImageView();
                shiptile.setFitWidth(40);
                shiptile.setFitHeight(40);
                shiptile.setPreserveRatio(true);
                shiptile.setSmooth(true);
                shiptile.setCache(true);
                shiptile.setMouseTransparent(true);

                highlighttile.setFitWidth(40);
                highlighttile.setFitHeight(40);
                highlighttile.setPreserveRatio(true);
                highlighttile.setSmooth(true);
                highlighttile.setCache(true);

                GridPane.setConstraints(shiptile,x,y);
                GridPane.setConstraints(highlighttile,x,y);

                shipgrid.add(shiptile);
                highlightgrid.add(highlighttile);
                shipGridPane.getChildren().add(shiptile);
                highlightGridPane.getChildren().add(highlighttile);
            }
        }

        shipGridPane.setAlignment(Pos.CENTER);
        highlightGridPane.setAlignment(Pos.CENTER);
        leftGrid.setAlignment(Pos.CENTER_LEFT);
        rightGrid.setAlignment(Pos.CENTER_RIGHT);

        StackPane stack = new StackPane();
        stack.getChildren().addAll(imageview, highlightGridPane, shipGridPane);

        Scene scene = new Scene(stack, 1400, 800);
        window.setScene(scene);
        window.show();

        //Uncomment this if you want a Fullscreen Game.
        //window.setFullScreen(true);

        shipGridPane.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                for (Node node : shipGridPane.getChildren()) { //We currently have to go through all 400 squares and
                    // check if it contains the mouse event - is they a better way of doing this?
                    //BUG
                    if (node.getBoundsInParent().contains(e.getSceneX(), e.getSceneY())) {
                        Position pos = PositionHelper.gridChange(GridPane.getColumnIndex(node), GridPane.getRowIndex(node));
                        //Position pos = new Position(GridPane.getColumnIndex(node), GridPane.getRowIndex(node)); //Replace with actual x/y
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
     * @param direction the direction of the ship to change to
     * @param position the position of the ship
     */
    public void setShipDirection(buccaneer.enumData.Direction direction, buccaneer.helpers.Position position) {
        ImageView toChange = shipgrid.get((position.getY() * 20) + position.getX());
        toChange.setRotate(DirectionHelper.directionToAngle(direction));
    }

    /**
     * Sets a ships position.
     * @param ship the ship to set the position of
     * @param position the new position of the ship
     */
    private void setShipPosition(Ship ship, buccaneer.helpers.Position position) {
        System.out.println(position.toString());
        Image shipImage = ship.getShipPhoto();
        ImageView toChange = shipgrid.get(PositionHelper.positionToGridID(position));
        toChange.setImage(shipImage);
    }

    /**
     * Moves a ship from one position to another.
     * @param ship the ship to move
     * @param moveFrom the starting location of the ship
     * @param moveTo the end location of the ship
     */
    void moveShip(Ship ship, Position moveFrom, Position moveTo) {
        ImageView toChange = shipgrid.get(PositionHelper.positionToGridID(moveFrom));
        toChange.setImage(null);
        setShipPosition(ship, moveTo);
    }


    /**
     * Highlights squares in the ArrayList in positions.
     * @param positions the positions to highlight
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
            gridImage = highlightgrid.get((i.getY() * 20) + i.getX());
            gridImage.setImage(highlight);
        }
    }

    /**
     * De-highlights all squares
     */
    public void dehighlight() {
        for(ImageView e : highlightgrid) {
            e.setImage(null);
        }
    }

    /**
     * Runs a players turn.
     * @param player the players turn to run
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