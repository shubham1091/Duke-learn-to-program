package SoftwereDesign.week1;

import java.util.*;

public class ClosestQuakes {
    public static void main(String[] args) {
        ClosestQuakes cq = new ClosestQuakes();
        cq.findClosestQuakes();
    }

    /**
     * This method finds the closest earthquakes to a given location.
     */
    public void findClosestQuakes() {
        // String source
        // ="http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "SoftwereDesign/data/Earthquake/nov20quakedatasmall.atom";
        Location city = new Location(-6.211, 106.845);

        EarthQuakeParser parser = new EarthQuakeParser();
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size());

        ArrayList<QuakeEntry> close = getClosest(list, city, 3);
        for (QuakeEntry qe : close) {
            double distanceInMeters = city.distanceTo(qe.getLocation());
            System.out.printf("%4.2f\t %s\n", distanceInMeters / 1000, qe);
        }
        System.out.println("number found: " + close.size());
    }

    /**
     * Returns an ArrayList of the closest earthquakes to a given location,
     * based on the number of earthquakes requested and the earthquake data
     * provided.
     * 
     * @param quakeData the list of earthquake data to search through
     * @param current   the location to find the closest earthquakes to
     * @param howMany   the number of closest earthquakes to find
     * @return an ArrayList of the closest earthquakes to the given location
     */
    public ArrayList<QuakeEntry> getClosest(ArrayList<QuakeEntry> quakeData, Location current, int howMany) {
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        for (int i = 0; i < howMany; i++) {
            int pos = 0;
            for (int j = 0; j < quakeData.size(); j++) {
                QuakeEntry quake = quakeData.get(j);
                Location loc = quake.getLocation();
                if (!ret.contains(quake)) {
                    if (loc.distanceTo(current) < quakeData.get(pos).getLocation().distanceTo(current)) {
                        pos = j;
                    }
                }
            }
            ret.add(quakeData.get(pos));
        }
        return ret;
    }

}
