package genetic_algorithms.replacement;

import genetic_algorithms.Chromosome;

import java.util.ArrayList;
import java.util.List;


public class SteadyStateReplacement<ChomoT extends Chromosome<ChomoT, ?>> implements ReplacementStrategy<ChomoT> {

    public SteadyStateReplacement() {}

    @Override
    public List<ChomoT> replace(List<ChomoT> oldPopulation, List<ChomoT> offspring) {

        int populationSize = oldPopulation.size();

        List<ChomoT> combinedPopulation = new ArrayList<>(oldPopulation);
        combinedPopulation.addAll(offspring);


        combinedPopulation.sort((a, b) -> Double.compare(b.getFitness(), a.getFitness()));

        List<ChomoT> newGeneration = new ArrayList<>();
        for (int i = 0; i < populationSize && i < combinedPopulation.size(); i++) {
            newGeneration.add(combinedPopulation.get(i));
        }

        return newGeneration;
    }
}