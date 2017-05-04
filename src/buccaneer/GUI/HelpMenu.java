package buccaneer.GUI;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

        VBox optionLayout = new VBox(20);
        VBox displayLayout = new VBox(20);
        VBox startAGameLayout = new VBox(20);

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

        Label startAGameTitle = new Label("Starting a game?");
        Label startAGameInfo = new Label("teeeessssstttttiiinnngggg");

        optionLayout.getChildren().addAll(startAGame, objective, theMainBoard, crewCardMenu, treasureMenu, chanceCardMenu, movement, flatIsland,
                treasureIsland, pirateIsland, smallIslands,personalPort,playerOwnedPort,unownedPort, attacking, winning);

        startAGameLayout.getChildren().addAll(startAGameTitle, startAGameInfo);

        stackPane.getChildren().addAll(optionLayout,displayLayout);

        Scene scene = new Scene(stackPane);
        window.setScene(scene);
        window.show();

        startAGame.setOnAction(event -> {

            window.hide();
            stackPane.getChildren().addAll(optionLayout, startAGameLayout);
            Scene sceneWithStartaAGame = new Scene(stackPane);
            window.setScene(sceneWithStartaAGame);
            window.show();
                }
        );
    }
}
