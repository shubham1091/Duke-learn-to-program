package SoftwereDesign.week1;

/**
 * The Filter interface provides a way to filter earthquake data based on
 * certain criteria.
 * It contains two methods: `satisfies()` and `getName()`.
 */
public interface Filter {
    /**
     * Determines whether an earthquake entry satisfies the filter's criteria.
     *
     * @param qe the earthquake entry to test
     * @return true if the entry satisfies the filter's criteria, false otherwise
     */
    public boolean satisfies(QuakeEntry qe);

    /**
     * Gets the name of the filter.
     *
     * @return the name of the filter
     */
    public String getName();
}
