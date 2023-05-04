package StructuredData.week4;

import java.util.HashMap;
import java.util.HashSet;

import edu.duke.FileResource;

public class VigenereBreaker {
    public static void main(String[] args) {
        // testSliceString();
        // testTryKeyLength();
        breakVigenere();
        // tryKeyLength2();
    }

    public static void breakVigenere() {
        // Read in the message and store it as a String
        FileResource fr = new FileResource();
        String message = fr.asString();

        // Create a HashMap to store the dictionaries for each language
        HashMap<String, HashSet<String>> languages = new HashMap<String, HashSet<String>>();

        // Read in the dictionaries and store them in the HashMap
        String[] languageNames = { "Danish", "Dutch", "English", "French", "German", "Italian", "Portuguese",
                "Spanish" };
        for (String languageName : languageNames) {
            FileResource dictionaryResource = new FileResource("StructuredData/data/vigenere/dictionaries/" + languageName);
            HashSet<String> dictionary = readDictionary(dictionaryResource);
            languages.put(languageName, dictionary);
            System.out.println("Finished reading " + languageName + " dictionary.");
        }

        // Call breakForAllLangs with the message and the HashMap of dictionaries
        breakForAllLangs(message, languages);
    }

    public static HashSet<String> readDictionary(FileResource fr) {
        HashSet<String> words = new HashSet<>();
        for (String line : fr.lines()) {
            String word = line.toLowerCase();
            words.add(word);
        }
        return words;
    }

    public static void breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages) {
        int maxValidWords = 0;
        String bestLanguage = "";
        String decryptedMessage = "";

        for (String language : languages.keySet()) {
            HashSet<String> dictionary = languages.get(language);
            String decrypted = breakForLanguage(encrypted, dictionary);
            int validWords = countWords(decrypted, dictionary);
            if (validWords > maxValidWords) {
                maxValidWords = validWords;
                bestLanguage = language;
                decryptedMessage = decrypted;
            }
        }

        System.out.println("Best language: " + bestLanguage);
        System.out.println("Valid words: " + maxValidWords);
        System.out.println("Decrypted message:");
        System.out.println(decryptedMessage);
    }

    public static String breakForLanguage(String encrypted, HashSet<String> dictionary) {
        int maxValidWords = 0;
        String decryptedMsg = "";
        char mostCommonChar = mostCommonCharIn(dictionary);

        for (int keyLength = 1; keyLength <= 100; keyLength++) {
            int[] keys = tryKeyLength(encrypted, keyLength, mostCommonChar);
            VigenereCipher vc = new VigenereCipher(keys);
            String decrypted = vc.decrypt(encrypted);
            int validWords = countWords(decrypted, dictionary);

            if (validWords > maxValidWords) {
                maxValidWords = validWords;
                decryptedMsg = decrypted;
            }
        }

        return decryptedMsg;
    }

    public static char mostCommonCharIn(HashSet<String> dictionary) {
        HashMap<Character, Integer> charFreq = new HashMap<Character, Integer>();
        for (String word : dictionary) {
            for (char ch : word.toCharArray()) {
                ch = Character.toLowerCase(ch);
                if (Character.isLetter(ch)) {
                    charFreq.put(ch, charFreq.getOrDefault(ch, 0) + 1);
                }
            }
        }
        char mostCommonChar = 'e';
        int maxFreq = 0;
        for (char ch : charFreq.keySet()) {
            int freq = charFreq.get(ch);
            if (freq > maxFreq) {
                maxFreq = freq;
                mostCommonChar = ch;
            }
        }
        return mostCommonChar;
    }

    public static int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        for (int i = 0; i < klength; i++) {
            String sliced = sliceString(encrypted, i, klength);
            CaesarCracker cracker = new CaesarCracker(mostCommon);
            key[i] = cracker.getKey(sliced);
        }
        return key;
    }

    public static String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder sliced = new StringBuilder();
        for (int i = whichSlice; i < message.length(); i += totalSlices) {
            sliced.append(message.charAt(i));
        }
        return sliced.toString();
    }

    public static int countWords(String message, HashSet<String> dictionary) {
        String[] words = message.toLowerCase().split("\\W+");
        int count = 0;
        for (String word : words) {
            if (dictionary.contains(word)) {
                count++;
            }
        }
        return count;
    }

    public static void tryKeyLength2() {
        FileResource fr = new FileResource();
        String encrypted = fr.asString();

        FileResource dictFile = new FileResource("StructuredData/data/vigenere/dictionaries/English");
        HashSet<String> dictionary = readDictionary(dictFile);

        int[] key = tryKeyLength(encrypted, 38, 'e');
        VigenereCipher cipher = new VigenereCipher(key);
        String decrypted = cipher.decrypt(encrypted);

        int count = countWords(decrypted, dictionary);

        System.out.println("Valid words found: " + count);
    }

}