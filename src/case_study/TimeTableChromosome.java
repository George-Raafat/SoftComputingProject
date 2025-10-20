package case_study;

import genetic_algorithms.Chromosome;

import java.util.*;

public class TimeTableChromosome implements Chromosome {
    private Integer fitness;
    private List<Integer> genes;
    // slotLookUpTable??

    TimeTableChromosome() {

    }

    @Override
    public void mutate(double mutationRate) {

    }

    @Override
    public Chromosome copy() {
        TimeTableChromosome chromosome = new TimeTableChromosome();
        chromosome.fitness = this.fitness;
        chromosome.genes = List.copyOf(this.genes);
        return chromosome;
    }

    @Override
    public Integer getFitness() {
        return fitness;
    }

    private void evaluateFitness() {

    }
    @Override
    public List<Chromosome> crossoverWith1(Chromosome partner) {
        TimeTableChromosome p = (TimeTableChromosome) partner;
        int n = genes.size();

        Set<Integer> used = new HashSet<>();

        int len = 1 + (int) (Math.random() * (n - 1));
        int index = (int) (Math.random() * (n - len + 1));

        List<Integer> childGenes = new ArrayList<>(Collections.nCopies(n, -1));

        for (int i = index; i < index + len; ++i) {
            int g = p.genes.get(i);
            childGenes.set(i, g);
            used.add(g);
        }

        for (int i = 0; i < n; ++i) {
            if (i >= index && i < index + len) continue;

            int g = genes.get(i);
            if (!used.contains(g)) {
                childGenes.set(i, g);
                used.add(g);
            } else {
                int alt = p.genes.get(i);
                assert (!used.contains(alt));
                childGenes.set(i, alt);
                used.add(alt);
            }
        }

        TimeTableChromosome child = new TimeTableChromosome();
        child.genes = childGenes;
        child.evaluateFitness();

        return List.of(child);
    }
    @Override
    public List<Chromosome> crossoverWith2(Chromosome partner) {
        return List.of();
    } @Override
    public List<Chromosome> crossoverWith3(Chromosome partner) {
        return List.of();
    }

    @Override
    public int compareTo(Chromosome c) {
        return fitness - c.getFitness();
    }
}
