package Booking;

import java.util.logging.Logger;

/**
 * This class represent the movie thicket server.
 */
public class MovieTicketServer {
    private final String movieName;
    private int availableSeats;
    private static final Logger logger = Logger.getLogger(MovieTicketServer.class.getName());

    /**
     * The movie ticket server.
     */
    public MovieTicketServer(String movieName, int availableSeats) throws IllegalArgumentException{
        try {
            stringChecker(movieName);
            numberChecker(availableSeats);
        }
        catch (IllegalArgumentException illegalArgumentException) {
            this.logger.warning("IllegalArgumentException was caught: " + illegalArgumentException.getMessage());
        }
        this.movieName = movieName;
        this.availableSeats = availableSeats;
    }

    /**
     * Order tickets for the movie.
     */
    public synchronized void bookTicket(String customerName, int numberOfSeats) {
        System.out.println("Hi," + customerName + " : " + availableSeats + " : Seats available for " + movieName);
        if ((availableSeats - numberOfSeats) < 0) {
            System.out.println("Hi," + customerName + " : Seats not available for " + movieName);
        }
        else {
            System.out.println("Hi," + customerName + " : " + numberOfSeats + " Seats booked successfully for " + movieName);
            decrementAvailableSears(numberOfSeats);
        }
    }

    /**
     * Checks that a given number is not below 0.
     */
    private int numberChecker(int n) {
        if(n < 0) {
            throw new IllegalArgumentException("Input cant be less then 0");
        }
        return n;
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
     * Decrease the number aof available seats in the
     */
    private void decrementAvailableSears(int n) {
        try {
            numberChecker(n);
        }
        catch (IllegalArgumentException illegalArgumentException) {
            logger.warning(illegalArgumentException.getMessage());
        }
        this.availableSeats -= n;
    }
}