# Stop System - When the System is running, enter "y" to stop the system

**Main.java**
```
import config.Configuration;
import core.TicketPool;
import logging.Logger;
import threads.Customer;
import threads.Vendor;
import ui.CommandLineInterface;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static volatile boolean running = true; // Flag to STOP SYSTEM
    private static final List<Thread> vendors = new ArrayList<>();
    private static final List<Thread> customers = new ArrayList<>();

    public static void main(String[] args) {
        Configuration config = CommandLineInterface.configureSystem();
        TicketPool ticketPool = new TicketPool();

        for (int i=0; i<2; i++) {
            Thread vendor = new Thread(new Vendor(ticketPool,
                    config.getTicketReleaseRate()),("Vendor -"+i));
            vendors.add(vendor);
            vendor.start();
        }

        for (int i=0; i<2; i++) {
            Thread customer = new Thread(new Customer(ticketPool), ("Customer -"+i));
            customers.add(customer);
            customer.start();
        }

        // _______ STOP the system - when entered to stop____________
        CommandLineInterface.manageSystem(()-> stopSystem(ticketPool));
        Logger.log("System terminated.");
    }

    // _______ Stop method - interrupt threads and update status ________
    public static void stopSystem(TicketPool ticketPool) {
        running = false;

        //Stop vendor anf customer threads
        vendors.forEach(Thread::interrupt);
        customers.forEach(Thread::interrupt);
        Logger.log(String.format("Stopping the system.. Current size is %s",ticketPool.getTicketCount()));
    }

    public static boolean isRunning() {return running;}
}
```
**CommandLineInterface.java**

Added ManageSystem() method to manage the system stop, when "y" is entered while system is running.
```

    //---------- STOP the system - Manage system STop --------------
    public static void manageSystem(Runnable stopCallback) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("System is running. Type 'Y' to gracefully shutdown.");

        while (true) {
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("Y")) {
                stopCallback.run();
                break;
            } else {
                System.out.println("Invalid command. Type 'Y' to shutdown.");
            }
        }
    }
```
