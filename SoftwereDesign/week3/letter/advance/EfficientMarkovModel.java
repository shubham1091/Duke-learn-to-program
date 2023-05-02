package SoftwereDesign.week3.letter.advance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class EfficientMarkovModel extends AbstractMarkovModel {
    private HashMap<String, ArrayList<String>> myMap;
    private int myOrder;

    public EfficientMarkovModel(int myOrder) {
        myRandom = new Random();
        this.myOrder = myOrder;
        myMap = new HashMap<String, ArrayList<String>>();
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String s) {
        myText = s.trim();
        buildMap();
    }

    public String getRandomText(int numChars) {
        if (myText == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length() - myOrder);
        String key = myText.substring(index, index + myOrder);
        sb.append(key);
        for (int k = 0; k < numChars - myOrder; k++) {
            ArrayList<String> follow = getFollows(key);
            if (follow.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follow.size());
            String next = follow.get(index);
            sb.append(next);
            key = key.substring(1) + next;
        }

        return sb.toString();
    }

    @Override
    public String toString() {
        return "MarkovModel of order " + myOrder;
    }

    public void buildMap() {
        for (int i = 0; i < myText.length() - myOrder + 1; i++) {
            String key = myText.substring(i, i + myOrder);
            ArrayList<String> follows = new ArrayList<>();
            if (i + myOrder >= myText.length()) {
                myMap.put(key, follows);
            } else {
                String value = myText.substring(i + myOrder, i + myOrder + 1);
                if (myMap.containsKey(key)) {
                    myMap.get(key).add(value);
                } else {
                    follows.add(value);
                    myMap.put(key, follows);
                }
            }
        }
    }

    public ArrayList<String> getFollows(String key) {
        ArrayList<String> follows = new ArrayList<String>();
        follows = myMap.get(key);
        return follows;
    }

    public void printHashMapInfo() {
        // if (myMap.size() < 20) {
        //     for (String key : myMap.keySet()) {
        //         System.out.println(myMap.get(key));
        //     }
        // }
        System.out.println("Number of keys in myMap: " + myMap.size());
        int sz = 0;
        for (String key : myMap.keySet()) {
            sz = Math.max(sz, myMap.get(key).size());
        }
        System.out.println("Largest values in myMap: " + sz);
        // for (String key : myMap.keySet()) {
        //     if (myMap.get(key).size() == sz) {
        //         System.out.println(key + ": " + myMap.get(key));
        //     }
        // }
    }

}
