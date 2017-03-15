package buccaneer.GUI;

import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by adam on 15/03/2017.
 */
public class ChanceCardsUI {

    //TODO: the Chance Cards that the player has GUI

    public static void display() {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Chance Cards");

    }
}
