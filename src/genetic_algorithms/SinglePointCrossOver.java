package genetic_algorithms;

import java.util.List;

public class SinglePointCrossOver<T extends Chromosome> implements CrossoverStrategy<T> {

    @Override
    public List<T> crossover(T parent1, T parent2) {
        int n = parent1.getGenes().size();
        int crossoverPoint = (int) (Math.random() * n);

        T child1 = (T) parent1.copy();
        T child2 = (T) parent2.copy();

        for (int i = crossoverPoint; i < n; i++) {
            var tempGene = child1.getGenes().get(i);
            child1.getGenes().set(i, child2.getGenes().get(i));
            child2.getGenes().set(i, tempGene);
        }

        return List.of(child1, child2);
    }
}
