package genetic_algorithms.crossover;

import genetic_algorithms.Chromosome;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PermutationCrossOver<ChomoT extends Chromosome<ChomoT, GeneT>, GeneT> implements CrossoverStrategy<ChomoT, GeneT> {
    @Override
    public List<ChomoT> crossover(ChomoT parent1, ChomoT parent2) {
        int size = parent1.getGenes().size();
        int point1 = (int) (Math.random() * size);
        int point2 = (int) (Math.random() * size);
        if (point1 > point2) {
            int temp = point1;
            point1 = point2;
            point2 = temp;
        }

        ChomoT child1 = parent1.copy();
        ChomoT child2 = parent2.copy();

        Set<GeneT> segment1 = new HashSet<>(child1.getGenes().subList(point1, point2));
        Set<GeneT> segment2 = new HashSet<>(child2.getGenes().subList(point1, point2));

        int index1 = point2;
        int index2 = point2;

        for (int i = 0; i < size; i++) {
            int idx = (point2 + i) % size;

            if (!segment1.contains(child2.getGenes().get(idx))) {
                child1.getGenes().set(index1 % size, child2.getGenes().get(idx));
                index1++;
            }

            if (!segment2.contains(child1.getGenes().get(idx))) {
                child2.getGenes().set(index2 % size, child1.getGenes().get(idx));
                index2++;
            }
        }
        List<ChomoT> children = new ArrayList<>(2);
        children.add(child1);
        children.add(child2);
        return children;
    }
}
