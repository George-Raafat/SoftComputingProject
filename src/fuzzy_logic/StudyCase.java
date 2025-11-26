package fuzzy_logic;

import fuzzy_logic.api.MamdaniSolver;
import fuzzy_logic.strategies.defuzzification.WeightedAverageDefuzzifier;
import fuzzy_logic.strategies.membership.TriangularFunction;

import java.util.Map;

public class StudyCase {
    public static void main(String[] args) {

        MamdaniSolver solver = new MamdaniSolver();
        solver.setDefuzzifier(new WeightedAverageDefuzzifier());

        solver.addVariable("SoilMoisture", 0, 100);
        solver.addSet("SoilMoisture", "Dry", new TriangularFunction(0, 0, 50));
        solver.addSet("SoilMoisture", "Normal", new TriangularFunction(0, 50, 100));
        solver.addSet("SoilMoisture", "Wet", new TriangularFunction(50, 100, 100));

        solver.addVariable("Temperature", 0, 50);
        solver.addSet("Temperature", "Low", new TriangularFunction(0, 0, 25));
        solver.addSet("Temperature", "Medium", new TriangularFunction(0, 25, 50));
        solver.addSet("Temperature", "High", new TriangularFunction(25, 50, 50));

        solver.addVariable("RainForecast", 0, 100);
        solver.addSet("RainForecast", "Low", new TriangularFunction(0, 0, 50));
        solver.addSet("RainForecast", "Medium", new TriangularFunction(0, 50, 100));
        solver.addSet("RainForecast", "High", new TriangularFunction(50, 100, 100));

        solver.addVariable("IrrigationTime", 0, 30);
        solver.addSet("IrrigationTime", "Short", new TriangularFunction(0, 0, 15));
        solver.addSet("IrrigationTime", "Medium", new TriangularFunction(0, 15, 30));
        solver.addSet("IrrigationTime", "Long", new TriangularFunction(15, 30, 30));

        solver.setOutputVariable("IrrigationTime");

        solver.createRule("SoilMoisture=Dry AND Temperature=High AND RainForecast=Low", "IrrigationTime=Long");
        solver.createRule("SoilMoisture=Dry AND Temperature=Medium AND RainForecast=Low", "IrrigationTime=Long");
        solver.createRule("SoilMoisture=Normal AND Temperature=High AND RainForecast=Medium", "IrrigationTime=Medium");
        solver.createRule("SoilMoisture=Normal AND Temperature=Low AND RainForecast=High", "IrrigationTime=Short");
        solver.createRule("SoilMoisture=Wet AND Temperature=Medium AND RainForecast=Low", "IrrigationTime=Short");
        solver.createRule("SoilMoisture=Wet OR RainForecast=High", "IrrigationTime=Short");

        solver.disableRule(3);
        solver.setRuleWeight(0, 0.75);

        Double val = solver.evaluate(Map.of("SoilMoisture", 100.0, "Temperature", 50.0, "RainForecast", 100.0));
        System.out.println(val + " minutes of irrigation needed.");
    }
}
