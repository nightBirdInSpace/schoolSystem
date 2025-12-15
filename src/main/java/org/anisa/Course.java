package org.anisa;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode
@Getter
@Setter
public class Course {
    private String courseId;
    private String courseName;
    private double credits;
    private Department department;
    private ArrayList<Assignment>  assignments;
    private ArrayList<Student> registeredStudents;
    private static int nextId = 1;

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

        for (Assignment assignment : assignments) {
            sumWeight += assignment.getWeight();
        }

        return Math.abs(sumWeight - 100) < 0.001;
    }

    public boolean registerStudent(Student student) {
        if (student == null || registeredStudents.contains(student)) {
            return false;
        }

        registeredStudents.add(student);

        for (Assignment assignment : assignments) {
            assignment.getScores().add(null);
        }

        return true;
    }

    public int[] calcStudentsAverage() {
        int numStudents = registeredStudents.size();

        if (numStudents == 0) {
            return new int[0];
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

    public void generateScores() {
        for (Assignment assignment : assignments) {
            assignment.generateRandomScores();
        }

        calcStudentsAverage();
    }

    public void displayScores() {
        System.out.println("--- Course Assignments & Weights ---");

        for (Assignment a : assignments) {
            System.out.printf("%s: %.1f%%\n", a.getAssignmentId(), a.getWeight());
        }

        System.out.println("----------------------------------");

        System.out.println("--- Student Scores ---");
        int[] finalScore = calcStudentsAverage();

        for (int m = 0; m < registeredStudents.size(); m++) {
            System.out.println("Student: " + registeredStudents.get(m).toSimplifiedString());

            for (Assignment a : assignments) {
                Integer score = a.getScores().get(m);
                System.out.printf("  - %s Score: %s\n", a.getAssignmentId(), score);
            }

            System.out.printf("  - FINAL Average: %s\n", calcAssignmentAvg());
            System.out.println();
        }

        System.out.println("--- Assignment Averages ---");

        for (Assignment a : assignments) {
            System.out.printf("%s Average: %.2f\n", a.getAssignmentId(), calcAssignmentAvg());
        }
    }

    public String toSimplifiedString() {
        return String.format("%s | %s | %.1f Credits | %s", courseId, courseName, credits, department.getDepartmentName());
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId='" + courseId + '\'' +
                ", courseName='" + courseName + '\'' +
                ", department=" + department.getDepartmentName() +
                ", assignments=" + assignments +
                ", registeredStudents=" + registeredStudents +
                ", isAssignmentWeightValid=" + isAssignmentWeightValid() +
                '}';
    }
}
