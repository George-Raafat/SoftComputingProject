package fuzzy_logic.strategies.membership;

public class TriangularFunction implements MembershipFunction {
    private final double a; // Left vertex
    private final double b; // Peak vertex
    private final double c; // Right vertex

    public TriangularFunction(double a, double b, double c) {
        if (a > b || b > c || a == c) {
            throw new IllegalArgumentException("Invalid triangular function parameters: a <= b, b <= c, a < c must hold.");
        }
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double calculateMembership(double input) {
        if (input < a || input > c) {
            return 0.0;
        } else if (input == b) {
            return 1.0;
        } else if (input < b) {
            return (input - a) / (b - a);
        } else { // input > b
            return (c - input) / (c - b);
        }
    }
}