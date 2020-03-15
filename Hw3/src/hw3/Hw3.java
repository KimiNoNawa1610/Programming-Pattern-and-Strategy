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
          
    }
    /**
     * 
     */
    abstract class Employee{
        
        private String Name;
        private int Tenure ;
        private double wages;
        /**
         * 
         * @return 
         */
        public String getName(){
            return Name;
        }
        /**
         * 
         * @return 
         */
        public int getTenure(){
            return Tenure;
        }
        /**
         * 
         * @return 
         */
        public double getBaseWage(){
            return wages;
        }
        /**
         * 
         * @param name
         * @param tenure
         * @param basewages 
         */
        public Employee(String name, int tenure, double basewages){
            Name=name;
            Tenure=tenure;    
            wages=basewages;
        }
        
        /**
         * 
         * @return 
         */
        public abstract double getWages();
        
        /**
         * 
         * @return 
         */
        @Override
        public String toString(){
            return "I'm "+this.getName()+". I'm the employee, whee!";
        }
        
}
    /**
     * 
     */
    public class HourlyEmployee extends Employee{
        
        private int workedHour;
        private double payrate;
        /**
         * 
         * @return 
         */
        @Override
        public double getWages(){
            return workedHour*payrate;
        }
        /**
         * 
         * @return 
         */
        public int getWorkedHour(){
            return workedHour;
        }
        /**
         * 
         * @return 
         */
        public double getPayRate(){
            return payrate;
        }
        /**
         * 
         * @param Name
         * @param Tenure
         * @param Hour
         * @param Payrate 
         */
        public HourlyEmployee(String Name,int Tenure,int Hour, double Payrate){
            super(Name, Tenure,0);
            workedHour=Hour;
            payrate=Payrate;
        }
        /**
         * 
         * @return 
         */
        @Override
        public String toString(){
            return "I'm "+this.getName()+". I'm an HourlyEmoloyee. "+"I have worked "+this.getTenure()+" years. I work "
                    +getWorkedHour()+" hours at a rate of "+getPayRate()+" dollars per hour. I made "+ " $"+getWages()+"!";
        }
        
    }
    /**
     * 
     */
    public class SalaryEmployee extends Employee{
        private double montlysalary;
        /**
         * 
         * @return 
         */
        @Override
        public double getWages(){
            return montlysalary;
        }
        /**
         * 
         * @param Name
         * @param Tenure
         * @param msalary 
         */
        public SalaryEmployee(String Name,int Tenure,double msalary){
            super(Name, Tenure,msalary);
            montlysalary=msalary;
        }
        /**
         * 
         * @return 
         */
        @Override
        public String toString(){
            return "I'm "+this.getName()+". I'm an SalaryEmoloyee. "+"I have worked "+this.getTenure()+" years. I made "+ " $"+getWages()+"!";
        }  
    }
    /**
     * 
     */
    public class CommissionEmployee extends Employee{
        private double basesalary;
        private double monthlysales;
        private double commission;
        /**
         * 
         * @return 
         */
        @Override
        public double getWages(){
            return basesalary+monthlysales*commission;
        }
        /**
         * 
         * @return 
         */
        public double getBasesalary(){
            return basesalary;
        }
        /**
         * 
         * @return 
         */
        public double getMonthlysales(){
            return monthlysales;
        }
        /**
         * 
         * @return 
         */
        public double getCommission(){
            return commission;
        }
        /**
         * 
         * @param Name
         * @param Tenure
         * @param base
         * @param commit
         * @param sales 
         */
        public CommissionEmployee(String Name, int Tenure, double base, double commit, double sales){
            super(Name, Tenure,base);
            basesalary=this.getBaseWage();
            monthlysales=sales;
            commission=commit;
        }
        /**
         * 
         * @return 
         */
        @Override
        public String toString(){
            return "I'm "+this.getName()+". I'm an CommissionEmployee. "+"I have worked "+this.getTenure()+" years. My base salary is: $"
                    +getBasesalary()+". My monthly sale is: "+getMonthlysales()+". My commission is: "+getCommission()+". I made "+" $"+
                    String.format("%.2f",getWages())+"!";
        }
    }
    /**
     * 
     */
    public class SupervisorEmployee extends Employee{
        private ArrayList<Employee> Employees;
        private double bonuspercentage;
        /**
         * 
         * @return 
         */
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
        /**
         * 
         * @return 
         */
        public double getbonus(){
            return bonuspercentage;
        }
        /**
         * 
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
         * 
         * @return 
         */
        @Override
        public String toString(){
            return "I'm "+this.getName()+". I'm an SuperviorEmoloyee. "+"My bonus is "+getbonus()+
                    ". I made "+ " $"+String.format("%.2f",getWages())+"!";
        }
        
    }
    /**
     * 
     */
    public class SeniorSalaryEmployee extends Employee{
        /**
         * 
         * @return 
         */
        @Override
        public double getWages(){
            double bonus=1.03;
            if(this.getTenure()>10){
                bonus=Math.pow(bonus,this.getTenure()-10);
                return this.getBaseWage()*bonus;
        }
            else{
                return this.getBaseWage();
        }
            
        }
        /**
         * 
         * @param Name
         * @param Tenure
         * @param wage 
         */
        public SeniorSalaryEmployee(String Name, int Tenure, double wage ){
            super(Name,Tenure,wage);
        }
        /**
         * 
         * @return 
         */
        @Override
        public String toString(){
            return "I'm "+this.getName()+". I'm an SeniorSalaryEmployee. "+"I have worked "+this.getTenure()+" years. My base salary is $"+
                    getBaseWage()+". I made "+ " $"+String.format("%.2f",getWages())+"!";
        }  
    }

    
    
}
