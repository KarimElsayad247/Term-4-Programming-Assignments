import com.sun.org.apache.xalan.internal.xsltc.dom.SingletonIterator;

import java.util.*;
import java.io.*;


public class RegisteredPassenger implements Logabble {

    private static RegisteredPassenger instance;
    private static ArrayList<Passenger> registeredPassengers;
    private int amountOfRegisteredPassenger = 0;
    private static Passenger activePassenegr = null;

    private RegisteredPassenger() {
        registeredPassengers = new ArrayList<>();
    }

    public static RegisteredPassenger getInstance() {
        if (instance == null) {
            instance = new RegisteredPassenger();
        }
        return instance;
    }


    public static void setActivePassenegr(Passenger passenger) {
        activePassenegr = passenger;
    }

    HashMap<String, String> passengersHashmap = new HashMap<String, String>();

    public void loadToHashMap() {
        for (int i = 0; i < amountOfRegisteredPassenger; i++) {
            passengersHashmap.put(registeredPassengers.get(i).getUsername(), registeredPassengers.get(i).getPassword());
        }
    }

    public void addNewPassenger(String username, String password) {
        Passenger passenger = new Passenger(username, password);
        registeredPassengers.add(passenger);
        passengersHashmap.put(username, password);
        amountOfRegisteredPassenger++;
        System.out.println("Added new passenger! " + username + " " + password);
        System.out.println(registeredPassengers.get(0).getUsername());

    }

    public Passenger findPassenger(String username, String password) {
        for (int i = 0; i < registeredPassengers.size(); i++) {
            Passenger currentPassenger = registeredPassengers.get(i);
            if (currentPassenger.getUsername().equalsIgnoreCase(username)
                    && currentPassenger.getPassword().equals(password)) {
                return currentPassenger;
            }

        }
        AlertBox alertBox = new AlertBox();
        alertBox.display("???", "No such Passenger!");
        return null;
    }

    public void buyTicket(String source, String dest, String vehicle,
                          String tripType, String numberOfStops, String ticketType) {
        AvailableTrips availableTrips = AvailableTrips.getInstance();
        System.out.println("trying to buy ticket now");
        try {
            System.out.println("trying to find");
            Trip boughtTicketTrip = availableTrips.findTrip(source, dest, vehicle, tripType, numberOfStops);
            if (boughtTicketTrip == null) {
                Boxes alert = new AlertBox();
                alert.display("No Such Trip", "Trip not found");
                return;
            }
            activePassenegr.buyTicket(ticketType,boughtTicketTrip);
        } catch (NullPointerException e) {
            System.out.println("Trip not found");
        }
    }

    @Override
    public boolean confirmSignIn(String username, String password) {
        if (!passengersHashmap.containsKey(username)) {
            return false;
        } else {            if (passengersHashmap.get(username).equals(password)) {
                return true;
            }
        }
        return false;
    }


    public void loadAllFromFile() {
        try {
            File temp = new File("passenger.ser");
            if (!temp.exists()) {
                return;
            }

            FileInputStream fileIn = new FileInputStream("passenger.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);

            registeredPassengers = (ArrayList<Passenger>) in.readObject();
            amountOfRegisteredPassenger = registeredPassengers.size();
            loadToHashMap();

            System.out.println("loaded passengers");
            System.out.println(registeredPassengers.get(0).getUsername());
            System.out.println(registeredPassengers.get(0).getPassword());
            System.out.println(amountOfRegisteredPassenger);
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("Employee class not found");

            return;
        }


    }


    public void saveAllToFile() {


        try {
            FileOutputStream fileOut = new FileOutputStream("passenger.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(registeredPassengers);
            out.close();
            fileOut.close();

            System.out.printf("Serialized data is saved in passenger.ser");
        } catch (Exception e) {
            System.out.println("Error saving data");
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


}
