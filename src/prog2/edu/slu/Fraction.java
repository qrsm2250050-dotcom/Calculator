package prog2.edu.slu;

public class Fraction {
    protected int numerator;
    protected int denominator;

    public Fraction() {
        numerator = 0;
        denominator = 1;
    }

    public Fraction(int wholeNumVal) {
        numerator = wholeNumVal;
        denominator = 1;
    }

    public Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
        simplify();
    }

    public void setNumerator(int num) {
        numerator = num;
    }

    public int getNumerator() {
        return numerator;
    }

    public void setDenominator(int den) {
        denominator = den;
    }

    public int getDenominator(){
        return denominator;
    }

    public String toString() {
        return numerator + "/" + denominator;
    }

    public double toDouble() {
        return (double) numerator/denominator;
    }

    public Fraction add (Fraction other) {
        int num = numerator * other.denominator + other.numerator * denominator;
        int den = denominator * other.denominator;

        return new Fraction(num, den);
    }

    public Fraction subtract(Fraction other) {
        int num = numerator * other.denominator - other.numerator * denominator;
        int den = denominator * other.denominator;

        return new Fraction(num, den);
    }

    public Fraction multiplyBy (Fraction other) {
        int num = numerator * other.numerator;
        int den = denominator * other.denominator;

        return new Fraction (num, den);
    }

    public Fraction divideBy(Fraction other) {

        int num = numerator * other.denominator;
        int den = denominator * other.numerator;

        return new Fraction(num, den);
    }

    private void simplify() {

        int gcd = gcd(Math.abs(numerator), Math.abs(denominator));

        numerator /= gcd;
        denominator /= gcd;
    }

    private int gcd(int a, int b) {

        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }

        return a;
    }

}
