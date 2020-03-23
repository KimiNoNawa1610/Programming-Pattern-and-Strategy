/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw3;

/**
     * SalaryEmployee class that inherits the Employee class
     */
    public class SalaryEmployee extends Employee{
        private double montlysalary;
        /**
         * The modified getWages method 
         * @return the total wages of this employee
         */
        @Override
        public double getWages(){
            return montlysalary;
        }
        /**
         * The constructor of the SalaryEmployee class
         * @param Name
         * @param Tenure
         * @param msalary 
         */
        public SalaryEmployee(String Name,int Tenure,double msalary){
            super(Name, Tenure,msalary);
            montlysalary=this.getBaseWage();
        }
        /**
         * The toString method to represent the SalaryEmployee class
         * @return the name and total salary of a employee.
         */
        @Override
        public String toString(){
            return "I'm "+this.getName()+". I'm an SalaryEmoloyee. "+"I have worked "+this.getTenure()+" years. I made "+ " $"+getWages()+"!";
        }  
    }
