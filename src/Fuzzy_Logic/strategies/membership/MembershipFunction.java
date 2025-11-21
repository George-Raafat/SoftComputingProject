package Fuzzy_Logic.strategies.membership;

public interface MembershipFunction {
    double calculateMembership(double input);

    String getName();
}