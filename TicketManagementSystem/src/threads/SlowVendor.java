package threads;

import core.TicketPool;

public class SlowVendor extends Vendor{
    public SlowVendor(TicketPool ticketpool, int releaseRate) {
        super(ticketpool,releaseRate/2);  //half of release rate
    }
}
