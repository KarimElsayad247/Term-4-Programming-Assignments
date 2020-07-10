import java.util.Formatter;

public class Trip implements java.io.Serializable {

    private String source;
    private String destination;
    private String type;
    private String numberOfStops;
    private String driver;

    private transient String vehicleName;

    private Vehicle vehicleType;
    private double priceOfTrip;

    public Trip(String source, String destination, String type, String numberOfStops,
                String driver, Vehicle vehicleType, double priceOfTrip) {

        this.source = source;
        this.destination = destination;
        this.type = type;
        this.numberOfStops = numberOfStops;
        this.driver = driver;
        this.vehicleType = vehicleType;
        this.vehicleName = vehicleType.getVehicleName();
        this.priceOfTrip = priceOfTrip;
    }


    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    /*******************************************************************/

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public String getType() {
        return type;
    }

    public String getNumberOfStops() {
        return numberOfStops;
    }

    public String getDriver() {
        return driver;
    }

    public Vehicle getVehicleType() {
        return vehicleType;
    }

    public double getPriceOfTrip() {
        return priceOfTrip;
    }


/***************************************************************************/


public void setSource(String source) {
        this.source = source;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public void setVehicleType(Vehicle vehicleType) {
        this.vehicleType = vehicleType;
    }



    public void setNumberOfStops(String numberOfStops) {
        this.numberOfStops = numberOfStops;
    }

    public void setPriceOfTrip(double priceOfTrip) {
        this.priceOfTrip = priceOfTrip;
    }

    public void saveSingleTripToFile(Formatter saveFile) {
        saveFile.format("%s,%s,%s,%s,%s,%s,%d,%f\n",
                source,
                destination,
                type,
                numberOfStops,
                driver,
                vehicleName,
                vehicleType.numberOfFreeSeats,
                priceOfTrip);
    }


}

/*******************************************************************************/

