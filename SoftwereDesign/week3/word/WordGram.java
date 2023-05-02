package SoftwereDesign.week3.word;

public class WordGram {

    private String[] myWords;
    // private int myHash;

    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
    }

    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt " + index);
        }
        return myWords[index];
    }

    public int length() {
        return myWords.length;
    }

    public String toString() {
        StringBuilder ret = new StringBuilder("");
        for (int k = 0; k < myWords.length; k++) {
            ret.append(myWords[k] + " ");
        }
        return ret.toString().trim();
    }

    public boolean equals(Object o) {
        WordGram other = (WordGram) o;
        if (this.length() != other.length()) {
            return false;
        }
        for (int i = 0; i < myWords.length; i++) {
            if (!myWords[i].equals(other.wordAt(i))) {
                return false;
            }
        }
        return true;

    }

    public WordGram shiftAdd(String word) {
        String[] shiftedWords = new String[myWords.length];
        System.arraycopy(myWords, 1, shiftedWords, 0, myWords.length - 1);
        shiftedWords[myWords.length - 1] = word;
        return new WordGram(shiftedWords, 0, myWords.length);
    }

    public int hashCode() {
        return toString().hashCode();
    }
}