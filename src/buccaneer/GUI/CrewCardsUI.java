package buccaneer.GUI;

import buccaneer.cards.CrewCard;
import javafx.scene.Scene;
import javafx.stage.*;

import java.util.ArrayList;

/**
 * Created by adam on 15/03/2017.
 */
public class CrewCardsUI {

    //TODO: the Crew Cards that the player has GUI

    public static void display(buccaneer.main.Player player) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Crew Cards");

        ArrayList<CrewCard> crewCardsToDisplay = new ArrayList<>();
        crewCardsToDisplay.addAll(player.getCrewCards());

        window.show();

    }

}
