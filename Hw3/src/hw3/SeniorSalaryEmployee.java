/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw3;


/**
     * SeniorSalaryEmployee class that is inherits the Employee class
     */
    public class SeniorSalaryEmployee extends Employee{
        /**
         * The modified getWages method
         * @return the total wage of this employee
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
         * The constructor of the SeniorSalaryEmployee class
         * @param Name
         * @param Tenure
         * @param wage 
         */
        public SeniorSalaryEmployee(String Name, int Tenure, double wage ){
            super(Name,Tenure,wage);
        }
        /**
         * toString method to represent the SeniorSalaryEmployee class
         * @return the name of a employee and their salary.
         */
        @Override
        public String toString(){
            return "I'm "+this.getName()+". I'm an SeniorSalaryEmployee. "+"I have worked "+this.getTenure()+" years. My base salary is $"+
                    getBaseWage()+". I made "+ " $"+String.format("%.2f",getWages())+"!";
        }  
    }
