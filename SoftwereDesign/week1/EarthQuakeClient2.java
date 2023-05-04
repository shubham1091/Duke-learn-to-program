package SoftwereDesign.week1;

import java.util.ArrayList;

public class EarthQuakeClient2 {
    public static void main(String[] args) {
        EarthQuakeClient2 eq = new EarthQuakeClient2();
        eq.testMatchAllFilter();
    }

    /**
     * Prints out the earthquakes that match the MagnitudeFilter and DepthFilter.
     */
    public void quakesWithFilter() {
        EarthQuakeParser parser = new EarthQuakeParser();
        // String source =
        // "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        // String source = "SoftwereDesign/data/Earthquake/nov20quakedatasmall.atom";
        String source = "SoftwereDesign/data/Earthquake/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");

        Filter f1 = new MagnitudeFilter(3.5, 4.5, "Magnitude");
        ArrayList<QuakeEntry> m7 = filter(list, f1);
        System.out.println("Found " + m7.size() +
                " quakes that match the MagnitudeFilter");

        Filter f2 = new DepthFilter(-55_000.0, -20_000.0, "Depth");
        ArrayList<QuakeEntry> deepQuakes = filter(m7, f2);
        System.out.println("Found " + deepQuakes.size() +
                " quakes that match both filters");

    }

    /**
     * Prints out the earthquakes that meet the DistanceFilter and PhraseFilter
     * criteria.
     */
    public void quakesWithFilter2() {
        // String source = "SoftwereDesign/data/Earthquake/nov20quakedatasmall.atom";
        String source = "SoftwereDesign/data/Earthquake/nov20quakedata.atom";
        Location city = new Location(39.7392, -104.9903);
        String where = "end", phrase = "a";

        EarthQuakeParser parser = new EarthQuakeParser();
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");

        DistanceFilter distanceFilter = new DistanceFilter(city, 1_000_000.0, "Distance");
        PhraseFilter phraseFilter = new PhraseFilter(where, phrase, "phrase");

        // Apply both filters
        ArrayList<QuakeEntry> filteredQuakes = new ArrayList<>();
        for (QuakeEntry qe : list) {
            if (distanceFilter.satisfies(qe) && phraseFilter.satisfies(qe)) {
                filteredQuakes.add(qe);
            }
        }
        System.out.println("Found " + filteredQuakes.size() + " quakes that meet the criteria.");
    }

    /**
     * Filters earthquakes that match the MagnitudeFilter, DepthFilter, and
     * PhraseFilter.
     * Prints out the filtered earthquakes and the filters used to filter them.
     */
    public void testMatchAllFilter() {
        // String source = "SoftwereDesign/data/Earthquake/nov20quakedatasmall.atom";
        String source = "SoftwereDesign/data/Earthquake/nov20quakedata.atom";
        String where = "any", phrase = "a";
        double minmg = 1.0, maxmg = 4.0;
        double mindp = -180_000.0, maxdp = -30_000.0;

        EarthQuakeParser parser = new EarthQuakeParser();
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("Read data for " + list.size() + " quakes");

        MatchAllFilter maf = new MatchAllFilter();
        // Add three filters to the MatchAllFilter
        maf.addFilter(new MagnitudeFilter(minmg, maxmg, "Magnitude"));
        maf.addFilter(new DepthFilter(mindp, maxdp, "Depth"));
        maf.addFilter(new PhraseFilter(where, phrase, "Phrase"));

        // Filter the list with the MatchAllFilter and print out the resulting
        // earthquakes
        ArrayList<QuakeEntry> filteredList = filter(list, maf);
        System.out.println("Found " + filteredList.size() + " earthquakes that match the criteria");
        System.out.println("Filters used are: " + maf.getName());
    }

    /**
     * Filters earthquakes that match the MagnitudeFilter, DistanceFilter, and
     * PhraseFilter.
     * Prints out the filtered earthquakes and the filters used to filter them.
     */
    public void testMatchAllFilter2() {
        // String source = "SoftwereDesign/data/Earthquake/nov20quakedatasmall.atom";
        String source = "SoftwereDesign/data/Earthquake/nov20quakedata.atom";
        Location city = new Location(55.7308, 9.1153);
        String phrase = "e", where = "any";
        double minMag = 0.0, maxMag = 5.0;
        double maxDist = 3_000_000.0;

        EarthQuakeParser parser = new EarthQuakeParser();
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("Read data for " + list.size() + " quakes");

        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MagnitudeFilter(minMag, maxMag, "Magitude"));
        maf.addFilter(new DistanceFilter(city, maxDist, "Distance"));
        maf.addFilter(new PhraseFilter(where, phrase, "phrase"));

        // Filter the list with the MatchAllFilter and print out the resulting
        ArrayList<QuakeEntry> filteredList = filter(list, maf);

        System.out.println("Found " + filteredList.size() + " earthquakes that match the criteria");
        System.out.println("Filters used are: " + maf.getName());
    }

    /**
     * Filters the data in the current instance of the `DataSet` object based on the
     * specified filter criteria.
     * 
     * @param criteria a `FilterCriteria` object representing the filter criteria
     * @return a new `DataSet` object containing only the rows that match the filter
     *         criteria
     */
    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe : quakeData) {
            if (f.satisfies(qe)) {
                answer.add(qe);
            }
        }
        return answer;
    }

    /**
     * Creates a CSV file containing the earthquake data.
     */
    public void createCSV() {
        // String source = "SoftwereDesign/data/Earthquake/nov20quakedata.atom";
        String source = "SoftwereDesign/data/Earthquake/nov20quakedatasmall.atom";
        // String source =
        // "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";

        EarthQuakeParser parser = new EarthQuakeParser();
        ArrayList<QuakeEntry> list = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }

    /**
     * Dumps the data in the current instance of the `DataSet` object to a CSV file.
     * 
     * @param filename the name of the CSV file to create
     * @throws IOException if an I/O error occurs while writing the CSV file
     */
    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for (QuakeEntry qe : list) {
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                    qe.getLocation().getLatitude(),
                    qe.getLocation().getLongitude(),
                    qe.getMagnitude(),
                    qe.getInfo());
        }
    }

}
