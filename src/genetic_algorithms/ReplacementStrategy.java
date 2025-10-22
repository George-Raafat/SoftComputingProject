package genetic_algorithms;

import java.util.List;

public interface ReplacementStrategy {
    List<Chromosome> replace(List<Chromosome> oldPopulation, List<Chromosome> offspring);
}