package StructuredData.week3;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Tester {
    public static void main(String[] args) {
        // testMostVisits();
        // testIpMostVisits();
        // testUniqIP();
        // testSomeDay();
        // testIPsRange();
        // testMostVisits();
        // testIpMostVisits();
        // testDayWithMostIPVisits();
        testIPsWithMostVisitsOnDay();
    }

    public static void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);

    }

    public static void testLogAnalyzer() {
        LogAnalyzer lg = new LogAnalyzer();
        lg.readFile("StructuredData/data/log/short-test_log");
        lg.printAll();
    }

    public static void testUniqIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("StructuredData/data/log/weblog2_log");
        int uniqueIPs = la.countUniqueIPs();
        System.out.println("There are " + uniqueIPs + " IPs");
    }

    public static void testStatusCode() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("StructuredData/data/log/weblog1_log");
        la.printAllHigherThanNum(400);
    }

    public static void testSomeDay() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("StructuredData/data/log/weblog2_log");
        int st = la.UniqueIPVisitsOnDay("Sep 24");
        System.out.println(st);
    }

    public static void testIPsRange() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("StructuredData/data/log/weblog2_log");
        // System.out.println(la.countUniqueIPsInRange(400, 499));
        System.out.println(la.countUniqueIPsInRange(200, 299));
    }

    public static void testUni() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("StructuredData/data/log/short-test_log");
        System.out.println(la.countVisitsPerIp());
    }

    public static void testMostVisits() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("StructuredData/data/log/weblog2_log");
        HashMap<String, Integer> visit = la.countVisitsPerIp();
        System.out.println(la.mostNumberVisitsByIP(visit));
    }

    public static void testIpMostVisits() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("StructuredData/data/log/weblog2_log");
        HashMap<String, Integer> visit = la.countVisitsPerIp();
        System.out.println(la.iPsMostVisits(visit));
    }

    public static void testIPsForDays() {
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile("StructuredData/data/log/weblog3-short_log");
        HashMap<String, ArrayList<String>> iPsForDays = analyzer.iPsForDays();
        for (String day : iPsForDays.keySet()) {
            System.out.println(day + ": " + iPsForDays.get(day));
        }
    }

    public static void testDayWithMostIPVisits() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("StructuredData/data/log/weblog2_log");
        HashMap<String, ArrayList<String>> ipMap = la.iPsForDays();
        String dayWithMostVisits = la.dayWithMostIPVisits(ipMap);
        System.out.println("Day with most IP visits: " + dayWithMostVisits);
    }

    public static void testIPsWithMostVisitsOnDay() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("StructuredData/data/log/weblog2_log");

        HashMap<String, ArrayList<String>> dayToIPsMap = la.iPsForDays();
        ArrayList<String> ips = la.iPsWithMostVisitsOnDay(dayToIPsMap, "Sep 29");

        System.out.println("IPs with most visits on Sep 30:");
        for (String ip : ips) {
            System.out.println(ip);
        }
    }

}
