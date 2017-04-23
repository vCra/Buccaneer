package buccaneer.GUI;

import buccaneer.cards.ChanceCard;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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

        Label title1 = new Label("Chance Card");
        title1.setFont(pirateFontTitle);
        Label title2 = new Label("Chance Card " + chanceCard.getID());
        title2.setFont(pirateFontTitle);

        Label chanceCardText = new Label(chanceCard.getText());
        chanceCardText.setFont(pirateFont);
        chanceCardText.setMaxWidth(150);
        chanceCardText.setWrapText(true);

        Button ok = new Button("Ok");
        ok.setOnAction(e -> {
            window.close();
        });

        Label instruction = new Label("Click the chance card to reveal what you got");
        instruction.setFont(pirateFont);

        ImageView imageView = new ImageView(chanceCard.getImage());
        imageView.setFitWidth(150);
        imageView.setFitHeight(100);
        imageView.setSmooth(true);
        imageView.setCache(true);
        imageView.setMouseTransparent(true);

        VBox layout1 = new VBox(15);
        layout1.getChildren().addAll(title1, imageView);
        layout1.setAlignment(Pos.CENTER);

        VBox layout2 = new VBox(15);
        layout2.getChildren().addAll(title2, chanceCardText, ok);
        layout2.setAlignment(Pos.CENTER);

        Scene scene1 = new Scene(layout1, 400, 400);
        Scene scene2 = new Scene(layout2, 400, 400);

        window.setScene(scene1);
        window.show();

        layout1.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                window.setScene(scene2);
            }
        });

    }
}
