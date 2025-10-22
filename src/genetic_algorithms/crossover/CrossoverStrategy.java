package genetic_algorithms.crossover;

import genetic_algorithms.Chromosome;

import java.util.List;

public interface CrossoverStrategy<ChomoT extends Chromosome<ChomoT, GeneT>, GeneT> {
    List<ChomoT> crossover(ChomoT parent1, ChomoT parent2);
}
