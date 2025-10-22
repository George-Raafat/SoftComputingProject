package genetic_algorithms;

import java.util.List;

public interface Chromosome<T> extends Comparable<Chromosome> {
    void mutate(double mutationRate);

    List<T> getGenes();

    Chromosome copy();

    Integer getFitness();

    List<Chromosome> crossoverWith(Chromosome partner);
}
