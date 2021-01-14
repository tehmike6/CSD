package model.Tile;

/**
 * A class that extends the FindingTile and represents an Skeleton Tile that contains it's color.
 * @version 1.0
 * @author Mike Bastakis csd4406
 * */
public class SkeletonTile extends FindingTile {
    SkeletonParts skeletonParts;
    /**
     * <b>Constructor</b>
     * <b>Postcondition</b> Sets the variable super.type to SkeletonTile and set's the skeleton Parts.
     * @param skeletonParts parts of the skeleton
     * */
    public SkeletonTile(SkeletonParts skeletonParts) {
        super(TileType.SKELETON);
        this.skeletonParts = skeletonParts;
    }

    /**
     * <b>Accessor</b> Get's the part of the Skeleton.
     * @return the part of the Skeleton.
     * */
    public SkeletonParts getSkeletonParts() {
        return skeletonParts;
    }

    /**
     * <b>Accessor</b> Returns the Parts of a Skeleton Tile.
     * <b>Postcondition</b> Returns a string representation of an Skeleton with it's part.
     * @return A string representation of an Skeleton with it's part.
     * */
    @Override
    public String toString() {
        return "skeleton_"+skeletonParts.toString();
    }
}
