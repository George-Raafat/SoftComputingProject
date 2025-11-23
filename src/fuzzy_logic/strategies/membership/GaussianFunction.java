package fuzzy_logic.strategies.membership;

public class GaussianFunction implements MembershipFunction {
    private final double mean; // Mean (center) of the Gaussian
    private final double stdDev; // Standard deviation (width) of the Gaussian

    public GaussianFunction(double mean, double stdDev) {
        if (stdDev <= 0) {
            throw new IllegalArgumentException("Standard deviation must be positive.");
        }
        this.mean = mean;
        this.stdDev = stdDev;
    }

    @Override
    public double calculateMembership(double input) {
        return Math.exp(-0.5 * Math.pow((input - mean) / stdDev, 2));
    }
}