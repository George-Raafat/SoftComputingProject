package Fuzzy_Logic.strategies.operators;

public class MinMaxOperator implements FuzzyOperator {
    @Override
    public double and(double a, double b) {
        return Math.min(a, b);
    }

    @Override
    public double or(double a, double b) {
        return Math.max(a, b);
    }

    @Override
    public double not(double a) {
        return 1.0 - a;
    }
}