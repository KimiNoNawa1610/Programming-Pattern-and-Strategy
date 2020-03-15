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
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Hw3 hw3=new Hw3();
        HourlyEmployee em1=hw3.new HourlyEmployee("Pam Beesly",5,40,10);
        System.out.println(em1);
        
       
    }
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
        
        public Employee(String name, int tenure){
            Name=name;
            Tenure=tenure;    
        }
        
        public void setWages(double wage){
            wages=wage;
        }
        
        public abstract double getWages();
        
        @Override
        public String toString(){
            return "I'm "+this.getName()+" I'm the employee, whee!";
        }
        
}
    public class HourlyEmployee extends Employee{
        
        private int workedHour;
        private double payrate;

        @Override
        public double getWages(){
            return workedHour*payrate;
        }
        
        public int getWorkedHour(){
            return workedHour;
        }
        
        public double getPayRate(){
            return payrate;
        }
        
        public HourlyEmployee(String Name,int Tenure,int Hour, double Payrate){
            super(Name, Tenure);
            workedHour=Hour;
            payrate=Payrate;
        }
        
        @Override
        public String toString(){
            return "I'm "+this.getName()+" I'm an HourlyEmoloyee. I worked "+getWorkedHour()+" hours at a rate of "+getPayRate()+
                    " dollars per hour. I made "+ " $"+getWages()+"!";
        }
        
    }
    
    public class SalaryEmployee extends Employee{
        private double montlysalary;
        
        @Override
        public double getWages(){
            return montlysalary;
        }
        
        public SalaryEmployee(String Name,int Tenure,double msalary){
            super(Name, Tenure);
            montlysalary=msalary;
        }
        
        @Override
        public String toString(){
            return "I'm "+this.getName()+" I'm an MonthlyEmoloyee. "+" I made "+ " $"+getWages()+"!";
        }
        
       
        
    }
    
    public class CommissionEmployee extends Employee{
        private double basesalary;
        private double monthlysales;
        private double commission;
        
        @Override
        public double getWages(){
            return basesalary+monthlysales+commission;
        }
        
        public double getBasesalary(){
            return basesalary;
        }
        
        public double getMonthlysales(){
            return monthlysales;
        }
        
        public double getCommission(){
            return commission;
        }
        
        public CommissionEmployee(String Name, int Tenure,double base, double sales, double commit){
            super(Name, Tenure);
            basesalary=base;
            monthlysales=sales;
            commission=commit;
        }
        
        @Override
        public String toString(){
            return "I'm "+this.getName()+" I'm an CommissionEmployee. My base salary is: "+getBasesalary()+". My monthly sale is: "+getMonthlysales()+
                    ". My commission is: "+getCommission()+". I made "+" $"+getWages()+"!";
        }
        
        
    }
    
    public class SuperviorEmployee extends Employee{
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
        
        public SuperviorEmployee(String Name,int Tenure,ArrayList<Employee> supervise,double bonus){
            super(Name, Tenure);
            Employees=supervise;
            bonuspercentage=bonus;
        }
        
        @Override
        public String toString(){
            return "I'm "+this.getName()+" I'm an SuperviorEmoloyee. "+" I made "+ " $"+getWages()+"!";
        }
        
    }
    public class SeniorSalaryEmployee extends Employee{
        
        @Override
        public double getWages(){
            double bonus=1.03;
            for(int i=0;i<this.getTenure();i++){
                bonus*=bonus;
            }
            return this.getWage()*bonus;
        }
        
        public SeniorSalaryEmployee(String Name, int Tenure, double wage ){
            super(Name,Tenure);
            this.setWages(wage);
        }
        
        @Override
        public String toString(){
            return "I'm "+this.getName()+" I'm an SeniorSalaryEmployee. "+" I made "+ " $"+getWages()+"!";
        }  
    }

    
    
}
