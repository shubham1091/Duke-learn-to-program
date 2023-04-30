package SoftwereDesign.week2;

import java.util.Comparator;

import SoftwereDesign.week1.QuakeEntry;

/**
 * 
 * The TitleLastAndMagnitudeComparator class implements the Comparator interface
 * and provides
 * a comparison method to compare QuakeEntry objects based on the last word of
 * their title and
 * their magnitude.
 */
public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {

    /**
     * This private helper method returns the last word of a given string. It splits
     * the string
     * by whitespace and returns the last element in the resulting array.
     * 
     * @param title the string from which to extract the last word.
     * @return the last word of the given string.
     */

    private String getLastWord(String title) {
        String[] words = title.split("\\s+");
        return words[words.length - 1];
    }

    /**
     * This method compares two QuakeEntry objects based on the last word of their
     * title and their magnitude.
     * It first extracts the last word of each object's title using the getLastWord
     * helper method, and then
     * compares these last words using the String class's compareTo method. If the
     * last words are equal, the
     * method compares the magnitude of each object using the Double class's compare
     * method. The method returns
     * a negative integer, zero, or a positive integer depending on whether the
     * first QuakeEntry should be sorted
     * before, the same as, or after the second QuakeEntry.
     * 
     * @param q1 the first QuakeEntry to compare.
     * @param q2 the second QuakeEntry to compare.
     * @return a negative integer, zero, or a positive integer depending on whether
     *         the first QuakeEntry should be sorted
     *         before, the same as, or after the second QuakeEntry.
     */
    @Override
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        String lastWord1 = getLastWord(q1.getInfo());
        String lastWord2 = getLastWord(q2.getInfo());
        int result = lastWord1.compareTo(lastWord2);
        if (result != 0) {
            return result;
        } else {
            return Double.compare(q1.getMagnitude(), q2.getMagnitude());
        }
    }
}
