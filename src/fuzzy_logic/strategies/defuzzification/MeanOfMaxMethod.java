package fuzzy_logic.strategies.defuzzification;

import fuzzy_logic.model.LinguisticVariable;
import fuzzy_logic.strategies.membership.MembershipFunction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class MeanOfMaxMethod implements Defuzzifier {

    @Override
    public double defuzzify(LinguisticVariable outputVariable, Map<String, List<Double>> activations) {

        List<Double> maximaPoints = new ArrayList<>();
        double globalMax = 0.0;

        for (Map.Entry<String, MembershipFunction> entry : outputVariable.getFuzzySets().entrySet()) {
            String setName = entry.getKey();
            List<Double> muList = activations.get(setName);

            if (muList == null || muList.isEmpty()) continue;

            double localMax = Collections.max(muList);

            if (localMax > globalMax) {
                globalMax = localMax;
            }
        }

        if (globalMax == 0.0)
            return 0.0;

        for (Map.Entry<String, MembershipFunction> entry : outputVariable.getFuzzySets().entrySet()) {
            String setName = entry.getKey();
            MembershipFunction mf = entry.getValue();
            List<Double> muList = activations.get(setName);

            if (muList == null || muList.isEmpty()) continue;

            double localMax = Collections.max(muList);

            if (localMax == globalMax) {
                maximaPoints.addAll(computeMaxPoints(mf));
            }
        }

        if (maximaPoints.isEmpty())
            return 0.0;

        double sum = 0.0;
        for (double x : maximaPoints) sum += x;

        return sum / maximaPoints.size();
    }

    private List<Double> computeMaxPoints(MembershipFunction mf) {
        List<Double> p = mf.getPoints();
        List<Double> res = new ArrayList<>();

        if (p.size() == 3) {
            res.add(p.get(1));
        } else if (p.size() == 4) {
            double b = p.get(1);
            double c = p.get(2);
            res.add(b);
            if (c != b)
                res.add(c);
        } else {
            throw new IllegalArgumentException("Unsupported membership function shape.");
        }

        return res;
    }
}
