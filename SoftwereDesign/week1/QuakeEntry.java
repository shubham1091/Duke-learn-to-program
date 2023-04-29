package SoftwereDesign.week1;

/**
 * Represents an earthquake entry with information on its location, magnitude,
 * depth, and title.
 * Provides methods to retrieve and compare this information.
 */
public class QuakeEntry implements Comparable<QuakeEntry> {

	private Location myLocation;
	private String title;
	private double depth;
	private double magnitude;

	/**
	 * Constructs a new QuakeEntry with the given latitude, longitude, magnitude,
	 * title, and depth.
	 * 
	 * @param lat the latitude of the earthquake's location
	 * @param lon the longitude of the earthquake's location
	 * @param mag the magnitude of the earthquake
	 * @param t   the title of the earthquake
	 * @param d   the depth of the earthquake
	 */
	public QuakeEntry(double lat, double lon, double mag,
			String t, double d) {
		myLocation = new Location(lat, lon);

		magnitude = mag;
		title = t;
		depth = d;
	}

	/**
	 * Retrieves the location of the earthquake as a Location object.
	 * 
	 * @return the Location object representing the earthquake's location
	 */
	public Location getLocation() {
		return myLocation;
	}

	/**
	 * Retrieves the magnitude of the earthquake.
	 * 
	 * @return the magnitude of the earthquake
	 */
	public double getMagnitude() {
		return magnitude;
	}

	/**
	 * Retrieves the title of the earthquake.
	 * 
	 * @return the title of the earthquake
	 */
	public String getInfo() {
		return title;
	}

	/**
	 * Retrieves the depth of the earthquake.
	 * 
	 * @return the depth of the earthquake
	 */
	public double getDepth() {
		return depth;
	}

	/**
	 * Compares this QuakeEntry with the specified QuakeEntry for order based on
	 * their locations.
	 * 
	 * @param loc the QuakeEntry to compare to
	 * @return a negative integer, zero, or a positive integer as this QuakeEntry is
	 *         less than, equal to,
	 *         or greater than the specified QuakeEntry
	 */
	@Override
	public int compareTo(QuakeEntry loc) {
		double difflat = myLocation.getLatitude() - loc.myLocation.getLatitude();
		if (Math.abs(difflat) < 0.001) {
			double diff = myLocation.getLongitude() - loc.myLocation.getLongitude();
			if (diff < 0)
				return -1;
			if (diff > 0)
				return 1;
			return 0;
		}
		if (difflat < 0)
			return -1;
		if (difflat > 0)
			return 1;

		// never reached
		return 0;
	}

	/**
	 * Returns a string representation of this QuakeEntry.
	 * 
	 * @return a string containing the latitude, longitude, magnitude, depth, and
	 *         title of the earthquake
	 */
	@Override
	public String toString() {
		return String.format("(%3.2f, %3.2f), mag = %3.2f, depth = %3.2f, title = %s", myLocation.getLatitude(),
				myLocation.getLongitude(), magnitude, depth, title);
	}

}
