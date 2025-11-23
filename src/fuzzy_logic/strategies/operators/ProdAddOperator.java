package fuzzy_logic.strategies.operators;

public class ProdAddOperator implements FuzzyOperator {
    @Override
    public double and(double a, double b) {
        return a * b;
    }

    @Override
    public double or(double a, double b) {
        return a + b - a * b;
    }

    @Override
    public double not(double a) {
        return 1.0 - a;
    }
}
