package SoftwereDesign.week1;

public class DepthFilter implements Filter {
    private double minDepth;
    private double maxDepth;
    private String name;

    /**
     * Constructs a new depth filter with the given parameters.
     * 
     * @param minDepth the minimum depth for earthquakes to be accepted
     * @param maxDepth the maximum depth for earthquakes to be accepted
     * @param name     the name of the filter
     */
    public DepthFilter(double minDepth, double maxDepth, String name) {
        this.minDepth = minDepth;
        this.maxDepth = maxDepth;
        this.name = name;
    }

    /**
     * Returns whether the given earthquake data satisfies this filter's depth
     * range.
     * 
     * @param qe the earthquake data to test
     * @return true if the earthquake data's depth is within the acceptable range,
     *         false otherwise
     */
    @Override
    public boolean satisfies(QuakeEntry qe) {
        return qe.getDepth() >= minDepth && qe.getDepth() <= maxDepth;
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
