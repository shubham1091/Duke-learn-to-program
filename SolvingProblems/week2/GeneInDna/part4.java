package SolvingProblems.week2.GeneInDna;

import edu.duke.*;

public class part4 {
    public static void main(String[] args) {
        testing();
    }
    public static void read(String url) {
        URLResource page = new URLResource(url);
        String ytb = "youtube.com";
        for (String word : page.words()) {
            String linelowercase = word.toLowerCase();
            String link = "";
            int i = linelowercase.indexOf(ytb);
            if (i != -1) {
                int start = linelowercase.indexOf("\"");
                int end = linelowercase.indexOf("\"", i);
                link = word.substring(start, end);
                System.out.println(link);
            }
        }
        return;
    }

    public static void testing() {
        read("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
    }
}
