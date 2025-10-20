package genetic_algorithms;

import java.util.List;
import java.util.Random;

public class TournamentSelection implements SelectionStrategy {
    private final int poolSize;

    public TournamentSelection(int poolSize) {
        this.poolSize = poolSize;
    }

    @Override
    public Chromosome select(List<Chromosome> population) {
        Random random = new Random();

        Chromosome best = population.get(random.nextInt(population.size()));

        for (int i = 1; i < poolSize; ++i) {
            Chromosome randomChromosome = population.get(random.nextInt(population.size()));
            if (randomChromosome.getFitness() > best.getFitness()) {
                best = randomChromosome;
            }
        }
        return best;
    }
}
