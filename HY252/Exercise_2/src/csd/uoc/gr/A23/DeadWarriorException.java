package csd.uoc.gr.A23;

public class DeadWarriorException extends Exception{
    public DeadWarriorException() {
    }

    public DeadWarriorException(String message) {
        super(message);
    }

    public DeadWarriorException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeadWarriorException(Throwable cause) {
        super(cause);
    }
}
