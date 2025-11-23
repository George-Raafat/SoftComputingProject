package fuzzy_logic.strategies.membership;

public class TrapezoidFunction implements MembershipFunction {
    private final double a; // Left foot
    private final double b; // Left shoulder
    private final double c; // Right shoulder
    private final double d; // Right foot
    private final String name;

    public TrapezoidFunction(double a, double b, double c, double d, String name) {
        if (a >= b || b >= c || c >= d) {
            throw new IllegalArgumentException("Invalid trapezoid function parameters: a < b < c < d must hold.");
        }
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.name = name;
    }

    @Override
    public double calculateMembership(double input) {
        if (input < a || input > d) {
            return 0.0;
        } else if (input >= b && input <= c) {
            return 1.0;
        } else if (input >= a && input < b) {
            return (input - a) / (b - a);
        } else { // input > c && input <= d
            return (d - input) / (d - c);
        }
    }

    @Override
    public String getName() {
        return name;
    }
}
