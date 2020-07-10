import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class DriverSceneController implements Initializable {

    AvailableTrips availableTrips = AvailableTrips.getInstance();
    RegisteredEmployees registeredEmployees = RegisteredEmployees.getInstance();

    public TableView<Trip> driverTable = new TableView<>();

    public TableColumn<Trip, String> sourceColumn = new javafx.scene.control.TableColumn<>("Source");
    public TableColumn<Trip, String> destinationColumn = new javafx.scene.control.TableColumn<>("Destination");
    public TableColumn<Trip, String> numberOfStopsColumn = new TableColumn<>("Number Of Stops");

    public Label driverNameLabel = new Label();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        sourceColumn.setCellValueFactory(new PropertyValueFactory<Trip, String>("source"));
        destinationColumn.setCellValueFactory(new PropertyValueFactory<Trip, String>("destination"));
        numberOfStopsColumn.setCellValueFactory(new PropertyValueFactory<Trip, String>("numberOfStops"));

        driverTable.setItems(availableTrips.getTripsObservable());
//        driverTable.setItems(registeredEmployees.getAssignedTripsObservable());
//        driverTable.getColumns().addAll(sourceColumn,destinationColumn,numberOfStopsColumn);

        driverNameLabel.setText(registeredEmployees.getActiveEmployee().getUsername());
    }
}
