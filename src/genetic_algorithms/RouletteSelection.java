package genetic_algorithms;

import java.util.List;
import java.util.Random;

public class RouletteSelection implements SelectionStrategy {

    @Override
    public Chromosome select(List<Chromosome> population) {
        double totalFitness = 0;
        for (Chromosome c: population) {
            totalFitness += c.getFitness();
        }

        Random random = new Random();
        double randomValue = random.nextDouble() * totalFitness;

        double sum = 0;
        for (Chromosome c: population) {
            sum += c.getFitness();
            if (sum >= randomValue) {
                return c;
            }
        }
        return null;
    }
}
