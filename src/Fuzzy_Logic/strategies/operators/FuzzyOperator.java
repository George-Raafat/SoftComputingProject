package Fuzzy_Logic.strategies.operators;

public interface FuzzyOperator {
    double and(double a, double b);
    double or(double a, double b);
    double not(double a);
}