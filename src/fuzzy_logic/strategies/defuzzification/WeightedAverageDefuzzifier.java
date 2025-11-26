package fuzzy_logic.strategies.defuzzification;

import fuzzy_logic.model.LinguisticVariable;
import fuzzy_logic.strategies.membership.MembershipFunction;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class WeightedAverageDefuzzifier implements Defuzzifier {

    @Override
    public double defuzzify(LinguisticVariable outputVariable, Map<String, List<Double>> activations) {

        double numerator = 0.0;
        double denominator = 0.0;

        for (Map.Entry<String, MembershipFunction> entry : outputVariable.getFuzzySets().entrySet()) {
            String name = entry.getKey();
            MembershipFunction mf = entry.getValue();

            List<Double> muList = activations.get(name);
            if (muList == null || muList.isEmpty()) continue;

            double centroid = computeCentroid(mf);
            double localMax = Collections.max(muList);
            numerator += localMax * centroid;
            denominator += localMax;
        }

        if (denominator == 0) return 0.0;
        return numerator / denominator;
    }

    private double computeCentroid(MembershipFunction mf) {
        List<Double> p = mf.getPoints();

        if (p.size() == 3) {
            return (p.get(0) + p.get(1) + p.get(2)) / 3.0;
        } else if (p.size() == 4) {
            return (p.get(0) + p.get(1) + p.get(2) + p.get(3)) / 4.0;
        }

        throw new IllegalArgumentException("Unsupported membership_function");
    }
}
