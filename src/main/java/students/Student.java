package students;

import java.util.List;

public class Student {
    private String name;
    private double gpa;
    private List<String> courses;

    public Student(String name, double gpa, String ... courses) {
        this.name = name;
        this.gpa = gpa;
        this.courses = List.of(courses);
    }

    public String getName() {
        return name;
    }

    public double getGpa() {
        return gpa;
    }

    public Student setGpa(double gpa) {
        return new Student(this.getName(),
                gpa,
                this.getCourses().toArray(new String[]{}));
    }

    public List<String> getCourses() {
        return courses;
    }

    @Override
    public String toString() {
        return "students.Student{" +
                "name='" + name + '\'' +
                ", gpa=" + gpa +
                ", courses=" + courses +
                '}';
    }
}
