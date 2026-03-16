public class Fraction {
    private int numerator;
    private int denominator;

    public Fraction() {
        numerator = 0;
        denominator = 0;
    }

    public Fraction(int wholeNumVal) {
        numerator = 1;
        denominator = 1;
    }

    public Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
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
        return numerator "/" denominator;
    }

    public double toDouble() {
        return numerator/denominator;
    }

    public Fraction add (Fraction other) {

    }

    public Fraction multiplyBy (Fraction other) {

    }


}