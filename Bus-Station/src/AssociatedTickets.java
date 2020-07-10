import java.io.Serializable;
import java.util.ArrayList;

public class AssociatedTickets implements Serializable {

    private ArrayList<Ticket> passengerTickets = new ArrayList<>();

    public void addAssociatedTicket(Ticket ticket) {
        passengerTickets.add(ticket);
    }

    public ArrayList<Ticket> getPassengerTickets() {
        return passengerTickets;
    }

    public void setPassengerTickets(ArrayList<Ticket> passengerTickets) {
        this.passengerTickets = passengerTickets;
    }
}
