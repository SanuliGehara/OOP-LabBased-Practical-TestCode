package logging;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class Logger {
    private static final String LOG_FILE = "resources/logs.txt";

    // Print and write to logs.txt file
    public static void log(String message) {
        String timeStampedMessage = LocalDateTime.now() + ": " + Thread.currentThread().getName() + " Thread - "
                + Thread.currentThread().getId() + " : " +message;
//        String timeStampedMessage = LocalDateTime.now() + ": " + message;
        System.out.println(timeStampedMessage);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            writer.write(timeStampedMessage);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Logs ticket operations with ticketId, operation type and date+time
    public static void logTicketOperation(String ticketId, String operationType) {
        String message = LocalDateTime.now() + " : TicketId : " + ticketId + "| operationType: " + operationType;
//                String.format("Ticket ID: %s | Operation Type: %s | Time: %s", ticketId, operationType, LocalDateTime.now());
        log(message); //logs to file
    }
}