package case_study;

import genetic_algorithms.Chromosome;
import java.util.*;

public class FirstCrossover implements CrossoverStrategy {

    @Override
    public List<Chromosome> crossover(TimeTableChromosome firstParent, TimeTableChromosome secondParent) {
        int n = firstParent.getGenes().size();

        int len = 1 + (int) (Math.random() * (n - 1));
        int index = (int) (Math.random() * (n - len + 1));

        TimeTableChromosome child1 = makeChild(firstParent, secondParent, index, len);
        TimeTableChromosome child2 = makeChild(secondParent, firstParent, index, len);

        return List.of(child1, child2);
    }

    private TimeTableChromosome makeChild(TimeTableChromosome primary, TimeTableChromosome secondary, int index, int len) {
        int n = primary.getGenes().size();

        List<Integer> childGenes = new ArrayList<>(Collections.nCopies(n, -1));
        Set<Integer> used = new HashSet<>();

        for (int i = index; i < index + len; ++i) {
            int g = secondary.getGenes().get(i);
            childGenes.set(i, g);
            used.add(g);
        }

        for (int i = 0; i < n; ++i) {
            if (i >= index && i < index + len) continue;

            int g = primary.getGenes().get(i);
            if (!used.contains(g)) {
                childGenes.set(i, g);
                used.add(g);
            } else {
                int alt = secondary.getGenes().get(i);
                if (!used.contains(alt)) {
                    childGenes.set(i, alt);
                    used.add(alt);
                }
            }
        }

        TimeTableChromosome child = new TimeTableChromosome();
        child.setGenes(childGenes);
        child.evaluateFitness();
        return child;
    }
}
