package genetic_algorithms;

import java.util.List;

public interface CrossoverStrategy<ChomoT extends Chromosome<ChomoT, GeneT>, GeneT> {
    List<ChomoT> crossover(ChomoT parent1, ChomoT parent2);
}
