package Fuzzy_Logic.model;

import Fuzzy_Logic.strategies.membership.MembershipFunction;

public class FuzzySet {
    private final String name;
    private final MembershipFunction function;

    public FuzzySet(String name, MembershipFunction function) {
        this.name = name;
        this.function = function;
    }

    public String getName() {
        return name;
    }

    public double getMembershipDegree(double crispValue) {
        return function.calculateMembership(crispValue);
    }
}