package buccaneer.GUI;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Created by Josh on 04/05/2017.uih
 */
public class HelpMenu {



    public static void display() {

        Stage window = new Stage();
        window.setTitle("Help Menu");

        StackPane stackPane = new StackPane();
        BorderPane scene1pane = new BorderPane();
        BorderPane scene2pane = new BorderPane();

        VBox optionLayout = new VBox(20);
        VBox startAGameLayout = new VBox(20);
        VBox objectiveLayout = new VBox(20);

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
        Button smallIslands = new Button("Mud Bay, Anchor Bay and Cliff Creek");
        Button personalPort = new Button("Your Port");
        Button playerOwnedPort = new Button("Player Owned Port");
        Button unownedPort = new Button("Trading Port / Unowned Port");
        Button attacking = new Button("Attacking");
        Button winning = new Button("Winning The Game");

        optionLayout.getChildren().addAll(startAGame, objective, theMainBoard, crewCardMenu, treasureMenu, chanceCardMenu, movement, flatIsland,
                treasureIsland, pirateIsland, smallIslands,personalPort,playerOwnedPort,unownedPort, attacking, winning);

        Label startAGameTitle = new Label("Starting a game?");
        Label startAGameInfo = new Label("To Start a game once you have 4 players and the application is running you will be presented with a start screen. " +
                "In the start screen, there will be 4 player input boxes labelled clearly. To start the players will have to enter their name in a player box. " +
                "the player's names can't is less than 1 letter and more than 12. A player can pick colours by choosing from the box background colour of the input box.");
        startAGameInfo.setWrapText(true);


        startAGameLayout.getChildren().addAll(startAGameTitle, startAGameInfo);

        scene1pane.setLeft(optionLayout);
        scene1pane.setCenter(startAGameLayout);

        Scene scene1 = new Scene(scene1pane, 700,500);


//====================================================================================================


        Label objectiveTitle = new Label("Starting a game?");
        Label objectiveInfo = new Label("To Start a game once you have 4 players and the application is running you will be presented with a start screen. " +
                "In the start screen, there will be 4 player input boxes labelled clearly. To start the players will have to enter their name in a player box. " +
                "the player's names can't is less than 1 letter and more than 12. A player can pick colours by choosing from the box background colour of the input box.");
        startAGameInfo.setWrapText(true);
        scene2pane.setLeft(optionLayout);
        scene2pane.setCenter(objectiveLayout);
        objectiveLayout.getChildren().addAll(objectiveTitle, objectiveInfo);

        Scene scene2 = new Scene(scene2pane, 700, 500);


        startAGame.setOnAction(event -> window.setScene(scene1));
        objective.setOnAction(event -> window.setScene(scene2));
        window.setScene(scene1);
        window.show();
    }
}
