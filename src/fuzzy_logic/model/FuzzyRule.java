package fuzzy_logic.model;

import fuzzy_logic.antecedents.Antecedent;

public class FuzzyRule {
    private final String text;
    private final Antecedent antecedent;
    private final RuleConsequent consequent;
    private boolean enabled = true;
    private double weight = 1.0;

    public FuzzyRule(String text, Antecedent antecedent, RuleConsequent consequent) {
        this.text = text;
        this.antecedent = antecedent;
        this.consequent = consequent;
    }

    public Antecedent getAntecedent() {
        return antecedent;
    }

    public RuleConsequent getConsequent() {
        return consequent;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double w) {
        this.weight = Math.max(0.0, Math.min(1.0, w));
    }

    public String getText() {
        return text;
    }
}