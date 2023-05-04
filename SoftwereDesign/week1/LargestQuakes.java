package SoftwereDesign.week1;

import java.util.ArrayList;

/**
 * The LargestQuakes class provides methods for finding the largest earthquakes
 * from a given list of earthquakes.
 */
public class LargestQuakes {
    public static void main(String[] args) {
        LargestQuakes lq = new LargestQuakes();
        lq.findLargestQuakes();
    }

    /**
     * Reads earthquake data from a file, finds the 50 largest earthquakes
     * and prints them to the console.
     */
    public void findLargestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        // String source = "SoftwereDesign/data/EarthQuake/nov20quakedatasmall.atom";
        String source = "SoftwereDesign/data/EarthQuake/nov20quakedata.atom";
        int howMany = 50;

        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for  " + list.size() + " quakes");
        ArrayList<QuakeEntry> lrg = getLargest(list, howMany);
        for (QuakeEntry entry : lrg) {
            System.out.println(entry);
        }

    }

    /**
     * Returns the index of the earthquake with the largest magnitude in the given
     * list of earthquakes.
     * 
     * @param data The list of earthquakes to search.
     * @return The index of the earthquake with the largest magnitude.
     */
    public int indexOfLargest(ArrayList<QuakeEntry> data) {
        int max = 0;
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getMagnitude() > data.get(max).getMagnitude()) {
                max = i;
            }
        }
        return max;
    }

    /**
     * Returns a list of the n largest earthquakes from the given list of
     * earthquakes.
     * 
     * @param quakeData The list of earthquakes to search.
     * @param howMany   The number of largest earthquakes to return.
     * @return A list of the n largest earthquakes.
     */
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany) {
        ArrayList<QuakeEntry> entries = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> cpy = new ArrayList<QuakeEntry>(quakeData);
        for (int i = 0; i < howMany; i++) {
            int pos = indexOfLargest(cpy);
            entries.add(cpy.remove(pos));
        }
        return entries;
    }

}
