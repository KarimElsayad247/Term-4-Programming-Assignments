import java.io.Serializable;

public abstract class Vehicle implements Serializable {

    protected String vehicleName;
    protected short maxNumberOfSeats;
    protected short numberOfFreeSeats;

    public short getMaxNumberOfSeats() {
        return maxNumberOfSeats;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public void setMaxNumberOfSeats(short maxNumberOfSeats) {
        this.maxNumberOfSeats = maxNumberOfSeats;
    }

    public short getNumberOfFreeSeats() {
        return numberOfFreeSeats;
    }

    public void setNumberOfFreeSeats(short numberOfFreeSeats) {
        this.numberOfFreeSeats = numberOfFreeSeats;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void decreaseAvailableSeatsByOne() {
        numberOfFreeSeats--;
    }
}
