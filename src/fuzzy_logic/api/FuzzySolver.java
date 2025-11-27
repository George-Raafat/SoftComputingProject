package fuzzy_logic.api;

import fuzzy_logic.model.FuzzyRegistry;
import fuzzy_logic.model.FuzzyRule;
import fuzzy_logic.model.LinguisticVariable;
import fuzzy_logic.model.RuleParser;
import fuzzy_logic.strategies.membership.MembershipFunction;
import fuzzy_logic.strategies.operators.FuzzyOperator;
import fuzzy_logic.strategies.operators.MinMaxOperator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class FuzzySolver {

    protected final List<FuzzyRule> rules = new ArrayList<>();
    protected final FuzzyRegistry registry = new FuzzyRegistry();
    protected final RuleParser parser = new RuleParser(registry);
    protected FuzzyOperator operator = new MinMaxOperator();

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

    // ---------------------------------------------------------
    // EVALUATION
    // ---------------------------------------------------------
    protected void validate(Map<String, Double> inputs) {
        for (Map.Entry<String, Double> entry : inputs.entrySet()) {
            String varName = entry.getKey();
            Double value = entry.getValue();
            LinguisticVariable variable = registry.getVariable(varName);
            if (variable == null)
                throw new RuntimeException("Input variable not found: " + varName);
            if (value < variable.getMinRange() || value > variable.getMaxRange()) {
                throw new IllegalArgumentException("Input value " + value + " for variable " + varName +
                        " is out of range [" + variable.getMinRange() + ", " + variable.getMaxRange() + "]");
            }
        }
    }

    public abstract double evaluate(Map<String, Double> inputs);
}
