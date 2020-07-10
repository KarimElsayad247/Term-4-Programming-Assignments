import java.util.ArrayList;

public class Driver extends Employee {

    private AvailableTrips availableTrips = AvailableTrips.getInstance();
    private ArrayList<Trip> assignedTrips = new ArrayList<>();

    public Driver(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public AvailableTrips getAvailableTrips() {
        return availableTrips;
    }

    public void setAvailableTrips(AvailableTrips availableTrips) {
        this.availableTrips = availableTrips;
    }

    public ArrayList<Trip> getAssignedTrips() {
        return assignedTrips;
    }

    public void setAssignedTrips(ArrayList<Trip> assignedTrips) {
        this.assignedTrips = assignedTrips;
    }
}
