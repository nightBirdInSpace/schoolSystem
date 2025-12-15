package org.anisa;

import java.util.ArrayList;

public class Course {
    private String courseId;
    private String courseName;
    private double credits;
    private Department department;
    private ArrayList<Assignment>  assignments;
    private ArrayList<Student> registeredStudents;
    private static int nextId;

    public Course(String courseName, double credits, Department department) {
        String twoDigitCourseId = String.format("%02d", nextId++);
        this.courseId = "C-" + department.getDepartmentId() + "-" + twoDigitCourseId;
        this.courseName = Util.toTitleCase(courseName);
        this.credits = credits;
        this.department = department;
        this.assignments = new ArrayList<>();
        this.registeredStudents = new ArrayList<>();
    }


}
