package Questions;

import core.TicketPool;

public class Q1 {
    public static void main (String[] args){
        TicketPool ticketPool = new TicketPool(3);

        // add tickets
        ticketPool.addTickets("Ticket-1");
        ticketPool.addTickets("Ticket-2");

        //Buy tickets
        ticketPool.removeTicket();
        ticketPool.removeTicket();

        System.out.println("Finished ticket operations!");
    }
}
