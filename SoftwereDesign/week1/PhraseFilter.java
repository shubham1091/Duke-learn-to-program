package SoftwereDesign.week1;

/**
 * The PhraseFilter class implements the Filter interface and provides a way to
 * filter
 * earthquake data based on the presence of a specific phrase in the
 * earthquake's information string.
 */

public class PhraseFilter implements Filter {
    private String location;
    private String phrase;
    private String name;

    /**
     * Constructs a new PhraseFilter with the specified location, phrase, and name.
     *
     * @param location the location of the phrase within the earthquake's
     *                 information string
     *                 (either "start", "end", or "any")
     * @param phrase   the phrase to search for within the earthquake's information
     *                 string
     * @param name     the name of the filter
     */
    public PhraseFilter(String location, String phrase, String name) {
        this.location = location;
        this.phrase = phrase;
        this.name = name;
    }

    /**
     * Determines whether an earthquake entry satisfies the filter's phrase
     * criteria.
     *
     * @param qe the earthquake entry to test
     * @return true if the earthquake's information string contains the filter's
     *         phrase in
     *         the specified location, false otherwise
     */
    @Override
    public boolean satisfies(QuakeEntry qe) {
        String title = qe.getInfo();
        if (location.equals("start")) {
            return title.startsWith(phrase);
        } else if (location.equals("end")) {
            return title.endsWith(phrase);
        } else {
            return title.contains(phrase);
        }
    }

    /**
     * Gets the name of the filter.
     *
     * @return the name of the filter
     */
    @Override
    public String getName() {
        return name;
    }

}
