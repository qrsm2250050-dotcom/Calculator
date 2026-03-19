package prog2.edu.slu;

import prog2.edu.slu.pregroup01.Fraction;

import java.util.Scanner;

public class FractionTester {
    public static Scanner kbd = new Scanner (System.in);
    public static void main(String[] args) {
        System.out.print("How many fractions to operate?: ");
        int count = Integer.parseInt(kbd.nextLine());
        Fraction[] fractions = new Fraction[count];
        Fraction result = new Fraction();

        for (int i = 0; i < count; i++) {
            fractions[i] = new Fraction();
            System.out.print("Input numerator: ");
            fractions[i].setNumerator(Integer.parseInt(kbd.nextLine()));
            System.out.print("Input denominator: ");
            fractions[i].setDenominator(Integer.parseInt(kbd.nextLine()));
            System.out.print("Input whole number: ");
            int whole = Integer.parseInt(kbd.nextLine());
            if (whole != 0) {
                fractions[i] = new MixedNumber(whole, fractions[i].getNumerator(), fractions[i].getDenominator());
            }
        }

        System.out.println("What operation?");
        System.out.println("1. Addition");
        System.out.println("2. Subtraction");
        System.out.println("3. Multiplication");
        System.out.println("4. Division");
        int choice = Integer.parseInt(kbd.nextLine());

        switch (choice) {
            case 1 -> {
                for (int i = 0; i < fractions.length-1; i++) {
                    result = fractions[i].add(fractions[i+1]);
                }
                System.out.println(result);
            }
            case 2 -> {
                for (int i = 0; i < fractions.length-1; i++) {
                    result = fractions[i].subtract(fractions[i+1]);
                }
                System.out.println(result);
            }
            case 3 -> {
                for (int i = 0; i < fractions.length-1; i++) {
                    result = fractions[i].multiplyBy(fractions[i+1]);
                }
                System.out.println(result);
            }
            case 4 -> {
                for (int i = 0; i < fractions.length-1; i++) {
                    result = fractions[i].divideBy(fractions[i+1]);
                }
                System.out.println(result);
            }
        }
        System.out.println("test");
    }
}