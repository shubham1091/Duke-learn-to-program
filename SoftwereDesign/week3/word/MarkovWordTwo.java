package SoftwereDesign.week3.word;

import java.util.ArrayList;
import java.util.Random;

import SoftwereDesign.week3.IMarkovModel;

public class MarkovWordTwo implements IMarkovModel {
    private String[] myText;
    private Random myRandom;

    public MarkovWordTwo() {
        myRandom = new Random();
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String text) {
        myText = text.split("\\s+");
    }

    public String getRandomText(int numWords) {
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length - 2); // random word to start with
        String key1 = myText[index];
        String key2 = myText[index + 1];
        sb.append(key1);
        sb.append(" ");
        sb.append(key2);
        sb.append(" ");
        for (int k = 0; k < numWords - 2; k++) {
            ArrayList<String> follows = getFollows(key1, key2);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key1 = key2;
            key2 = next;
        }

        return sb.toString().trim();
    }

    private ArrayList<String> getFollows(String key1, String key2) {
        ArrayList<String> res = new ArrayList<String>();
        int pos = 0;
        while (pos < myText.length - 2) {
            int start = indexOf(myText, key1, key2, pos);
            if (start == -1) {
                break;
            }

            String next = myText[start + 2];
            res.add(next);
            pos = start + 2;
        }
        return res;
    }

    private int indexOf(String[] words, String target1, String target2, int start) {
        for (int i = start; i < words.length - 1; i++) {
            if (words[i].equals(target1) && words[i + 1].equals(target2)) {
                return i;
            }
        }
        return -1;
    }
}
