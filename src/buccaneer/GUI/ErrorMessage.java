package buccaneer.GUI;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by User on 23/04/2017.
 */
public class ErrorMessage {

    /**
     * Displays text to the user in its own box to be used when the user has done something they can't do the program
     * can tell them what they have done wrong.
     * @param message The explanation of what was done wrong.
     */
    public static void display(String message) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        window.setTitle("Error");

        Label text = new Label(message);
        text.setWrapText(true);
        Button button = new Button("Ok");

        button.setOnAction(e -> {window.close();});

        VBox layout = new VBox(20);
        layout.getChildren().addAll(text, button);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 400, 200);
        window.setScene(scene);
        window.show();
    }

}