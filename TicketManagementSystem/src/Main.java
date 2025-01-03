import config.Configuration;
import core.TicketPool;
import logging.Logger;
import threads.Customer;
import threads.StatisticThread;
import threads.Vendor;
import ui.CommandLineInterface;
public class Main {
    public static void main(String[] args) {
        Configuration config = CommandLineInterface.configureSystem();
        TicketPool ticketPool = new TicketPool(config.getMaxTicketCapacity());

        // for multiple vendors and customers
        Thread[] vendors = new Thread[4];
        Thread[] customers = new Thread[4];

        Thread statisticalReporter = new Thread(new StatisticThread(ticketPool),"statistical reporter");
        statisticalReporter.start();

        for (int i=0; i<vendors.length; i++) {
            vendors[i] = new Thread(new Vendor(ticketPool, config.getTicketReleaseRate()), "Vendor " + (i+1));
            vendors[i].start();
        }

        for (int i=0; i<customers.length; i++) {
            customers[i] = new Thread(new Customer(ticketPool), "Customer " + (i+1));
            customers[i].start();
        }

        // waiting to stopping all vendors and customers
        try {
            for (Thread vendor : vendors) {
                vendor.join();
            }

            for (Thread customer : customers) {
                customer.join();
            }

            try{
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Logger.log("Main thread Interrupted");
            }


            statisticalReporter.interrupt();


        }
        catch (InterruptedException e) {
            Logger.log("Thread interrupted");
        }
        Logger.log("System terminated.");


//        Thread vendor = new Thread(new Vendor(ticketPool,
//                config.getTicketReleaseRate()));
//        Thread customer = new Thread(new Customer(ticketPool));
//
//        vendor.start();
//        customer.start();
//
//        try {
//            vendor.join();
//            customer.join();
//        } catch (InterruptedException e) {
//            Logger.log("Main thread interrupted.");
//        }

    }
}