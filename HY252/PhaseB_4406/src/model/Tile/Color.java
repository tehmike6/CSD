package model.Tile;

/**
 * This enum contains the color of Masaics and Amphoras
 * @version 1.0
 * @author Mike Bastakis csd4406
 * */
public enum Color {
    blue(0),
    brown(1),
    red(2),
    green(3),
    yellow(4),
    purple(5);

    private final int value;

    Color(int i) {
        this.value = i;
    }

    /**
     * <b>Accessor</b> Returns the enum Color.
     * <b>Postcondition</b> Returns a string representation of the enum Color.
     * @return A string representation of the enum Color.
     * */
    public String toString(){ return this.name();}

    /**
     * <b>Accessor</b> Returns the value of enum.
     * @return the private variable value.
     * */
    public int getValue() {
        return value;
    }
}
