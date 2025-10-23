package genetic_algorithms.replacement;

import genetic_algorithms.Chromosome;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SteadyStateReplacement<ChomoT extends Chromosome<ChomoT, ?>> implements ReplacementStrategy<ChomoT> {

    private final int k;
    private final Random random = new Random();

    public SteadyStateReplacement(int k) {
        this.k = k;
    }

    @Override
    public List<ChomoT> replace(List<ChomoT> oldPopulation, List<ChomoT> offspring) {
        List<ChomoT> newGeneration = new ArrayList<>(oldPopulation);

        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < oldPopulation.size(); ++i) {
            indices.add(i);
        }
        Collections.shuffle(indices, random);

        for (int i = 0; i < k && i < offspring.size() && i < indices.size(); ++i) {
            int replaceIndex = indices.get(i);
            newGeneration.set(replaceIndex, offspring.get(i));
        }

        return newGeneration;
    }
}
