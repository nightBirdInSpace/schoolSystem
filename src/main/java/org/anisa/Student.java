package org.anisa;

import java.util.ArrayList;

public class Student {
    private String studentId;
    private String studentName;
    private Gender gender;
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

    public boolean registerCourse (Course course) {
        if (couse == null || registeredCourses.contains(course)) {
            return false;
        }

        registeredCourses.add(course);

        course.registeredStudent(this);

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
    }
}
