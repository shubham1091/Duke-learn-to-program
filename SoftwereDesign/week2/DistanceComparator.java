package SoftwereDesign.week2;

import java.util.Comparator;

import SoftwereDesign.week1.Location;
import SoftwereDesign.week1.QuakeEntry;

/**
 * 
 * The DistanceComparator class implements the Comparator interface to compare
 * two QuakeEntry objects based on their distance
 * 
 * from a given location. The class calculates the distance between each
 * QuakeEntry's location and the given location using
 * 
 * the distanceTo method of the Location class, and compares them based on the
 * result.
 * 
 * The class has one constructor that takes a Location object as a parameter to
 * specify the location from which the distance
 * 
 * should be calculated. The compare method takes two QuakeEntry objects and
 * returns the result of the comparison.
 */
public class DistanceComparator implements Comparator<QuakeEntry> {
    /**
     * 
     * The Location object from which the distance will be calculated
     */
    Location fromWhere;

    /**
     * 
     * Constructs a DistanceComparator object with the specified Location object
     * 
     * @param where the Location object from which the distance will be calculated
     */
    public DistanceComparator(Location where) {
        fromWhere = where;
    }

    /**
     * 
     * Compares two QuakeEntry objects based on their distance from the specified
     * location. The distance is calculated
     * using the distanceTo method of the Location class, and the comparison is done
     * based on the result of the calculation.
     * 
     * @param q1 the first QuakeEntry object to compare
     * @param q2 the second QuakeEntry object to compare
     * @return the result of the comparison
     */
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        double dist1 = q1.getLocation().distanceTo(fromWhere);
        double dist2 = q2.getLocation().distanceTo(fromWhere);
        return Double.compare(dist1, dist2);
    }
}
