package genetic_algorithms;

import java.util.List;
import java.util.Random;

public class TournamentSelection<ChomoT extends Chromosome<ChomoT, ?>> implements SelectionStrategy<ChomoT> {
    private final int poolSize;

    public TournamentSelection(int poolSize) {
        this.poolSize = poolSize;
    }

    @Override
    public ChomoT select(List<ChomoT> population) {
        Random random = new Random();

        ChomoT best = population.get(random.nextInt(population.size()));

        for (int i = 1; i < poolSize; ++i) {
            ChomoT randomChromosome = population.get(random.nextInt(population.size()));
            if (randomChromosome.getFitness() > best.getFitness()) {
                best = randomChromosome;
            }
        }
        return best;
    }
}
