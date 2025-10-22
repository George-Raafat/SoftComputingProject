package genetic_algorithms.replacement;

import genetic_algorithms.Chromosome;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ElitismReplacement<ChomoT extends Chromosome<ChomoT, ?>> implements ReplacementStrategy<ChomoT> {

    private final int eliteCount;

    public ElitismReplacement(int eliteCount) {
        this.eliteCount = eliteCount;
    }

    @Override
    public List<ChomoT> replace(List<ChomoT> oldPopulation, List<ChomoT> offspring) {
        oldPopulation.sort(Collections.reverseOrder());

        List<ChomoT> newGeneration = new ArrayList<>();

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
