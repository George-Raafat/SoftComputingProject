package Fuzzy_Logic.model;

public class RuleTerm {
    private final LinguisticVariable variable;
    private final String setName;

    public RuleTerm(LinguisticVariable variable, String setName) {
        this.variable = variable;
        this.setName = setName;
    }

    public double getMembership() {
        return variable.getMembershipDegree(setName);
    }
}
