package StructuredData.week1;

import edu.duke.FileResource;

public class CommonWords {
    public static void main(String[] args) {
        countShakespeare();
    }

    public static void countShakespeare() {
        String[] plays = { "caesar.txt", "errors.txt", "hamlet.txt", "likeit.txt", "macbeth.txt", "romeo.txt" };
        // String[] plays= {"small.txt"};
        String[] common = getCommon();
        int[] counts = new int[common.length];
        for (int i = 0; i < plays.length; i++) {
            FileResource resource = new FileResource("StructuredData/data/common_words/" + plays[i]);
            countWords(resource, common, counts);
            System.out.println("done with " + plays[i]);
        }
        for (int i = 0; i < common.length; i++) {
            System.out.println(common[i] + "\t" + counts[i]);
        }
    }

    public static String[] getCommon() {
        FileResource resource = new FileResource("StructuredData/data/common_words/common.txt");
        String[] common = new String[20];
        int index = 0;
        for (String s : resource.words()) {
            common[index++] = s;
        }
        return common;
    }

    public static void countWords(FileResource resource, String[] common, int[] counts) {
        for (String word : resource.words()) {
            word = word.toLowerCase();
            int index = indexOf(common, word);
            if (index != -1) {
                counts[index]++;
            }
        }
    }

    public static int indexOf(String[] list, String word) {
        for (int i = 0; i < list.length; i++) {
            if (list[i].equals(word)) {
                return i;
            }
        }
        return -1;
    }
}
