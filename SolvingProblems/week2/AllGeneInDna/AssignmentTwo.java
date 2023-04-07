package SolvingProblems.week2.AllGeneInDna;

public class AssignmentTwo {
    public static void main(String[] args) {
        testHowMany();
    }
    public static void testHowMany() {
        System.out.println(howMany("GAA", "ATGAACGAATTGAATC"));
        System.out.println(howMany("AA", "ATAAAA"));
    }

    public static int howMany(String a, String b) {
        int count = 0;
        int idx = 0;
        while (true) {
            if (b.indexOf(a, idx) == -1) {
                break;
            } else {
                count++;
                idx = b.indexOf(a, idx) + a.length();
            }
        }
        return count;
    }

}
