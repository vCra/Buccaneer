package buccaneer.gui;

import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by Jakub Janas on 5/4/2017.
 */
public class LongJohnSilver {
    private static boolean bool;

    public static boolean display() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        window.setTitle("Do you want to hire Long John Silver?");

        Label text = new Label("Do you want to hire Long John Silver at the cost of one treasure?");
        Button yes = new Button("Yes");
        Button no = new Button("No");

        window.setOnCloseRequest(Event::consume);

        yes.setOnAction(e -> {
            bool = true;
            window.close();
        });

        no.setOnAction(e -> {
            bool = false;
            window.close();
        });

        HBox buttons = new HBox(20);
        buttons.getChildren().addAll(yes, no);
        buttons.setAlignment(Pos.CENTER);
        VBox layout = new VBox(20);
        layout.getChildren().addAll(text, buttons);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 400, 200);
        window.setScene(scene);
        window.showAndWait();

        return bool;
    }
}
