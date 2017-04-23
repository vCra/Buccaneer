package buccaneer.GUI;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by User on 23/04/2017.
 */
public class AreYouSure {

    private static boolean bool;

    /**
     * A GUI box to let user change there mind used for when more perminent actions happen so the user has chance to
     * undo a mistake
     * @return bool. True for yes, false for no.
     */
    public static boolean display() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        window.setTitle("Are you sure?");

        Label text = new Label("Are you sure?");
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

        HBox buttons = new HBox(20);
        buttons.getChildren().addAll(yes, no);
        VBox layout = new VBox(10);
        layout.getChildren().addAll(text, buttons);

        Scene scene = new Scene(layout, 400, 200);
        window.setScene(scene);
        window.showAndWait();

        return bool;
    }

}
