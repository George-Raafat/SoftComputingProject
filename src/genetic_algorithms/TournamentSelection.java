package genetic_algorithms;

import java.util.List;
import java.util.Random;

public class TournamentSelection implements SelectionStrategy {
    private int poolSize;
    public TournamentSelection(int poolSize) {
        this.poolSize = poolSize;
    }

    @Override
    public Chromosome select(List<Chromosome> population) {
        Chromosome best = null;
        Random random = new Random();

        for (int i = 0; i < poolSize; ++i) {
            Chromosome randomChromosome = population.get(random.nextInt(population.size()));
            if (best == null || randomChromosome.getFitness() > best.getFitness()) {
                best = randomChromosome;
            }
        }
        return best;
    }
}
