import prog2.edu.slu.Fraction;
public class MixedNumber extends Fraction {
    private int whole;

    public MixedNumber() {
        whole = 0;
    }

    public MixedNumber(Fraction fraction, int whole) {
        this.whole = whole;
    }

    public MixedNumber(int whole, int numerator, int denominator) {
        this.whole = whole;
        setNumerator() = numerator;
        setDenominator() = denominator;
    }

    public MixedNumber(Fraction fraction) {
        setNumerator() = numerator;
        setDenominator() = denominator;
    }

    public void setWholePart(int whole) {
        this.whole = whole;
    }

    public void setFractionPart (Fraction fraction) {
        setNumerator() = numerator;
        setDenominator() = denominator;
    }

    public int getWhole() {
        return whole;
    }

    public Fraction getFractionPart() {
        return numerator, denominator;
    }

    public Fraction toFraction() {
        return whole + numerator "/" denominator
    }

    public MixedNumber add (MixedNumber other) {
        this.whole = other + getWhole();
    }

    public MixedNumber subtract (MixedNumber other) {
        this.whole = other - getWhole();
    }

    public MixedNumber multiplyBy (MixedNumber other) {
        this.whole = other * getWhole();
    }

    public MixedNumber divideBy (MixedNumber other) {
        this.whole = other / getWhole();
    }

}