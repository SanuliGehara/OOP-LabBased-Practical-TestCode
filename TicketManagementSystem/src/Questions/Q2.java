package Questions;

import core.IDRetrieval;
import core.PriorityRetrieval;
import core.TicketPool;
import threads.Customer;

public class Q2 {
    public static void main(String[] args) {
        TicketPool ticketPool = new TicketPool(15);

        ticketPool.addTickets("Ticket - 1");
        ticketPool.addTickets("Ticket - 2");
        ticketPool.addTickets("Ticket - 3");
        ticketPool.addTickets("Ticket - 4");

        // get all tickets
        System.out.println(ticketPool.getTickets());

        Thread customer1 = new Thread(new Customer(ticketPool, new PriorityRetrieval()), "customer-1");
        Thread customer2 = new Thread(new Customer(ticketPool, new IDRetrieval("Ticket - 2")),"customer-2");

        customer1.start();
        customer2.start();

        // Wait for threads to complete
        try {
            customer1.join();
            customer2.join();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Completed!");
    }
}
