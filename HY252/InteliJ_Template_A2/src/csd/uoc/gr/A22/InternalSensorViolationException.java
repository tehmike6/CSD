package csd.uoc.gr.A22;

public class InternalSensorViolationException extends Exception{
    public InternalSensorViolationException() {
    }

    public InternalSensorViolationException(String message) {
        super(message);
    }

    public InternalSensorViolationException(String message, Throwable cause) {
        super(message, cause);
    }
}
