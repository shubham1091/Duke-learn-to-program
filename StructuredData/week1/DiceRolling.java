package StructuredData.week1;

import java.util.Random;
import java.util.Scanner;

public class DiceRolling {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        simulate(num);
        sc.close();
    }

    public static void simulate(int rolls) {
        Random rand = new Random();
        int[] counts = new int[13];
        for (int i = 0; i < rolls; i++) {
            int d1 = rand.nextInt(6) + 1;
            int d2 = rand.nextInt(6) + 1;
            counts[d1 + d2]++;
        }
        for (int i = 2; i <= 12; i++) {
            System.out.println(i + "'s =\t" + counts[i] + "\t" + 100.0 * counts[i] / rolls);
        }
    }

    public static void simpleSimulate(int rolls) {
        Random rand = new Random();
        int two = 0;
        int twelves = 0;
        for (int i = 0; i < rolls; i++) {
            int d1 = rand.nextInt(6) + 1;
            int d2 = rand.nextInt(6) + 1;
            if (d1 + d2 == 2) {
                two++;
            } else if (d1 + d2 == 12) {
                twelves++;
            }
        }
        System.out.println("2's=\t" + two + "\t" + 100.0 * two / rolls);
        System.out.println("12's=\t" + twelves + "\t" + 100.0 * twelves / rolls);
    }

}
