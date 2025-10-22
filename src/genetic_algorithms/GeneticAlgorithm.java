package genetic_algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class GeneticAlgorithm<ChomoT extends Chromosome<ChomoT, GeneT>, GeneT> {
    private final Random random = new Random();
    private List<ChomoT> population = new ArrayList<>();
    private int populationSize = 50;
    private int generations = 100;
    private double crossoverRate = 0.8;
    private double mutationRate = 0.02;
    private Supplier<ChomoT> chromosomeFactory;
    private SelectionStrategy<ChomoT> selectionStrategy;
    private CrossoverStrategy<ChomoT, GeneT> crossoverStrategy;
    private ChomoT firstBest;
    private ChomoT best;

    public GeneticAlgorithm(Supplier<ChomoT> chromosomeFactory, SelectionStrategy<ChomoT> selectionStrategy, CrossoverStrategy<ChomoT, GeneT> crossoverStrategy) {
        this.chromosomeFactory = chromosomeFactory;
        this.selectionStrategy = selectionStrategy;
        this.crossoverStrategy = crossoverStrategy;
    }

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

    public void setChromosomeFactory(Supplier<ChomoT> factory) {
        this.chromosomeFactory = factory;
    }

    public void setSelectionStrategy(SelectionStrategy<ChomoT> strategy) {
        this.selectionStrategy = strategy;
    }

    public void setCrossoverStrategy(CrossoverStrategy<ChomoT, GeneT> crossoverStrategy) {
        this.crossoverStrategy = crossoverStrategy;
    }

    public void run() {
        initializePopulation();

        firstBest = Collections.max(population);
        best = firstBest;

        for (int g = 0; g < generations; g++) {
            List<ChomoT> newPopulation = new ArrayList<>();

            while (newPopulation.size() < populationSize) {
                ChomoT parent1 = selectionStrategy.select(population);
                ChomoT parent2 = selectionStrategy.select(population);

                List<ChomoT> children;
                if (random.nextDouble() < crossoverRate) {
                    children = crossoverStrategy.crossover(parent1, parent2);
                } else {
                    children = new ArrayList<>();
                    children.add(parent1.copy());
                }

                for (ChomoT child : children) {
                    child.mutate(mutationRate);
                    newPopulation.add(child);
                    if (newPopulation.size() >= populationSize) break;
                }
            }

            population = newPopulation;
            ChomoT currentMax = Collections.max(population);
            if (currentMax.getFitness() > best.getFitness()) {
                best = currentMax;
            }
//            System.out.println("Generation " + g + " best fitness: " + currentMax.getFitness());
        }
    }

    private void initializePopulation() {
        population.clear();
        for (int i = 0; i < populationSize; i++) {
            population.add(chromosomeFactory.get());
        }
    }

    public ChomoT getFirstBest() {
        return firstBest;
    }

    public ChomoT getBestSolution() {
        return best;
    }
}