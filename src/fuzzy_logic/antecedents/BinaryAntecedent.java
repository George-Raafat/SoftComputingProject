package fuzzy_logic.antecedents;

import fuzzy_logic.strategies.operators.FuzzyOperator;

import java.util.Map;

public class BinaryAntecedent implements Antecedent {
    private final Antecedent left, right;
    private final boolean isAnd; // true => AND, false => OR

    public BinaryAntecedent(Antecedent left, Antecedent right, boolean isAnd) {
        this.left = left;
        this.right = right;
        this.isAnd = isAnd;
    }

    @Override
    public double evaluate(FuzzyOperator op, Map<String, Double> inputs) {
        double a = left.evaluate(op, inputs);
        double b = right.evaluate(op, inputs);
        return isAnd ? op.and(a, b) : op.or(a, b);
    }
}