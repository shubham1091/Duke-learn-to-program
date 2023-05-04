package SoftwereDesign.week2;

import java.util.ArrayList;
import java.util.Collections;

import SoftwereDesign.week1.EarthQuakeParser;
import SoftwereDesign.week1.Location;
import SoftwereDesign.week1.QuakeEntry;

/**
 * 
 * This class demonstrates different ways to sort earthquake data using
 * comparators. It contains five methods:
 * 
 * sortWithCompareTo(), sortByMagnitude(), sortByDistance(),
 * sortByTitleAndDepth(), and sortByLastWordInTitleThenByMagnitude().
 * 
 * Each method reads earthquake data from a file or URL, creates an ArrayList of
 * QuakeEntry objects, sorts the list using
 * 
 * a specific comparator, and prints out the sorted list.
 * 
 * The MagnitudeComparator class and DistanceComparator class are used to sort
 * the list by magnitude and distance, respectively.
 * 
 * The TitleAndDepthComparator class and TitleLastAndMagnitudeComparator class
 * are used to sort the list by title and depth, and
 * 
 * by last word in title and magnitude, respectively.
 */
public class DifferentSorters {
    public static void main(String[] args) {
        DifferentSorters ds = new DifferentSorters();
        // ds.sortWithCompareTo();
        // ds.sortByTitleAndDepth();
        ds.sortByLastWordInTitleThenByMagnitude();
    }

    /**
     * 
     * This method reads earthquake data from a file or URL, creates an ArrayList of
     * QuakeEntry objects, sorts the list using
     * the compareTo() method of QuakeEntry class, and prints out the QuakeEntry
     * object at a specific position in the sorted list.
     */
    public void sortWithCompareTo() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "SoftwereDesign/data/Earthquake/earthQuakeDataWeekDec6sample2.atom";

        ArrayList<QuakeEntry> list = parser.read(source);
        Collections.sort(list);
        int quakeNumber = 600;
        System.out.println("Print quake entry in position " + quakeNumber);
        System.out.println(list.get(quakeNumber));

    }

    /**
     * 
     * This method reads earthquake data from a file or URL, creates an ArrayList of
     * QuakeEntry objects, sorts the list using
     * the MagnitudeComparator class, and prints out the sorted list.
     */
    public void sortByMagnitude() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "SoftwereDesign/data/Earthquake/nov20quakedata.atom";

        ArrayList<QuakeEntry> list = parser.read(source);
        Collections.sort(list, new MagnitudeComparator());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }

    }

    /**
     * 
     * This method reads earthquake data from a file or URL, creates an ArrayList of
     * QuakeEntry objects, sorts the list using
     * the DistanceComparator class, and prints out the sorted list.
     */
    public void sortByDistance() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "SoftwereDesign/data/Earthquake/nov20quakedata.atom";
        
        ArrayList<QuakeEntry> list = parser.read(source);
        // Location is Durham, NC
        Location where = new Location(35.9886, -78.9072);
        Collections.sort(list, new DistanceComparator(where));
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }

    }

    /**
     * 
     * This method reads earthquake data from a file or URL, creates an ArrayList of
     * QuakeEntry objects, sorts the list using
     * the TitleAndDepthComparator class, and prints out the QuakeEntry object at a
     * specific position in the sorted list.
     */
    public void sortByTitleAndDepth() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "SoftwereDesign/data/Earthquake/earthQuakeDataWeekDec6sample1.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        Collections.sort(list, new TitleAndDepthComparator());
        int quakeNumber = 500;
        System.out.println("Print quake entry in position " + quakeNumber);
        System.out.println(list.get(quakeNumber));
    }

    /**
     * 
     * This method reads earthquake data from a file or URL, creates an ArrayList of
     * QuakeEntry objects, sorts the list using
     * the TitleLastAndMagnitudeComparator class, and prints out the QuakeEntry
     * object at a specific position in the sorted list.
     */
    public void sortByLastWordInTitleThenByMagnitude() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "SoftwereDesign/data/Earthquake/earthQuakeDataWeekDec6sample1.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        Collections.sort(list, new TitleLastAndMagnitudeComparator());
        int quakeNumber = 500;
        System.out.println("Print quake entry in position " + quakeNumber);
        System.out.println(list.get(quakeNumber));
    }
}
