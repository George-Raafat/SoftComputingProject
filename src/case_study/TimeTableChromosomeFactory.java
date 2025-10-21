package case_study;

import java.util.*;

public class TimeTableChromosomeFactory {
    private final int slotsPerWeek = 36;

    private final List<List<Integer>> availabilityTable;
    private final int[] slotToProfessor = new int[slotsPerWeek];

    // Track visited slots during DFS
    private boolean[] visited;

    public TimeTableChromosomeFactory(List<LectureInfo> lectures, CrossoverStrategy crossoverStrategy) {
        int numLectures = 0;
        for (LectureInfo lecture : lectures) {
            numLectures += lecture.lecturesPerWeek();
        }

        availabilityTable = new ArrayList<>(numLectures);
        for (LectureInfo lecture : lectures) {
            for (int j = 0; j < lecture.lecturesPerWeek(); ++j) {
                availabilityTable.add(lecture.availableSlots());
            }
        }
        TimeTableChromosome.lecturesInfo = lectures;
        TimeTableChromosome.availabilityTable = availabilityTable;
        TimeTableChromosome.crossoverStrategy = crossoverStrategy;
    }

    private boolean dfs(int prof) {
        Collections.shuffle(availabilityTable.get(prof));
        for (int i = 0; i < availabilityTable.get(prof).size(); ++i) {
            int slot = availabilityTable.get(prof).get(i);
            if (visited[slot]) continue;
            visited[slot] = true;

            // If slot is free or we can reassign current professor
            if (slotToProfessor[slot] == -1 || dfs(slotToProfessor[slot])) {
                slotToProfessor[slot] = prof;
                return true;
            }
        }
        return false;
    }

    private List<Integer> assignSlots() {
        Arrays.fill(slotToProfessor, -1);

        for (int i = 0; i < availabilityTable.size(); ++i) {
            visited = new boolean[slotsPerWeek];
            if (!dfs(i)) {
                return null; // No feasible assignment
            }
        }

        // Convert slotToProfessor to professor -> slot mapping
        List<Integer> result = new ArrayList<>(Collections.nCopies(availabilityTable.size(), -1));
        for (int i = 0; i < slotToProfessor.length; ++i) {
            if (slotToProfessor[i] != -1) {
                result.set(slotToProfessor[i], i);
            }
        }
        return result;
    }

    public TimeTableChromosome create() {
        List<Integer> genes = assignSlots();
        if (genes == null) {
            return null;
        }
        TimeTableChromosome chromosome = new TimeTableChromosome();
        chromosome.setGenes(genes);
        chromosome.evaluateFitness();
        return chromosome;
    }
}
