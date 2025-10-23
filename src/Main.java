import case_study.FirstCrossover;
import case_study.LectureInfo;
import case_study.TimeTableChromosome;
import case_study.TimeTableChromosomeFactory;
import genetic_algorithms.GeneticAlgorithm;
import genetic_algorithms.replacement.SteadyStateReplacement;
import genetic_algorithms.selection.RouletteSelection;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        List<LectureInfo> lecturesInfo = getLectureInfos();
        TimeTableChromosomeFactory factory = new TimeTableChromosomeFactory(lecturesInfo);
        GeneticAlgorithm<TimeTableChromosome, Integer> geneticAlgorithm = new GeneticAlgorithm<>(
                factory::create,
                new RouletteSelection<>(),
                new FirstCrossover(),
                new SteadyStateReplacement<>()
        );
        for (int i = 0; i < 3; i++) {
            geneticAlgorithm.run();
            TimeTableChromosome firstBest = geneticAlgorithm.getFirstBest();
            TimeTableChromosome best = geneticAlgorithm.getBestSolution();
            System.out.println("First Best Fitness: " + firstBest.getFitness());
            firstBest.printTimeTable();
            System.out.println("\n===================================\n");

            System.out.println("Best Fitness: " + best.getFitness());
            best.printTimeTable();
            System.out.println("\n\n|||||||||||||||||||||||||||||||||||||||||||||||\n\n");
        }
    }

    private static List<LectureInfo> getLectureInfos() {
        String[] lectures = {"CS", "Math", "ML", "Compilers", "XYZ", "ABC"};
        List<LectureInfo> lecturesInfo = new ArrayList<>();
        Random random = new Random();
        for (String lecture : lectures) {
            Set<Integer> availableSlots = new HashSet<>();
            int lecturesPerWeek = 3;
            while (availableSlots.size() < 10) {
                availableSlots.add(random.nextInt(36));
            }
            lecturesInfo.add(new LectureInfo(lecture, new ArrayList<>(availableSlots), lecturesPerWeek));
        }
        return lecturesInfo;
    }
}
