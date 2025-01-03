package threads;
import core.AbstractTicketHandler;
import core.TicketPool;
import logging.Logger;

public class Vendor extends AbstractTicketHandler implements Runnable {
    private final int ticketReleaseRate;

    public Vendor(TicketPool ticketPool, int ticketReleaseRate) {
        super(ticketPool);
        this.ticketReleaseRate = ticketReleaseRate;
    }

    @Override
    public void run() {
        for (int i = 0; i < ticketReleaseRate; i++) {
            String ticket = "Ticket-" + System.nanoTime();
            ticketPool.addTickets(ticket);
            Logger.log("Added " + ticket);
//            Logger.log(Thread.currentThread().getName() +" added: " + ticket);
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