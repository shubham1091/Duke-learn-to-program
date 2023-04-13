package StructuredData.week1;

public class CharacterDemo {
    public static void main(String[] args) {
        // digitTest();
        conversionTest();
    }

    public static void digitTest() {
        String test = "ABCabc0123456789';#!";
        for (int k = 0; k < test.length(); k++) {
            char ch = test.charAt(k);
            if (Character.isDigit(ch)) {
                System.out.println(ch + " is a digit");
            }
            if (Character.isAlphabetic(ch)) {
                System.out.println(ch + " is a alphabetic");
            }
        }
    }

    public static void conversionTest() {
        String test = "ABCabc0123456789';#!";
        for (int k = 0; k < test.length(); k++) {
            char ch = test.charAt(k);
            char uch = Character.toUpperCase(ch);
            char lch = Character.toLowerCase(ch);
            System.out.println(ch + " " + uch + " " + lch);
        }
    }

}
