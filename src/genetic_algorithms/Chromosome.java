package genetic_algorithms;

import java.util.List;

public interface Chromosome<ChromoT extends Chromosome<ChromoT, GeneT>, GeneT> extends Comparable<Chromosome<ChromoT, GeneT>> {
    void mutate(double mutationRate);

    List<GeneT> getGenes();

    ChromoT copy();

    Integer getFitness();
}
