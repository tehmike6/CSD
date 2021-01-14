package model.Tile;

/**
 * This enum contains the Parts of adult a child skeletons.
 * @version 1.0
 * @author Mike Bastakis csd4406
 * */
public enum SkeletonParts {
    big_bottom(0),
    big_top(1),
    small_bottom(2),
    small_top(3);

    private final int value;

    SkeletonParts(int i) {
        this.value = i;
    }

    /**
     * <b>Accessor</b> Returns the enum SkeletonParts.
     * <b>Postcondition</b> Returns a string representation of the enum SkeletonParts.
     * @return A string representation of the enum SkeletonParts.
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
