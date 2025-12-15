package org.anisa;

import java.util.ArrayList;

public class Assignment {
    private String assignmentId;
    private String assignmentName;
    private double weight;
    private ArrayList<Integer> scores;
    private static int nextId = 1;

    public Assignment(String assignmentName, double weight) {
        this.assignmentId = String.valueOf(nextId++);
        this.assignmentName = assignmentName;
        this.weight = weight;
        this.scores = new  ArrayList<>();
    }

    public void calcAssignmentAvg() {
        int sum = 0;
        int count = 0;
        for (Integer i : scores) {
            sum += i;
            count++;
        }
    }
}
