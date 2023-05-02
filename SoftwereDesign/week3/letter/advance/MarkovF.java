package SoftwereDesign.week3.letter.advance;

import java.util.ArrayList;
import java.util.Random;

public class MarkovF extends AbstractMarkovModel {

    public MarkovF() {
        myRandom = new Random();
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String s) {
        myText = s.trim();
    }

    public String getRandomText(int numChars) {
        if (myText == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length() - 4);
        String key = myText.substring(index, index + 4);
        sb.append(key);
        for (int k = 0; k < numChars - 4; k++) {
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
        return "MarkovMOdel of order 4";
    }

}
