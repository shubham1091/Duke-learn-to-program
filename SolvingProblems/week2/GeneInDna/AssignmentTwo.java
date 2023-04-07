package SolvingProblems.week2.GeneInDna;

public class AssignmentTwo {
    public String findSimpleGene(String dna, String startcodon, String stopcodon) {
        String newdna = dna.toLowerCase();
        int start = newdna.indexOf(startcodon);
        if (start == -1) {
            return "";
        }
        int stop = newdna.indexOf(stopcodon, start);
        if (stop < start) {
            return "";
        }
        String result = newdna.substring(start, stop + 3);
        if (result.length() % 3 != 0) {
            return "";
        }
        return result.toUpperCase();
    }

    public void testSimpleGene() {
        String DNA = "AAATCHTHGTHAATG";
        System.out.println("DNA is " + DNA);
        System.out.println("Gen is " + findSimpleGene(DNA, "atg", "taa"));

        DNA = "AATCGTPADPATGGCAADPATPTAA";
        System.out.println("DNA is " + DNA);
        System.out.println("Gen is " + findSimpleGene(DNA, "atg", "taa"));

        DNA = "CGTATPADPATGCGATAP";
        System.out.println("DNA is " + DNA);
        System.out.println("Gen is " + findSimpleGene(DNA, "atg", "taa"));

        DNA = "TCAACGGCCTCPTAA";
        System.out.println("DNA is " + DNA);
        System.out.println("Gen is " + findSimpleGene(DNA, "atg", "taa"));

        DNA = "ATGADTAA";
        System.out.println("DNA is " + DNA);
        System.out.println("Gen is " + findSimpleGene(DNA, "atg", "taa"));
    }
}
