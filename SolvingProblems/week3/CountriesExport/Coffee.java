package SolvingProblems.week3.CountriesExport;

import edu.duke.*;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class Coffee {
    public static void main(String[] args) {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        ListExporters(parser, "coffee");
    }

    public static void ListExporters(CSVParser parser, String exportOfIntrest) {
        for (CSVRecord record : parser) {
            String export = record.get("Exports");
            if (export.contains(exportOfIntrest)) {
                String contry = record.get("Country");
                System.out.println(contry);
                System.out.println();
            }
        }
    }

}
