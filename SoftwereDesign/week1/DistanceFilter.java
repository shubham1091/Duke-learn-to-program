package SoftwereDesign.week1;

/**
 * A filter for earthquakes that are within a certain distance of a given
 * location.
 */
public class DistanceFilter implements Filter {
    private Location location;
    private double distance;
    private String name;

    /**
     * Constructs a new DistanceFilter with the given location, distance, and name.
     * 
     * @param location the location to search for earthquakes around
     * @param distance the maximum distance from the location to include in the
     *                 filtered results
     * @param name     the name of this filter
     */

    public DistanceFilter(Location location, double distance, String name) {
        this.location = location;
        this.distance = distance;
        this.name = name;
    }

    /**
     * Returns true if the given QuakeEntry satisfies this filter (i.e. is within
     * the maximum distance
     * from the location), and false otherwise.
     * 
     * @param qe the QuakeEntry to test against this filter
     * @return true if the QuakeEntry satisfies the filter, false otherwise
     */

    @Override
    public boolean satisfies(QuakeEntry qe) {
        return qe.getLocation().distanceTo(location) < distance;
    }

    /**
     * Returns the name of this filter.
     * 
     * @return the name of this filter
     */
    @Override
    public String getName() {
        return name;
    }

}
