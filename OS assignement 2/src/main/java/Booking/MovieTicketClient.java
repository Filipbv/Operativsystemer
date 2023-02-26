package Booking;

import java.util.logging.Logger;

/**
 * This class is the movie ticket client
 */
public class MovieTicketClient extends Thread {
    private final String customerName;
    private final int numberOfTickets;
    private final MovieTicketServer movieTicketServer;
    private static final Logger logger = Logger.getLogger(MovieTicketClient.class.getName());

    /**
     * The movie ticket client.
     */
    public MovieTicketClient(MovieTicketServer movieTicketServer, String customerName, int numberOfTickets) throws IllegalArgumentException {
        try {
            stringChecker(customerName);
            numberChecker(numberOfTickets);
        }
        catch (IllegalArgumentException illegalArgumentException) {
            this.logger.warning("Caught IllegalArgumentException: " + illegalArgumentException.getMessage());
        }
        this.customerName = customerName;
        this.numberOfTickets = numberOfTickets;
        this.movieTicketServer = movieTicketServer;
    }

    /**
     * Runs the booking ot tickets.
     */
    @Override
    public void run() {
        this.movieTicketServer.bookTicket(this.customerName, this.numberOfTickets);
    }

    /**
     * Checks the String is valid.
     */
    private String stringChecker(String string) {
        if(string.isEmpty()) {
            throw new IllegalArgumentException("Invalid input");
        }
        return string;
    }

    /**
     * Checks that a given number is not below 1.
     */
    private int numberChecker(int n) {
        if(n < 1) {
            throw new IllegalArgumentException("Input cant be less then 1");
        }
        return n;
    }
}