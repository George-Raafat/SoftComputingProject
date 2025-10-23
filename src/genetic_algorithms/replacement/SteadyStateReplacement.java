package genetic_algorithms.replacement;

import genetic_algorithms.Chromosome;
import java.util.ArrayList;
import java.util.List;

public class SteadyStateReplacement<ChomoT extends Chromosome<ChomoT, ?>> implements ReplacementStrategy<ChomoT> {

    private final int k;

    public SteadyStateReplacement(int k) {
        this.k = k;
    }

    @Override
    public List<ChomoT> replace(List<ChomoT> oldPopulation, List<ChomoT> offspring) {
        List<ChomoT> newGeneration = new ArrayList<>(oldPopulation);

        for (int i = 0; i < k && i < offspring.size() && i < newGeneration.size(); ++i) {
            newGeneration.set(i, offspring.get(i));
        }

        return newGeneration;
    }
}
