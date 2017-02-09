package main;

import javafx.application.Application;
/**
 * Created by aaw13 on 02/02/2017.
 */

public class GameApp {
    public static void main(String[] args) {
        Game game = new Game();
        Application.launch(main.GUI.class, args);
        game.begin();
    }
}
