import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class AlertBox implements Alerts {


    public void display(String title, String alertMessage) {


        Stage alertWindow = new Stage();
        alertWindow.initModality(Modality.APPLICATION_MODAL);
        alertWindow.setTitle(title);


        Label alertLabel = new Label(alertMessage);
        Button closeButton = new Button("OK");
        closeButton.setOnAction(event -> alertWindow.close());

        VBox alertWindowLayout = new VBox(15);
        alertWindowLayout.getChildren().addAll(alertLabel, closeButton);
        alertWindowLayout.setAlignment(Pos.CENTER);

        Scene alertWindowScene = new Scene(alertWindowLayout, 400, 100);
        alertWindow.setScene(alertWindowScene);
        alertWindow.showAndWait();
    }

}