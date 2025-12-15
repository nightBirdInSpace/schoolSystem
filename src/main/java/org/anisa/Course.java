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

    public boolean isAssignmentWeightValid() {
        double sumWeight = 0;

        for (int i = 0; i < assignments.size(); i++) {
            sumWeight += assignments.get(i).getWeight();
        }

        return Math.abs(sumWeight - 100) < 0.001;
    }

    public boolean registeredStudent(Student student) {
        if (student == null || registeredStudents.contains(student)) {
            return false;
        }

        registeredStudents.add(student);

        for (Assignment assignment : assignments) {
            assignment.getScores().add(null);
        }

        for (Assignment assignment : assignments) {
            assignment.calcAssignmentAvg().add(null);
        }

        return true;
    }
}
