package core;

import logging.Logger;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class TicketPool implements TicketOperation {
    private final List<String> tickets = Collections.synchronizedList(new LinkedList<>());
    private final int maxCapacity; // NEW ATTRIBUTE TO CHECK POOL - In addTicket() method
    private int totalTicketsAdded;
    private int totalTicketsRemoved;

    public TicketPool (int maxCapacity) {
        super();
        this.maxCapacity = maxCapacity;
        this.totalTicketsAdded = 0;
        this.totalTicketsRemoved = 0;
    }



    @Override
    public synchronized void addTickets(String ticket) {
        // pool full - should wait
        while (tickets.size() >= maxCapacity) {     // DO NOT USE IF, USE WHILE
            try {
                wait();
            }
            catch (InterruptedException e) {
                Logger.log("Vendor interrupted");
            }
        }
        // pool not full - can add
        tickets.add(ticket);
        totalTicketsAdded++;
        notifyAll();
        Logger.logTicketOperation(ticket, "Add");
    }

    @Override
    public synchronized String removeTicket() {
        // No tickets available - should wait
        while (tickets.isEmpty()) {     // DO NOT USE IF, USE WHILE
            try {
                wait();
            }
            catch (InterruptedException e) {
                Logger.log("Customer interrupted");
            }
        }
        // Tickets available - can buy

        notifyAll();
        String ticket = tickets.remove(0);
        totalTicketsRemoved++;
        Logger.logTicketOperation(ticket, "Buy");
        return ticket;
    }

    // should be synchronized ****
    public synchronized int getTicketCount() {
        return tickets.size();
    }

    // get all tickets - SHOULD BE synchronized
    public synchronized List<String> getTickets() {
        return this.tickets;
    }

    //get totalTicketsAdded
    public synchronized int getTotalTicketsAdded() { return this.totalTicketsAdded;}

    // get totalRemoved
    public synchronized int getTotalRemoved() { return this.totalTicketsRemoved;}

    public int getMaxCapacity() {
        return this.maxCapacity;
    }

    @Override
    public String toString() {
        return "TicketPool{" +
                "tickets=" + tickets +
                '}';
    }
}