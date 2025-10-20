import case_study.*;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        String[] lecturs = {"CS", "Math", "ML", "Compilers", "XYZ", "ABC"};
        List<LectureInfo> lecturesInfo = new ArrayList<>();
        Random random = new Random();
        for (String lectur : lecturs) {
            Set<Integer> availableSlots = new HashSet<>();
            int lecturesPerWeek = random.nextInt(3) + 1;
            while (availableSlots.size() < 5) {
                availableSlots.add(random.nextInt(36));
            }
            lecturesInfo.add(new LectureInfo(lectur, new ArrayList<>(availableSlots), lecturesPerWeek));
        }
        CrossoverStrategy crossoverStrategy = new FirstCrossover();
        TimeTableChromosomeFactory factory = new TimeTableChromosomeFactory(lecturesInfo, crossoverStrategy);
        TimeTableChromosome chromosome = factory.create();
        chromosome.printTimeTable();

        System.out.println("\n==============================\n");
        chromosome = factory.create();
        chromosome.printTimeTable();
    }
}
