package StructuredData.week2;

import java.util.ArrayList;

import edu.duke.FileResource;

public class CharactersInPlay {
    private ArrayList<String> characters;
    private ArrayList<Integer> counts;

    public CharactersInPlay() {
        characters = new ArrayList<String>();
        counts = new ArrayList<Integer>();
    }

    public static void main(String[] args) {
        CharactersInPlay play = new CharactersInPlay();
        play.tester();
    }

    public void update(String person) {
        if (person.equals(person.toUpperCase())) {

            int idx = characters.indexOf(person);
            if (idx == -1) {
                characters.add(person);
                counts.add(1);
            } else {
                int val = counts.get(idx);
                counts.set(idx, val + 1);
            }
        }
    }

    public void findAllCharacters() {
        characters.clear();
        counts.clear();
        FileResource fr = new FileResource();
        for (String s : fr.lines()) {
            // s = s.toLowerCase();
            int lsp = s.indexOf('.');
            if (lsp != -1) {
                String name = s.substring(0, lsp);
                update(name);
            }
        }
    }

    public void tester() {
        findAllCharacters();
        charactersWithNUmParts(50, 100);
        int mos = Integer.MIN_VALUE;
        for (int i = 0; i < counts.size(); i++) {
            mos = Math.max(mos, counts.get(i));
        }
        int idx = counts.indexOf(mos);
        System.err.println(characters.get(idx) + "  " + counts.get(idx));
        
    }

    public void charactersWithNUmParts(int num1, int num2) {
        for (int i = 0; i < counts.size(); i++) {
            int val = counts.get(i);
            if (val >= num1 && val <= num2) {
                System.out.println(characters.get(i) + " " + val);
            }
        }
    }
}
