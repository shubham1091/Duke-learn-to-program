package SoftwereDesign.week3.word;

import SoftwereDesign.week3.IMarkovModel;
import edu.duke.FileResource;

public class MarkovWordRunner {
    public static void main(String[] args) {
        MarkovWordRunner mr = new MarkovWordRunner();
        mr.testHashMap();
    }

    public void runModel(IMarkovModel markov, String text, int size) {
        markov.setTraining(text);
        System.out.println("running with " + markov);
        for (int k = 0; k < 3; k++) {
            String st = markov.getRandomText(size);
            printOut(st);
        }
    }

    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setTraining(text);
        markov.setRandom(seed);
        System.out.println("running with " + markov);
        for (int k = 0; k < 3; k++) {
            String st = markov.getRandomText(size);
            printOut(st);
        }
    }

    public void runMarkov() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovWordOne markovWord = new MarkovWordOne();
        runModel(markovWord, st, 120, 139);
    }

    public void runMarkovTwo() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovWordTwo markovWord = new MarkovWordTwo();
        runModel(markovWord, st, 120, 832);
    }

    public void runMarkovWord() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovWord markovWord = new MarkovWord(5);
        runModel(markovWord, st, 50, 844);
    }

    public void testHashMap() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        // String text = "this is a test yes this is really a test yes a test this is wow";
        EfficientMarkovWord ew = new EfficientMarkovWord(2);
        // runModel(ew, st, 50, 371);
        ew.setRandom(65);
        ew.setTraining(st);
    }

    public void compareMethods() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int ord = 2;
        int seed = 42;
        int sz = 100;
        MarkovWord mw = new MarkovWord(ord);
        EfficientMarkovWord ew = new EfficientMarkovWord(ord);
        long start = System.nanoTime();
        runModel(mw, st, sz, seed);
        long end = System.nanoTime();

        long mt = (end - start) / 1000000;
        start = System.nanoTime();
        runModel(ew, st, sz, seed);
        end = System.nanoTime();
        long et = (end - start) / 1000000;

        System.out.println("MarkovWord took " + mt + " ms");
        System.out.println("EfficientMarkovWord took " + et + " ms");
    }

    private void printOut(String s) {
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for (int k = 0; k < words.length; k++) {
            System.out.print(words[k] + " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println();
                psize = 0;
            }
        }
        System.out.println("\n----------------------------------");
    }
}
