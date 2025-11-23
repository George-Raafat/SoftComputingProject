package fuzzy_logic.strategies.operators;

public interface FuzzyOperator {
    double and(double a, double b);

    double or(double a, double b);

    double not(double a);
}

// we need to add more operators