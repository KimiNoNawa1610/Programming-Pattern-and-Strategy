/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw3;

/**
     * The Hourly Employee class that is inheriting the Employee class
     */
    public class HourlyEmployee extends Employee{
        
        private int workedHour;
        private double payrate;
        /**
         * Modified getWages method
         * @return the total salary of this employee
         */
        @Override
        public double getWages(){
            return workedHour*payrate;
        }
        /**
         * The mutator method of work hour field
         * @return workedHour
         */
        public int getWorkedHour(){
            return workedHour;
        }
        /**
         * The mutator method of pay rate field
         * @return payrate
         */
        public double getPayRate(){
            return payrate;
        }
        /**
         * the constructor of the HourlyEmployee class
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
         * toString method to represent the HourlyEmployee class
         * @return the name and the details that relate to the salary of a employee. 
         */
        @Override
        public String toString(){
            return "I'm "+this.getName()+". I'm an HourlyEmoloyee. "+"I have worked "+this.getTenure()+" years. I work "
                    +getWorkedHour()+" hours at a rate of $"+getPayRate()+" dollars per hour. I made "+ " $"+getWages()+"!";
        }
        
    }
