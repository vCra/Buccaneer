package buccaneer.GUI;

import buccaneer.cards.ChanceCard;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * Created by adam on 15/03/2017.
 */
//TODO: Javadoc

public class ChanceCardsUI {

    public static void display(ChanceCard chanceCard) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Chance Cards");

        Font pirateFont = Font.loadFont(CrewCardsUI.class.getResource("/fonts/keelhauled-bb.regular.ttf").toExternalForm(), 18);
        Font pirateFontTitle = Font.loadFont(CrewCardsUI.class.getResource("/fonts/keelhauled-bb.regular.ttf").toExternalForm(), 32);

        Label title = new Label("Chance Card " + chanceCard.getID());
        title.setFont(pirateFontTitle);
        Label chanceCardText = new Label(chanceCard.getText());
        chanceCardText.setFont(pirateFont);
        chanceCardText.setMaxWidth(300);

        Button ok = new Button("Ok");
        ok.setOnAction(e -> {
            window.close();
        });

        VBox layout = new VBox(15);
        layout.getChildren().addAll(title, chanceCardText);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 400, 300);
        window.setScene(scene);
        window.show();
    }
}
