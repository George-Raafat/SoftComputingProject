package genetic_algorithms;

import java.util.List;

public interface SelectionStrategy<ChomoT extends Chromosome<ChomoT, ?>> {
    ChomoT select(List<ChomoT> population);
}