package fuzzy_logic.api;

import fuzzy_logic.antecedents.Antecedent;
import fuzzy_logic.model.*;
import fuzzy_logic.strategies.membership.MembershipFunction;
import fuzzy_logic.strategies.operators.FuzzyOperator;
import fuzzy_logic.strategies.operators.MinMaxOperator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FuzzySolver {

    private final List<FuzzyRule> rules = new ArrayList<>();
    //    private InferenceEngine engine = new MamdaniEngine(operator);
//    private Defuzzifier defuzzifier = new CentroidDefuzzifier();
    private final FuzzyRegistry registry = new FuzzyRegistry();
    private final RuleParser parser = new RuleParser(registry);
    private FuzzyOperator operator = new MinMaxOperator();
    private LinguisticVariable outputVariable = null;

    public FuzzySolver() {
        registry.clear();
    }

    // ---------------------------------------------------------
    // VARIABLE SETUP (public, simple)
    // ---------------------------------------------------------
    public void addVariable(String name, double min, double max) {
        LinguisticVariable variable = new LinguisticVariable(min, max);
        registry.register(name, variable);
    }

    public void addSet(String variableName, String setName, MembershipFunction mf) {
        LinguisticVariable variable = registry.getVariable(variableName);
        if (variable == null)
            throw new RuntimeException("Variable not found: " + variableName);

        variable.addFuzzySet(setName, mf);
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
        FuzzyRule rule = new FuzzyRule(ant, con);
        rules.add(rule);
    }

    // Rule editing
    public void enableRule(int index) {
        rules.get(index).setEnabled(true);
    }

    public void disableRule(int index) {
        rules.get(index).setEnabled(false);
    }

    public void setRuleWeight(int index, double w) {
        rules.get(index).setWeight(w);
    }

    public FuzzyRule getRule(int index) {
        return rules.get(index);
    }

    public int ruleCount() {
        return rules.size();
    }

    // ---------------------------------------------------------
    // OPERATOR & ENGINE SELECTION
    // ---------------------------------------------------------
    public void setOperator(FuzzyOperator operator) {
        this.operator = operator;
    }

//    public void setEngine(InferenceEngine engine){
//        this.engine = engine;
//    }
//
//    public void setDefuzzifier(Defuzzifier defuzzifier) { this.defuzzifier = defuzzifier; }

    // ---------------------------------------------------------
    // EVALUATION
    // ---------------------------------------------------------
    public double evaluate(Map<String, Double> inputs) {

        Map<String, List<Double>> vals = new HashMap<>();
        double numerator = 0.0;
        double denominator = 0.0;


        for (FuzzyRule rule : rules) {
            if (!rule.isEnabled()) continue;
            double antecedentDegree = rule.getAntecedent().evaluate(operator, inputs);
            double activation = antecedentDegree * rule.getWeight();

            String cons = rule.getConsequent().set();

            vals.putIfAbsent(cons, new ArrayList<>());
            vals.get(cons).add(activation);
            denominator += activation;
        }
        for (Map.Entry<String, MembershipFunction> entry : outputVariable.getFuzzySets().entrySet()) {
            List<Double> points = entry.getValue().getPoints();
            double centroid = points.stream()
                    .mapToDouble(Double::doubleValue)
                    .average()
                    .orElse(0);
            for (Double val : vals.get(entry.getKey())) {
                numerator += val * centroid;
            }
        }
        return numerator / denominator;
    }
}
