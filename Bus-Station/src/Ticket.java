import java.io.Serializable;

public class Ticket implements Serializable {

    private double ticketPrice;
    private String ticketType;
    private Trip ticketTrip;



    public Ticket(double ticketPrice, String ticketType,Trip ticketTrip) {
        this.ticketType = ticketType;
        if (ticketType.equalsIgnoreCase("One-way")) {
            this.ticketPrice = ticketPrice;
        } else if (ticketType.equalsIgnoreCase("Round Trip")) {
            this.ticketPrice = ticketPrice * 1.75;
        }
        this.ticketTrip = ticketTrip;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public Trip getTicketTrip() {
        return ticketTrip;
    }

    public void setTicketTrip(Trip ticketTrip) {
        this.ticketTrip = ticketTrip;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }
}
