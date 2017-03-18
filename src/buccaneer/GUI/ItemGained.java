package buccaneer.GUI;

import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by adam on 15/03/2017.
 */
//TODO: Javadoc

public class ItemGained {

    //TODO: Gaining crew cards or treasure or anything else GUI

    public static void display() {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("You have gained:");

    }
}
