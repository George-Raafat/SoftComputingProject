package fuzzy_logic.antecedents;

import fuzzy_logic.strategies.operators.FuzzyOperator;

import java.util.Map;

public interface Antecedent {
    double evaluate(FuzzyOperator op, Map<String, Double> inputs);
}
