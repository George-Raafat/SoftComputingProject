package fuzzy_logic.engine;

import fuzzy_logic.antecedents.Antecedent;
import fuzzy_logic.strategies.membership.MembershipFunction;
import fuzzy_logic.strategies.operators.FuzzyOperator;

import java.util.*;

public class MamdaniEnginee {

    private final FuzzyOperator operator;
    private final List<MamdaniRule> rules = new ArrayList<>();

    public MamdaniEnginee(FuzzyOperator operator) {
        this.operator = Objects.requireNonNull(operator);
    }

    public void addRule(MamdaniRule rule) {
        rules.add(Objects.requireNonNull(rule));
    }

    public List<MamdaniRule> getRules() {
        return Collections.unmodifiableList(rules);
    }

    public double evaluate(Map<String, Double> inputs,
                           double minX, double maxX, double step) {

        Map<Double, Double> aggregated = new LinkedHashMap<>();

        for (double x = minX; x <= maxX; x += step) {
            aggregated.put(x, 0.0);
        }

        for (MamdaniRule rule : rules) {
            if (!rule.isEnabled())
                continue;

            double firingStrength = rule.firingStrength(operator, inputs);
            if (firingStrength <= 0)
                continue;

            MembershipFunction mf = rule.getConsequent();

            for (Map.Entry<Double, Double> entry : aggregated.entrySet()) {
                double x = entry.getKey();
                double original = entry.getValue();
                double mu = mf.calculateMembership(x);
                double clipped = Math.min(mu, firingStrength);

                aggregated.put(x, Math.max(original, clipped));
            }
        }

        double num = 0.0;
        double den = 0.0;

        for (Map.Entry<Double, Double> entry : aggregated.entrySet()) {
            double x = entry.getKey();
            double mu = entry.getValue();
            num += x * mu;
            den += mu;
        }

        return (den == 0) ? Double.NaN : num / den;
    }

    public static class MamdaniRule {
        private final Antecedent antecedent;
        private final MembershipFunction consequent;
        private boolean enabled = true;

        public MamdaniRule(Antecedent antecedent, MembershipFunction consequent) {
            this.antecedent = antecedent;
            this.consequent = consequent;
        }

        public double firingStrength(FuzzyOperator op, Map<String, Double> inputs) {
            if (antecedent == null) return 1.0;
            return antecedent.evaluate(op, inputs);
        }

        public MembershipFunction getConsequent() {
            return consequent;
        }

        public boolean isEnabled() { return enabled; }
        public void setEnabled(boolean enabled) { this.enabled = enabled; }
    }
}
