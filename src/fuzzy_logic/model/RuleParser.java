package fuzzy_logic.model;

import fuzzy_logic.antecedents.Antecedent;
import fuzzy_logic.antecedents.BinaryAntecedent;
import fuzzy_logic.antecedents.NotAntecedent;
import fuzzy_logic.antecedents.SimpleAntecedent;
import fuzzy_logic.strategies.membership.MembershipFunction;

public class RuleParser {

    private final FuzzyRegistry registry;

    public RuleParser(FuzzyRegistry registry) {
        this.registry = registry;
    }

    public Antecedent parseAntecedent(String text) {
        text = text.trim();
        // try OR first (lowest precedence)
        int orIndex = text.indexOf(" OR ");
        if (orIndex >= 0) {
            String left = text.substring(0, orIndex);
            String right = text.substring(orIndex + 4);
            return new BinaryAntecedent(parseAntecedent(left), parseAntecedent(right), false);
        }

        int andIndex = text.indexOf(" AND ");
        if (andIndex >= 0) {
            String left = text.substring(0, andIndex);
            String right = text.substring(andIndex + 5);
            return new BinaryAntecedent(parseAntecedent(left), parseAntecedent(right), true);
        }

        if (text.startsWith("NOT ")) {
            return new NotAntecedent(parseAntecedent(text.substring(4)));
        }

        String[] parts = text.split("=");
        if (parts.length != 2) throw new IllegalArgumentException("Bad simple antecedent: " + text);
        String variableName = parts[0].trim();
        String setName = parts[1].trim();
        MembershipFunction set = registry.getSet(variableName, setName);
        return new SimpleAntecedent(variableName, set);
    }

    public RuleConsequent parseConsequent(String text) {
        String[] parts = text.split("=");
        if (parts.length != 2) throw new IllegalArgumentException("Bad consequent: " + text);
        String var = parts[0].trim();
        String set = parts[1].trim();
        return new RuleConsequent(var, set);
    }
}
