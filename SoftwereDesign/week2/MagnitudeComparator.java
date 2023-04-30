package SoftwereDesign.week2;

import java.util.Comparator;

import SoftwereDesign.week1.QuakeEntry;

/**
 * 
 * A Comparator implementation to compare QuakeEntry objects based on their
 * magnitudes.
 */
public class MagnitudeComparator implements Comparator<QuakeEntry> {
    /**
     * 
     * Compares two QuakeEntry objects based on their magnitudes.
     * 
     * @param qe1 the first QuakeEntry object to be compared
     * @param qe2 the second QuakeEntry object to be compared
     * @return a negative integer, zero, or a positive integer as the first argument
     *         is less than, equal to,
     *         or greater than the second, based on the comparison of their
     *         magnitudes
     */
    public int compare(QuakeEntry qe1, QuakeEntry qe2) {
        return Double.compare(qe1.getMagnitude(), qe2.getMagnitude());
    }

}