package StructuredData.week1;

import edu.duke.FileResource;

public class CaesarCipher {
    public static void main(String[] args) {
        // testCaesar();
        // System.out.println(encryptTwoKey("Rpc ndj xbpvxct axut LXIWDJI iwt xcitgcti PCS rdbejitgh xc ndjg edrzti?", 21,8));
        System.out.println(encrypt("Can you imagine life WITHOUT the internet AND computers in your pocket?", 15));
        // System.out.println(encryptTwoKey("Hfs cpwewloj loks cd Hoto kyg Cyy.", 26-14, 26-24));
    }

    public static String encryptTwoKey(String input, int key1, int key2) {
        StringBuilder sb = new StringBuilder(input.toLowerCase());
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String k1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        String k2 = alphabet.substring(key2) + alphabet.substring(0, key2);
        for(int i=0;i<input.length();i++){
            char curr = input.charAt(i);
            boolean fg= false;
            if(Character.isUpperCase(curr)){
                fg=true;
                curr=Character.toLowerCase(curr);
            }
            int idx = alphabet.indexOf(curr);
            if(idx!=-1){
                char newChar;
                if(i%2==0){
                    newChar=k1.charAt(idx);
                }else{
                    newChar=k2.charAt(idx);
                }
                if(fg){
                    newChar=Character.toUpperCase(newChar);
                }
                sb.setCharAt(i, newChar);
            }
        }
        return sb.toString();

    }

    public static void testCaesar() {
        int key = 17;
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encrypt(message, key);
        System.out.println(encrypted);
        String decrypted = encrypt(encrypted, 26 - key);
        System.out.println(decrypted);
    }

    public static String encrypt(String input, int key) {
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        for (int i = 0; i < encrypted.length(); i++) {
            char curr = encrypted.charAt(i);
            boolean flag = false;
            if (Character.isUpperCase(curr)) {
                flag = true;
                curr = Character.toLowerCase(curr);
            }
            int idx = alphabet.indexOf(curr);
            if (idx != -1) {
                char newChar = shiftedAlphabet.charAt(idx);
                if (flag) {
                    newChar = Character.toUpperCase(newChar);
                }
                encrypted.setCharAt(i, newChar);
            }
        }
        return encrypted.toString();
    }

}
