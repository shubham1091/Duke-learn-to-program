package SoftwereDesign.week1;

/**
 * The MinMagFilter class implements the Filter interface and provides a way to
 * filter
 * earthquake data based on the minimum magnitude of the earthquakes.
 */
public class MinMagFilter implements Filter {
    private double magMin;
    private String name;

    /**
     * Constructs a new MinMagFilter with the specified minimum magnitude and name.
     *
     * @param min  the minimum magnitude to include in the filter
     * @param name the name of the filter
     */
    public MinMagFilter(double min, String name) {
        magMin = min;
        this.name = name;
    }

    /**
     * Determines whether an earthquake entry satisfies the filter's minimum
     * magnitude criteria.
     *
     * @param qe the earthquake entry to test
     * @return true if the magnitude of the earthquake entry is greater than or
     *         equal to
     *         the minimum magnitude specified in the filter, false otherwise
     */
    @Override
    public boolean satisfies(QuakeEntry qe) {
        return qe.getMagnitude() >= magMin;
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
