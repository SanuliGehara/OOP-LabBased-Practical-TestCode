package Questions;

import core.TicketPool;
import threads.Customer;
import threads.StatisticThread;
import threads.Vendor;

public class Q5 {
    public static void main(String[] args) {
        TicketPool ticketPool = new TicketPool(10);

        Thread vendor = new Thread(new Vendor(ticketPool,5), "Vendor thread");
        Thread customer = new Thread(new Customer(ticketPool), "Customer thread");
        Thread staticReporter = new Thread(new StatisticThread(ticketPool),"Static Reporter");

        vendor.start();
        customer.start();
        staticReporter.start();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        vendor.interrupt();
        customer.interrupt();
        staticReporter.interrupt();
    }
}
