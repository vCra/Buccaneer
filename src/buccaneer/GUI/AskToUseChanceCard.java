package buccaneer.GUI;

import buccaneer.cards.ChanceCard;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by adam on 02/05/2017.
 */
public class AskToUseChanceCard {

    private static boolean bool = false;

    public static boolean display(ChanceCard chanceCard) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        window.setTitle("Use The Chance Card?");

        Label title = new Label("Would you like to use Chance Card " + Integer.toString(chanceCard.getID()));
        Label text = new Label(chanceCard.getText());
        text.setMaxWidth(300);
        text.setWrapText(true);
        Button yes = new Button("Yes");
        Button no = new Button("No");

        window.setOnCloseRequest(e -> {
            e.consume();
        });

        yes.setOnAction(e -> {
            bool = true;
            window.close();
        });

        no.setOnAction(e -> {
            bool = false;
            window.close();
        });

        HBox buttons = new HBox(50);
        buttons.getChildren().addAll(yes, no);
        buttons.setAlignment(Pos.CENTER);
        VBox layout = new VBox(20);
        layout.getChildren().addAll(title, text, buttons);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 600, 600);
        window.setScene(scene);
        window.showAndWait();

        return bool;
    }

}
