package genetic_algorithms.crossover;

import genetic_algorithms.Chromosome;
import java.util.List;
import java.util.Random;

public class UniformCrossOver<ChomoT extends Chromosome<ChomoT, GeneT>, GeneT> implements CrossoverStrategy<ChomoT, GeneT> {

    private final Random random = new Random();

    @Override
    public List<ChomoT> crossover(ChomoT parent1, ChomoT parent2) {
        List<GeneT> genes1 = parent1.getGenes();
        List<GeneT> genes2 = parent2.getGenes();

        ChomoT child1 = parent1.copy();
        ChomoT child2 = parent2.copy();

        for (int i = 0; i < genes1.size(); ++i) {
            boolean coinFlip = random.nextBoolean();

            if (coinFlip) {
                child1.getGenes().set(i, genes1.get(i));
                child2.getGenes().set(i, genes2.get(i));
            } else {
                child1.getGenes().set(i, genes2.get(i));
                child2.getGenes().set(i, genes1.get(i));
            }
        }

        return List.of(child1, child2);
    }
}
