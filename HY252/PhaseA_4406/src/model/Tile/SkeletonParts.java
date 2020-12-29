package model.Tile;

/**
 * This enum contains the Parts of adult a child skeletons.
 * @version 1.0
 * @author Mike Bastakis csd4406
 * */
public enum SkeletonParts {
    UPPER_SMALL,
    LEGS_SMALL,
    UPPER_LARGE,
    LEGS_LARGE;

    /**
     * <b>Accessor</b> Returns the enum SkeletonParts.
     * <b>Postcondition</b> Returns a string representation of the enum SkeletonParts.
     * @return A string representation of the enum SkeletonParts.
     * */
    public String toString(){ return this.name();}
}
