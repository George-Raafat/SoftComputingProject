package fuzzy_logic.model;

import fuzzy_logic.strategies.membership.MembershipFunction;

import java.util.HashMap;
import java.util.Map;

public class FuzzyRegistry {
    private final Map<String, LinguisticVariable> variables = new HashMap<>();

    public void register(String name, LinguisticVariable variable) {
        variables.put(name, variable);
    }

    public LinguisticVariable getVariable(String name) {
        return variables.get(name);
    }

    public MembershipFunction getSet(String varName, String setName) {
        LinguisticVariable variable = variables.get(varName);
        if (variable == null) throw new IllegalArgumentException("Unknown variable: " + varName);
        return variable.getFuzzySet(setName);
    }

    public void clear() {
        variables.clear();
    }
}
