package genetic_algorithms;

import java.util.List;

public interface SelectionStrategy {
    Chromosome select(List<Chromosome> population);
}