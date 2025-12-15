package org.anisa;

import java.util.ArrayList;
import java.util.Random;

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

    public void generateRandomScores() {
        Random random = new Random();

        for (int i = 0; i < scores.size(); i++) {
            int range = random.nextInt(11);
            int generatedScore;

            if (range == 0) {
                generatedScore = random.nextInt(0, 60);
            } else if (range <= 2) {
                generatedScore = random.nextInt(60, 70);
            } else if (range <= 4) {
                generatedScore = random.nextInt(70, 80);
            } else if (range <= 8) {
                generatedScore = random.nextInt(80, 90);
            } else {
                generatedScore = random.nextInt(90, 101);
            }

            if (i < scores.size()) {
                scores.set(i, generatedScore);
            }
        }
    }
}
