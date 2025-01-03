package Questions;

import core.TicketPool;
import logging.Logger;
import threads.FastVendor;
import threads.SlowVendor;
import threads.Vendor;

public class Q4 {
    public static void main(String[] args) {
        TicketPool ticketPool = new TicketPool(50);
        int releaseRate = 2;

        Thread fastVendor = new Thread(new FastVendor(ticketPool,releaseRate),"Fast Vendor");
        Thread slowVendor = new Thread(new SlowVendor(ticketPool, releaseRate), "Slow Vendor");
        Thread vendor = new Thread(new Vendor(ticketPool, releaseRate), "Vendor");

        fastVendor.start();
        slowVendor.start();
        vendor.start();

        try {
            fastVendor.join();
            slowVendor.join();
            vendor.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Logger.log("Stopped adding tickets");
    }
}
