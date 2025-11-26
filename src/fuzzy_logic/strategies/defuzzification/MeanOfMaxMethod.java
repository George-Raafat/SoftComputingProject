package fuzzy_logic.strategies.defuzzification;

import fuzzy_logic.model.LinguisticVariable;
import fuzzy_logic.strategies.membership.MembershipFunction;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class MeanOfMaxMethod implements Defuzzifier {

    @Override
    public double defuzzify(LinguisticVariable outputVariable, Map<String, List<Double>> activations) {

        double numerator = 0.0;
        double denominator = 0.0;

        for (Map.Entry<String, MembershipFunction> entry : outputVariable.getFuzzySets().entrySet()) {
            String setName = entry.getKey();
            List<Double> muList = activations.get(setName);

            if (muList == null || muList.isEmpty()) continue;

            double localMax = Collections.max(muList);
            double maxPoint = getMaxPoint(entry.getValue());
            numerator += localMax * maxPoint;
            denominator += localMax;
        }
        if (denominator == 0) return 0.0;
        return numerator / denominator;
    }

    private double getMaxPoint(MembershipFunction mf) {
        List<Double> p = mf.getPoints();

        if (p.size() == 3) {
            return p.get(1);
        }
        double b = p.get(1);
        double c = p.get(2);
        return (b + c) / 2.0;
    }
}
