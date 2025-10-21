import case_study.*;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        List<LectureInfo> lecturesInfo = getLectureInfos();
        CrossoverStrategy crossoverStrategy = new FirstCrossover();
        TimeTableChromosomeFactory factory = new TimeTableChromosomeFactory(lecturesInfo, crossoverStrategy);
        TimeTableChromosome chromosome = factory.create();
        chromosome.printTimeTable();
        System.out.println("\n==============================\n");
        chromosome.mutate(0.7);
        chromosome.printTimeTable();

//        System.out.println("\n==============================\n");
//        chromosome = factory.create();
//        chromosome.printTimeTable();
    }

    private static List<LectureInfo> getLectureInfos() {
        String[] lectures = {"CS", "Math", "ML", "Compilers", "XYZ", "ABC"};
        List<LectureInfo> lecturesInfo = new ArrayList<>();
        Random random = new Random();
        for (String lecture : lectures) {
            Set<Integer> availableSlots = new HashSet<>();
            int lecturesPerWeek = random.nextInt(3) + 1;
            while (availableSlots.size() < 5) {
                availableSlots.add(random.nextInt(36));
            }
            lecturesInfo.add(new LectureInfo(lecture, new ArrayList<>(availableSlots), lecturesPerWeek));
        }
        return lecturesInfo;
    }
}
