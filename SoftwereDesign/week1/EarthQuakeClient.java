package SoftwereDesign.week1;

import java.util.*;

public class EarthQuakeClient {
    public static void main(String[] args) {
        EarthQuakeClient client = new EarthQuakeClient();
        client.quakesByPhrase();
    }

    /**
     * Filters quake entries by magnitude and outputs those with a magnitude greater
     * than 5.0.
     */
    public void bigQuakes() {
        // String source =
        // "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "SoftwereDesign/data/EarthQuake/nov20quakedatasmall.atom";
        double mag = 5.0;

        EarthQuakeParser parser = new EarthQuakeParser();
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");

        ArrayList<QuakeEntry> listBig = filterByMagnitude(list, mag);
        // dumpCSV(listBig);
        System.out.println("Found " + listBig.size() + " quakes that mathc that criteria");
    }

    /**
     * Filters quake entries by depth and outputs the number of entries that match
     * the specified range.
     */
    public void quakesOfDepth() {
        // String source = "SoftwereDesign/data/EarthQuake/nov20quakedatasmall.atom";
        String source = "SoftwereDesign/data/EarthQuake/nov20quakedata.atom";
        double minDepth = -4000.0;
        double maxDepth = -2000.0;

        EarthQuakeParser parser = new EarthQuakeParser();
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for  " + list.size() + " quakes");
        System.out.println("Find quakes with depth between " + minDepth + " and " + maxDepth);
        ArrayList<QuakeEntry> bw = filterByDepth(list, minDepth, maxDepth);
        // dumpCSV(bw);
        System.out.println("Found " + bw.size() + " quakes that match that criteria");
    }

    /**
     * 
     * Filters earthquake data based on a given phrase and location.
     * The method reads earthquake data from a file and filters the data based on a
     * given phrase and location.
     * The method then prints out the number of earthquakes that match the phrase
     * and location.
     */
    public void quakesByPhrase() {
        // String source = "SoftwereDesign/data/EarthQuake/nov20quakedatasmall.atom";
        String source = "SoftwereDesign/data/EarthQuake/nov20quakedata.atom";
        String phrase = "Can";
        String where = "any";

        EarthQuakeParser parser = new EarthQuakeParser();
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for  " + list.size() + " quakes");
        ArrayList<QuakeEntry> found = filterByPhrase(list, where, phrase);
        // dumpCSV(found);
        System.out.println("Found " + found.size() + " quakes that match " + phrase + " at " + where);
    }

    /**
     * Filters the given list of quake data to only include entries with a magnitude
     * greater than the specified minimum.
     *
     * @param quakeData the list of quake entries to filter
     * @param magMin    the minimum magnitude to include
     * @return a new list of quake entries with magnitudes greater than magMin
     */
    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData, double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe : quakeData) {
            if (qe.getMagnitude() > magMin) {
                answer.add(qe);
            }
        }
        return answer;
    }

    /**
     * Filters the given list of quake data to only include entries within the
     * specified distance of a given location.
     *
     * @param quakeData the list of quake entries to filter
     * @param distMax   the maximum distance from the location to include
     * @param from      the location to measure distance from
     * @return a new list of quake entries within distMax of the given location
     */
    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData, double distMax, Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe : quakeData) {
            if (qe.getLocation().distanceTo(from) < distMax) {
                answer.add(qe);
            }
        }
        return answer;
    }

    /**
     * Filters the given list of quake data to only include entries with a depth
     * between the specified minimum and maximum.
     *
     * @param quakeData the list of quake entries to filter
     * @param minDepth  the minimum depth to include
     * @param maxDepth  the maximum depth to include
     * @return a new list of quake entries with depths between minDepth and maxDepth
     */
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, double minDepth, double maxDepth) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe : quakeData) {
            if (qe.getDepth() > minDepth && qe.getDepth() < maxDepth) {
                answer.add(qe);
            }
        }
        return answer;
    }

    /**
     * Filters quake entries to those within 1000 kilometers of a specified location
     * and outputs their information
     * along with their distance from the location.
     */
    public void closeToMe() {
        // String source =
        // "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "SoftwereDesign/data/EarthQuake/nov20quakedatasmall.atom";
        // Bridgeport, CA
        Location city = new Location(38.17, -118.82);

        EarthQuakeParser parser = new EarthQuakeParser();
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for  " + list.size() + " quakes");

        ArrayList<QuakeEntry> close = filterByDistanceFrom(list, 1000 * 1000, city);
        for (int k = 0; k < close.size(); k++) {
            QuakeEntry entry = close.get(k);
            double distanceInMeters = city.distanceTo(entry.getLocation());
            System.out.println(distanceInMeters / 1000 + " " + entry.getInfo());
        }
        System.out.println("Found " + close.size() + " quakes that match that criteria");
    }

    /**
     * Filters the given list of quake data to only include entries whose
     * information contains the specified phrase.
     *
     * @param quakeData the list of quake entries to filter
     * @param where     the position in the information string where the phrase
     *                  should occur ("start" or "any")
     * @param phrase    the phrase to search for
     * @return a new list of quake entries with information containing the specified
     *         phrase
     */
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, String where, String phrase) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe : quakeData) {
            if (qe.getInfo().contains(phrase)) {
                switch (where) {
                    case "start" -> {
                        if (qe.getInfo().startsWith(phrase))
                            answer.add(qe);
                    }
                    case "end" -> {
                        if (qe.getInfo().endsWith(phrase))
                            answer.add(qe);
                    }
                    case "any" -> answer.add(qe);
                }
            }
        }
        return answer;
    }

    /**
     * Reads in quake data from a file and outputs it in CSV format.
     */
    public void createCSV() {
        String source = "SoftwereDesign/data/EarthQuake/nov20quakedatasmall.atom";
        // String source =
        // "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";

        EarthQuakeParser parser = new EarthQuakeParser();
        ArrayList<QuakeEntry> list = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }

    /**
     * Prints out a comma-separated value (CSV) representation of the given list of
     * quake entries.
     *
     * @param list the list of quake entries to output
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

