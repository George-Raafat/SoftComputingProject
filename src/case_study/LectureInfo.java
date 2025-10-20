package case_study;

import java.util.List;

public record LectureInfo(String course, List<Integer> availableSlots, int lecturesPerWeek) {
}