package fuzzy_logic.model;

import fuzzy_logic.strategies.membership.MembershipFunction;

import java.util.HashMap;
import java.util.Map;

public class LinguisticVariable {
    private final double minRange;
    private final double maxRange;
    private final Map<String, MembershipFunction> fuzzySets = new HashMap<>();

    public LinguisticVariable(double minRange, double maxRange) {
        this.minRange = minRange;
        this.maxRange = maxRange;
    }

    public void addFuzzySet(String name, MembershipFunction mf) {
        fuzzySets.put(name, mf);
    }

    public double getMembershipDegree(String setName, double value) {
        MembershipFunction set = getFuzzySet(setName);

        return set.calculateMembership(value);
    }

    public double getMinRange() {
        return minRange;
    }

    public double getMaxRange() {
        return maxRange;
    }

    public Map<String, MembershipFunction> getFuzzySets() {
        return fuzzySets;
    }

    public MembershipFunction getFuzzySet(String setName) {
        MembershipFunction set = fuzzySets.get(setName);

        if (set == null) {
            throw new IllegalArgumentException("Unknown set " + setName);
        }
        return set;
    }
}