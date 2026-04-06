package prog2.edu.slu;

import prog2.edu.slu.pregroup01.Fraction;

public class MixedNumber extends Fraction {
    public MixedNumber(int whole, int numerator, int denominator) {
        super((Math.abs(whole) * denominator + numerator) * (whole < 0 ? -1 : 1), denominator);
    }

    public MixedNumber(Fraction f) {
        super(f.getNumerator(), f.getDenominator());
    }

    @Override
    public String toString() {
        int absNum = Math.abs(numerator);
        int whole = absNum / denominator;
        int remNum = absNum % denominator;

        String sign = (numerator < 0) ? "-" : "";

        if (remNum == 0) return sign + whole;
        if (whole == 0) return sign + remNum + "/" + denominator;
        return sign + whole + "_" + remNum + "/" + denominator;
    }

    @Override
    public Fraction add(Fraction other) { return new MixedNumber(super.add(other)); }
    @Override
    public Fraction subtract(Fraction other) { return new MixedNumber(super.subtract(other)); }
    @Override
    public Fraction multiplyBy(Fraction other) { return new MixedNumber(super.multiplyBy(other)); }
    @Override
    public Fraction divideBy(Fraction other) { return new MixedNumber(super.divideBy(other)); }
}