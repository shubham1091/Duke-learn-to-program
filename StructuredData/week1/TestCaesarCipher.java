package StructuredData.week1;

import edu.duke.FileResource;

public class TestCaesarCipher {
    public static void main(String[] args) {
        simpleTests();
    }
    private static int maxIndex(int[] values) {
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

    private static int[] countLetters(String message) {
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

    private static int getKey(String e_message) {
        int[] freqs = countLetters(e_message);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if (maxDex < 4) {
            dkey = 26 - (4 - maxDex);
        }
        return dkey;
    }

    public static void simpleTests() {
        FileResource resource = new FileResource("StructuredData/data/Breaking_cipher/smallHamlet.txt");
        String message = resource.asString();
        CaesarCipher2 cc2 = new CaesarCipher2(18);
        String encrypted = cc2.encrypt(message);
        String decrypted = cc2.decrypt(encrypted);
        System.out.println(message);
        System.out.println(encrypted);
        System.out.println(decrypted);
        String theAnswer = breakCaesarCipher(encrypted);
        System.out.println(theAnswer);
    }

    public static String breakCaesarCipher(String e_message) {
        int foundKey = getKey(e_message);
        System.out.println("The key found is " + foundKey);
        CaesarCipher2 cc = new CaesarCipher2(26 - foundKey);
        String theAnswer = cc.encrypt(e_message);
        return theAnswer;
    }
}
