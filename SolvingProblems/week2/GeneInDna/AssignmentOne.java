package SolvingProblems.week2.GeneInDna;

public class AssignmentOne {
    public String findSimpleGene(String dna) {
        int start = dna.indexOf("ATG");
        if (start == -1) {
            return "";
        }
        int stop = dna.indexOf("TAA", start);
        if (stop < start) {
            return "";
        }
        String result = dna.substring(start, stop + 3);
        if (result.length() % 3 != 0) {
            return "";
        }
        return result;
    }

    public void testSimpleGene() {
        String DNA = "AAATCHTHGTHAATG";
        System.out.println("DNA is " + DNA);
        System.out.println("Gen is " + findSimpleGene(DNA));

        DNA = "AATCGTPADPATGGCAADPATPTAA";
        System.out.println("DNA is " + DNA);
        System.out.println("Gen is " + findSimpleGene(DNA));

        DNA = "CGTATPADPATGCGATAP";
        System.out.println("DNA is " + DNA);
        System.out.println("Gen is " + findSimpleGene(DNA));

        DNA = "TCAACGGCCTCPTAA";
        System.out.println("DNA is " + DNA);
        System.out.println("Gen is " + findSimpleGene(DNA));

        DNA = "ATGADTAA";
        System.out.println("DNA is " + DNA);
        System.out.println("Gen is " + findSimpleGene(DNA));
    }
}
