package SolvingProblems.week3.CountriesExport;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import edu.duke.FileResource;

public class Assignment_one {
    public static void main(String[] args) {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        // bigExporters(parser, "$999,999,999");
        // listExportersTwoProducts(parser, "fish", "nuts");
        bigExporters(parser, "$999,999,999,999");
    }

    public static String countryInfo(CSVParser parser, String country) {
        for (CSVRecord record : parser) {
            String info = record.get("Country");
            if (info.contains(country)) {
                String ls = record.get("Exports");
                String val = record.get("Value (dollars)");
                return info + ": " + ls + ": " + val;
            }
        }
        return "NOT FOUND";
    }

    public static void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
        for (CSVRecord record : parser) {
            String export = record.get("Exports");
            boolean hasone =export.contains(exportItem1);
            boolean hastwo =export.contains(exportItem2);
            if(hasone&& hastwo){
                System.out.println(record.get("Country"));
            }
        }
    }

    public static int numberOfExporters(CSVParser parser,String exportItem){
        int count = 0;
        for(CSVRecord record : parser){
            String export = record.get("Exports");
            if(export.contains(exportItem)){
                count++;
            }
        }
        return count;
    }

    public static void bigExporters(CSVParser parser, String amount){
        int len = amount.length();
        for (CSVRecord record : parser){
            String val = record.get("Value (dollars)");
            if(val.length()>len){
                System.out.println(record.get("Country")+" "+val);
            }
        }
    }

}
