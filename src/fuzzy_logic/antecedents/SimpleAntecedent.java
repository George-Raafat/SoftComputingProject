package fuzzy_logic.antecedents;

import fuzzy_logic.strategies.membership.MembershipFunction;
import fuzzy_logic.strategies.operators.FuzzyOperator;

import java.util.Map;

public class SimpleAntecedent implements Antecedent {
    private final String variable;
    private final MembershipFunction set;

    public SimpleAntecedent(String variable, MembershipFunction set) {
        this.variable = variable;
        this.set = set;
    }

    @Override
    public double evaluate(FuzzyOperator op, Map<String, Double> inputs) {
        Double x = inputs.get(variable);
        if (x == null) throw new IllegalArgumentException("Missing input: " + variable);
        return set.calculateMembership(x);
    }
}
