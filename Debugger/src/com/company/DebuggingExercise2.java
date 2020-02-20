package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class DebuggingExercise2 {

    public static void main(String[] args) {
        System.out.print("How many students in the class? ");

        Scanner in = new Scanner(System.in);

        int numStudents = in.nextInt();
        ArrayList<Student> classRoster = new ArrayList<>();

        System.out.print("How many assignments in the class? ");
        int numAssignments = in.nextInt();

        System.out.println("Enter student names: ");
        in.nextLine();

        for (int i = 0; i < numStudents; i++) {
            classRoster.add(new Student(in.nextLine()));
        }

        for (Student s : classRoster) {
            System.out.println("Enter grades for " + s.getName() + ": ");

            for (int i = 0; i < numAssignments; i++) {
                s.addGrade(in.nextDouble());
            }
        }

        double classAverage = 0.0;

        for (Student s : classRoster) {
            classAverage += s.finalGrade();
        }

        classAverage /= numStudents;

        System.out.println("The class average is: " + classAverage);
    }
}
