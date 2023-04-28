package StructuredData.week3;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import edu.duke.FileResource;

public class LogAnalyzer {
    private ArrayList<LogEntry> records;

    /**
     * Creates a new LogAnalyzer instance with an empty list of log entries.
     */
    public LogAnalyzer() {
        records = new ArrayList<LogEntry>();
    }

    /**
     * Reads a log file and creates LogEntry instances for each line in the file.
     *
     * @param filename the name of the file to read
     */
    public void readFile(String filename) {
        FileResource fr = new FileResource(filename);
        for (String line : fr.lines()) {
            LogEntry le = WebLogParser.parseEntry(line);
            records.add(le);
        }
    }

    /**
     * Prints all log entries in the list of records.
     */
    public void printAll() {
        for (LogEntry le : records) {
            System.out.println(le);
        }
    }

    /**
     * Prints all log entries with a status code higher than a given number.
     *
     * @param num the status code to compare against
     */
    public void printAllHigherThanNum(int num) {
        for (LogEntry le : records) {
            if (le.getStatusCode() > num) {
                System.out.println(le);
            }
        }
    }

    /**
     * Returns the number of unique IP addresses that visited on a given day.
     *
     * @param someday the day to count visits for (in the format MMM DD)
     * @return the number of unique IP addresses that visited on the given day
     */
    public int UniqueIPVisitsOnDay(String someday) {
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for (LogEntry le : records) {
            Date d = le.getAccessTime();
            String ipAddr = le.getIpAddress();
            if (d.toString().contains(someday) && !uniqueIPs.contains(ipAddr)) {
                uniqueIPs.add(ipAddr);
            }
        }
        return uniqueIPs.size();
    }

    /**
     * Returns the number of unique IP addresses that had a status code in a given
     * range.
     *
     * @param low  the lower bound of the status code range
     * @param high the upper bound of the status code range
     * @return the number of unique IP addresses with a status code in the given
     *         range
     */
    public int countUniqueIPsInRange(int low, int high) {
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for (LogEntry le : records) {
            String ipAddr = le.getIpAddress();
            if (!uniqueIPs.contains(ipAddr)) {
                if (le.getStatusCode() >= low && le.getStatusCode() <= high) {
                    uniqueIPs.add(ipAddr);
                }
            }
        }
        return uniqueIPs.size();
    }

    /**
     * Returns a HashMap<String, Integer> containing counts of the number of times
     * each unique IP address appears in the records.
     * 
     * @return a HashMap<String, Integer> containing counts of the number of times
     *         each unique IP address appears in the records.
     */
    public HashMap<String, Integer> countVisitsPerIp() {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for (LogEntry le : records) {
            String ipAddr = le.getIpAddress();
            if (map.containsKey(ipAddr)) {
                map.put(ipAddr, map.get(ipAddr) + 1);
            } else {
                map.put(ipAddr, 1);
            }
        }
        return map;
    }

    /**
     * Returns the number of unique IP addresses in the records.
     * 
     * @return the number of unique IP addresses in the records.
     */
    public int countUniqueIPs() {
        HashMap<String, Integer> counts = countVisitsPerIp();
        return counts.size();
    }

    /**
     * Returns the maximum number of visits to this website by a single IP address.
     * 
     * @param counts a HashMap<String, Integer> containing counts of the number of
     *               times each unique IP address appears in the records.
     * @return the maximum number of visits to this website by a single IP address.
     */
    public int mostNumberVisitsByIP(HashMap<String, Integer> counts) {
        int max = 0;
        for (String ip : counts.keySet()) {
            if (counts.get(ip) > max) {
                max = counts.get(ip);
            }
        }
        return max;
    }

    /**
     * Returns an ArrayList<String> of IP addresses that visited this website the
     * maximum number of times.
     * 
     * @param map a HashMap<String,Integer> containing counts of the number of times
     *            each unique IP address appears in the records.
     * @return an ArrayList<String> of IP addresses that visited this website the
     *         maximum number of times.
     */
    public ArrayList<String> iPsMostVisits(HashMap<String, Integer> map) {
        ArrayList<String> ips = new ArrayList<String>();
        int mx = mostNumberVisitsByIP(map);
        for (String ip : map.keySet()) {
            if (map.get(ip) == mx) {
                ips.add(ip);
            }
        }
        return ips;
    }

    /**
     * Returns a HashMap<String, ArrayList<String>> containing IP addresses that
     * visited this website for each day in the records.
     * 
     * @return a HashMap<String, ArrayList<String>> containing IP addresses that
     *         visited this website for each day in the records.
     */
    public HashMap<String, ArrayList<String>> iPsForDays() {
        HashMap<String, ArrayList<String>> ipsPerDay = new HashMap<String, ArrayList<String>>();
        for (LogEntry le : records) {
            String dateString = le.getAccessTime().toString().substring(4, 10);
            String ip = le.getIpAddress();
            ArrayList<String> ips = ipsPerDay.get(dateString);
            if (ips == null) {
                ips = new ArrayList<String>();
            }
            ips.add(ip);
            ipsPerDay.put(dateString, ips);
        }
        return ipsPerDay;
    }

    /**
     * Returns the day with the most IP visits to this website.
     * 
     * @param dayToIPsMap a HashMap<String, ArrayList<String>> containing IP
     *                    addresses that visited this website for each day in the
     *                    records.
     * @return the day with the most IP visits to this website.
     */
    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> dayToIPsMap) {
        String dayWithMostVisits = "";
        int maxVisits = 0;
        for (String day : dayToIPsMap.keySet()) {
            int visits = dayToIPsMap.get(day).size();
            if (visits > maxVisits) {
                maxVisits = visits;
                dayWithMostVisits = day;
            }
        }
        return dayWithMostVisits;
    }

    /**
     * 
     * Returns an ArrayList of IP addresses that had the most visits on the given
     * day.
     * 
     * @param dayToIPsMap a HashMap containing the days as keys and an ArrayList of
     *                    IP addresses as values.
     * 
     * @param date        a String representing the day to search for IP addresses
     *                    with the most visits.
     * 
     * @return an ArrayList of IP addresses that had the most visits on the given
     *         day.
     */
    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> dayToIPsMap, String date) {
        ArrayList<String> ipsOnDay = dayToIPsMap.get(date);
        HashMap<String, Integer> ipToCountMap = new HashMap<String, Integer>();

        for (String ip : ipsOnDay) {
            if (!ipToCountMap.containsKey(ip)) {
                ipToCountMap.put(ip, 1);
            } else {
                ipToCountMap.put(ip, ipToCountMap.get(ip) + 1);
            }
        }

        int maxVisits = 0;
        for (int count : ipToCountMap.values()) {
            if (count > maxVisits) {
                maxVisits = count;
            }
        }

        ArrayList<String> ipsWithMostVisits = new ArrayList<String>();
        for (String ip : ipToCountMap.keySet()) {
            if (ipToCountMap.get(ip) == maxVisits) {
                ipsWithMostVisits.add(ip);
            }
        }

        return ipsWithMostVisits;
    }

}
