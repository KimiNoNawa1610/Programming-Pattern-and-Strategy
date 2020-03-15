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
        ArrayList<Employee> ems=new ArrayList<>();
        Hw3 hw3=new Hw3();
        HourlyEmployee em1=hw3.new HourlyEmployee("Pam Beesly",5,40,10);
        System.out.println(em1);
        System.out.println(" ");
        SalaryEmployee em2=hw3.new SalaryEmployee("Angela Martin",10,4500);
        System.out.println(em2);
        System.out.println(" ");
        CommissionEmployee em3=hw3.new CommissionEmployee("Dwight K. Shrute",11,2000,0.1,40000);
        System.out.println(em3);
        System.out.println(" ");
        SeniorSalaryEmployee em4=hw3.new SeniorSalaryEmployee("Todd Packer",18, 50000);
        System.out.println(em4);
        System.out.println(" ");
        ems.add(em1);
        ems.add(em2);
        ems.add(em3);
        ems.add(em4);
        SupervisorEmployee em5=hw3.new SupervisorEmployee(" Michael L. Scott", 6,ems,0.2);
        System.out.println(em5);
        System.out.println(" ");
        
        
        
        
       
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
            return "I'm "+this.getName()+". I'm the employee, whee!";
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
            return "I'm "+this.getName()+". I'm an HourlyEmoloyee. I worked "+getWorkedHour()+" hours at a rate of "+getPayRate()+
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
            return "I'm "+this.getName()+". I'm an MonthlyEmoloyee. "+" I made "+ " $"+getWages()+"!";
        }
        
       
        
    }
    
    public class CommissionEmployee extends Employee{
        private double basesalary;
        private double monthlysales;
        private double commission;
        
        @Override
        public double getWages(){
            return basesalary+monthlysales*commission;
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
        
        public CommissionEmployee(String Name, int Tenure, double base, double commit, double sales){
            super(Name, Tenure);
            basesalary=base;
            monthlysales=sales;
            commission=commit;
        }
        
        @Override
        public String toString(){
            return "I'm "+this.getName()+". I'm an CommissionEmployee. My base salary is: "+getBasesalary()+". My monthly sale is: "+getMonthlysales()+
                    ". My commission is: "+getCommission()+". I made "+" $"+String.format("%.2f",getWages())+"!";
        }
        
        
    }
    
    public class SupervisorEmployee extends Employee{
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
        
        public SupervisorEmployee(String Name,int Tenure,ArrayList<Employee> supervise,double bonus){
            super(Name, Tenure);
            Employees=supervise;
            bonuspercentage=bonus;
        }
        
        @Override
        public String toString(){
            return "I'm "+this.getName()+". I'm an SuperviorEmoloyee. "+" I made "+ " $"+String.format("%.2f",getWages())+"!";
        }
        
    }
    public class SeniorSalaryEmployee extends Employee{
        
        @Override
        public double getWages(){
            double bonus=1.03;
            bonus=Math.pow(bonus,this.getTenure());
            return this.getWage()*bonus;
            
        }
        
        public SeniorSalaryEmployee(String Name, int Tenure, double wage ){
            super(Name,Tenure);
            this.setWages(wage);
        }
        
        @Override
        public String toString(){
            return "I'm "+this.getName()+". I'm an SeniorSalaryEmployee. "+" I made "+ " $"+String.format("%.2f",getWages())+"!";
        }  
    }

    
    
}
