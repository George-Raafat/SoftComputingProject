package genetic_algorithms;

import java.util.List;

public interface Chromosome extends Comparable<Chromosome> {
    void mutate(double mutationRate);

    Chromosome copy();

    Integer getFitness();

    List<Chromosome> crossoverWith1(Chromosome partner);
    List<Chromosome> crossoverWith2(Chromosome partner);
    List<Chromosome> crossoverWith3(Chromosome partner);
}
