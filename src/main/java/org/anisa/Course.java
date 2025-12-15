package org.anisa;

import java.util.ArrayList;
import java.util.List;

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

        for (int h = 0; h < assignments.size(); h++) {
            sumWeight += assignments.get(h).getWeight();
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

    public int[] calcStudentsAverage() {
        int numStudents = registeredStudents.size();

        if (numStudents == 0) {
            return new int[];
        }

        int[] averages  = new int[numStudents];
        for (int k = 0; k < numStudents; k++) {
            double weightedSum = 0;

            for (Assignment assignment : assignments) {
                List<Integer> scores = assignment.getScores();
                if (k < scores.size() && scores.get(k) != null) {
                    double score = scores.get(k);
                    double weight = assignment.getWeight();
                    weightedSum += (weight * score / 100);
                }
            }

            int finalAvg = (int) Math.round(weightedSum);
            averages[k] = finalAvg;
        }

        return averages;
    }

    public boolean addAssignment(String assignmentName, double weight, int maxScore) {
        Assignment newAssignment = new Assignment(assignmentName, weight);
        assignments.add(newAssignment);

        for (int l = 0; l < registeredStudents.size(); l++) {
            newAssignment.getScores().add(null);
        }

        return true;
    }
}
