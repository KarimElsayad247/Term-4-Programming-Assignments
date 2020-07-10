import java.io.*;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.*;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class Main extends Application {

    public void setActivePassneegr(Passenger current, Passenger found) {
        current = found;
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        /***  preparation   ********************************/
        AvailableTrips availableTrips = AvailableTrips.getInstance();
        availableTrips.loadAllFromFile();

        Parent passengerRoot = FXMLLoader.load(getClass().getResource("passenger.fxml"));
        Parent driverRoot;


        RegisteredPassenger registeredPassenger = RegisteredPassenger.getInstance();
        RegisteredEmployees registeredEmployees = RegisteredEmployees.getInstance();


        registeredPassenger.loadAllFromFile();
        registeredEmployees.loadTestCases();


        Manager currentManager;
        Driver currentDriver;
        Passenger currentPassenger = null;


        Scene signinMenuScene;
        Scene passengerProfileScene = new Scene(passengerRoot, 500, 500);
        Scene driverProfileScene;
        Scene managerProfileScene;


        /*************************************************************************************************************/


        /***** Passenger profile scene  *************************************************************************/


        /*******************************************************************************************************/


        /***** Manager Profile scene and other management related stuff *****************************************/


        /*** Add trip  ******************************************************************************************/
        TabPane managaerPane = new TabPane();
        Tab addTripTab = new Tab("Add Trip");
        addTripTab.closableProperty().setValue(false);
        managaerPane.getTabs().addAll(addTripTab);

        Label addSourceLabel = new Label("Source");
        Label addDestinationLabel = new Label("Destination");
        Label chooseTypeLabel = new Label("Type");
        Label chooseNumberOfStopsLabel = new Label("Number of Stops");
        Label assignDriverLabel = new Label("Driver");
        Label assignVehicleLabel = new Label("Vehicle");
        Label tripPriceLabel = new Label("Price");

        TextField newSourceTextfield = new TextField();
        TextField newDestTextfield = new TextField();
        ChoiceBox tripTypeChoixebox = new ChoiceBox();
        ChoiceBox tripNumberOfStopsChoicebox = new ChoiceBox();
        ChoiceBox assignVehicleChoicebox = new ChoiceBox();
        ChoiceBox assignDriverChoicebox = new ChoiceBox();
        TextField tripPriceTextfield = new TextField();


        newSourceTextfield.setPrefSize(200, 30);
        newDestTextfield.setPrefSize(200, 30);
        tripTypeChoixebox.setPrefSize(200, 30);
        tripNumberOfStopsChoicebox.setPrefSize(200, 30);
        assignVehicleChoicebox.setPrefSize(200, 30);
        assignDriverChoicebox.setPrefSize(200, 30);
        tripPriceTextfield.setPrefSize(200, 30);

        tripPriceTextfield.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String inputString, String outputString) {
                if (!outputString.matches("\\d*")) {
                    tripPriceTextfield.setText(outputString.replaceAll("[^\\d]", ""));
                }
            }
        });

        tripTypeChoixebox.getItems().addAll("Internal", "External");


        tripNumberOfStopsChoicebox.getItems().addAll("One stop", "non-stop", "many stops");

        registeredEmployees.importDriversToChoicebox(assignDriverChoicebox);


        assignVehicleChoicebox.getItems().addAll("Mini-Bus", "Bus", "Limousine");


        GridPane addTripPane = new GridPane();
        addTripPane.setPadding(new Insets(50));
        addTripPane.setHgap(20);
        addTripPane.setVgap(10);
        //addTripPane.setAlignment(Pos.CENTER);

        GridPane.setConstraints(addSourceLabel, 0, 0);
        GridPane.setConstraints(addDestinationLabel, 0, 1);
        GridPane.setConstraints(chooseTypeLabel, 0, 2);
        GridPane.setConstraints(chooseNumberOfStopsLabel, 0, 3);
        GridPane.setConstraints(assignDriverLabel, 0, 4);
        GridPane.setConstraints(assignVehicleLabel, 0, 5);
        GridPane.setConstraints(tripPriceLabel, 0, 6);

        GridPane.setConstraints(newSourceTextfield, 1, 0, 2, 1);
        GridPane.setConstraints(newDestTextfield, 1, 1, 2, 1);
        GridPane.setConstraints(tripTypeChoixebox, 1, 2, 2, 1);
        GridPane.setConstraints(tripNumberOfStopsChoicebox, 1, 3, 2, 1);
        GridPane.setConstraints(assignDriverChoicebox, 1, 4, 2, 1);
        GridPane.setConstraints(assignVehicleChoicebox, 1, 5, 2, 1);
        GridPane.setConstraints(tripPriceTextfield, 1, 6, 2, 1);

        addTripPane.getChildren().addAll(addSourceLabel,
                addDestinationLabel, chooseTypeLabel, assignDriverLabel, assignVehicleLabel,
                newSourceTextfield, newDestTextfield, tripTypeChoixebox, chooseNumberOfStopsLabel,
                tripNumberOfStopsChoicebox, assignDriverChoicebox, assignVehicleChoicebox,
                tripPriceLabel, tripPriceTextfield);

        addTripTab.setContent(addTripPane);


        /**------------************/
        /**------------************/

        Button addNewTripButotn = new Button("Add");
        addNewTripButotn.setPrefSize(60, 30);


        Button saveTripsButton = new Button("Save");
        saveTripsButton.setPrefSize(60, 30);
        saveTripsButton.setOnAction(event -> {
            try {
                availableTrips.saveAllToFile();
            } catch (Exception e) {
                System.out.println("Error saving");
            }
        });
        /**------------************/
        /**------------************/

        HBox addBottom = new HBox(20);
        addBottom.setPadding(new Insets(30));
        addBottom.setAlignment(Pos.CENTER_RIGHT);
        addBottom.getChildren().addAll(addNewTripButotn, saveTripsButton);

//        addTripPane.setGridLinesVisible(true);

        GridPane.setConstraints(addBottom, 1, 7, 5, 1);
        addTripPane.getChildren().add(addBottom);
/*************************************************************************************************************************/

        /*****  remove trip           **************************/
        Tab removeTripTab = new Tab("Remove Trip");
        removeTripTab.closableProperty().setValue(false);
        managaerPane.getTabs().add(removeTripTab);

        Button removeTripButton = new Button("Remove");
        removeTripButton.setPrefSize(120, 30);
        ComboBox<String> availableTripsSourceCombo = new ComboBox();
        ComboBox<String> availableTripsDestinationCombo = new ComboBox();
        ComboBox<String> availableTripsTypeCombo = new ComboBox();
        ComboBox<String> availableTripsNumberStopsCombo = new ComboBox();
        ComboBox<String> availableTripsDriverCombo = new ComboBox();
        ComboBox<String> availableTripsVehicleCombo = new ComboBox();


        Label removeSourceLabel = new Label("Select Source");
        Label removeDestinationLabel = new Label("Select Destination");
        Label removeTypeLabel = new Label("Select Type");
        Label removeNumberOfStopsLabel = new Label("Select Number of Stops");
        Label removeDriverLabel = new Label("Select Driver");
        Label removeVehicleLabel = new Label("Select Vehicle");

        availableTripsSourceCombo.setPrefSize(300, 30);
        availableTripsSourceCombo.setPrefSize(300, 30);
        availableTripsDestinationCombo.setPrefSize(300, 30);
        availableTripsTypeCombo.setPrefSize(300, 30);
        availableTripsNumberStopsCombo.setPrefSize(300, 30);
        availableTripsDriverCombo.setPrefSize(300, 30);
        availableTripsVehicleCombo.setPrefSize(300, 30);


        try {
            availableTrips.importComboboxFromList(
                    availableTripsSourceCombo,
                    availableTripsDestinationCombo,
                    availableTripsTypeCombo,
                    availableTripsNumberStopsCombo,
                    availableTripsDriverCombo,
                    availableTripsVehicleCombo);
        } catch (Exception e) {
            System.out.println("Unknown Error while comboing");
        }


        //availableTripsSourceCombo.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) ->
//        availableTrips.refreshCombobox(oldValue));

//        removeTripButton.setOnAction(event -> availableTrips.removeTrip(availableTrips.findTrip(
//            availableTripsSourceCombo.getValue(),
//        )));


        GridPane removeTripGrid = new GridPane();
        removeTripGrid.setPadding(new Insets(50));
        removeTripGrid.setHgap(20);
        removeTripGrid.setVgap(20);

        GridPane.setConstraints(removeSourceLabel, 0, 0);
        GridPane.setConstraints(removeDestinationLabel, 0, 1);
        GridPane.setConstraints(removeTypeLabel, 0, 2);
        GridPane.setConstraints(removeNumberOfStopsLabel, 0, 3);
        GridPane.setConstraints(removeDriverLabel, 0, 4);
        GridPane.setConstraints(removeVehicleLabel, 0, 5);


        GridPane.setConstraints(availableTripsSourceCombo, 1, 0, 2, 1);
        GridPane.setConstraints(availableTripsDestinationCombo, 1, 1, 2, 1);
        GridPane.setConstraints(availableTripsTypeCombo, 1, 2, 2, 1);
        GridPane.setConstraints(availableTripsNumberStopsCombo, 1, 3, 2, 1);
        GridPane.setConstraints(availableTripsDriverCombo, 1, 4, 2, 1);
        GridPane.setConstraints(availableTripsVehicleCombo, 1, 5, 2, 1);


        GridPane.setConstraints(removeTripButton, 1, 6, 1, 1);


        removeTripGrid.getChildren().addAll(removeTripButton, removeSourceLabel, availableTripsSourceCombo,
                removeDestinationLabel,
                removeTypeLabel,
                removeNumberOfStopsLabel,
                removeDriverLabel,
                removeVehicleLabel,
                availableTripsDestinationCombo,
                availableTripsTypeCombo,
                availableTripsNumberStopsCombo,
                availableTripsDriverCombo,
                availableTripsVehicleCombo);

        removeTripTab.setContent(removeTripGrid);

        /**************************************************************************************/

        //Review Trips
        managaerPane.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Tab>() {
                    @Override
                    public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {
                        if (newValue.getText().equals("Review Trips")) {
                            primaryStage.setWidth(760);
                        } else {
                            primaryStage.setWidth(500);
                        }
                    }
                }
        );

        Tab reviewTripsTab = new Tab("Review Trips");
        reviewTripsTab.setClosable(false);
        TableView managerReviewTripsTable;

        //Source Column
        TableColumn<Trip, String> sourceColumn = new TableColumn<>("Source");
        sourceColumn.setMinWidth(150);
        sourceColumn.setCellValueFactory(new PropertyValueFactory<>("source"));

        //destination Column
        TableColumn<Trip, String> destinationColumn = new TableColumn<>("Destination");
        destinationColumn.setMinWidth(150);
        destinationColumn.setCellValueFactory(new PropertyValueFactory<>("destination"));


        //type Column
        TableColumn<Trip, String> typeColumn = new TableColumn<>("Type");
        typeColumn.setMinWidth(150);
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

        //destination Column
        TableColumn<Trip, String> vehicleNameColumn = new TableColumn<>("Vehicle");
        vehicleNameColumn.setMinWidth(150);
        vehicleNameColumn.setCellValueFactory(new PropertyValueFactory<>("vehicleName"));

        //numberOfStops Column
        TableColumn<Trip, String> numberOfStopsColumn = new TableColumn<>("Number Of Stops");
        numberOfStopsColumn.setMinWidth(150);
        numberOfStopsColumn.setCellValueFactory(new PropertyValueFactory<>("numberOfStops"));

        //driver Column
        TableColumn<Trip, String> driverColumn = new TableColumn<>("Driver");
        driverColumn.setMinWidth(150);
        driverColumn.setCellValueFactory(new PropertyValueFactory<>("driver"));


        //priceOfTrip Column
        TableColumn<Trip, Double> priceOfTripColumn = new TableColumn<>("Base Ticket Price");
        priceOfTripColumn.setMinWidth(150);
        priceOfTripColumn.setCellValueFactory(new PropertyValueFactory<>("priceOfTrip"));


        managerReviewTripsTable = new TableView();
        managerReviewTripsTable.setItems(availableTrips.getTripsObservable());
        managerReviewTripsTable.getColumns().addAll(sourceColumn, destinationColumn,
                typeColumn, vehicleNameColumn, numberOfStopsColumn, driverColumn, priceOfTripColumn);

        //Button deleteButton = new Button("Remove");

        addNewTripButotn.setOnAction(event -> {

            try {

                availableTrips.addTrip(
                        newSourceTextfield.getText(),
                        newDestTextfield.getText(), tripTypeChoixebox.getValue().toString(),
                        tripNumberOfStopsChoicebox.getValue().toString(),
                        availableTrips.toVehicle(assignVehicleChoicebox.getValue().toString()),
                        assignDriverChoicebox.getValue().toString(),
                        Double.parseDouble("+" + tripPriceTextfield.getText()));


            } catch (Exception e) {
                System.out.println("error adding trip");
            }});


            BorderPane managerReviewPane = new BorderPane();
            managerReviewPane.setTop(managerReviewTripsTable);

            reviewTripsTab.setContent(managerReviewPane);
            managaerPane.getTabs().add(reviewTripsTab);

            /*************************************************************************/
            managerProfileScene = new Scene(managaerPane, 500, 500);


            /****  Sign in Menu - appears first   **************************************************************************/


            Label usernameSignInLabel = new Label("Username");
            TextField usernameSignInTextfield = new TextField();

            RadioButton passengerToggle = new RadioButton("Passenger");
            RadioButton employeeToggle = new RadioButton("Employee");

            Label signInAsLabel = new Label("Sign in as:");
            ToggleGroup signInSwitch = new ToggleGroup();
            passengerToggle.setToggleGroup(signInSwitch);
            employeeToggle.setToggleGroup(signInSwitch);

            AlertBox alertBox = new AlertBox();

            Label passwordSighnInLabel = new Label("Password");
            TextField passwordSignInTextfield = new TextField();

            Button confirmSignInButton = new Button("Sign in");
            confirmSignInButton.setPrefSize(80, 30);

            Button exitButton = new Button("Exit");
            exitButton.setPrefSize(80, 30);
            exitButton.setOnAction(event -> {
                registeredPassenger.saveAllToFile();
                primaryStage.close();
            });

            confirmSignInButton.setOnAction(event -> {
                if ((usernameSignInTextfield.getText().equals("") || passwordSignInTextfield.getText().equals(""))) {
                    alertBox.display("Error", "Neither the username field nor the password field can be blank!");
                } else if (passengerToggle.isSelected()) {
                    if (registeredPassenger.confirmSignIn(usernameSignInTextfield.getText(), passwordSignInTextfield.getText())) {
                        setActivePassneegr(currentPassenger, registeredPassenger.findPassenger(
                                usernameSignInTextfield.getText(), passwordSignInTextfield.getText()));
                        registeredPassenger.setActivePassenegr(currentPassenger);
                        primaryStage.setScene(passengerProfileScene);
                    } else {
                        Boxes newbox = new AlertBox();
                        newbox.display("Sign in Error", "Incorrect username or password!");
                    }
                } else if (employeeToggle.isSelected()) {
                    if (registeredEmployees.confirmSignIn(usernameSignInTextfield.getText(), passwordSignInTextfield.getText())) {
                        if (registeredEmployees.isManager(usernameSignInTextfield.getText(), passwordSignInTextfield.getText())) {
                            registeredEmployees.setActiveEmployee(registeredEmployees.findEmployee(
                                    usernameSignInTextfield.getText(), passwordSignInTextfield.getText()));
                            primaryStage.setScene(managerProfileScene);
                        } else {
                            registeredEmployees.setActiveEmployee(registeredEmployees.findEmployee(
                                    usernameSignInTextfield.getText(), passwordSignInTextfield.getText()));
                            loadDriverScene(primaryStage);
//                        DriverSceneController.setDriverNameLabel(registeredEmployees.getActiveEmployee().getUsername());
                        }
                    } else {
                        Boxes newbox = new AlertBox();
                        newbox.display("Sign in Error", "Incorrect username or password!");
                    }
                } else if (!passengerToggle.isSelected() && !employeeToggle.isSelected()) {
                    Boxes alert = new AlertBox();
                    alert.display("Error Signing in", "Please select either passenger or employee!");
                } else {
                    Boxes alert = new AlertBox();
                    alert.display("", "something wrong!");
                }

            });


            Button createNewAccountButton = new Button("Create new Account");
            createNewAccountButton.setPrefSize(190, 30);
            createNewAccountButton.setOnAction(event -> {
                if ((usernameSignInTextfield.getText().equals("") || passwordSignInTextfield.getText().equals(""))) {
                    alertBox.display("Error", "Neither the username field nor the password field can be blank");

                } else {
                    registeredPassenger.addNewPassenger(
                            usernameSignInTextfield.getText(),
                            passwordSignInTextfield.getText());

                }
            });
            GridPane signinMenuGridpane = new GridPane();
            signinMenuGridpane.setPadding(new Insets(20, 5, 20, 5));
            signinMenuGridpane.setHgap(30);
            signinMenuGridpane.setVgap(10);
            signinMenuGridpane.setAlignment(Pos.CENTER);


            GridPane.setConstraints(signInAsLabel, 0, 0);
            GridPane.setConstraints(passengerToggle, 1, 0);
            GridPane.setConstraints(employeeToggle, 2, 0);
            GridPane.setConstraints(usernameSignInLabel, 0, 1);
            GridPane.setConstraints(passwordSighnInLabel, 0, 2);
            GridPane.setConstraints(usernameSignInTextfield, 1, 1, 2, 1);
            GridPane.setConstraints(passwordSignInTextfield, 1, 2, 2, 1);
            GridPane.setConstraints(confirmSignInButton, 1, 3);
            GridPane.setConstraints(exitButton, 2, 3);
            GridPane.setConstraints(createNewAccountButton, 1, 4, 2, 1);


            signinMenuGridpane.getChildren().addAll(usernameSignInLabel, usernameSignInTextfield, passwordSighnInLabel,
                    passwordSignInTextfield, confirmSignInButton, exitButton, createNewAccountButton,
                    passengerToggle, employeeToggle, signInAsLabel);
            signinMenuScene = new Scene(signinMenuGridpane, 400, 200);


            primaryStage.setTitle("Bus Station - Sign in");
            primaryStage.setScene(signinMenuScene);
//
            primaryStage.show();

//        primaryStage.setOnCloseRequest();


    }

        public void loadDriverScene (Stage primaryStage){
            try {
                Parent driverRoot = FXMLLoader.load(getClass().getResource("DriverScene.fxml"));
                Scene driverProfileScene = new Scene(driverRoot, 600, 500);
                primaryStage.setScene(driverProfileScene);

            } catch (Exception e) {
                System.out.println("Error");
            }
        }



    }
