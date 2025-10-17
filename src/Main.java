import java.util.*;

public class Main {

    // Map of slot -> assigned professor
    private final Map<String, String> slotToProfessor = new HashMap<>();

    // Track visited slots during DFS
    private Set<String> visited;

    /**
     * Try to assign each professor to a slot.
     *
     * @param professors List of professor names
     * @param availability Map of professor -> available slots
     * @return Map of professor -> assigned slot, or null if impossible
     */
    public Map<String, String> assignSlots(List<String> professors, Map<String, List<String>> availability) {
        slotToProfessor.clear();

        for (String prof : professors) {
            visited = new HashSet<>();
            if (!dfs(prof, availability)) {
                return null; // No feasible assignment
            }
        }

        // Convert slotToProfessor to professor -> slot mapping
        Map<String, String> result = new HashMap<>();
        for (Map.Entry<String, String> entry : slotToProfessor.entrySet()) {
            result.put(entry.getValue(), entry.getKey());
        }
        return result;
    }

    /**
     * DFS to find an available slot for a professor
     */
    private boolean dfs(String prof, Map<String, List<String>> availability) {
        List<String> slots = new ArrayList<>(availability.getOrDefault(prof, Collections.emptyList()));
        Collections.shuffle(slots);
        for (String slot : slots) {
            if (visited.contains(slot)) continue;
            visited.add(slot);

            // If slot is free or we can reassign current professor
            if (!slotToProfessor.containsKey(slot) || dfs(slotToProfessor.get(slot), availability)) {
                slotToProfessor.put(slot, prof);
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int numProfessors = 20;
        int days = 6;
        int slotsPerDay = 6;
        List<String> professors = new ArrayList<>();
        List<String> allSlots = new ArrayList<>();

        // Generate professors
        for (int i = 1; i <= numProfessors; i++) {
            professors.add("Prof" + i);
        }

        // Generate slots (e.g., Mon-1, Mon-2, ..., Sat-6)
        String[] dayNames = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        for (int d = 0; d < days; d++) {
            for (int s = 1; s <= slotsPerDay; s++) {
                allSlots.add(dayNames[d] + "-" + s);
            }
        }

        Random random = new Random();
        Map<String, List<String>> availability = new HashMap<>();

        // Assign 8-12 random available slots per professor
        for (String prof : professors) {
            List<String> shuffledSlots = new ArrayList<>(allSlots);
            Collections.shuffle(shuffledSlots, random);
            int numAvailable = 8 + random.nextInt(5); // 8-12 slots
            availability.put(prof, shuffledSlots.subList(0, numAvailable));
        }

        // Solve timetable
        Main solver = new Main();
        for (int i = 0; i < 5; i++) {
            Map<String, String> solution = solver.assignSlots(professors, availability);

            if (solution != null) {
                System.out.println("Feasible timetable found:\n");
                for (String prof : professors) {
                    System.out.println(prof + " -> " + solution.get(prof));
                }
            } else {
                System.out.println("No feasible timetable found.");
            }

            System.out.println("\n---------------------------\n");
        }
    }
}
