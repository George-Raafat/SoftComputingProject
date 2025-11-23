package fuzzy_logic.antecedents;

import fuzzy_logic.strategies.operators.FuzzyOperator;

import java.util.Map;

public class NotAntecedent implements Antecedent {
    private final Antecedent inner;

    public NotAntecedent(Antecedent inner) {
        this.inner = inner;
    }

    @Override
    public double evaluate(FuzzyOperator op, Map<String, Double> inputs) {
        return 1.0 - inner.evaluate(op, inputs);
    }
}
