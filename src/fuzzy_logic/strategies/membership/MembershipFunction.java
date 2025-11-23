package fuzzy_logic.strategies.membership;

import java.util.List;

public interface MembershipFunction {
    double calculateMembership(double input);

    List<Double> getPoints();
}