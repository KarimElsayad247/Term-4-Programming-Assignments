import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;

import java.util.ArrayList;
import java.util.HashMap;

public class RegisteredEmployees implements Logabble {


    private static RegisteredEmployees instance;

    private ObservableList<Trip> assignedTripsObservable = FXCollections.observableArrayList();

    private Employee activeEmployee;
    private static  ArrayList<Employee> registeredEmployees;

    public void setActiveEmployee(Employee activeEmployee) {
        this.activeEmployee = activeEmployee;
    }


    private HashMap<String, String> employeeMap = new HashMap<>();

    private RegisteredEmployees() {
        registeredEmployees = new ArrayList<>();
    }

    public static RegisteredEmployees getInstance() {
        if (instance == null) {
            instance = new RegisteredEmployees();
        }
        return instance;
    }

    public ObservableList<Trip> getAssignedTripsObservable() {
        AvailableTrips trips = AvailableTrips.getInstance();
        Driver current = (Driver)activeEmployee;
        for (int i = 0; i < current.getAssignedTrips().size(); i++) {
            Trip trip = trips.getAvailableTrips().get(i);
            assignedTripsObservable.add(trip);
//            if (trip.getDriver().equalsIgnoreCase(current.getUsername())) {
//                assignedTripsObservable.add(trip);
//            }
        }
        return assignedTripsObservable;
    }

    public Employee getActiveEmployee() {
        return activeEmployee;
    }

    public Employee findEmployee(String username, String password) {
        for (int i = 0; i < registeredEmployees.size(); i++) {
            Employee currentEmployee = registeredEmployees.get(i);
            if (currentEmployee.getUsername().equalsIgnoreCase(username)
                    && currentEmployee.getPassword().equals(password)) {
                return currentEmployee;
            }

        }
        AlertBox alertBox = new AlertBox();
        alertBox.display("???", "No such Employee!");
        return null;
    }

    public void loadTestCases() {
        registeredEmployees.add(new Driver("Hamdy", "1234"));
        registeredEmployees.add(new Driver("Ibrahim", "1234"));
        registeredEmployees.add(new Driver("Ahmed", "1234"));

        registeredEmployees.add(new Manager("admin", "admin"));

        for (int i = 0; i < registeredEmployees.size(); i++) {
            employeeMap.put(registeredEmployees.get(i).getUsername(), registeredEmployees.get(i).getPassword());
        }

    }

    @Override
    public boolean confirmSignIn(String username, String password) {
        if (!employeeMap.containsKey(username)) {
            return false;
        } else {
            if (employeeMap.get(username).equals(password)) {
                return true;
            }
        }
        return false;
    }

    boolean isManager(String username, String password) {
        Employee currentEmloyee;
        for (int i = 0; i < registeredEmployees.size(); i++) {
            currentEmloyee = registeredEmployees.get(i);
            if (currentEmloyee.getUsername().equals(username)
                    && currentEmloyee.getPassword().equals(password)) {
                if (currentEmloyee instanceof Manager) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;

    }

    boolean isManager(Employee employee) {

        if (employee instanceof Manager) {
            return true;
        } else {
            return false;
        }


    }


    public void importDriversToChoicebox(ChoiceBox assignDriverChoicebox) {
        for (int i = 0; i < registeredEmployees.size(); i++) {
            if ((!isManager(registeredEmployees.get(i)))){
                assignDriverChoicebox.getItems().add(registeredEmployees.get(i).getUsername());
            }

        }
    }


}
