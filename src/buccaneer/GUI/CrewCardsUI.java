package buccaneer.GUI;

import buccaneer.cards.CrewCard;
import buccaneer.enumData.CardColor;
import com.sun.scenario.effect.impl.sw.java.JSWBlend_BLUEPeer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Created by adam on 15/03/2017.
 */
//TODO: Javadoc

public class CrewCardsUI {

    /**
     * Displays the Crew cards that the player has to the user via a new window in front of the game board
     * @param player
     */
    public static void display(buccaneer.main.Player player) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Crew Cards");

        ArrayList<CrewCard> crewCardsToDisplay = player.getCrewCards();

        Font pirateFont = Font.loadFont(CrewCardsUI.class.getResource("/fonts/keelhauled-bb.regular.ttf").toExternalForm(), 30);

        Label title = new Label();
        title.setFont(pirateFont);
        title.setText(player.getName() + " Crew Cards");

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setMaxSize(450, 400);

        GridPane cards = new GridPane();

        ImageView crewCard;
        int moveTotal = 0;
        int redTotal = 0;
        int blackTotal = 0;
        int x = 0;
        int y = 0;
        for (CrewCard i : crewCardsToDisplay) {
            moveTotal += i.getValue();
            if (i.getColor().equals(CardColor.Red)) {
                redTotal += i.getValue();
            } else {
                blackTotal += i.getValue();
            }
            crewCard = new ImageView(i.getImage());
            crewCard.setFitWidth(90);
            crewCard.setFitHeight(104);
            crewCard.setPreserveRatio(true);
            crewCard.setSmooth(true);
            crewCard.setCache(true);
            GridPane.setColumnIndex(crewCard, x);
            GridPane.setRowIndex(crewCard, y);
            GridPane.setMargin(crewCard, new Insets(10, 10, 10, 10));
            cards.getChildren().add(crewCard);
            x++;
            if (x > 3) {
                x = 0;
                y++;
            }
        }

        scrollPane.setContent(cards);

        int attackTotal = 0;
        if (redTotal >= blackTotal) {
            attackTotal = redTotal - blackTotal;
        } else {
            attackTotal = blackTotal - redTotal;
        }

        Label movement = new Label("Movement: " + moveTotal);
        Label attackPower = new Label("Attack Power: " + attackTotal);
        HBox hBox = new HBox(40);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(movement, attackPower);

        VBox layout = new VBox(20);

        layout.getChildren().addAll(title, scrollPane, hBox);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout, 600, 600);
        window.setScene(scene);
        window.show();

    }

}
