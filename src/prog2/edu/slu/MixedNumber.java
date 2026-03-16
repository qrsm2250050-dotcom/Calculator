package prog2.edu.slu;

import prog2.edu.slu.pregroup01.Fraction;

public class MixedNumber extends Fraction {
    private int whole;
    private Fraction fractionPart;

    public MixedNumber() {
        whole = 0;
        fractionPart = new Fraction(0,1);
    }

    public MixedNumber(int whole, Fraction fraction) {
        this.whole = whole;
        this.fractionPart = fraction;
    }

    public MixedNumber(int whole, int numerator, int denominator) {
        this.whole = whole;
        fractionPart = new Fraction(numerator, denominator);
    }

    public MixedNumber(Fraction fraction) {
        int num = fraction.getNumerator();
        int den = fraction.getDenominator();

        whole = num / den;
        int remainder = num % den;

        fractionPart = new Fraction(remainder, den);
    }

    public void setWholePart(int whole) {
        this.whole = whole;
    }

    public void setFractionPart (Fraction fraction) {
        this.fractionPart = fraction;
    }

    public int getWhole() {
        return whole;
    }

    public Fraction getFractionPart() {
        return fractionPart;
    }

    public Fraction toFraction() {
        int num = whole * fractionPart.getDenominator() + fractionPart.getNumerator();
        int den = fractionPart.getDenominator();

        return new Fraction(num, den);
    }

    public MixedNumber add(MixedNumber other) {
        return (MixedNumber) add((Fraction) other);
    }

    public MixedNumber add(int wholeNumber) {
        MixedNumber temp = new MixedNumber(wholeNumber, new Fraction(0,1));
        return add(temp);
    }

    @Override
    public Fraction subtract(Fraction other) {
        Fraction op1 = this.toFraction();
        Fraction op2;

        if (other instanceof MixedNumber)
            op2 = ((MixedNumber) other).toFraction();
        else
            op2 = other;

        Fraction result = op1.subtract(op2);
        return new MixedNumber(result);
    }

    public MixedNumber subtract(MixedNumber other) {
        return (MixedNumber) subtract((Fraction) other);
    }

    @Override
    public Fraction multiplyBy(Fraction other) {

        Fraction op1 = this.toFraction();
        Fraction op2;

        if (other instanceof MixedNumber)
            op2 = ((MixedNumber) other).toFraction();
        else
            op2 = other;

        Fraction result = op1.multiplyBy(op2);

        return new MixedNumber(result);
    }

    public MixedNumber multiplyBy(MixedNumber other) {
        return (MixedNumber) multiplyBy((Fraction) other);
    }

    @Override
    public Fraction divideBy(Fraction other) {

        Fraction op1 = this.toFraction();
        Fraction op2;

        if (other instanceof MixedNumber)
            op2 = ((MixedNumber) other).toFraction();
        else
            op2 = other;

        Fraction result = op1.divideBy(op2);

        return new MixedNumber(result);
    }

    public MixedNumber divideBy(MixedNumber other) {
        return (MixedNumber) divideBy((Fraction) other);
    }

    @Override
    public double toDouble() {
        return whole + fractionPart.toDouble();
    }

    @Override
    public String toString() {

        int num = fractionPart.getNumerator();
        int den = fractionPart.getDenominator();

        if (num == 0)
            return String.valueOf(whole);

        if (whole == 0)
            return num + "/" + den;

        if (den == 1)
            return String.valueOf(whole + num);

        return whole + " " + num + "/" + den;
    }

}
