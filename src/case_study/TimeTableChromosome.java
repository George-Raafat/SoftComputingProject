package case_study;

import genetic_algorithms.Chromosome;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TimeTableChromosome implements Chromosome<Integer> {
    public static List<LectureInfo> lecturesInfo;
    public static CrossoverStrategy crossoverStrategy;
    public static List<List<Integer>> availabilityTable;
    private Integer fitness;
    private List<Integer> genes;

    TimeTableChromosome() {}

    @Override
    public void mutate(double mutationRate) {
        boolean[] usedSlots = new boolean[36];
        for (int gene : genes) {
            usedSlots[gene] = true;
        }
        for (int i = 0; i < genes.size(); ++i) {
            if (Math.random() < mutationRate) {
                Collections.shuffle(availabilityTable.get(i));
                for (int slot : availabilityTable.get(i)) {
                    if (!usedSlots[slot]) {
                        usedSlots[genes.get(i)] = false;
                        genes.set(i, slot);
                        usedSlots[slot] = true;
                        break;
                    }
                }
            }
        }
        evaluateFitness();
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

    public void evaluateFitness() {
        boolean[] usedSlots = new boolean[36];
        for (int gene : genes) {
            usedSlots[gene] = true;
        }
        int cost = 0;
        int numDays = 0;
        for (int i = 0; i < 6; i++){
            int j = i * 6;
            int k = j + 5;
            while(j <= k && !usedSlots[j]){
                ++j;
            }
            while(k >= j && !usedSlots[k]){
                --k;
            }
            int interval = k - j + 1;
            if (interval > 0){
                ++numDays;
            }
            cost += interval * interval;
        }
        cost += numDays * 20;
        fitness = 340 - cost;
    }

    @Override
    public List<Chromosome> crossoverWith(Chromosome partner) {
        return crossoverStrategy.crossover(this, (TimeTableChromosome) partner);
    }

    @Override
    public int compareTo(Chromosome c) {
        return fitness - c.getFitness();
    }

    @Override
    public List<Integer> getGenes() {
        return genes;
    }

    public void setGenes(List<Integer> genes) {
        this.genes = genes;
    }

    public void printTimeTable() {
        int longestCourseName = 0;
        List<LectureInfo> lectures = new ArrayList<>();
        for (LectureInfo lecture : lecturesInfo) {
            longestCourseName = Math.max(longestCourseName, lecture.course().length());
            for (int i = 0; i < lecture.lecturesPerWeek(); ++i) {
                lectures.add(lecture);
            }
        }
        int[] slotToLecture = new int[36];
        Arrays.fill(slotToLecture, -1);
        for (int i = 0; i < genes.size(); ++i) {
            slotToLecture[genes.get(i)] = i;
        }
        String[] days = {"Sat", "Sun", "Mon", "Tue", "Wed", "Thu"};
        for (int day = 0; day < 6; ++day) {
            System.out.print(days[day] + ": |");
            for (int slot = 0; slot < 6; ++slot) {
                int globalSlot = day * 6 + slot;
                int lectureIdx = slotToLecture[globalSlot];
                if (lectureIdx == -1) {
                    System.out.print(" ".repeat(longestCourseName + 4) + " |");
                    continue;
                }
                LectureInfo lecture = lectures.get(lectureIdx);
                System.out.print(" " + (slot + 1) + ". " + lecture.course() + " ".repeat(longestCourseName - lecture.course().length()) + " |");
            }
            System.out.println();
        }
    }
}
