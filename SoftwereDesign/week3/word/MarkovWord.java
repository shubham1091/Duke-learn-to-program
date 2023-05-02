package SoftwereDesign.week3.word;

import java.util.ArrayList;
import java.util.Random;

import SoftwereDesign.week3.IMarkovModel;

public class MarkovWord implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private int myOrder;

    public MarkovWord(int myOrder) {
        myRandom = new Random();
        this.myOrder = myOrder;
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String text) {
        myText = text.split("\\s+");
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

    private ArrayList<String> getFollows(WordGram kGram) {
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        while (pos < myText.length - kGram.length()) {
            int index = indexOf(myText, kGram, pos);
            if (index == -1) {
                break;
            }
            follows.add(myText[index + kGram.length()]);
            pos = index + 1;
        }
        return follows;
    }

    private int indexOf(String[] words, WordGram target, int start) {
        for (int i = start; i <= words.length - target.length(); i++) {
            WordGram wg = new WordGram(words, i, target.length());
            if (wg.equals(target)) {
                return i;
            }
        }
        return -1;
    }

}
