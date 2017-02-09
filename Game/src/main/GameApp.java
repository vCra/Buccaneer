package main;


/**
 * Created by aaw13 on 02/02/2017.
 */

public class GameApp {
    public static void main(String[] args) {
        Game game = new Game();
        game.begin();
        GUI ui = new GUI();
        ui.setGame(game);
    }
}
