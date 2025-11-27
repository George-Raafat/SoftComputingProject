package fuzzy_logic.api;

import fuzzy_logic.antecedents.Antecedent;
import fuzzy_logic.model.FuzzyRule;
import fuzzy_logic.model.LinguisticVariable;
import fuzzy_logic.model.RuleConsequent;
import fuzzy_logic.strategies.defuzzification.Defuzzifier;
import fuzzy_logic.strategies.defuzzification.WeightedAverageDefuzzifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MamdaniSolver extends FuzzySolver {

    private LinguisticVariable outputVariable = null;
    private Defuzzifier defuzzifier = new WeightedAverageDefuzzifier();


    public MamdaniSolver() {
        registry.clear();
    }

    public void setOutputVariable(String name) {
        LinguisticVariable variable = registry.getVariable(name);
        if (variable == null)
            throw new RuntimeException("Variable not found: " + name);
        this.outputVariable = variable;
    }

    public void createRule(String antecedent, String consequent) {
        Antecedent ant = parser.parseAntecedent(antecedent);
        RuleConsequent con = parser.parseConsequent(consequent);
        FuzzyRule rule = new FuzzyRule(antecedent, ant, con);
        rules.add(rule);
    }

    public void setDefuzzifier(Defuzzifier defuzzifier) {
        this.defuzzifier = defuzzifier;
    }

    // ---------------------------------------------------------
    // EVALUATION
    // ---------------------------------------------------------
    public double evaluate(Map<String, Double> inputs) {

        validate(inputs);

        Map<String, List<Double>> activations = new HashMap<>();

        int ruleIndex = -1;
        for (FuzzyRule rule : rules) {
            ruleIndex++;
            if (!rule.isEnabled()) continue;

            double degree = rule.getAntecedent().evaluate(operator, inputs);
            double activation = degree * rule.getWeight();
            String setName = rule.getConsequent().set();

            System.out.println("Rule" + ruleIndex + ": " + rule.getText() + " | Degree: " + degree + " | Weight: " + rule.getWeight() + " | Activation: " + activation + " -> " + setName);

            activations.putIfAbsent(setName, new ArrayList<>());
            activations.get(setName).add(activation);
        }

        return defuzzifier.defuzzify(outputVariable, activations);
    }

}
