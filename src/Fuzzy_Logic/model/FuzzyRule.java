package Fuzzy_Logic.model;

import Fuzzy_Logic.strategies.operators.FuzzyOperator;

import java.util.ArrayList;
import java.util.List;

public class FuzzyRule {
    private final List<RuleTerm> antecedents;
    private final RuleTerm consequent; // For Mamdani, consequent is also a Set

    public FuzzyRule(RuleTerm consequent) {
        this.antecedents = new ArrayList<>();
        this.consequent = consequent;
    }

    public void addAntecedent(RuleTerm term) {
        this.antecedents.add(term);
    }

    /**
     * Calculates how strongly this rule applies.
     * @param operator The strategy for AND logic (e.g., Min or Product)
     * @return A value 0.0 - 1.0
     */
    public double getFiringStrength(FuzzyOperator operator) {
        double strength = 1.0;

        for (RuleTerm term : antecedents) {
            if (strength == 1.0) {
                // First iteration
                strength = term.getMembership();
            } else {
                // Combine with previous using AND strategy
                strength = operator.and(strength, term.getMembership());
            }
        }
        return strength;
    }

    public RuleTerm getConsequent() {
        return consequent;
    }

    @Override
    public String toString() {
        return "IF " + antecedents + " THEN " + consequent;
    }
}