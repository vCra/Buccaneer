package buccaneer.main;

import buccaneer.helpers.DirectionHelper;
import buccaneer.helpers.Position;
import buccaneer.helpers.PositionHelper;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

import java.net.URISyntaxException;
import java.util.ArrayList;

/**
 * Starts a Game and provides a GUI, while linking them both together
 */
public class GameApp extends Application {
    private AudioClip pirateSong = new AudioClip(getClass().getResource("/sound/PirateSong.mp3").toString());
    private Game game = new Game(this);
    private ArrayList<ImageView> shipgrid = new ArrayList<>();
    private ArrayList<ImageView> highlightgrid = new ArrayList<>();

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage window) throws Exception {
        //MAIN BOARD
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
        VBox leftGrid = new VBox(10);
        VBox rightGrid = new VBox(10);

        Label name1 = new Label();
        Label name2 = new Label();
        Label name3 = new Label();
        Label name4 = new Label();

        rightGrid.getChildren().addAll(name1, name2, name3, name4);

        Button mute = new Button("mute");
        mute.setOnAction(e -> {
            pirateSong.stop();
        });
        leftGrid.getChildren().add(mute);

        Button crewCards = new Button("Crew Cards");
        crewCards.setOnAction(e -> {
            buccaneer.GUI.CrewCardsUI.display(game.getCurrentPlayer());
        });
        leftGrid.getChildren().add(crewCards);

        //playSound();

        //game.begin();

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

        StackPane stack = new StackPane();
        stack.getChildren().addAll(imageview, highlightGridPane, shipGridPane);

        HBox mainBoardLayout = new HBox(20);
        mainBoardLayout.setAlignment(Pos.CENTER);
        mainBoardLayout.getChildren().addAll(leftGrid, stack, rightGrid);

        Scene mainBoardScene = new Scene(mainBoardLayout, 1400, 800);
        //END OF MAIN BOARD


        //START SCREEN
        window.setTitle("Welcome to Buccaneer");
        TextField player1, player2, player3, player4;
        player1 = new TextField();
        player1.setPromptText("Enter Player 1 Name");
        player1.setMaxWidth(200);
        player2 = new TextField();
        player2.setPromptText("Enter Player 2 Name");
        player2.setMaxWidth(200);
        player3 = new TextField();
        player3.setPromptText("Enter Player 3 Name");
        player3.setMaxWidth(200);
        player4 = new TextField();
        player4.setPromptText("Enter Player 4 Name");
        player4.setMaxWidth(200);
        Button start = new Button("Start");
        VBox test = new VBox(10);
        test.setAlignment(Pos.CENTER);
        test.getChildren().addAll(player1, player2, player3, player4, start);
        Scene welcomeScene = new Scene(test, 1400, 800);
        window.setScene(welcomeScene);
        window.show();


        start.setOnAction(e -> {
            window.setTitle("Buccaneer Board");
            window.setScene(mainBoardScene);
            name1.setText(player1.getText());
            name1.setTextFill(javafx.scene.paint.Color.BLACK);
            name2.setText(player2.getText());
            name2.setTextFill(javafx.scene.paint.Color.GREEN);
            name3.setText(player3.getText());
            name3.setTextFill(javafx.scene.paint.Color.RED);
            name4.setText(player4.getText());
            name4.setTextFill(javafx.scene.paint.Color.YELLOW);
            game.onUserNameInput(player1.getText(), player2.getText(), player3.getText(), player4.getText());
            game.onGameBegin();
        });
        //END OF START SCREEN

        //Uncomment this if you want a Fullscreen Game.
        //window.setFullScreen(true);

        shipGridPane.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                for (Node node : shipGridPane.getChildren()) { //We currently have to go through all 400 squares and
                    // check if it contains the mouse event - is they a better way of doing this?
                    if (node.getBoundsInParent().contains(e.getX(), e.getY())) {
                        Position pos = PositionHelper.gridChange(GridPane.getColumnIndex(node), GridPane.getRowIndex(node));
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
        pirateSong.play();
        pirateSong.setCycleCount(AudioClip.INDEFINITE);
    }

    /**
     * Changes the direction of the ship in the current location.
     * @param direction the direction of the ship to change to
     * @param position the position of the ship
     */
    void setShipDirection(buccaneer.enumData.Direction direction, buccaneer.helpers.Position position) {
        ImageView toChange = shipgrid.get(PositionHelper.positionToGridID(position));
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
        setShipDirection(ship.getDirection(), position);
    }

    /**
     * Moves a ship from one position to another.
     * @param ship the ship to move
     * @param moveTo the end location of the ship
     */
    void moveShip(Ship ship, Position moveTo) {
        ImageView toChange = shipgrid.get(PositionHelper.positionToGridID(ship.getLocation()));
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
            gridImage = highlightgrid.get(PositionHelper.positionToGridID(i));
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
     * Updates the sidebar with the information in Game
     * including elements such as the current player and score.
     */
    public void updateSidebar(){

    }


}