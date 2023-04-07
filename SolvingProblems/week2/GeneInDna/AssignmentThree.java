package SolvingProblems.week2.GeneInDna;

public class AssignmentThree {
    public boolean twoOccurrences(String a, String b) {
        int length = a.length();
        int first = b.indexOf(a);
        int second = b.indexOf(a, first + length);
        if (second > 0) {
            return true;
        }
        return false;
    }

    public String lastPart(String a, String b) {
        int idx = b.indexOf(a);
        if (idx < 0) {
            return b;
        }
        return b.substring(idx);
    }

    public void testing() {
        System.out.println(twoOccurrences("by", "A story by Abby Long"));
        System.out.println(twoOccurrences("a", "banana"));
        System.out.println(twoOccurrences("atg", "ctgtatgta"));
        String a = "an";
        String b = "banana";
        System.out.println("The part of the string after " + a + " in " + b + " is " + lastPart(a, b));
        a = "zoo";
        b = "forest";
        System.out.println("The part of the string after " + a + " in " + b + " is " + lastPart(a, b));
    }
}
