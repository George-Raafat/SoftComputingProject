package fuzzy_logic.engine;

import fuzzy_logic.antecedents.Antecedent;
import fuzzy_logic.strategies.operators.FuzzyOperator;

import java.util.*;

public class SugenoEngine {

    private final FuzzyOperator operator;
    private final List<SugenoRule> rules = new ArrayList<>();

    public SugenoEngine(FuzzyOperator operator) {
        this.operator = Objects.requireNonNull(operator);
    }

    public void addRule(SugenoRule rule) {
        rules.add(Objects.requireNonNull(rule));
    }

    public List<SugenoRule> getRules() {
        return Collections.unmodifiableList(rules);
    }

    public double evaluate(Map<String, Double> inputs) {
        double num = 0.0;
        double den = 0.0;

        for (SugenoRule r: rules) {
            if (!r.isEnabled()) {
                continue;
            }

            double firing = r.firingStrength(operator, inputs);

            if (firing <= 0.0) {
                continue;
            }

            double z = r.getConsequent().evaluate(inputs);
            num += firing * z;
            den += firing;
        }

        if (den == 0.0) {
            return Double.NaN;
        }
        return num / den;
    }

    public double evaluateOrDefault(Map<String, Double> inputs, double defaultValue) {
        double result = evaluate(inputs);
        return Double.isNaN(result) ? defaultValue : result;
    }

    public static final class SugenoRule {
        private final Antecedent antecedent;
        private final SugenoConsequent consequent;
        private boolean enabled = true;
        private double weight = 1.0;

        public SugenoRule(Antecedent antecedent, SugenoConsequent consequent) {
            this.antecedent = antecedent;
            this.consequent = Objects.requireNonNull(consequent);
        }

        public double firingStrength(FuzzyOperator op, Map<String, Double> inputs) {
            double base = 1.0;
            if (antecedent != null) {
                base = antecedent.evaluate(op, inputs);
            }
            return base * weight;
        }

        public SugenoConsequent getConsequent() { return consequent; }

        public boolean isEnabled() { return enabled; }
        public void setEnabled(boolean enabled) { this.enabled = enabled; }

        public double getWeight() { return weight; }
        public void setWeight(double weight) { this.weight = Math.max(0.0, Math.min(1.0, weight)); }
    }

    public interface SugenoConsequent {
        double evaluate(Map<String, Double> inputs);

        static SugenoConsequent constant(double value) {
            return inputs -> value;
        }

        static SugenoConsequent linear(double intercept, Map<String, Double> coefficients) {
            Map<String, Double> coeff = new LinkedHashMap<>(coefficients);
            return inputs -> {
                double sum = intercept;
                for (Map.Entry<String, Double> e : coeff.entrySet()) {
                    Double x = inputs.get(e.getKey());
                    if (x == null) {
                        throw new IllegalArgumentException("Missing input for variable: " + e.getKey());
                    }
                    sum += e.getValue() * x;
                }
                return sum;
            };
        }
    }

    public static SugenoConsequent linearConsequent(double intercept, Object... varCoefPairs) {
        if (varCoefPairs.length % 2 != 0) {
            throw new IllegalArgumentException("Provide pairs of (String varName, Double coef)");
        }
        Map<String, Double> map = new LinkedHashMap<>();
        for (int i = 0; i < varCoefPairs.length; i += 2) {
            String var = Objects.toString(varCoefPairs[i]);
            Object coefObj = varCoefPairs[i + 1];
            double coef;
            if (coefObj instanceof Number) {
                coef = ((Number) coefObj).doubleValue();
            } else {
                coef = Double.parseDouble(coefObj.toString());
            }
            map.put(var, coef);
        }
        return SugenoConsequent.linear(intercept, map);
    }
}
