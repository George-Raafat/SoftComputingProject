package case_study;

import java.util.*;

public class TimeTableChromosomeFactory {
    private final int slotsPerWeek = 36;

    private final int[][] availabilityTable;
    private final int[] slotToProfessor = new int[slotsPerWeek];
    private final Random random = new Random();
    // Track visited slots during DFS
    private boolean[] visited;

    public TimeTableChromosomeFactory(List<LectureInfo> lectures, CrossoverStrategy crossoverStrategy) {
        int numLecturs = 0;
        for (LectureInfo lecture : lectures) {
            numLecturs += lecture.lecturesPerWeek();
        }

        availabilityTable = new int[numLecturs][];
        int currentIndex = 0;
        for (LectureInfo lecture : lectures) {
            int[] availableSlots = lecture.availableSlots().stream().mapToInt(Integer::intValue).toArray();
            for (int j = 0; j < lecture.lecturesPerWeek(); ++j) {
                availabilityTable[currentIndex] = availableSlots;
                ++currentIndex;
            }
        }
        TimeTableChromosome.lecturesInfo = lectures;
        TimeTableChromosome.availabilityTable = availabilityTable;
        TimeTableChromosome.crossoverStrategy = crossoverStrategy;
    }

    private boolean dfs(int prof) {
        shuffleArray(availabilityTable[prof]);
        for (int i = 0; i < availabilityTable[prof].length; ++i) {
            int slot = availabilityTable[prof][i];
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

    private void shuffleArray(int[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }

    private List<Integer> assignSlots() {
        Arrays.fill(slotToProfessor, -1);

        for (int i = 0; i < availabilityTable.length; ++i) {
            visited = new boolean[slotsPerWeek];
            if (!dfs(i)) {
                return null; // No feasible assignment
            }
        }

        // Convert slotToProfessor to professor -> slot mapping
        List<Integer> result = new ArrayList<>(Collections.nCopies(availabilityTable.length, -1));
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
