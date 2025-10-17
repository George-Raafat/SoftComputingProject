package genetic_algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class GeneticAlgorithm {
    private final Random random = new Random();
    private List<Chromosome> population = new ArrayList<>();
    private int populationSize = 50;
    private int generations = 100;
    private double crossoverRate = 0.8;
    private double mutationRate = 0.02;
    private Supplier<Chromosome> chromosomeFactory;

    public void setPopulationSize(int size) {
        this.populationSize = size;
    }

    public void setGenerations(int gens) {
        this.generations = gens;
    }

    public void setCrossoverRate(double rate) {
        this.crossoverRate = rate;
    }

    public void setMutationRate(double rate) {
        this.mutationRate = rate;
    }

    public void setChromosomeFactory(Supplier<Chromosome> factory) {
        this.chromosomeFactory = factory;
    }

    public void run() {
        initializePopulation();

        for (int g = 0; g < generations; g++) {
            List<Chromosome> newPopulation = new ArrayList<>();

            while (newPopulation.size() < populationSize) {
                Chromosome parent1 = roulette();
                Chromosome parent2 = roulette();

                List<Chromosome> children;
                if (random.nextDouble() < crossoverRate) {
                    children = parent1.crossoverWith(parent2);
                } else {
                    children = List.of(parent1.copy());
                }

                for (Chromosome child : children) {
                    child.mutate(mutationRate);
                    newPopulation.add(child);
                    if (newPopulation.size() >= populationSize) break;
                }
            }

            population = newPopulation;
            population.sort(Collections.reverseOrder());
            System.out.println("Generation " + g + " best fitness: " + population.get(0).getFitness());
        }
    }

    private void initializePopulation() {
        population.clear();
        for (int i = 0; i < populationSize; i++) {
            population.add(chromosomeFactory.get());
        }
    }

    public Chromosome getBestSolution() {
        return population.get(0);
    }

    private Chromosome roulette() {
        double totalFitness = 0;
        for (Chromosome c : population) totalFitness += c.getFitness();

        double randomValue = random.nextDouble() * totalFitness;
        double sum = 0;
        for (Chromosome c : population) {
            sum += c.getFitness();
            if (sum >= randomValue) return c;
        }
        return population.get(population.size() - 1);
    }
}