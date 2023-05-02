package SoftwereDesign.week3.word;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import SoftwereDesign.week3.IMarkovModel;

public class EfficientMarkovWord implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    private HashMap<WordGram, ArrayList<String>> followsMap;

    public EfficientMarkovWord(int myOrder) {
        myRandom = new Random();
        this.myOrder = myOrder;
        followsMap = new HashMap<WordGram, ArrayList<String>>();
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String text) {
        myText = text.split("\\s+");
        buildMap();
        printHashMapInfo();
    }

    public String getRandomText(int numWords) {
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length - myOrder); // random index to start with
        WordGram currentGram = new WordGram(myText, index, myOrder);
        sb.append(currentGram.toString());
        sb.append(" ");
        for (int k = 0; k < numWords - myOrder; k++) {
            ArrayList<String> follows = getFollows(currentGram);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            currentGram = currentGram.shiftAdd(next);
        }

        return sb.toString().trim();
    }

    public ArrayList<String> getFollows(WordGram kGram) {
        return followsMap.get(kGram);
    }

    // private int indexOf(String[] words, WordGram target, int start) {
    //     for (int i = start; i <= words.length - target.length(); i++) {
    //         WordGram wg = new WordGram(words, i, target.length());
    //         if (wg.equals(target)) {
    //             return i;
    //         }
    //     }
    //     return -1;
    // }

    private void buildMap() {
        for (int i = 0; i <= myText.length - myOrder; i++) {
            WordGram key = new WordGram(myText, i, myOrder);
            if (!followsMap.containsKey(key)) {
                followsMap.put(key, new ArrayList<String>());
            }
            if (i + myOrder < myText.length) {
                String next = myText[i + myOrder];
                followsMap.get(key).add(next);
            }
        }
    }

    public void printHashMapInfo() {
        int maxArrayListSize = 0;
        ArrayList<WordGram> maxArrayListKeys = new ArrayList<>();

        System.out.println("HashMap:");
        for (WordGram key : followsMap.keySet()) {
            // System.out.println(key + ": " + followsMap.get(key));
            int arrayListSize = followsMap.get(key).size();
            if (arrayListSize > maxArrayListSize) {
                maxArrayListSize = arrayListSize;
                maxArrayListKeys.clear();
                maxArrayListKeys.add(key);
            } else if (arrayListSize == maxArrayListSize) {
                maxArrayListKeys.add(key);
            }
        }

        System.out.println("Number of keys in the HashMap: " + followsMap.size());
        System.out.println("Size of the largest value in the HashMap: " + maxArrayListSize);
        System.out.println("Keys that have the maximum size value: " + maxArrayListKeys);
    }

}
