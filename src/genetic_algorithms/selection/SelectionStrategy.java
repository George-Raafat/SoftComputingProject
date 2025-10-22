package genetic_algorithms.selection;

import genetic_algorithms.Chromosome;

import java.util.List;

public interface SelectionStrategy<ChomoT extends Chromosome<ChomoT, ?>> {
    ChomoT select(List<ChomoT> population);
}