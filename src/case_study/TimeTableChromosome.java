package case_study;

import genetic_algorithms.Chromosome;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TimeTableChromosome implements Chromosome {
    public static List<LectureInfo> lecturesInfo;
    public static CrossoverStrategy crossoverStrategy;
    public static int[][] availabilityTable;
    private Integer fitness;
    private List<Integer> genes;

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

    public void evaluateFitness() {

    }

    @Override
    public List<Chromosome> crossoverWith(Chromosome partner) {
        return crossoverStrategy.crossover(this, (TimeTableChromosome) partner);
    }

    @Override
    public int compareTo(Chromosome c) {
        return fitness - c.getFitness();
    }

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
