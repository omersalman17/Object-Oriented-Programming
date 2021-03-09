package filesprocessing.exceptions;

public class ErrorTypeOneException extends Exception {

    public ErrorTypeOneException(int lineNumber){
        super();
        System.err.print("Warning in line " + lineNumber + "\n");
    }
}
