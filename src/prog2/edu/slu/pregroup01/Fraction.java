package prog2.edu.slu.pregroup01;

public class Fraction {
    protected int numerator;
    protected int denominator;

    public Fraction() {
        this(0, 1);
    }
    public Fraction(int wholeNumVal) {
        this(wholeNumVal, 1);
    }

    public Fraction(int numerator, int denominator) {
        if (denominator == 0) throw new ArithmeticException("Division by zero");
        this.numerator = numerator;
        this.denominator = denominator;
        simplify();
    }

    public Fraction add(Fraction other) {
        int num = this.numerator * other.denominator + other.numerator * this.denominator;
        int den = this.denominator * other.denominator;
        return new Fraction(num, den);
    }

    public Fraction subtract(Fraction other) {
        int num = this.numerator * other.denominator - other.numerator * this.denominator;
        int den = this.denominator * other.denominator;
        return new Fraction(num, den);
    }

    public Fraction multiplyBy(Fraction other) {
        return new Fraction(this.numerator * other.numerator, this.denominator * other.denominator);
    }

    public Fraction divideBy(Fraction other) {
        return new Fraction(this.numerator * other.denominator, this.denominator * other.numerator);
    }

    private void simplify() {
        if (numerator == 0) {
            denominator = 1;
            return;
        }
        int common = gcd(Math.abs(numerator), Math.abs(denominator));
        numerator /= common;
        denominator /= common;
        if (denominator < 0) {
            numerator = -numerator;
            denominator = -denominator;
        }
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public double toDouble() { return (double) numerator / denominator; }
    public String toString() { return (denominator == 1) ? String.valueOf(numerator) : numerator + "/" + denominator; }

    public int getNumerator() { return numerator; }
    public void setNumerator(int numerator) { this.numerator = numerator; simplify(); }
    public int getDenominator() { return denominator; }
    public void setDenominator(int denominator) {
        if (denominator == 0) throw new ArithmeticException("Division by zero");
        this.denominator = denominator;
        simplify();
    }
}