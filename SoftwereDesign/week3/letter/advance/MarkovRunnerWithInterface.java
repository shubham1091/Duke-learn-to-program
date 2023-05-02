package SoftwereDesign.week3.letter.advance;

import SoftwereDesign.week3.IMarkovModel;
import edu.duke.FileResource;

public class MarkovRunnerWithInterface {
    public static void main(String[] args) {
        MarkovRunnerWithInterface mi = new MarkovRunnerWithInterface();
        mi.testHashMap();
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
        int size = 200;
        int seed = 24;

        MarkovZ mz = new MarkovZ();
        runModel(mz, st, size, seed);

        MarkovO mOne = new MarkovO();
        runModel(mOne, st, size, seed);

        MarkovM mThree = new MarkovM(3);
        runModel(mThree, st, size, seed);

        MarkovF mFour = new MarkovF();
        runModel(mFour, st, size, seed);

    }

    public void testHashMap() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 150;
        int seed = 531;
        int ord = 5;
        EfficientMarkovModel mod = new EfficientMarkovModel(ord);
        runModel(mod, st, size, seed);
        mod.printHashMapInfo();
    }

    public void compareMethods() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 1000;
        int seed = 42;

        long startM = System.nanoTime();
        MarkovM mr = new MarkovM(2);
        runModel(mr, st, size, seed);
        long endM = System.nanoTime();

        long startE = System.nanoTime();
        EfficientMarkovModel em = new EfficientMarkovModel(2);
        runModel(em, st, size, seed);
        long endE = System.nanoTime();

        System.out.println("Markov model took " + (endM - startM));
        System.out.println("Efficient markov model took " + (endE - startE));
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
