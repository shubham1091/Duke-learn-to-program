package StructuredData.week1;

import edu.duke.FileResource;

public class WordLengths {
    public static void main(String[] args) {
        testCountWordLengths();
    }

    public static void testCountWordLengths() {
        FileResource fr = new FileResource();
        int[] counts = new int[31];
        countWordLengths(fr, counts);
        for (int i = 1; i < counts.length; i++) {
            if (counts[i] > 0) {
                System.out.println(counts[i] + " words of length " + i);
            }
        }
        int Common = indexOfMax(counts);
        System.out.println("timse most common: " + Common);
        mostCommonLength(counts);
    }

    public static void mostCommonLength(int[] counts) {
        int len = Integer.MIN_VALUE;
        for(int i=0;i<counts.length;i++) {
            len = Math.max(len,counts[i]);
        }
        System.out.println("most common length: " + len);
    }

    public static void countWordLengths(FileResource resource, int[] counts) {
        for (String word : resource.words()) {
            int len = word.length();
            char ch = word.charAt(0);
            if (!Character.isLetter(ch)) {
                len--;
            }
            ch = word.charAt(word.length() - 1);
            if (!Character.isLetter(ch)) {
                len--;
            }
            counts[len]++;
        }
    }

    public static int indexOfMax(int[] values) {
        int mx = Integer.MIN_VALUE;
        for (int i = 0; i < values.length; i++) {
            mx = Math.max(mx, values[i]);
        }
        return mx;
    }

}
