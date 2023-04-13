package StructuredData.week1;

public class WordPlay {
    public static void main(String[] args) {
        testEmphasize();
    }

    public static void testEmphasize() {
        String one = "dna ctgaaactga";
        String two = "Mary Bella Abracadab";
        System.out.println(emphasize(one, 'a'));
        System.out.println(emphasize(two, 'a'));
    }

    public static String emphasize(String phrase, char ch) {
        StringBuilder sb = new StringBuilder(phrase);
        for (int i = 0; i < phrase.length(); i++) {
            char c = phrase.charAt(i);
            char u = Character.toLowerCase(c);
            if (c == ch || u == ch) {
                if (i % 2 == 0) {
                    sb.setCharAt(i, '*');
                } else {
                    sb.setCharAt(i, '+');
                }
            }
        }
        return sb.toString();
    }

    public static String replaceVowels(String phrase, char ch) {
        StringBuilder sb = new StringBuilder(phrase);
        for (int i = 0; i < phrase.length(); i++) {
            if (isVowel(phrase.charAt(i))) {
                sb.setCharAt(i, ch);
            }
        }
        return sb.toString();
    }

    public static boolean isVowel(char ch) {
        char c = Character.toLowerCase(ch);
        if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
            return true;
        }
        return false;
    }

}
