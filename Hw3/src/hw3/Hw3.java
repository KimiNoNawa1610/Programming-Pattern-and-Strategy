/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw3;

import java.util.ArrayList;

/**
 *
 * @author votha
 */
public class Hw3 {
    

    abstract class Employee{
        
        private String Name;
        private int Tenure ;
        private double wages;
        
        public String getName(){
            return Name;
        }
        
        public int getTenure(){
            return Tenure;
        }
        
        public double getWage(){
            return wages;
        }
        
        public void setWages(double newwages){
            wages=newwages;
        }
        
        public void setTenure(int newtenure){
            Tenure=newtenure;
        }
        
        public abstract double getWages();
        
        public String toString(){
            return "I'm the employee, whee!";
        }
        
}
    class HourlyEmoloyee extends Employee{
        
        private int workedHour;
        private double payrate;

        @Override
        public double getWages(){
            return workedHour*payrate;
        }
        
    }
    
    class SalaryEmployee extends Employee{
        private double montlysalary;
        
        @Override
        public double getWages(){
            return montlysalary;
        }
        
    }
    
    class CommissionEmployee extends Employee{
        private double basesalary;
        private double monthlysales;
        private double commission;
        
        @Override
        public double getWages(){
            return basesalary+monthlysales+commission;
        }
    }
    
    class SuperviorEmployee extends Employee{
        private ArrayList<Employee> Employees;
        private double bonuspercentage;
        
        @Override
        public double getWages(){
            double maxwage=0;
            for(Employee i:Employees){
                if(i.getWages()>maxwage){
                    maxwage=i.getWages();
                }
            }
            return maxwage+maxwage*bonuspercentage;
        }
    }
    class SeniorSalaryEmployee extends Employee{
        
        @Override
        public double getWages(){
            double bonus=1.03;
            for(int i=0;i<this.getTenure();i++){
                bonus*=bonus;
            }
            return this.getWage()*bonus;
        }
                
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
