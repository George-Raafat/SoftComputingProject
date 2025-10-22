package genetic_algorithms.crossover;

import genetic_algorithms.Chromosome;

import java.util.List;

public class SinglePointCrossOver<ChomoT extends Chromosome<ChomoT, GeneT>, GeneT> implements CrossoverStrategy<ChomoT, GeneT> {

    @Override
    public List<ChomoT> crossover(ChomoT parent1, ChomoT parent2) {
        int n = parent1.getGenes().size();
        int crossoverPoint = (int) (Math.random() * n);

        ChomoT child1 = parent1.copy();
        ChomoT child2 = parent2.copy();

        for (int i = crossoverPoint; i < n; i++) {
            var tempGene = child1.getGenes().get(i);
            child1.getGenes().set(i, child2.getGenes().get(i));
            child2.getGenes().set(i, tempGene);
        }

        return List.of(child1, child2);
    }
}
