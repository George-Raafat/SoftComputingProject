package fuzzy_logic.strategies.defuzzification;

import fuzzy_logic.model.LinguisticVariable;

import java.util.List;
import java.util.Map;

public interface Defuzzifier {

    double defuzzify(
            LinguisticVariable outputVariable,
            Map<String, List<Double>> activations
    );
}
