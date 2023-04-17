package StructuredData.week2;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;

public class WordsInFiles {
    private HashMap<String, ArrayList<String>> map;

    public WordsInFiles() {
        map = new HashMap<String, ArrayList<String>>();
    }
    public static void main(String[] args) {
         WordsInFiles word = new WordsInFiles();
         word.tester();
    }

    public void addWordsFromFile(File f) {
        FileResource fr = new FileResource(f);
        String Name = f.getName();

        for (String word : fr.words()) {
            // System.out.println("map: " + map);
            if (!map.containsKey(word)) {
                ArrayList<String> fileNames = new ArrayList<String>();
                fileNames.add(Name);
                map.put(word, fileNames);
            } else {
                ArrayList<String> tempList = map.get(word);
                if (!tempList.contains(Name)) {
                    tempList.add(Name);
                    // System.out.println("word: " + word + "; tempList: " + tempList);
                    map.put(word, tempList);
                    // System.out.println("map is: " + map);
                }
            }
        }
    }

    public void buildWordFileMap() {
        map.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            addWordsFromFile(f);
        }
    }
    public int findMax(){
        int mx = Integer.MIN_VALUE;
        for(String word: map.keySet()) {
            Integer tp = map.get(word).size();
            mx = Math.max(mx, tp);
        }
        return mx;
    }

    public void tester() {
        buildWordFileMap();
        System.out.println(map.get("tree"));
        
    }

}