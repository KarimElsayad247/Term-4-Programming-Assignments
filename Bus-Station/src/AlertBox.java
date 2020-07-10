import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class AlertBox implements Boxes{

    @Override
    public void display(String title, String alertMessage) {


        Stage alertWindow = new Stage();
        alertWindow.initModality(Modality.APPLICATION_MODAL);
        alertWindow.setTitle(title);


        Label alertLabel = new Label(alertMessage);
        Button closeButton = new Button("OK");
        closeButton.setOnAction(event -> alertWindow.close());

        alertLabel.setAlignment(Pos.CENTER);
        closeButton.setAlignment(Pos.CENTER);

        VBox alertWindowLayout = new VBox(15);
        alertWindowLayout.getChildren().addAll(alertLabel, closeButton);
        alertWindowLayout.setAlignment(Pos.CENTER);

        Scene alertWindowScene = new Scene(alertWindowLayout, 600, 100);
        alertWindow.setScene(alertWindowScene);
        alertWindow.showAndWait();
    }

}