package StructuredData.week2;

import java.util.ArrayList;

import edu.duke.FileResource;

public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;

    public WordFrequencies() {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }

    public static void main(String[] args) {
        WordFrequencies word = new WordFrequencies();
        word.tester();
    }
    public void findUnique() {
        myFreqs.clear();
        myWords.clear();
        // romeo.txt
        FileResource resource = new FileResource();

        for (String s : resource.words()) {
            s = s.toLowerCase();
            int idx = myWords.indexOf(s);
            if (idx == -1) {
                myWords.add(s);
                myFreqs.add(1);
            } else {
                int value = myFreqs.get(idx);
                myFreqs.set(idx, value + 1);
            }
        }
    }

    public void tester() {
        findUnique();
        System.out.println("# unique words: " + myWords.size());
        // for (int i = 0; i < myWords.size(); i++) {
        //     System.out.println(myFreqs.get(i) + "  " + myWords.get(i));
        // }
        int idx = findIndexOfMax();
        System.out.println("The word that occurs most often and its count are : "+myWords.get(idx)+" "+myFreqs.get(idx));
       
    }

    public int findIndexOfMax(){
        int idx = -1;
        int mx = Integer.MIN_VALUE;
        for(int i = 0; i < myFreqs.size(); i++){
            if(myFreqs.get(i)>mx){
                mx = myFreqs.get(i);
                idx = i;
            }
        }
        return idx;

    }

}
