package fuzzy_logic;

import fuzzy_logic.api.FuzzySolver;
import fuzzy_logic.strategies.membership.TriangularFunction;

import java.util.Map;

public class Main {
    public static void main(String[] args) {

        FuzzySolver solver = new FuzzySolver();

        solver.addVariable("Size", 0, 10);
        solver.addSet("Size", "S", new TriangularFunction(0, 0, 100));
        solver.addSet("Size", "L", new TriangularFunction(0, 100, 100));

        solver.addVariable("Weight", 0, 100);
        solver.addSet("Weight", "S", new TriangularFunction(0, 0, 100));
        solver.addSet("Weight", "L", new TriangularFunction(0, 100, 100));

        solver.addVariable("Quality", 0, 10);
        solver.addSet("Quality", "B", new TriangularFunction(0, 0, 5));
        solver.addSet("Quality", "M", new TriangularFunction(0, 5, 10));
        solver.addSet("Quality", "G", new TriangularFunction(5, 10, 10));

        solver.setOutputVariable("Quality");

        solver.createRule("Size=S AND Weight=S", "Quality=B");
        solver.createRule("Size=S AND Weight=L", "Quality=M");
        solver.createRule("Size=L AND Weight=S", "Quality=M");
        solver.createRule("Size=L AND Weight=L", "Quality=G");

        Double val = solver.evaluate(Map.of("Size", 20.0, "Weight", 25.0));
        System.out.println(val);
    }
}
