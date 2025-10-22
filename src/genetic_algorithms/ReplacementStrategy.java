package genetic_algorithms;

import java.util.List;

public interface ReplacementStrategy<ChomoT extends Chromosome<ChomoT, ?>> {
    List<ChomoT> replace(List<ChomoT> oldPopulation, List<ChomoT> offspring);
}