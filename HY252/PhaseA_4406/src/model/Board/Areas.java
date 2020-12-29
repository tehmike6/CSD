package model.Board;

import java.util.HashMap;
import java.util.Map;

/**
 * An Enum that holds the different areas that tiles can be places.
 * This enum can represent both integer values and enum.
 * Example: MOSAIC_AREA.getValue() == 0;
 * @version 1.0
 * @author Mike Bastakis csd4406
 * */
public enum Areas {
    MOSAIC_AREA(0),
    STATUE_AREA(1),
    AMPHORA_AREA(2),
    SKELETON_AREA(3),
    LANDSLIDE_AREA(4);

    int value;
    private static Map map = new HashMap<>();

    /**
     * <b>Constructor</b>
     * Sets the value for every enum. Then the static is called and for every Area based on
     * its value it is put in a map.
     * */
    Areas(int value) {
        this.value = value;
    }

    static {
        for (Areas areas : Areas.values()) {
            map.put(areas.value, areas);
        }
    }
    /**
     * <b>Accessor</b>
     * Search the map for the Area with the value @param areas and return it.
     * <b>Postcondition</b> Returns the area that has value @param areas.
     * @param areas value of an Area.
     * @return the Area that has value equals to @param areas.
     * */
    public static Areas valueOf(int areas) {
        return (Areas) map.get(areas);
    }
    /**
     * <b>Accessor</b>
     * Returns the value of an enum. The value is set from the constructor.
     * <b>Postcondition</b> Return the value of an Area.
     * @return the integer representation of an Area.
     * */
    public int getValue() {
        return value;
    }
}
