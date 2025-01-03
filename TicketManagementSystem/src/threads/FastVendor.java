package threads;

import core.TicketPool;

public class FastVendor extends Vendor{

    public FastVendor(TicketPool ticketPool, int ticketReleaseRate) {
        super(ticketPool, ticketReleaseRate * 2); // double rate
    }
}

