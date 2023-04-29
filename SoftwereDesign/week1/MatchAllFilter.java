package SoftwereDesign.week1;

import java.util.ArrayList;

/**
 * The MatchAllFilter class implements the Filter interface and provides a way
 * to combine
 * multiple filters using a logical AND operation.
 */
public class MatchAllFilter implements Filter {
    private ArrayList<Filter> filterList;

    /**
     * Constructs a new MatchAllFilter with an empty list of filters.
     */
    public MatchAllFilter() {
        this.filterList = new ArrayList<Filter>();
    }

    /**
     * Adds a new filter to the list of filters to be combined.
     *
     * @param filter the filter to add
     */
    public void addFilter(Filter filter) {
        filterList.add(filter);
    }

    /**
     * Determines whether an earthquake entry satisfies all of the filter criteria.
     *
     * @param qe the earthquake entry to test
     * @return true if the earthquake entry satisfies all of the filter criteria,
     *         false otherwise
     */
    @Override
    public boolean satisfies(QuakeEntry qe) {
        for (Filter filter : filterList) {
            if (!filter.satisfies(qe)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Gets a string representation of the names of all the filters in the list.
     *
     * @return a string containing the names of all the filters in the list,
     *         separated by spaces
     */
    @Override
    public String getName() {
        StringBuilder sb = new StringBuilder();
        for (Filter f : filterList) {
            sb.append(f.getName()).append(" ");
        }
        return sb.toString().trim();
    }

}
