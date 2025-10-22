package genetic_algorithms;

public class PermutationCrossOver<T extends Chromosome> implements CrossoverStrategy<T> {
    @Override
    public java.util.List<T> crossover(T parent1, T parent2) {
        int size = parent1.getGenes().size();
        int point1 = (int) (Math.random() * size);
        int point2 = (int) (Math.random() * size);
        if (point1 > point2) {
            int temp = point1;
            point1 = point2;
            point2 = temp;
        }

        T child1 = (T) parent1.copy();
        T child2 = (T) parent2.copy();

        java.util.Set<Object> segment1 = new java.util.HashSet<>(child1.getGenes().subList(point1, point2));
        java.util.Set<Object> segment2 = new java.util.HashSet<>(child2.getGenes().subList(point1, point2));

        int index1 = point2;
        int index2 = point2;

        for (int i = 0; i < size; i++) {
            int idx = (point2 + i) % size;

            if (!segment1.contains(child2.getGenes().get(idx))) {
                child1.getGenes().set(index1 % size, child2.getGenes().get(idx));
                index1++;
            }

            if (!segment2.contains(child1.getGenes().get(idx))) {
                child2.getGenes().set(index2 % size, child1.getGenes().get(idx));
                index2++;
            }
        }

        return java.util.List.of(child1, child2);
    }
}
