package genetic_algorithms.replacement;

import genetic_algorithms.Chromosome;

import java.util.ArrayList;
import java.util.List;


public class GenerationalReplacement<ChomoT extends Chromosome<ChomoT, ?>> implements ReplacementStrategy<ChomoT> {

    @Override
    public List<ChomoT> replace(List<ChomoT> oldPopulation, List<ChomoT> offspring) {

        List<ChomoT> newGeneration = new ArrayList<>();

        int populationSize = oldPopulation.size();

        for (int i = 0; i < populationSize && i < offspring.size(); i++) {
            newGeneration.add(offspring.get(i));
        }
        return newGeneration;
    }
}