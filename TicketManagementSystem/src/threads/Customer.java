package threads;
import core.AbstractTicketHandler;
import core.PriorityRetrieval;
import core.TicketPool;
import core.TicketRetrievalStrategy;
import logging.Logger;

public class Customer extends AbstractTicketHandler implements Runnable {
    private TicketRetrievalStrategy ticketRetrievalStrategy;

    public Customer (TicketPool ticketPool) {
        super(ticketPool);
        this.ticketRetrievalStrategy = new PriorityRetrieval();
    }

    public Customer(TicketPool ticketPool, TicketRetrievalStrategy ticketRetrievalStrategy) {
        super(ticketPool);
        this.ticketRetrievalStrategy = ticketRetrievalStrategy;
    }

    @Override
    public void run() {
        while (true) {
            String ticket = ticketRetrievalStrategy.retrieveTicket(ticketPool); // buy according to strategy - Q2
//            String ticket = ticketPool.removeTicket();
            if (ticket != null) {
                Logger.log(" Retrieved " + ticket);
//                Logger.log(Thread.currentThread().getName() + " retrieved: " + ticket);
            } else {
                Logger.log("found no tickets available.");
//                Logger.log(Thread.currentThread().getName() + " found no tickets available.");
                break;
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Logger.log("interrupted.");
            }
        }
    }

    @Override
    public void handleTickets() {
        run();
    }
}