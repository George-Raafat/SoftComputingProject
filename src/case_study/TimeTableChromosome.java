package case_study;

import genetic_algorithms.Chromosome;

import java.util.List;

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
    public List<Chromosome> crossoverWith(Chromosome partner) {
        return List.of();
    }

    @Override
    public int compareTo(Chromosome c) {
        return fitness - c.getFitness();
    }
}
