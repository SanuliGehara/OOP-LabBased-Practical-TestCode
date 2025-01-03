package core;

// Interface for add and remove ticket functionalitiess - implemented in Ticket Pool
public interface TicketOperation {
    void addTickets(String ticket);
    String removeTicket();
}

