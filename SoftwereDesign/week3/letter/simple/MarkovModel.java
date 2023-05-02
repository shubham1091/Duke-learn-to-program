package SoftwereDesign.week3.letter.simple;

import java.util.ArrayList;
import java.util.Random;

public class MarkovModel {
    private String myText;
    private Random myRandom;
    private int num;

    public MarkovModel(int num) {
        myRandom = new Random();
        this.num = num;
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
        int index = myRandom.nextInt(myText.length() - num);
        String key = myText.substring(index, index + num);
        sb.append(key);
        for (int k = 0; k < numChars - num; k++) {
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

    public ArrayList<String> getFollows(String key) {
        ArrayList<String> res = new ArrayList<String>();
        int pos = 0;
        while (true) {
            pos = myText.indexOf(key, pos);
            if (pos == -1 || pos + 1 >= myText.length()) {
                break;
            }
            char ch = myText.charAt(pos + key.length());
            res.add(String.valueOf(ch));
            pos++;
        }
        return res;
    }
}
