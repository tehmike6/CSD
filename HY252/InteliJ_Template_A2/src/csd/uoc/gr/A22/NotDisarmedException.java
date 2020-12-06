package csd.uoc.gr.A22;

public class NotDisarmedException extends Exception{
    public NotDisarmedException() {
    }

    public NotDisarmedException(String message) {
        super(message);
    }

    public NotDisarmedException(String message, Throwable cause) {
        super(message, cause);
    }
}
