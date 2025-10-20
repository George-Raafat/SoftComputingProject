package genetic_algorithms;

import java.util.List;

public interface Chromosome extends Comparable<Chromosome> {
    void mutate(double mutationRate);

    Chromosome copy();

    Integer getFitness();

    List<Chromosome> crossoverWith(Chromosome partner);
}
