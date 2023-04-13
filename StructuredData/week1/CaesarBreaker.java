package StructuredData.week1;

import edu.duke.FileResource;

public class CaesarBreaker {
    public static void main(String[] args) {
        // test_decrypt();
        test_decryptTwoKeys();
    }

    public static String decryptTwoKeys(String encrypted) {
        String message1 = halfOfString(encrypted, 0);
        String message2 = halfOfString(encrypted, 1);
        StringBuilder theAnswer = new StringBuilder(encrypted);
        int key1 = getKey(message1);
        // int key1 = 2;
        int key2 = getKey(message2);
        // int key2 = 20;

        String d_message1 = CaesarCipher.encrypt(message1, (26 - key1));
        String d_message2 = CaesarCipher.encrypt(message2, (26 - key2));

        // build up the final answer

        for (int k = 0; k < (message1.length()); k++) {
            theAnswer.setCharAt((2 * k), d_message1.charAt(k));
        }

        for (int k = 0; k < (message2.length()); k++) {
            theAnswer.setCharAt((2 * k) + 1, d_message2.charAt(k));
        }

        System.out.println(key1 + " " + key2 + " " + theAnswer.toString());

        return theAnswer.toString();
    }

    public static void test_decryptTwoKeys() {
        // FileResource resource = new FileResource("data\\Breaking_cipher\\mysteryTwoKeysPractice.txt");
        FileResource resource = new FileResource("data\\Breaking_cipher\\mysteryTwoKeysQuiz.txt");
        // FileResource resource = new FileResource("data\\Breaking_cipher\\wordsLotsOfEs.txt");
        String message = resource.asString();
        // String message = "Akag tjw Xibhr awoa aoee xakex znxag xwko";
        // String message = "Top ncmy qkff vi vguv vbg ycpx";
        // String message = "Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!";
        String d_TwoKeyMessage = decryptTwoKeys(message);

        // System.out.println(message);
        System.out.println(d_TwoKeyMessage);
    }

    public static String decrypt(String encrypted) {
        int key = getKey(encrypted);
        return CaesarCipher.encrypt(encrypted, (26 - key));
    }

    public static int maxIndex(int[] values) {
        int maxLength = 0;
        int indexOfMax = 0;

        for (int k = 0; k < values.length; k++) {
            if (values[k] > maxLength) {
                maxLength = values[k];
                indexOfMax = k;
            }
        }
        return indexOfMax;
    }

    public static int[] countLetters(String message) {
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];

        for (int k = 0; k < message.length(); k++) {
            char ch = Character.toLowerCase(message.charAt(k));
            int dex = alph.indexOf(ch);
            if (dex != -1) {
                counts[dex] += 1;
            }
        }
        return counts;
    }

    public static void test_countLetters() {
        FileResource resource = new FileResource("data\\common_words\\romeo.txt");
        // FileResource resource = new FileResource("data\\Breaking_cipher\\wordsLotsOfEs.txt");
        String message = resource.asString();

        int[] counts = countLetters(message);
        System.out.println("Most common length is " + maxIndex(counts));
    }

    public static void test_decrypt() {
        FileResource resource = new FileResource("data\\Breaking_cipher\\smallHamlet.txt");
        // FileResource resource = new FileResource("data\\Breaking_cipher\\wordsLotsOfEs.txt");
        String message = resource.asString();

        String encrypted = CaesarCipher.encrypt(message, 20);
        String decrypted = decrypt(encrypted);
        System.out.println(encrypted + "\n" + decrypted);
    }

    public static String halfOfString(String message, int start) {
        String halfMessage = "";
        for (int i = start; i < message.length(); i = i + 2) {
            halfMessage = halfMessage + message.charAt(i);
        }
        return halfMessage;
    }

    public static void test_halfOfString() {
        FileResource resource = new FileResource("data\\Breaking_cipher\\smallHamlet.txt");
        // FileResource resource = new FileResource("data\\Breaking_cipher\\wordsLotsOfEs.txt");
        String message = resource.asString();
        System.out.println(message);
        // System.out.println(halfOfString(message, 0));
        // System.out.println(halfOfString(message, 1));

        String encrypted = CaesarCipher.encrypt(halfOfString(message, 0), 20);
        String decrypted = decrypt(encrypted);
        System.out.println(encrypted);
        System.out.println(decrypted);
    }

    public static int getKey(String e_message) {
        int[] freqs = countLetters(e_message);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if (maxDex < 4) {
            dkey = 26 - (4 - maxDex);
        }
        return dkey;
    }

    public static void test_getKey() {
        // FileResource resource = new FileResource("data/smallHamlet.txt");
        FileResource resource = new FileResource("data\\Breaking_cipher\\wordsLotsOfEs.txt");
        String message = resource.asString();
        String e_message = CaesarCipher.encrypt(message, 5);
        System.out.println(getKey(e_message) + " is the key for: " + message + " to: " + e_message);

    }

}
