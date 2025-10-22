package genetic_algorithms;

import java.util.ArrayList;
import java.util.List;

public class ElitismReplacement implements ReplacementStrategy {

    private final int eliteCount;

    public ElitismReplacement(int eliteCount) {
        this.eliteCount = eliteCount;
    }

    @Override
    public List<Chromosome> replace(List<Chromosome> oldPopulation, List<Chromosome> offspring) {
        oldPopulation.sort((a, b) -> Double.compare(b.getFitness(), a.getFitness()));

        List<Chromosome> newGeneration = new ArrayList<>();

        for (int i = 0; i < eliteCount && i < oldPopulation.size(); ++i) {
            newGeneration.add(oldPopulation.get(i));
        }

        int remainingSlots = oldPopulation.size() - eliteCount;
        for (int i = 0; i < remainingSlots && i < offspring.size(); ++i) {
            newGeneration.add(offspring.get(i));
        }

        return newGeneration;
    }
}
