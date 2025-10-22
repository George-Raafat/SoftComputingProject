package case_study;

import genetic_algorithms.crossover.CrossoverStrategy;

import java.util.*;

public class FirstCrossover implements CrossoverStrategy<TimeTableChromosome, Integer> {

    @Override
    public List<TimeTableChromosome> crossover(TimeTableChromosome firstParent, TimeTableChromosome secondParent) {
        TimeTableChromosome child1 = makeChild(firstParent, secondParent);
        TimeTableChromosome child2 = makeChild(secondParent, firstParent);

        List<TimeTableChromosome> children = new ArrayList<>(2);
        children.add(child1);
        children.add(child2);
        return children;
    }

    private TimeTableChromosome makeChild(TimeTableChromosome primary, TimeTableChromosome secondary) {
        int n = primary.getGenes().size();
        List<Integer> childGenes = new ArrayList<>(primary.getGenes());
        Set<Integer> used = new HashSet<>(childGenes);

        Random rand = new Random();

        for (int i = 0; i < n; i++) {
            boolean swap = rand.nextBoolean();
            int newGene = secondary.getGenes().get(i);

            if (!used.contains(newGene) && swap) {
                used.remove(childGenes.get(i));
                used.add(newGene);
                childGenes.set(i, newGene);
            }
        }

        TimeTableChromosome child = new TimeTableChromosome();
        child.setGenes(childGenes);
        child.evaluateFitness();
        return child;
    }
}
