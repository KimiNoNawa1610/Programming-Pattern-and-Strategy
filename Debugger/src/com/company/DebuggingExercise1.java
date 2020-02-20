package com.company;

public class DebuggingExercise1 {

    public static class Thing {
        public Thing(){}

        @Override
        public String toString() {
            return "Thing";
        }
    }

    public static void main(String[] args) {

        Thing t = makeAThing();
        printThis(t);
    }
    

    private static Thing makeAThing() {
        Thing t = new Thing();
        System.out.println(t);

        return t;
    }

    private static void printThis(Thing t) {

        System.out.println(t.toString());
    }
}
