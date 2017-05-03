package buccaneer.main;

import buccaneer.GUI.ChanceCardsInHand;
import buccaneer.GUI.CrewCardsUI;
import buccaneer.GUI.ErrorMessage;
import buccaneer.GUI.PlayersTreasureUI;
import buccaneer.enumData.Direction;
import buccaneer.helpers.DirectionHelper;
import buccaneer.helpers.Position;
import buccaneer.helpers.PositionHelper;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
    private Label score1 = new Label();
    private Label score2 = new Label();
    private Label score3 = new Label();
    private Label score4 = new Label();
    private Label playersTurn = new Label();
    private Label playersHomePort = new Label();
    private Label numOfTreasureInShip = new Label();
    private Label turnNumber = new Label();

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage window) throws Exception {
        //MAIN BOARD
        Image background = new Image(getClass().getResource("/images/bg/grid-bg.gif").toURI().toString());

        ImageView imageview = new ImageView(background);
        imageview.setFitWidth(900);
        imageview.setFitHeight(900);
        imageview.setPreserveRatio(true);
        imageview.setSmooth(true);
        imageview.setCache(true);
        imageview.setMouseTransparent(true);

        GridPane shipGridPane = new GridPane();
        GridPane highlightGridPane = new GridPane();
        VBox leftGrid = new VBox(10);
        VBox rightGrid = new VBox(30);

        Font pirateFont = Font.loadFont(getClass().getResource("/fonts/keelhauled-bb.regular.ttf").toExternalForm(), 18);
        Font titlePirateFont = Font.loadFont(getClass().getResource("/fonts/keelhauled-bb.regular.ttf").toExternalForm(), 30);

        Label name1 = new Label();
        Label name2 = new Label();
        Label name3 = new Label();
        Label name4 = new Label();
        name1.setFont(pirateFont);
        name2.setFont(pirateFont);
        name3.setFont(pirateFont);
        name4.setFont(pirateFont);
        score1.setFont(pirateFont);
        score2.setFont(pirateFont);
        score3.setFont(pirateFont);
        score4.setFont(pirateFont);
        Label playerTurnTitle = new Label("Current Player");
        playerTurnTitle.setFont(titlePirateFont);
        playersTurn.setFont(pirateFont);
        playersHomePort.setFont(pirateFont);
        numOfTreasureInShip.setFont(pirateFont);
        HBox nameScore1 = new HBox(10);
        HBox nameScore2 = new HBox(10);
        HBox nameScore3 = new HBox(10);
        HBox nameScore4 = new HBox(10);
        nameScore1.getChildren().addAll(name1, score1);
        nameScore2.getChildren().addAll(name2, score2);
        nameScore3.getChildren().addAll(name3, score3);
        nameScore4.getChildren().addAll(name4, score4);
        VBox upRight = new VBox(15);

        upRight.getChildren().addAll(turnNumber,nameScore1, nameScore2, nameScore3, nameScore4);
        VBox bottomRight = new VBox(15);
        bottomRight.getChildren().addAll(playerTurnTitle, playersTurn, playersHomePort, numOfTreasureInShip);
        rightGrid.getChildren().addAll(upRight, bottomRight);

        Button mute = new Button("mute");
        mute.setOnAction(e -> pirateSong.stop());
        leftGrid.getChildren().add(mute);

        Button crewCards = new Button("Crew Cards");
        crewCards.setOnAction(e -> CrewCardsUI.display(game.getCurrentPlayer()));
        leftGrid.getChildren().add(crewCards);

        Button treasureInShip = new Button("Treasure");
        treasureInShip.setOnAction(e -> PlayersTreasureUI.display(game.getCurrentPlayer()));
        leftGrid.getChildren().add(treasureInShip);

        Button playersChanceCards = new Button("Chance Cards");
        playersChanceCards.setOnAction(e -> ChanceCardsInHand.display(game.getCurrentPlayer()));
        leftGrid.getChildren().add(playersChanceCards);


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

        StackPane gridStack = new StackPane();
        gridStack.getChildren().addAll(highlightGridPane, shipGridPane);

        ImageView horizontalIndent = new ImageView();
        horizontalIndent.setFitWidth(50);
        ImageView verticalIndent = new ImageView();
        verticalIndent.setFitHeight(50);
        HBox horizontalIndentLayout = new HBox();
        VBox verticalIndentLayout = new VBox();
        horizontalIndentLayout.getChildren().addAll(horizontalIndent, gridStack);
        verticalIndentLayout.getChildren().addAll(verticalIndent, horizontalIndentLayout);


        StackPane stack = new StackPane();
        stack.getChildren().addAll(imageview, verticalIndentLayout);

        HBox mainBoardLayout = new HBox(20);
        mainBoardLayout.setAlignment(Pos.CENTER);
        mainBoardLayout.getChildren().addAll(leftGrid, stack, rightGrid);

        Scene mainBoardScene = new Scene(mainBoardLayout, 1500, 900);
        mainBoardLayout.setStyle("-fx-background-color: #ffffff;");


        //END OF MAIN BOARD


        //START SCREEN

        window.setTitle("Welcome to Buccaneer");
        Label note = new Label(" \n \n Please make all names between 1 and 12 characters long");
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
        VBox welcomeLayout = new VBox(20);
        welcomeLayout.setAlignment(Pos.CENTER);

        HBox name12Layout = new HBox(10);
        name12Layout.getChildren().addAll(player1, player2);
        name12Layout.setAlignment(Pos.CENTER);
        HBox name34Layout = new HBox(10);
        name34Layout.getChildren().addAll(player3, player4);
        name34Layout.setAlignment(Pos.CENTER);

        Button exitButton = new Button("x");
        exitButton.setTextFill(Color.RED);
        exitButton.setTranslateX(500);
        exitButton.setTranslateY(- 500);

        welcomeLayout.getChildren().addAll(note, name12Layout, name34Layout, start, exitButton);

        Image bgimg = new Image(getClass().getResourceAsStream("/images/bg/mainbg.png"));
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(new ImageView(bgimg), welcomeLayout);
        stackPane.setBackground(null);
        Scene welcomeScene = new Scene(stackPane, Color.TRANSPARENT);

        Stage welcomeWindow = new Stage();
        welcomeWindow.initStyle(StageStyle.TRANSPARENT);
        welcomeWindow.setScene(welcomeScene);
        welcomeWindow.show();

        exitButton.setOnAction(x -> Platform.exit());

        start.setOnAction(e -> {
            String playerName1 = player1.getText();
            String playerName2 = player2.getText();
            String playerName3 = player3.getText();
            String playerName4 = player4.getText();
            if (playerName1.length() > 0 && playerName1.length() <= 12 && playerName2.length() > 0 && playerName2.length() <= 12 && playerName3.length() > 0 && playerName3.length() <= 12 && playerName4.length() > 0 && playerName4.length() <= 12) {
                window.setTitle("Buccaneer Board");
                window.setScene(mainBoardScene);
                welcomeWindow.hide();
                window.show();
                name1.setText(playerName1);
                name1.setStyle("-fx-background-color: #000;");
                name1.setTextFill(Color.WHITE);
                name2.setText(playerName2);
                name2.setStyle("-fx-background-color: #0b0;");
                name2.setTextFill(Color.WHITE);
                name3.setText(playerName3);
                name3.setStyle("-fx-background-color: #f30;");
                name3.setTextFill(Color.BLACK);
                name4.setText(playerName4);
                name4.setStyle("-fx-background-color: #ff0;");
                name4.setTextFill(Color.BLACK);
                game.onUserNameInput(playerName1, playerName2, playerName3, playerName4);
                game.onGameBegin();
                updateScores();
                updateTurnNumber();
                updatePlayersTurn();
            } else {
                ErrorMessage.display("Please enter names between 1 and 12 characters long");
                note.setTextFill(Color.RED);
            }

            exitButton.setOnAction(x -> Platform.exit());

        });
        //END OF START SCREEN

        //Uncomment this if you want a Fullscreen Game.
        //window.setFullScreen(true);

        shipGridPane.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
            for (Node node : shipGridPane.getChildren()) { //We currently have to go through all 400 squares and
                // check if it contains the mouse event - is they a better way of doing this?
                if (node.getBoundsInParent().contains(e.getX(), e.getY())) {
                    Position pos = PositionHelper.gridChange(GridPane.getColumnIndex(node), GridPane.getRowIndex(node));
                    game.onSquareClick(pos);
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
     * updates the turn number on the GUI
     */
    public void updateTurnNumber() {
        turnNumber.setText("Turn Number: " + Integer.toString(game.getTurnNum()));
    }

    /**
     * updates the current player on the GUI
     */
    public void updatePlayersTurn() {
        Player player = game.getCurrentPlayer();
        playersTurn.setText(player.getName());
        int playersID = player.getId();
        switch (playersID) {
            case 1:
                playersTurn.setStyle("-fx-background-color: #000;");
                playersTurn.setTextFill(Color.WHITE);
                break;
            case 2:
                playersTurn.setStyle("-fx-background-color: #0b0;");
                playersTurn.setTextFill(Color.WHITE);
                break;
            case 3:
                playersTurn.setStyle("-fx-background-color: #f30;");
                playersTurn.setTextFill(Color.BLACK);
                break;
            case 4:
                playersTurn.setStyle("-fx-background-color: #ff0;");
                playersTurn.setTextFill(Color.BLACK);
                break;
        }
        playersHomePort.setText("Home Port: " + player.getPort().getName());
        numOfTreasureInShip.setText("no. of treasures: " + player.getPlayerShip().getTreasures().size());
    }

    /**
     * updates the scores displayed next to the players names
     */
    public void updateScores(){
        Player player;
        player = game.getPlayer(1);
        score1.setText("Score: " + player.getScore().toString());
        player = game.getPlayer(2);
        score2.setText("Score: " + player.getScore().toString());
        player = game.getPlayer(3);
        score3.setText("Score: " + player.getScore().toString());
        player = game.getPlayer(4);
        score4.setText("Score: " + player.getScore().toString());
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
    void setShipPosition(Ship ship, buccaneer.helpers.Position position) {
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
            highlight = new Image(getClass().getResource("/images/tiles/highlight.png").toURI().toString());
        } catch (URISyntaxException e) {
            ErrorMessage.display("Problem with highlight image");
        }
        ImageView gridImage;
        for (buccaneer.helpers.Position i : positions) {
            gridImage = highlightgrid.get(PositionHelper.positionToGridID(i));
            gridImage.setImage(highlight);
        }
    }

    /**
     * Highlights a square the ship can change direction too
     * @param highlightPosition this is the position that the square to highlight will go NOT the ship
     * @param arrowDirection    this is the direction that the arrow should face
     */
    public void highlightDirection(Position highlightPosition, Direction arrowDirection) {
        Image arrow = null;
        Image arrow45 = null;
        try {
            arrow = new Image(getClass().getResource("/images/tiles/spin.png").toURI().toString());
            arrow45 = new Image(getClass().getResource("/images/tiles/spin(45).png").toURI().toString());
        }
        catch (URISyntaxException e) {
            System.err.println("Problem with directional highlight images");
        }
        ImageView toChange = highlightgrid.get(PositionHelper.positionToGridID(highlightPosition));
        switch (arrowDirection) {
            case N:
                toChange.setImage(arrow);
                toChange.setRotate(0);
                break;
            case NE:
                toChange.setImage(arrow45);
                toChange.setRotate(0);
                break;
            case E:
                toChange.setImage(arrow);
                toChange.setRotate(90);
                break;
            case SE:
                toChange.setImage(arrow45);
                toChange.setRotate(90);
                break;
            case S:
                toChange.setImage(arrow);
                toChange.setRotate(180);
                break;
            case SW:
                toChange.setImage(arrow45);
                toChange.setRotate(180);
                break;
            case W:
                toChange.setImage(arrow);
                toChange.setRotate(270);
                break;
            case NW:
                toChange.setImage(arrow45);
                toChange.setRotate(270);
                break;
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
}