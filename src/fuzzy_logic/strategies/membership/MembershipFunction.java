package fuzzy_logic.strategies.membership;

public interface MembershipFunction {
    double calculateMembership(double input);

    String getName();
}