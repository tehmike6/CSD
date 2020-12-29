package model.Tile;

/**
 * This enum contains the color of Masaics and Amphoras
 * @version 1.0
 * @author Mike Bastakis csd4406
 * */
public enum Color {
    BLUE,
    BROWN,
    RED,
    GREEN,
    YELLOW,
    PINK;

    /**
     * <b>Accessor</b> Returns the enum Color.
     * <b>Postcondition</b> Returns a string representation of the enum Color.
     * @return A string representation of the enum Color.
     * */
    public String toString(){ return this.name();}
}
