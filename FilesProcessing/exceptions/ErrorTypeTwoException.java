package filesprocessing.exceptions;

public class ErrorTypeTwoException extends Exception {

    public ErrorTypeTwoException(String message){
        super();
        System.err.print("ERROR: " + message + "\n");
    }
}
