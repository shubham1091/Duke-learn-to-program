package SoftwereDesign.week2;

import java.util.ArrayList;

import SoftwereDesign.week1.EarthQuakeParser;
import SoftwereDesign.week1.QuakeEntry;

/**
 * 
 * The QuakeSortInPlace class provides methods for sorting an ArrayList of
 * QuakeEntry objects in place.
 * The class includes several sorting algorithms, including bubble sort and
 * selection sort, which can be used to sort the ArrayList by different criteria
 * such as magnitude or depth.
 */
public class QuakeSortInPlace {
    public static void main(String[] args) {
        QuakeSortInPlace qp = new QuakeSortInPlace();
        qp.testSort();
    }

    /**
     * The testSort method reads earthquake data from a file, sorts the data using
     * one of the sorting algorithms in the QuakeSortInPlace class, and prints the
     * sorted data.
     */
    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser();
        // String source = "SoftwereDesign/data/Earthquake/earthquakeDataSampleSix1.atom";
        String source = "SoftwereDesign/data/Earthquake/earthQuakeDataDec6sample2.atom";
        ArrayList<QuakeEntry> list = parser.read(source);

        System.out.println("read data for " + list.size() + " quakes");

        // sortByMagnitude(list);
        sortByLargestDepth(list);
        // sortByMagnitudeWithBubbleSort(list);
        // sortByMagnitudeWithBubbleSortWithCheck(list);
        // sortByMagnitudeWithCheck(list);

        print(list);

    }

    /**
     * The sortByMagnitudeWithCheck method sorts the earthquake data by magnitude
     * using selection sort and performs a check to verify that the data is sorted.
     * 
     * @param in an ArrayList of QuakeEntry objects to be sorted
     */
    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in) {
        int i = 0;
        while (!checkInSortedOrder(in)) {
            int minIdx = getSmallestMagnitude(in, i);
            swap(in, i, minIdx);
            i++;
        }
        System.out.println("passes needed to sort " + i);
    }

    /**
     * The sortByMagnitudeWithBubbleSortWithCheck method sorts the earthquake data
     * by magnitude using bubble sort and performs a check to verify that the data
     * is sorted.
     * 
     * @param in an ArrayList of QuakeEntry objects to be sorted
     */
    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in) {
        int i = 0;
        while (!checkInSortedOrder(in)) {
            onePassubbleSort(in, i);
            i++;
        }
        System.out.println("passes needed to sort " + i);
    }

    /**
     * The checkInSortedOrder method checks if an ArrayList of QuakeEntry objects is
     * sorted by magnitude.
     * 
     * @param quakes an ArrayList of QuakeEntry objects to be checked
     * @return true if the ArrayList is sorted by magnitude, false otherwise
     */
    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes) {
        for (int i = 0, j = 1; i < quakes.size() - 1; i++, j++) {
            if (quakes.get(i).getMagnitude() > quakes.get(j).getMagnitude()) {
                return false;
            }
        }
        return true;
    }

    /**
     * The sortByMagnitudeWithBubbleSort method sorts the earthquake data by
     * magnitude using bubble sort.
     * 
     * @param in an ArrayList of QuakeEntry objects to be sorted
     */
    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in) {
        // print(in);
        for (int i = 0; i < in.size() - 1; i++) {
            onePassubbleSort(in, i);
            // print(in);
        }
    }

    /**
     * Performs one pass of a bubble sort algorithm on the input ArrayList of
     * QuakeEntry objects, starting at the specified index and sorting the unsorted
     * portion of the ArrayList by magnitude.
     * 
     * @param quakeData the ArrayList of QuakeEntry objects to be sorted
     * @param numSorted the number of elements at the beginning of the ArrayList
     *                  that are already sorted
     */
    public void onePassubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted) {
        for (int i = 0, j = 1; i < quakeData.size() - numSorted - 1; i++, j++) {
            if (quakeData.get(i).getMagnitude() > quakeData.get(j).getMagnitude()) {
                swap(quakeData, i, j);
            }
        }
    }

    /**
     * Sorts the input ArrayList of QuakeEntry objects by their depth, from
     * largest to smallest.
     * 
     * @param in the ArrayList of QuakeEntry objects to be sorted
     */
    public void sortByLargestDepth(ArrayList<QuakeEntry> in) {
        // for (int i = 0; i < in.size(); i++) {
        for (int i = 0; i < 50; i++) {
            int maxIndex = getLargestDepth(in, i);
            QuakeEntry maxQuake = in.get(maxIndex);
            in.set(maxIndex, in.get(i));
            in.set(i, maxQuake);
        }
    }

    /**
     * Returns the index of the QuakeEntry with the largest depth from the given
     * ArrayList starting from the given index.
     *
     * @param quakeData the ArrayList of QuakeEntry objects to be sorted.
     * @param from      the index to start from.
     * @return the index of the QuakeEntry with the largest depth.
     */
    public int getLargestDepth(ArrayList<QuakeEntry> quakeData, int from) {
        int maxIndex = from;
        for (int i = from + 1; i < quakeData.size(); i++) {
            QuakeEntry curr = quakeData.get(i);
            if (curr.getDepth() > quakeData.get(maxIndex).getDepth()) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    /**
     * Returns the index of the QuakeEntry with the smallest magnitude from the
     * given ArrayList starting from the given index.
     *
     * @param quakes the ArrayList of QuakeEntry objects to be sorted.
     * @param from   the index to start from.
     * @return the index of the QuakeEntry with the smallest magnitude.
     */
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i = from + 1; i < quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }

    /**
     * Sorts the given ArrayList of QuakeEntry objects by magnitude in ascending
     * order using selection sort algorithm.
     *
     * @param in the ArrayList of QuakeEntry objects to be sorted.
     */
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {

        for (int i = 0; i < in.size(); i++) {
            int minIdx = getSmallestMagnitude(in, i);
            swap(in, i, minIdx);
        }

    }

    /**
     * Prints the given ArrayList of QuakeEntry objects.
     *
     * @param list the ArrayList of QuakeEntry objects to be printed.
     */
    public void print(ArrayList<QuakeEntry> list) {
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }

    /**
     * Swaps the QuakeEntry objects at the given indices in the given ArrayList.
     *
     * @param quakeData the ArrayList of QuakeEntry objects in which the swap will
     *                  be performed.
     * @param i         the index of the first QuakeEntry object to be swapped.
     * @param j         the index of the second QuakeEntry object to be swapped.
     */
    public void swap(ArrayList<QuakeEntry> quakeData, int i, int j) {
        QuakeEntry tmp = quakeData.get(i);
        quakeData.set(i, quakeData.get(j));
        quakeData.set(j, tmp);
    }
}
