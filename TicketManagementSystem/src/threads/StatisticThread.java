package threads;

import core.TicketPool;
import logging.Logger;

public class StatisticThread implements Runnable{
    private TicketPool ticketPool;

    public StatisticThread(TicketPool ticketPool) {
        this.ticketPool = ticketPool;
    }

    @Override
    public void run() {
        while (true) {
            try {
//                Thread.sleep(300);
//                int totalTicketsAdded = ticketPool.getTotalTicketsAdded();
//                int totalTicketsRemoved = ticketPool.getTotalRemoved();
//                int remainingTickets = ticketPool.getTicketCount();
//                Logger.log("********** Total tickets added: "+totalTicketsAdded +", Total tickets removed: "+ totalTicketsRemoved +", Current size: " + remainingTickets);
                Logger.log("Total tickets added: " + ticketPool.getTotalTicketsAdded());
                Logger.log(("Total tickets removed : " + ticketPool.getTotalRemoved()));
                Logger.log("Current Size: " + ticketPool.getTicketCount());
                Thread.sleep(5000);  // sleeps for 5 seconds
            }
            catch (InterruptedException e) {
                Logger.log("Static Reporter Thread Interrupted");
                return;
            }
        }
    }
}
