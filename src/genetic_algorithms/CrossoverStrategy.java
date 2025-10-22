package genetic_algorithms;

import java.util.List;

public interface CrossoverStrategy<T extends Chromosome> {
    List<T> crossover(T parent1, T parent2);
}
