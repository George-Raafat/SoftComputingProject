package case_study;

import genetic_algorithms.Chromosome;
import java.util.*;

public class SecondCrossover implements CrossoverStrategy {

    @Override
    public List<Chromosome> crossover(TimeTableChromosome firstParent, TimeTableChromosome secondParent) {
        TimeTableChromosome child1 = makeChild(firstParent, secondParent);
        TimeTableChromosome child2 = makeChild(secondParent, firstParent);
        return List.of(child1, child2);
    }

    private TimeTableChromosome makeChild(TimeTableChromosome primary, TimeTableChromosome secondary) {
        int n = primary.getGenes().size();
        List<Integer> childGenes = new ArrayList<>(primary.getGenes());
        Set<Integer> used = new HashSet<>(childGenes);

        int swaps = (n + 1) >> 1;
        Random rand = new Random();

        for (int i = 0; i < swaps; i++) {
            int j = rand.nextInt(n);
            int newGene = secondary.getGenes().get(j);

            if (!used.contains(newGene)) {
                used.remove(childGenes.get(j));
                used.add(newGene);
                childGenes.set(j, newGene);
            }
        }

        TimeTableChromosome child = new TimeTableChromosome();
        child.setGenes(childGenes);
         child.evaluateFitness();
        return child;
    }
}
