package superiterable;

import students.Student;

import java.util.List;

public class UseSuperIterable {
    public static void main(String[] args) {
        SuperIterable<String> sis = new SuperIterable<>(
                List.of("Fred", "Jim", "Sheila")
        );

//        for (String s : sis) {
//            System.out.println(s);
//        }

        sis
                .filter(s -> s.length() > 3)
                .map(s -> s.toUpperCase())
                .map(s -> s.length())
                .forEach(s -> System.out.println(s));

        new SuperIterable<Student>(List.of(
                new Student("Fred", 3.2, "Math", "Physics"),
                new Student("Jim", 2.2, "Art"),
                new Student("Sheila", 3.9, "Math", "Physics", "Astrophysics", "Quantum Mechanics")
        ))
                .filter(s -> s.getGpa() > 3)
                .flatMap(s -> new SuperIterable<>(s.getCourses()))
                .forEach(s -> System.out.println(s));
        System.out.println("-----------------");
        List.of(
                new Student("Fred", 3.2, "Math", "Physics"),
                new Student("Jim", 2.2, "Art"),
                new Student("Sheila", 3.9, "Math", "Physics", "Astrophysics", "Quantum Mechanics")
        ).stream()
                .filter(s -> s.getGpa() > 3)
                .flatMap(s -> s.getCourses().stream())
                .forEach(s -> System.out.println(s));
    }
}
