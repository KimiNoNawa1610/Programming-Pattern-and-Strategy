/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw3;

/**
     * CommmissionEmployee class that inherits the Employee class
     */
    public class CommissionEmployee extends Employee{
        private double basesalary;
        private double monthlysales;
        private double commission;
        /**
         * The modified getWages method
         * @return the total wages of this employee
         */
        @Override
        public double getWages(){
            return basesalary+monthlysales*(commission/100);
        }
        /**
         * Mutator method of basesalary field
         * @return basesalary
         */
        public double getBasesalary(){
            return basesalary;
        }
        /**
         * Mutator method of monthlysales field
         * @return monthlysales
         */
        public double getMonthlysales(){
            return monthlysales;
        }
        /**
         * Mutator method commission field
         * @return commission
         */
        public double getCommission(){
            return commission;
        }
        /**
         * The constructor of the CommissionEmployee class
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
         * toString method to represent the CommissionEmployee class
         * @return the name and relative field of the total salary.
         */
        @Override
        public String toString(){
            return "I'm "+this.getName()+". I'm an CommissionEmployee. "+"I have worked "+this.getTenure()+" years. My base salary is: $"
                    +getBasesalary()+". My monthly sale is: "+getMonthlysales()+". My commission is: "+getCommission()+"%. I made "+" $"+
                    String.format("%.2f",getWages())+"!";
        }
    }
