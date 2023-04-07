package SolvingProblems.week3.Weather;

import java.io.File;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;

public class AssignmentOne {
    public static void main(String[] args) {
        testLosestHumidityInManyFiles();
    }

    public static void testColdestHourInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord low = coldestHourInFile(parser);
        System.out.println("Coldest temperature is " + low.get("TemperatureF"));
    }

    public static CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord lowest = null;
        for (CSVRecord currentRow : parser) {
            lowest = getLargestOfTwo(currentRow, lowest, "TemperatureF", true);
        }
        return lowest;
    }

    public static CSVRecord getLargestOfTwo(CSVRecord currentRow, CSVRecord lowestSoFar, String comp, boolean low) {
        if (lowestSoFar == null) {
            lowestSoFar = currentRow;
        } else {
            // double currentTemp = Double.parseDouble()
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            if (currentTemp <= -9999) {
                return lowestSoFar;
            }
            double largestTemp = Double.parseDouble(lowestSoFar.get("TemperatureF"));

            if (low) {
                if (currentTemp > largestTemp) {
                    lowestSoFar = currentRow;
                }
            } else {
                if (currentTemp < largestTemp) {
                    lowestSoFar = currentRow;
                }
            }
        }
        return lowestSoFar;
    }

    public static void testFileWithColdestTemperature() {
        String tempFile = fileWithColdestTemperature();
        FileResource fr = new FileResource(tempFile);
        CSVRecord cold = coldestHourInFile(fr.getCSVParser());
        int idx = tempFile.lastIndexOf("\\") + 1;
        System.out.println("Coldest day was in file " + tempFile.substring(idx));
        System.out.println("Coldest temperaturn on that day was " + cold.get("TemperatureF"));
        System.out.println("All the Temperature on the coldest day were:");
        for (CSVRecord currentRow : fr.getCSVParser()) {
            System.out.println(currentRow.get("DateUTC") + ": " + currentRow.get("TemperatureF"));
        }

    }

    public static String fileWithColdestTemperature() {
        DirectoryResource dr = new DirectoryResource();
        String name = null;
        CSVRecord low = null;
        for (File file : dr.selectedFiles()) {
            FileResource fr = new FileResource(file);
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            low = getLargestOfTwo(currentRow, low, "TemperatureF", true);
            name = file.getPath();
        }
        return name;
    }

    public static CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord lowest = null;
        for (CSVRecord currentRow : parser) {
            lowest = getLargestOfTwo(currentRow, lowest, "Humidity", true);
        }
        return lowest;
    }

    public static void testLowestHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Losest Humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
    }
    public static CSVRecord lowestHumidityInManyFiles(){
        DirectoryResource dr = new DirectoryResource();
        CSVRecord lowestSoFar = null;
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
            lowestSoFar = getLargestOfTwo(currentRow, lowestSoFar, "Humidity", true);
        }
        return lowestSoFar;
    }
    public static void testLosestHumidityInManyFiles(){
        CSVRecord record = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was "+record.get("Humidity")+" at "+record.get("DateUTC"));
    }
}
