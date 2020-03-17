/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw3;

import java.util.ArrayList;

/**
     * SupervisorEmployee class that inherits that Employee class
     */
    public class SupervisorEmployee extends Employee{
        private ArrayList<Employee> Employees;
        private double bonuspercentage;
        /**
         * The modified getWages method 
         * @return the total wage for this employee
         */
        @Override
        public double getWages(){
            double maxwage=0;
            for(Employee i:Employees){
                if(i.getWages()>maxwage){
                    maxwage=i.getWages();
                }
            }
            return maxwage+maxwage*(bonuspercentage/100);
        }
        /**
         * mutator method of bonuspercentage field
         * @return bonuspercentage
         */
        public double getbonus(){
            return bonuspercentage;
        }
        /**
         * The constructor of the SupervisorEmployee class
         * @param Name
         * @param Tenure
         * @param supervise
         * @param bonus 
         */
        
        public SupervisorEmployee(String Name,int Tenure,ArrayList<Employee> supervise,double bonus){
            super(Name, Tenure,0);
            Employees=supervise;
            bonuspercentage=bonus;
        }
        /**
         * toString method to represent the SuperviosrEmployee class
         * @return the name and the relative field of the salary
         */
        @Override
        public String toString(){
            return "I'm "+this.getName()+". I'm an SuperviorEmoloyee. "+"My bonus is "+getbonus()+
                    "%. I made "+ " $"+String.format("%.2f",getWages())+"!";
        }
        
    }
