package com.company;

import java.util.ArrayList;

public class Student {

    private String name;
    private ArrayList<Double> grades;

    public Student(String name) {
        this.name = name;
        grades = new ArrayList<>();
    }

    public void addGrade(double grade) {
        grades.add(grade);
    }

    // calculates final grade as an average
    // of all the student's grades
    public double finalGrade() {
        double sum = 0.0;

        for (int i = 1; i < grades.size(); i++) {
            sum += grades.get(i);
        }

        return sum/grades.size();
    }

    public String getName() {
        return name;
    }
}
