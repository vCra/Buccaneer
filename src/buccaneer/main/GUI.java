package buccaneer.main;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URISyntaxException;
import java.util.ArrayList;

/**
 * Depleted - please do not use
 * For GUI stuff please store in GameApp
 * This file may be used for GUI events etc
 * in the future
 */
public class GUI extends Application {
    private int shipPosX = 5;
    private int shipPosY = 5;
    private int shipDirection = 0; // 0 is upwards the clockwise round the
    // direction adding 1 each time
    private boolean shipAction = true; // true = movement / false = direction
    private Label lblDirection = new Label();
    private ArrayList<ImageView> grid = new ArrayList<ImageView>();
    private ArrayList<ImageView> tilesToReset = new ArrayList<ImageView>();

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage window) throws Exception {
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

        lblDirection.setText("Direction: " + shipDirection);

        move.setOnAction(e -> {
            try {
                resetTilesAfterHighlight();
            } catch (URISyntaxException e1) {
                System.err.println("Error resetting to water");
            }
            try {
                highlightMoveable();
            } catch (Exception e1) {
                System.err.println("Highlight Image not found for moveable");
            }
            shipAction = true;
        });
        direction.setOnAction(e -> {
            try {
                resetTilesAfterHighlight();
            } catch (URISyntaxException e1) {
                System.err.println("Error resetting to water");
            }
            try {
                highlightDirection();
            } catch (Exception e1) {
                System.err.println("Highlight Image not found for direction");
            }
            shipAction = false;
        });

        Image water = new Image(getClass().getResource("/tile.jpg").toURI().toString());
        Image ship = new Image(getClass().getResource("/pirateship.jpeg").toURI().toString());

        gridpane.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
            for (Node node : gridpane.getChildren()) {
                if (node.getBoundsInParent().contains(e.getSceneX(), e.getSceneY())) {
                    int check;
                    if (shipAction) {
                        check = checkMoveValid(node);
                        if (check == 1) {
                            ImageView change = grid.get((shipPosY * 20) + shipPosX);
                            change.setImage(water);
                            shipPosX = GridPane.getColumnIndex(node);
                            shipPosY = GridPane.getRowIndex(node);
                            change = grid.get((shipPosY * 20) + shipPosX);
                            change.setImage(ship);
                            try {
                                resetTilesAfterHighlight();
                            } catch (URISyntaxException e1) {
                                System.err.println("Error resetting to water");
                            }
                        }
                    } else {
                        check = checkDirectionValid(node);
                        if (check == 1){
                            try {
                                resetTilesAfterHighlight();
                            } catch (URISyntaxException e1) {
                                System.err.println("Error resetting to water");
                            }
                        }
                    }
                }

            }
        });
    }

    private void resetTilesAfterHighlight() throws URISyntaxException {
        Image water = new Image(getClass().getResource("/tile.jpg").toURI().toString());
        Image ship = new Image(getClass().getResource("/pirateship.jpeg").toURI().toString());
        for(ImageView e : tilesToReset) {
            e.setImage(water);
        }
        ImageView setShip = grid.get((shipPosY * 20) + shipPosX);
        setShip.setImage(ship);
    }

    private void highlightDirection() throws URISyntaxException {
        Image highlightImage = new Image(getClass().getResource("/highlight.jpg").toURI().toString());
        ImageView toHighlight;
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                if (x == 0 && y == 0) {

                } else {
                    toHighlight = grid.get(((shipPosY + y) * 20) + (shipPosX + x));
                    toHighlight.setImage(highlightImage);
                    tilesToReset.add(toHighlight);
                }
            }
        }
    }

    private void highlightMoveable() throws URISyntaxException {
        int xIncrements = 0;
        int yIncrements = 0;
        switch (shipDirection) {
            case 0:
                xIncrements = 0;
                yIncrements = -1;
                break;
            case 1:
                xIncrements = 1;
                yIncrements = -1;
                break;
            case 2:
                xIncrements = 1;
                yIncrements = 0;
                break;
            case 3:
                xIncrements = 1;
                yIncrements = 1;
                break;
            case 4:
                xIncrements = 0;
                yIncrements = 1;
                break;
            case 5:
                xIncrements = -1;
                yIncrements = 1;
                break;
            case 6:
                xIncrements = -1;
                yIncrements = 0;
                break;
            case 7:
                xIncrements = -1;
                yIncrements = -1;
                break;
            default:
                System.err.println("Error on the move highlighting");
        }
        int highlightX = shipPosX;
        int highlightY = shipPosY;
        highlightX += xIncrements;
        highlightY += yIncrements;
        ImageView toHighlight;
        Image highlightImage = new Image(getClass().getResource("/highlight.jpg").toURI().toString());
        while (highlightX >= 0 && highlightY >= 0 && highlightX <= 19 && highlightY <= 19) {
            toHighlight = grid.get((highlightY * 20) + highlightX);
            toHighlight.setImage(highlightImage);
            tilesToReset.add(toHighlight);
            highlightX += xIncrements;
            highlightY += yIncrements;
        }
    }

    private int checkDirectionValid(Node node) {
        int valid = 0;
        int xClicked = GridPane.getColumnIndex(node);
        int yClicked = GridPane.getRowIndex(node);
        if ((xClicked == shipPosX) && (yClicked == (shipPosY - 1))) {
            shipDirection = 0;
            valid = 1;
        } else if ((xClicked == (shipPosX + 1)) && (yClicked == (shipPosY - 1))) {
            shipDirection = 1;
            valid = 1;
        } else if ((xClicked == (shipPosX + 1)) && (yClicked == shipPosY)) {
            shipDirection = 2;
            valid = 1;
        } else if ((xClicked == (shipPosX + 1)) && (yClicked == (shipPosY + 1))) {
            shipDirection = 3;
            valid = 1;
        } else if ((xClicked == shipPosX) && (yClicked == (shipPosY + 1))) {
            shipDirection = 4;
            valid = 1;
        } else if ((xClicked == (shipPosX - 1)) && (yClicked == (shipPosY + 1))) {
            shipDirection = 5;
            valid = 1;
        } else if ((xClicked == (shipPosX - 1)) && (yClicked == shipPosY)) {
            shipDirection = 6;
            valid = 1;
        } else if ((xClicked == (shipPosX - 1)) && (yClicked == (shipPosY - 1))) {
            shipDirection = 7;
            valid = 1;
        } else {
            System.err.println("Error on the direction checking");
        }
        lblDirection.setText("Direction: " + shipDirection);
        return valid;
    }

    private int checkMoveValid(Node node) {
        int valid = 0;
        int xClicked = GridPane.getColumnIndex(node);
        int yClicked = GridPane.getRowIndex(node);
        if (((xClicked >= 1 && yClicked >= 15) && (xClicked <= 3 && yClicked <= 18))
                || ((xClicked >= 16 && yClicked >= 1) && (xClicked <= 18 && yClicked <= 4))
                || ((xClicked >= 8 && yClicked >= 8) && (xClicked <= 11 && yClicked <= 11))) {
            // valid already = 0 so no need to do anything here
        } else {
            switch (shipDirection) {
                case 0:
                    if ((yClicked < shipPosY) && (xClicked == shipPosX))
                        valid = 1;
                    break;
                case 1:
                    if ((shipPosY - yClicked) == (xClicked - shipPosX))
                        valid = 1;
                    break;
                case 2:
                    if ((yClicked == shipPosY) && (xClicked > shipPosX))
                        valid = 1;
                    break;
                case 3:
                    if ((yClicked - shipPosY) == (xClicked - shipPosX))
                        valid = 1;
                    break;
                case 4:
                    if ((yClicked > shipPosY) && (xClicked == shipPosX))
                        valid = 1;
                    break;
                case 5:
                    if ((yClicked - shipPosY) == (shipPosX - xClicked))
                        valid = 1;
                    break;
                case 6:
                    if ((yClicked == shipPosY) && (xClicked < shipPosX))
                        valid = 1;
                    break;
                case 7:
                    if ((shipPosY - yClicked) == (shipPosX - xClicked))
                        valid = 1;
                    break;
                default:
                    System.err.println("Error on the checking moving");
            }
        }
        return valid;
    }

    private void updateGrid(GridPane gridpane) throws URISyntaxException {
        Image image = null;
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
