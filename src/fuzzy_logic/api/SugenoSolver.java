package fuzzy_logic.api;

import fuzzy_logic.antecedents.Antecedent;
import fuzzy_logic.model.FuzzyRule;
import fuzzy_logic.model.RuleConsequent;

import java.util.Map;
import java.util.function.Function;

public class SugenoSolver extends FuzzySolver {

    public SugenoSolver() {
        registry.clear();
    }

    public void createRule(String antecedent, Function<Map<String, Double>, Double> consequent) {
        Antecedent ant = parser.parseAntecedent(antecedent);
        RuleConsequent con = new RuleConsequent(consequent);
        FuzzyRule rule = new FuzzyRule(antecedent, ant, con);
        rules.add(rule);
    }

    // ---------------------------------------------------------
    // EVALUATION
    // ---------------------------------------------------------
    public double evaluate(Map<String, Double> inputs) {

        validate(inputs);

        double numerator = 0.0;
        double denominator = 0.0;

        int ruleIndex = -1;
        for (FuzzyRule rule : rules) {
            ruleIndex++;
            if (!rule.isEnabled()) continue;

            double degree = rule.getAntecedent().evaluate(operator, inputs);
            double activation = degree * rule.getWeight();

            System.out.println("Rule" + ruleIndex + ": " + rule.getText() + " | Degree: " + degree + " | Weight: " + rule.getWeight() + " | Activation: " + activation);

            numerator += activation * rule.getConsequent().consequentFunction().apply(inputs);
            denominator += activation;
        }
        if (denominator == 0.0) {
            return 0.0;
        }
        return numerator / denominator;
    }

}