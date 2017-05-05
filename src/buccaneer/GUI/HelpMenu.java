package buccaneer.GUI;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.net.URISyntaxException;

/**
 * Created by Josh on 04/05/2017.uih
 */
public class HelpMenu {



    public static void display() {
        Stage window = new Stage();
        window.setTitle("Help Menu");

        ImageView imageView = new ImageView();


        VBox buttons = new VBox(20);

        Button startAGame = new Button("Starting A Game");
        Button objective = new Button("Objective");
        Button theMainBoard = new Button("The Main Board");
        Button crewCardMenu = new Button("Crew Card Menu");
        Button treasureMenu = new Button("Treasure Menu");
        Button chanceCardMenu = new Button("Chance Card Menu");
        Button movement = new Button("Movement");
        Button flatIsland = new Button("Flat Island");
        Button treasureIsland = new Button("Treasure Island");
        Button pirateIsland = new Button("Pirate Island");
        Button smallIslands = new Button("Bays");
        Button personalPort = new Button("Your Port");
        Button playerOwnedPort = new Button("Player Owned Port");
        Button unownedPort = new Button("Trading Port");
        Button attacking = new Button("Attacking");

        Label spacing = new Label();
        spacing.setMinHeight(1);

        buttons.getChildren().addAll(spacing, startAGame, objective, theMainBoard, crewCardMenu, treasureMenu, chanceCardMenu, movement, flatIsland,
                treasureIsland, pirateIsland, smallIslands,personalPort,playerOwnedPort,unownedPort, attacking);

        startAGame.setOnAction(e -> {
            try {
                imageView.setImage(new Image(HelpMenu.class.getResource("/images/helpscrolls/start").toURI().toString()));
            } catch (URISyntaxException e1) {
                ErrorMessage.display("Error loading help image");
            }
        });

        objective.setOnAction(e -> {
            try {
                imageView.setImage(new Image(HelpMenu.class.getResource("/images/helpscrolls/objective.png").toURI().toString()));
            } catch (URISyntaxException e1) {
                ErrorMessage.display("Error loading help image");
            }
        });

        theMainBoard.setOnAction(e -> {
            try {
                imageView.setImage(new Image(HelpMenu.class.getResource("/images/helpscrolls/mainboard.png").toURI().toString()));
            } catch (URISyntaxException e1) {
                ErrorMessage.display("Error loading help image");
            }
        });

        crewCardMenu.setOnAction(e -> {
            try {
                imageView.setImage(new Image(HelpMenu.class.getResource("/images/helpscrolls/crewcards.png").toURI().toString()));
            } catch (URISyntaxException e1) {
                ErrorMessage.display("Error loading help image");
            }
        });

        treasureMenu.setOnAction(e -> {
            try {
                imageView.setImage(new Image(HelpMenu.class.getResource("/images/helpscrolls/treasure.png").toURI().toString()));
            } catch (URISyntaxException e1) {
                ErrorMessage.display("Error loading help image");
            }
        });

        chanceCardMenu.setOnAction(e -> {
            try {
                imageView.setImage(new Image(HelpMenu.class.getResource("/images/helpscrolls/chancecards.png").toURI().toString()));
            } catch (URISyntaxException e1) {
                ErrorMessage.display("Error loading help image");
            }
        });

        movement.setOnAction(e -> {
            try {
                imageView.setImage(new Image(HelpMenu.class.getResource("/images/helpscrolls/movement.png").toURI().toString()));
            } catch (URISyntaxException e1) {
                ErrorMessage.display("Error loading help image");
            }
        });

        flatIsland.setOnAction(e -> {
            try {
                imageView.setImage(new Image(HelpMenu.class.getResource("/images/helpscrolls/flatisland.png").toURI().toString()));
            } catch (URISyntaxException e1) {
                ErrorMessage.display("Error loading help image");
            }
        });

        treasureIsland.setOnAction(e -> {
            try {
                imageView.setImage(new Image(HelpMenu.class.getResource("/images/helpscrolls/treasureisland.png").toURI().toString()));
            } catch (URISyntaxException e1) {
                ErrorMessage.display("Error loading help image");
            }
        });

        pirateIsland.setOnAction(e -> {
            try {
                imageView.setImage(new Image(HelpMenu.class.getResource("/images/helpscrolls/pirateisland.png").toURI().toString()));
            } catch (URISyntaxException e1) {
                ErrorMessage.display("Error loading help image");
            }
        });

        smallIslands.setOnAction(e -> {
            try {
                imageView.setImage(new Image(HelpMenu.class.getResource("/images/helpscrolls/smallislands.png").toURI().toString()));
            } catch (URISyntaxException e1) {
                ErrorMessage.display("Error loading help image");
            }
        });

        personalPort.setOnAction(e -> {
            try {
                imageView.setImage(new Image(HelpMenu.class.getResource("/images/helpscrolls/personalport.png").toURI().toString()));
            } catch (URISyntaxException e1) {
                ErrorMessage.display("Error loading help image");
            }
        });

        playerOwnedPort.setOnAction(e -> {
            try {
                imageView.setImage(new Image(HelpMenu.class.getResource("/images/helpscrolls/playerownedport.png").toURI().toString()));
            } catch (URISyntaxException e1) {
                ErrorMessage.display("Error loading help image");
            }
        });

        unownedPort.setOnAction(e -> {
            try {
                imageView.setImage(new Image(HelpMenu.class.getResource("/images/helpscrolls/unownedport.png").toURI().toString()));
            } catch (URISyntaxException e1) {
                ErrorMessage.display("Error loading help image");
            }
        });

        attacking.setOnAction(e -> {
            try {
                imageView.setImage(new Image(HelpMenu.class.getResource("/images/helpscrolls/attack.png").toURI().toString()));
            } catch (URISyntaxException e1) {
                ErrorMessage.display("Error loading help image");
            }
        });

        Label spacing2 = new Label();
        spacing2.setMinHeight(1);

        try {
            imageView.setImage(new Image(HelpMenu.class.getResource("/images/helpscrolls/start.png").toURI().toString()));
        } catch (URISyntaxException e1) {
            ErrorMessage.display("Error loading help image");
        }

        VBox imageLayout = new VBox();
        imageLayout.getChildren().addAll(spacing2, imageView);

        HBox layout = new HBox();
        layout.getChildren().addAll(buttons, imageLayout);
        Scene scene = new Scene(layout, 600, 825);
        window.setScene(scene);
        window.show();
    }
}
