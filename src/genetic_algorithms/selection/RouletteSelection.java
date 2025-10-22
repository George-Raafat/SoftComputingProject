package genetic_algorithms.selection;

import genetic_algorithms.Chromosome;

import java.util.List;
import java.util.Random;

public class RouletteSelection<ChomoT extends Chromosome<ChomoT, ?>> implements SelectionStrategy<ChomoT> {
    @Override
    public ChomoT select(List<ChomoT> population) {
        double totalFitness = 0;
        for (ChomoT c : population) {
            totalFitness += c.getFitness();
        }

        Random random = new Random();
        double randomValue = random.nextDouble() * totalFitness;

        double sum = 0;
        for (ChomoT c : population) {
            sum += c.getFitness();
            if (sum >= randomValue) {
                return c;
            }
        }
        return population.get(population.size() - 1);
    }
}
