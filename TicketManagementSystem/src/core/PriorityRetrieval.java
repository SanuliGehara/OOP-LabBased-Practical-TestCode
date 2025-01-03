package core;

public class PriorityRetrieval implements TicketRetrievalStrategy{
    @Override
    public String retrieveTicket(TicketPool ticketPool) {
        // FIFO - remove(0) from List
        return ticketPool.removeTicket();
    }
}
