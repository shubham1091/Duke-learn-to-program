package SoftwereDesign.week3.letter.advance;

import java.util.ArrayList;
import java.util.Random;

import SoftwereDesign.week3.IMarkovModel;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;

    public AbstractMarkovModel() {
        myRandom = new Random();
    }

    public void setTraining(String s) {
        myText = s.trim();
    }

    abstract public String getRandomText(int numChars);

    protected ArrayList<String> getFollows(String key) {
        ArrayList<String> res = new ArrayList<String>();
        int pos = 0;
        while (pos < myText.length()) {
            int start = myText.indexOf(key, pos);
            if (start == -1 || start + key.length() >= myText.length() - 1) {
                break;
            }
            String next = myText.substring(start + key.length(), start + key.length() + 1);
            res.add(next);
            pos = start + key.length();
        }
        return res;
    }

    @Override
    abstract public String toString();

}