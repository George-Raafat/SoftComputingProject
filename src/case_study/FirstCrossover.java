package case_study;

import genetic_algorithms.Chromosome;

import java.util.*;

public class FirstCrossover implements CrossoverStrategy {
    @Override
    public List<Chromosome> crossover(TimeTableChromosome firstParent, TimeTableChromosome secondParent) {
        int n = firstParent.getGenes().size();

        Set<Integer> used = new HashSet<>();

        int len = 1 + (int) (Math.random() * (n - 1));
        int index = (int) (Math.random() * (n - len + 1));

        List<Integer> childGenes = new ArrayList<>(Collections.nCopies(n, -1));

        for (int i = index; i < index + len; ++i) {
            int g = secondParent.getGenes().get(i);
            childGenes.set(i, g);
            used.add(g);
        }

        for (int i = 0; i < n; ++i) {
            if (i >= index && i < index + len) continue;

            int g = firstParent.getGenes().get(i);
            if (!used.contains(g)) {
                childGenes.set(i, g);
                used.add(g);
            } else {
                int alt = secondParent.getGenes().get(i);
                assert (!used.contains(alt));
                childGenes.set(i, alt);
                used.add(alt);
            }
        }

        TimeTableChromosome child = new TimeTableChromosome();
        child.setGenes(childGenes);
        child.evaluateFitness();

        return List.of(child);
    }
}
