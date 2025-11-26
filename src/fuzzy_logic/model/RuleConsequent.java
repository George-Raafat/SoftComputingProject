package fuzzy_logic.model;

import java.util.Map;
import java.util.function.Function;

public record RuleConsequent(String variable, String set, Function<Map<String, Double>, Double> consequentFunction) {
    public RuleConsequent(String variable, String set) {
        this(variable, set, inputs -> 1.0);
    }

    public RuleConsequent(Function<Map<String, Double>, Double> consequentFunction) {
        this(null, null, consequentFunction);
    }
}