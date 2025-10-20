package case_study;

import genetic_algorithms.Chromosome;

import java.util.List;

public interface CrossoverStrategy {
    List<Chromosome> crossover(TimeTableChromosome firstParent, TimeTableChromosome secondParent);
}
