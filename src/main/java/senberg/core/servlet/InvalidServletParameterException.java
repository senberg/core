package senberg.core.servlet;


/**
 * This exception should be thrown when a servlet is passed invalid parameters.
 */
public class InvalidServletParameterException extends IllegalArgumentException {

    /**
     * Constructs an InvalidServletParameterException with no detail message.
     */
    public InvalidServletParameterException() {
        super();
    }

    /**
     * Constructs an InvalidParameterException with the specified detail message.
     * A detail message is a String that describes this particular exception.
     *
     * @param msg the detail message.
     */
    public InvalidServletParameterException(String msg) {
        super(msg);
    }
}
