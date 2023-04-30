package SoftwereDesign.week2;

import java.util.Comparator;

import SoftwereDesign.week1.QuakeEntry;

/**
 * 
 * The TitleAndDepthComparator class implements the Comparator interface and
 * provides
 * a comparison method to compare QuakeEntry objects based on their title and
 * depth.
 */
public class TitleAndDepthComparator implements Comparator<QuakeEntry> {

    /**
     * This method compares two QuakeEntry objects based on their title and depth.
     * It first compares
     * the titles of each object using the String class's compareTo method. If the
     * titles are equal,
     * the method compares the depth of each object using the Double class's compare
     * method. The method
     * returns a negative integer, zero, or a positive integer depending on whether
     * the first QuakeEntry
     * should be sorted before, the same as, or after the second QuakeEntry.
     * 
     * @param q1 the first QuakeEntry to compare.
     * @param q2 the second QuakeEntry to compare.
     * @return a negative integer, zero, or a positive integer depending on whether
     *         the first QuakeEntry
     *         should be sorted before, the same as, or after the second QuakeEntry.
     */
    @Override
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        int result = q1.getInfo().compareTo(q2.getInfo());
        if (result != 0) {
            return result;
        } else {
            return Double.compare(q1.getDepth(), q2.getDepth());
        }
    }

}
