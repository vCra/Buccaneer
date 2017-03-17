package buccaneer.GUI;

import buccaneer.cards.CrewCard;
import buccaneer.enumData.CardColor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
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

        Label title = new Label(player.getName() + " Crew Cards");

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPrefSize(400, 400);

        ImageView crewCard;
        int moveTotal = 0;
        int redTotal = 0;
        int blackTotal = 0;
        for (CrewCard i : crewCardsToDisplay) {
            moveTotal += i.getValue();
            if (i.getColor().equals(CardColor.Red)) {
                redTotal += i.getValue();
            } else {
                blackTotal += i.getValue();
            }
            //crewCard = new ImageView(i.getImage);
        }
        int attackTotal = 0;
        if (redTotal >= blackTotal) {
            attackTotal = redTotal - blackTotal;
        } else {
            attackTotal = blackTotal - redTotal;
        }

        Label movement = new Label("Movement: " + moveTotal);
        Label attackPower = new Label("Attack Power: " + attackTotal);

        //Scene will be 600, 600
        window.show();

    }

}
