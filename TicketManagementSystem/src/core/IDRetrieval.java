package core;

public class IDRetrieval implements TicketRetrievalStrategy{
    private String targetID;

    public IDRetrieval(String targetID) {
        this.targetID = targetID;
    }

    @Override
    public String retrieveTicket(TicketPool ticketPool) {
        // Should be in sync block - accessing monitor/ ticketPool
        synchronized (ticketPool) {
            for (String ticket : ticketPool.getTickets()) {
                if (ticket.equals(this.targetID)) {
                    ticketPool.getTickets().remove(ticket);
                    System.out.println("Removed ticket "+ ticket + ", now "+ ticketPool);
                    return ticket;
                }

            }
            return null; // no ticket found
        }
    }
}
