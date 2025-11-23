package fuzzy_logic.strategies.membership;

public class GaussianFunction implements MembershipFunction {
    private final double mean; // Mean (center) of the Gaussian
    private final double stdDev; // Standard deviation (width) of the Gaussian
    private final String name;

    public GaussianFunction(double mean, double stdDev, String name) {
        if (stdDev <= 0) {
            throw new IllegalArgumentException("Standard deviation must be positive.");
        }
        this.mean = mean;
        this.stdDev = stdDev;
        this.name = name;
    }

    @Override
    public double calculateMembership(double input) {
        return Math.exp(-0.5 * Math.pow((input - mean) / stdDev, 2));
    }

    @Override
    public String getName() {
        return name;
    }
}