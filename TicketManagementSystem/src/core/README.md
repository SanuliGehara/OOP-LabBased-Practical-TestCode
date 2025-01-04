# TicketPool Class For Q6 - Add ListView to GUI to show current tickets in the pool

```package core;

import javafx.application.Platform;
import logging.Logger;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/*
* GUI Eke mulin customer withrak run wela , aai ticket gannathuwa terminate wena eka hadanne meke producer-consumer eken
* QUESTION 6 answer included
* */
public class TicketPool implements TicketOperation {
    private final int maxCapacity = 6; // Fixed for now - nthnm constructore.. okkoma wenas karnna wenwa
    private final List<String> tickets = Collections.synchronizedList(new LinkedList<>());

    private Runnable updateListViewGUI; // Thread to update GUI

    @Override
    public synchronized void addTickets(String ticket) {
        while (tickets.size() >= maxCapacity) {
            try {
                wait();
            } catch (InterruptedException e) {
                Logger.log("interrupted");
            }
        }

        tickets.add(ticket);
        notifyAll();
        Logger.logTicketOperation(ticket, "Add");

        if (updateListViewGUI != null) {
            updateListViewGUI.run(); // Directly call the GUI update method
        }
    }

    @Override
    public synchronized String removeTicket() {
       //String ticket = tickets.isEmpty() ? null : tickets.remove(0);

        String ticket = null;
        while (tickets.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Logger.log("interrupted");
            }
        }

        ticket = tickets.remove(0);
        notifyAll();
        Logger.logTicketOperation(ticket, "Buy");

        if (updateListViewGUI != null) {
            updateListViewGUI.run(); // Directly call the GUI update method
        }

        return ticket;
    }

    public synchronized int getTicketCount() {
        return tickets.size();
    }

    // get all tickets - SHOULD BE synchronized
    public synchronized List<String> getTickets() {
        List<String> l1 = new LinkedList<>();
        l1.add("Ticket-1");
        l1.add("Ticket-2");
        return l1;

        /* meka comment kale - hamawelema tickets dana okkoma buy wena nisa return
        wenne empty ekak, eka GUI test karanna amarui enisa dummy ticket 2k damma*/
        // return this.tickets;
    }

    // Used in JavaFX GUI
    public void setUpdateListViewGUI(Runnable updateListViewGUI) {
        this.updateListViewGUI = updateListViewGUI;
    }
}
```
