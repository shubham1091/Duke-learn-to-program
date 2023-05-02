package SoftwereDesign.week3.letter.simple;

import edu.duke.FileResource;

public class Tester {
    public static void main(String[] args) {
        testGetFollowsWithFile();
    }

    public static void testGetFollows() {
        MarkovOne mv = new MarkovOne();
        String st = "this is a test yes this is a test.";
        mv.setTraining(st);
        System.out.println(mv.getFollows("es"));
    }

    public static void testGetFollowsWithFile() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        MarkovOne mv = new MarkovOne();
        mv.setTraining(st);
        System.out.println(mv.getFollows("he").size());
    }

}
