package SoftwereDesign.week1;

/**
 * The MagnitudeFilter class implements the Filter interface and provides a way
 * to filter
 * earthquake data based on the magnitude of the earthquakes.
 */

public class MagnitudeFilter implements Filter {
    private double minimum;
    private double maximum;
    private String name;

    /**
     * Constructs a new MagnitudeFilter with the specified minimum and maximum
     * magnitudes,
     * and a name for the filter.
     *
     * @param minimum the minimum magnitude to include in the filter
     * @param maximum the maximum magnitude to include in the filter
     * @param name    the name of the filter
     */
    public MagnitudeFilter(double minimum, double maximum, String name) {
        this.minimum = minimum;
        this.maximum = maximum;
        this.name = name;
    }

    /**
     * Determines whether an earthquake entry satisfies the filter's magnitude
     * criteria.
     *
     * @param qe the earthquake entry to test
     * @return true if the magnitude of the earthquake entry is within the specified
     *         range,
     *         false otherwise
     */
    @Override
    public boolean satisfies(QuakeEntry qe) {
        return qe.getMagnitude() >= minimum && qe.getMagnitude() <= maximum;
    }

    /**
     * Gets the name of the filter.
     *
     * @return the name of the filter
     */
    @Override
    public String getName() {
        return name;
    }

}
