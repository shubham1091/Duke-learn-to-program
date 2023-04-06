package SolvingProblems.week2.SR;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import edu.duke.FileResource;
import edu.duke.StorageResource;

public class part1 {
    public static void main(String[] args) {
        // System.out.println(cgRatio("ATTCTATCGACCCTAATTCTTCCACTCACCCCAACCCTCTCCATTCTGGA"));
        // System.out.println(countCTG("CTGCTGCTGCTGCTG"));
        test();

    }

    public static void test() {
        // String dna = convertFileToString(location);
        FileResource re = new FileResource();
        String dna = re.asString();
        StorageResource sr = new StorageResource();
        sr.add(dna);
        processGenes(sr);
    }

    public static String convertFileToString(String filePath) {
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(filePath));
            return new String(bytes, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.println("Failed to read file: " + filePath);
            e.printStackTrace();
            return null;
        }
    }

    public static StorageResource gettAllGenes(String dna) {
        StorageResource sr = new StorageResource();
        int startIndex = 0;
        while (true) {
            String currentGene = findGene(dna, startIndex);
            if (currentGene.isEmpty()) {
                break;
            }
            sr.add(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
        return sr;
    }

    public static String findGene(String dna, int where) {
        int startIndex = dna.indexOf("ATG", where);
        if (startIndex == -1) {
            return "";
        }
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");

        int minIndex = 0;
        if (taaIndex == -1 || (tgaIndex != -1 && tgaIndex < taaIndex)) {
            minIndex = tgaIndex;
        } else {
            minIndex = taaIndex;
        }
        if (minIndex == -1 || (tagIndex != -1 && tgaIndex < minIndex)) {
            minIndex = tagIndex;
        }
        if (minIndex == -1) {
            return "";
        }
        return dna.substring(startIndex, minIndex + 3);
    }

    public static int findStopCodon(String dna, int startIndex, String stopCodon) {
        int currentIndex = dna.indexOf(stopCodon, startIndex + 3);
        while (currentIndex != -1) {
            int diff = currentIndex - startIndex;
            if (diff % 3 == 0) {
                return currentIndex;
            } else {
                currentIndex = dna.indexOf(stopCodon, currentIndex + 1);
            }
        }

        return dna.length();
    }

    public static double cgRatio(String dna) {
        int countcg = 0;
        int start = 0;
        while (true) {
            int pos = dna.indexOf("C", start);
            if (pos == -1) {
                break;
            }
            countcg += 1;
            start = pos + 1;
        }
        start = 0;
        while (true) {
            int pos = dna.indexOf("G", start);
            if (pos == -1) {
                break;
            }
            countcg += 1;
            start = pos + 1;
        }

        return ((double) countcg) / dna.length();
    }

    public static int countCTG(String dna) {
        int count = 0;
        int idx = 0;
        while (true) {
            idx = dna.indexOf("CTG", idx);
            if (idx == -1)
                break;
            idx += 3;
            count++;
        }
        return count;
    }

    public static void processGenes(StorageResource sr) {
        int number = 0;
        int count = 0;
        int counting = 0;
        System.out.println("Printing genes with +60 charas:");
        for (String gene60 : sr.data()) {
            number++;
            if (gene60.length() > 60) {
                System.out.println(gene60);
                count = count + 1;
            }
            counting = Math.max(gene60.length(), counting);
        }
        count = 0;
        System.out.println("Printing number of strings above: " + count);
        for (String cgRat : sr.data()) {
            if (cgRatio(cgRat) > 0.35) {
                System.out.println(
                        "Gene with C-G ratio higher than 0.35 = " + cgRat + " and the ratio is: " + cgRatio(cgRat));
                count = count + 1;
            }
        }
        System.out.println("Genes with cgRatio greater than 0.35: " + count);
        System.out.println("Length of the longest gene: " + counting);
        System.out.println("total number genes: "+number);

    }
}
