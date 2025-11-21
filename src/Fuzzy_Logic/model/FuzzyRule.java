package Fuzzy_Logic.model;

public class FuzzyRule {
    private String antecedent; // e.g., "IF Temperature IS High AND Humidity IS Low"
    private String consequent; // e.g., "THEN FanSpeed IS High"

    public FuzzyRule(String antecedent, String consequent) {
        this.antecedent = antecedent;
        this.consequent = consequent;
    }

    public String getAntecedent() {
        return antecedent;
    }

    public String getConsequent() {
        return consequent;
    }
}