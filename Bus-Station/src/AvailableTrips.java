import javafx.collections.*;
import javafx.scene.control.ComboBox;

import java.io.*;
import java.util.*;

public class AvailableTrips {

    private static AvailableTrips instance;
    private ArrayList<Trip> availableTrips;
    private int amountOfTripsIndex = 0;

    private ArrayList<Trip> filteredTrips = new ArrayList<>();
    private ObservableList<Trip> tripsObservable = FXCollections.observableArrayList();


    private AvailableTrips() {
        availableTrips = new ArrayList<>();
    }

    public static AvailableTrips getInstance() {
        if (instance == null) {
            instance = new AvailableTrips();
        }
        return instance;
    }

    public ArrayList<Trip> getAvailableTrips() {
        return availableTrips;
    }



    public void addTrip(String source, String destination, String tripType, String numberOfStops, Vehicle vehicleType,
                        String driver, double priceOfTrip) {

        int i;
        Trip newTrip = new Trip(source, destination, tripType, numberOfStops, driver, vehicleType, priceOfTrip);
        availableTrips.add(newTrip);
        amountOfTripsIndex++;
        System.out.println("added!");
        for (i = 0; i < amountOfTripsIndex; i++) {
            System.out.println(getSource(i));
            System.out.println("this is a trip");
        }
        Boxes alert = new AlertBox();
        alert.display("Success!", "Trip added successfully");

    }

    public ObservableList<Trip> getTripsObservable() {

        int i;
        for (i = 0; i < amountOfTripsIndex; i++) {
            tripsObservable.add(availableTrips.get(i));
        }
        return tripsObservable;
    }


    public void loadAllFromFile() {

        int i = 0;
        Scanner sourceFile;
        try {
            sourceFile = openFileToLoad("trips.txt");
            while (sourceFile.hasNext()) {
                loadSingleFromFile(sourceFile.nextLine());
                i++;
            }
            sourceFile.close();
            amountOfTripsIndex = i;
            for (i = 0; i < amountOfTripsIndex; i++) {
                System.out.println(availableTrips.get(i).getSource());
            }
        } catch (Exception e) {
            System.out.println("Error loading data");
        }


    }

    private void loadSingleFromFile(String sourceLine) {

        String testSource;
        String testDest;
        String testType;
        String numberOfStops;
        String driver;
        Vehicle vehicle;
        short numberOfFreeSeats;
        double price;

        Scanner source = new Scanner(sourceLine);
        source.useDelimiter(",");
        while (source.hasNext()) {
            testSource = source.next();
            testDest = source.next();
            testType = source.next();
            numberOfStops = source.next();
            driver = source.next();
            vehicle = toVehicle(source.next());
            numberOfFreeSeats = Short.valueOf(source.next());
            price = Double.parseDouble(source.next());

            vehicle.setNumberOfFreeSeats(numberOfFreeSeats);
            Trip loadedTrip = new Trip(testSource, testDest, testType, numberOfStops, driver, vehicle, price);

            availableTrips.add(loadedTrip);
        }


    }

    //Saving

    public void saveAllToFile() {


        int i;
        Formatter saveFile;
        try {
            saveFile = openFileToSave("trips.txt");
            for (i = 0; i < amountOfTripsIndex; i++) {
                availableTrips.get(i).saveSingleTripToFile(saveFile);
                System.out.println("Saved new Trip");
            }
            saveFile.close();
        } catch (Exception e) {
            System.out.println("Error saving data");
        }


    }


    public void importComboboxFromList(ComboBox sourceCombo, ComboBox destCombo, ComboBox typeCombo, ComboBox stopsCombo,
                                       ComboBox driverCombo, ComboBox vehicleCombo) {
        int i;
        for (i = 0; i < amountOfTripsIndex; i++) {

            sourceCombo.getItems().add(availableTrips.get(i).getSource());
            destCombo.getItems().add(availableTrips.get(i).getDestination());
            typeCombo.getItems().add(availableTrips.get(i).getType());
            stopsCombo.getItems().add(availableTrips.get(i).getNumberOfStops());
            driverCombo.getItems().add(availableTrips.get(i).getDriver());
            vehicleCombo.getItems().add(availableTrips.get(i).getVehicleType().getVehicleName());
        }
    }

    public Formatter openFileToSave(String fileName) {
        Formatter destinationFile;
        try {
            destinationFile = new Formatter(fileName);
            return destinationFile;

        } catch (Exception e) {
            System.out.println("Error opening file");
            return null;
        }


    }
    //TODO: search and filtering

    public Scanner openFileToLoad(String fileName) {
        File source = new File(fileName);
        Scanner sourceFile;
        try {
            sourceFile = new Scanner(source);
            sourceFile.useDelimiter(",");
            return sourceFile;
        } catch (Exception e) {
            System.out.println("Error opening file to load trips");
            return null;
        }


    }


    public void addToTrips(Trip newTrip) {
        availableTrips.add(newTrip);
    }

    public String getSource(int tripIndex) {
        return availableTrips.get(tripIndex).getSource();
    }

    public void removeTrip(Trip tripToRmeove) {
        //TODO: remove trip from table view
        availableTrips.remove(availableTrips.indexOf(tripToRmeove));
    }

    public Trip getSingleTrip(int indexOfTrip) {
        return availableTrips.get(indexOfTrip);
    }

    public Trip findTrip(String source, String destination, String vehicle, String tripType, String numberOfStops) {
        for (int i = 0; i < amountOfTripsIndex; i++) {
            if (availableTrips.get(i).getSource().equalsIgnoreCase(source)
                    && availableTrips.get(i).getDestination().equalsIgnoreCase(destination)
                    && availableTrips.get(i).getVehicleType().getVehicleName().equalsIgnoreCase(vehicle)
                    && availableTrips.get(i).getType().equalsIgnoreCase(tripType)
                    && availableTrips.get(i).getNumberOfStops().equalsIgnoreCase(numberOfStops))
            {
                return availableTrips.get(i);
            }
        }
        return null;
    }


    public Vehicle toVehicle(String vehicleName) {
        Vehicle newVehicle;

        if (vehicleName.equalsIgnoreCase("Mini-bus")) {
            newVehicle = new MiniBus();
        } else if (vehicleName.equalsIgnoreCase("Bus")) {
            newVehicle = new Bus();
        } else if (vehicleName.equalsIgnoreCase("Limousine")) {
            newVehicle = new Limousine();
        } else {
            return null;
        }

        return newVehicle;
    }


}