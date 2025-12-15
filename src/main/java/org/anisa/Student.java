package org.anisa;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@EqualsAndHashCode
@Getter
@Setter
public class Student {
    private final String studentId;
    private String studentName;
    private final Gender gender;
    private Address address;
    private Department department;
    private ArrayList<Course> registeredCourses;
    private static int nextId = 1;

    public Student(String studentName, Gender gender, Address address, Department department) {
        this.studentId = String.format("S%06d", nextId++);
        this.studentName = Util.toTitleCase(studentName);
        this.gender = gender;
        this.address = address;
        this.department = department;
        this.registeredCourses = new ArrayList<>();
    }

    public boolean registeredCourse (Course course) {
        if (course == null || registeredCourses.contains(course)) {
            return false;
        }

        registeredCourses.add(course);

        course.registerStudent(this);

        return true;
    }

    public boolean dropCourse(Course course) {
        if (course == null || !registeredCourses.contains(course)) {
            return false;
        }

        registeredCourses.remove(course);

        int studentIndex = course.getRegisteredStudents().indexOf(this);

        if (studentIndex != -1) {
            course.getRegisteredStudents().remove(studentIndex);
        }

        return true;
    }

    public String toSimplifiedString() {
        String deptName = department != null && department.getDepartmentName() != null ? department.getDepartmentName() : "no answer";
        return String.format("%s | %s | %s", studentId, studentName, deptName);
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", gender=" + gender +
                ", address=" + address +
                ", department=" + department +
                ", registeredCourses=" + registeredCourses +
                '}';
    }
}
