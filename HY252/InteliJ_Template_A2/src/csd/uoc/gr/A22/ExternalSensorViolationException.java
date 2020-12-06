package csd.uoc.gr.A22;

public class ExternalSensorViolationException extends Exception{
    public ExternalSensorViolationException() {
    }

    public ExternalSensorViolationException(String message) {
        super(message);
    }

    public ExternalSensorViolationException(String message, Throwable cause) {
        super(message, cause);
    }
}
