import java.io.Serializable;

public class Passenger implements Serializable {

    private String username;
    private String password;
    private AssociatedTickets associatedTickets = new AssociatedTickets();

    public void buyTicket(String ticketType, Trip chosenTrip) {
        Ticket boughtTicket = new Ticket(chosenTrip.getPriceOfTrip(), ticketType, chosenTrip);


        try {
            associatedTickets.addAssociatedTicket(boughtTicket);
            boughtTicket.getTicketTrip().getVehicleType().decreaseAvailableSeatsByOne();
            System.out.println("bought successfully");
        } catch (NullPointerException e) {
            System.out.println("error adding ticket");
        }
    }


    public void setAssociatedTickets(AssociatedTickets associatedTickets) {
        this.associatedTickets = associatedTickets;
    }

    public Passenger(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AssociatedTickets getAssociatedTickets() {
        return associatedTickets;
    }

    public void setAssociatedTickets(Ticket ticket) {
        associatedTickets.addAssociatedTicket(ticket);
    }



}
