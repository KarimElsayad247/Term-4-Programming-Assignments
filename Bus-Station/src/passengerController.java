import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class passengerController implements Initializable {

    RegisteredPassenger registeredPassenger = RegisteredPassenger.getInstance();
    AvailableTrips availableTrips = AvailableTrips.getInstance();


    public ComboBox<String> sourceLocationCombo;
    public ComboBox<String> destinationLocationCombo;
    public ComboBox<String> assignedVehicleCombo;
    public ComboBox<String> numberOfStopsCombo;
    public ComboBox<String> tripTypeCombo;
    public ComboBox<String> ticketTypeCombo;
    public ComboBox<String> dummyCombo;

    public Button buyButton;
    public Button saveAndExitButton;

    public void close() {
        Stage stage = (Stage) saveAndExitButton.getScene().getWindow();
        stage.close();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        availableTrips.loadAllFromFile();
        availableTrips.importComboboxFromList(
                sourceLocationCombo,
                destinationLocationCombo,
                dummyCombo,
                dummyCombo,
                dummyCombo,
                dummyCombo
        );
        numberOfStopsCombo.getItems().addAll("One stop", "non-stop", "many stops");
        tripTypeCombo.getItems().addAll("Internal", "External");
        ticketTypeCombo.getItems().addAll("One-way", "Round Trip");
        assignedVehicleCombo.getItems().addAll("Bus", "Mini-bus", "Limousine");

        saveAndExitButton.setOnAction(event -> {
            try {
                registeredPassenger.saveAllToFile();
                close();
//                // get a handle to the stage
//                Stage stage = (Stage) saveAndExitButton.getScene().getWindow();
//                // do what you have to do
//                stage.close();


            } catch (Exception e) {
                System.out.println("Couldn't save and exit");
            }
        });

        buyButton.setOnAction(event -> {
            try {
                registeredPassenger.buyTicket(
                        sourceLocationCombo.getValue(), destinationLocationCombo.getValue(),
                        assignedVehicleCombo.getValue(), tripTypeCombo.getValue(),
                        numberOfStopsCombo.getValue(), ticketTypeCombo.getValue()
                );
            } catch (Exception e) {
                System.out.println("error sending buy order");
            }
        });
    }
}


