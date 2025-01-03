package ui;

import config.Configuration;
import exceptions.InvalidConfigurationException;
import logging.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class CommandLineInterface {
    public static Configuration configureSystem() {
        Scanner scanner = new Scanner(System.in);

        Logger.log("Starting system configuration...");

        // ********** Q - 9 = Loading default config from config.json file *****************
        // Ask to use default config
        int choice = getInput(scanner, "Do you want to use default configuration? (1 - default /2- new config): ");
        Configuration config = null;

        if (choice == 1) {
            // use default from config.json
            try {
                Logger.log("Default Configuration loaded...");
                config = Configuration.loadFromFile("./resources/config.json");
                Logger.log("Default Config: "+ config);
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return config;
        }
            // ************************************************************

//            while (config == null) {
                int totalTickets = getInput(scanner, "Enter Total Tickets: ");
                int ticketReleaseRate = getInput(scanner, "Enter Ticket Release Rate: ");
                int customerRetrievalRate = getInput(scanner, "Enter Customer Retrieval Rate: ");
                int maxTicketCapacity = getInput(scanner, "Enter Max Ticket Capacity: ");

                Logger.log("System configured successfully.");

                // ********************** Q - 10 = SAVE CONFIG TO JSON FILE *****************************
                try {
                    try {
                        config = new Configuration(totalTickets, ticketReleaseRate, customerRetrievalRate,
                                maxTicketCapacity);
                    } catch (InvalidConfigurationException e) {
                        e.printStackTrace();
                    }

                    config.saveToFile("./resources/config.json");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
//            }

            //****************************************************************
//        return new Configuration(totalTickets, ticketReleaseRate, customerRetrievalRate,
//                maxTicketCapacity);

        return config;
    }


    private static int getInput(Scanner scanner, String prompt) {
        int value;

        while (true) {
            System.out.print(prompt);

            try {
                value = Integer.parseInt(scanner.nextLine());
//
//                if (value > 0) {
//                    return value;
//                } else {
//                    System.out.println("Value must be positive. Try again.");
//                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }
}