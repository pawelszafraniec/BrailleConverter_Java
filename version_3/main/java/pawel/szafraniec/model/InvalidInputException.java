package pawel.szafraniec.model;

/**
 * Exception class for invalid user input
 *
 * @author Pawel Szafraniec
 * @version 3.0
 */
public class InvalidInputException extends Exception {

    /**
     * Exception for empty input text
     * @param s String message
     */
    public InvalidInputException(String s) {
        super(s);
    }
}
