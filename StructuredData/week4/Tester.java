package StructuredData.week4;

import edu.duke.FileResource;

public class Tester {
    public static void main(String[] args) {
        CaesarCipher cs = new CaesarCipher(2);
        FileResource fr = new FileResource();
        String ck  = fr.asString();

        String ss = cs.encrypt(ck);
        System.out.println(ss);
        System.out.println(cs.decrypt(ss));
    }

}
