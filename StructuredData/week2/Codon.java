package StructuredData.week2;

import java.util.HashMap;

import edu.duke.FileResource;

public class Codon {
    private HashMap<String, Integer> map;
    private HashMap<String, Integer> uniqe;

    public Codon() {
        map = new HashMap<String, Integer>();
        uniqe = new HashMap<String, Integer>();

    }

    public static void main(String[] args) {
        Codon cn = new Codon();
        cn.testdna();
    }

    // This method will build a new map of codons mapped
    // to their counts from the string dna
    public void buildCodonMap(int start, String dna) {
        map.clear();
        for (int k = start; k < dna.length()-3; k += 3) {
            String codon = dna.substring(k, k + 3);
            // System.out.println(codon);
            if (!map.containsKey(codon)) {
                map.put(codon, 1);
            } else {
                map.put(codon, map.get(codon) + 1);
            }
        }
        /*
         * for (String c : map.keySet()){
         * System.out.println(c + "\t "+ map.get(c));
         * }
         */
    }

    public String getMostCommonCodon() {
        String codon = "";
        int max = 0;
        for (String w : map.keySet())
            if (map.get(w) > max) {
                codon = w;
                max = map.get(w);
            }
        return codon;
    }

    public void printCodonCounts() {

        for (String key : map.keySet()) {
            if (!uniqe.containsKey(key)) {
                int value = map.get(key);
                uniqe.put(key, value);
                System.out.println(key + "\t" + value);
            }
        }

    }

    public void testdna() {
        // smalldna.txt
        FileResource fr = new FileResource();
        // int i =0;
        String dna = fr.asString();
        int end = dna.length();
        end = (end / 3) - 1;

        // System.out.println("start = 0");
        // buildCodonMap(0, dna);
        // printCodonCounts();
        // String codon = getMostCommonCodon();
        // System.out.println("Most Codon is : " + codon + " = " + map.get(codon));

        // System.out.println("start = 1");
        // buildCodonMap(1, dna);
        // printCodonCounts();
        // codon = getMostCommonCodon();
        // System.out.println("Most Codon is : " + codon + " = " + map.get(codon));

        System.out.println("start = 2");
        buildCodonMap(2, dna);
        printCodonCounts();

        String codon = getMostCommonCodon();
        System.out.println("Most Codon is : " + codon + " = " + map.get(codon));

    }
}
