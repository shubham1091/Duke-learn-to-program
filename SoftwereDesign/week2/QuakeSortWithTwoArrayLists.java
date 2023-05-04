package SoftwereDesign.week2;

import java.util.ArrayList;

import SoftwereDesign.week1.EarthQuakeParser;
import SoftwereDesign.week1.QuakeEntry;

/**
 * 
 * The TitleAndDepthComparator class implements the Comparator interface and
 * provides
 * a comparison method to compare QuakeEntry objects based on their title and
 * depth.
 */
public class QuakeSortWithTwoArrayLists {
    // This is the code from the Video of Selection Sort with Two ArrayLists

    /**
     * This method returns the QuakeEntry object with the smallest magnitude in an
     * ArrayList of QuakeEntry objects.
     * 
     * @param quakes the ArrayList of QuakeEntry objects to search for the smallest
     *               magnitude.
     * @return the QuakeEntry object with the smallest magnitude in the ArrayList.
     */
    public QuakeEntry getSmallestMagnitude(ArrayList<QuakeEntry> quakes) {
        QuakeEntry min = quakes.get(0);
        for (QuakeEntry q : quakes) {
            if (q.getMagnitude() < min.getMagnitude()) {
                min = q;
            }
        }

        return min;
    }

    /**
     * This method sorts an ArrayList of QuakeEntry objects based on their magnitude
     * using a selection sort algorithm.
     * It creates a new ArrayList to store the sorted objects and repeatedly calls
     * the getSmallestMagnitude method to
     * find the QuakeEntry with the smallest magnitude in the input ArrayList and
     * add it to the output ArrayList until
     * the input ArrayList is empty.
     * 
     * @param in the ArrayList of QuakeEntry objects to sort.
     * @return the sorted ArrayList of QuakeEntry objects.
     */
    public ArrayList<QuakeEntry> sortByMagnitude(ArrayList<QuakeEntry> in) {
        ArrayList<QuakeEntry> out = new ArrayList<QuakeEntry>();
        while (!in.isEmpty()) {
            QuakeEntry minElement = getSmallestMagnitude(in);
            in.remove(minElement);
            out.add(minElement);
        }

        return out;
    }

    /**
     * This method tests the sorting method by reading earthquake data from a file,
     * sorting it by magnitude, and
     * printing the sorted list of QuakeEntry objects.
     */
    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "SoftwereDesign/data/Earthquake/nov20quakedatasmall.atom";
        // String source = "data/Earthquake/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);

        System.out.println("read data for " + list.size() + " quakes");
        list = sortByMagnitude(list);

        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }

    }

}
