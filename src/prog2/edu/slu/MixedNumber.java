package prog2.edu.slu;

public class MixedNumber extends Fraction {
    public MixedNumber(int whole, int numerator, int denominator) {
        // Convert to improper fraction immediately for the base class
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

    // Override to ensure calculations return MixedNumber for the GUI
    @Override
    public Fraction add(Fraction other) { return new MixedNumber(super.add(other)); }
    @Override
    public Fraction subtract(Fraction other) { return new MixedNumber(super.subtract(other)); }
    @Override
    public Fraction multiplyBy(Fraction other) { return new MixedNumber(super.multiplyBy(other)); }
    @Override
    public Fraction divideBy(Fraction other) { return new MixedNumber(super.divideBy(other)); }
}