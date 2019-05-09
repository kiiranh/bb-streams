package streamer;

import students.Student;

import java.util.List;
import java.util.stream.Collectors;

public class SchoolGroups {
    public static String getGrade(Student s) {
        double gpa = s.getGpa();
        if (gpa > 3) return "A";
        if (gpa > 2) return "B";
        return "C";
    }
    public static void main(String[] args) {
        List<Student> ls  = List.of(
                new Student("Fred", 3.2, "Math", "Physics"),
                new Student("Jim", 2.2, "Art"),
                new Student("Sheila", 3.9, "Math", "Physics", "Astrophysics", "Quantum Mechanics")
        );

        ls.stream()
                .collect(Collectors.groupingBy(SchoolGroups::getGrade,
                        Collectors.mapping(Student::getName,
                                Collectors.joining(", "))))
                .entrySet().stream()
                .forEach(e -> System.out.println("Students with grade " + e.getKey()
                + " are " + e.getValue()));
    }
}
