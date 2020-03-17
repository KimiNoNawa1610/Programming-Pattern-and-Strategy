/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw3;
    /**
    * Employee class
    * abstract class that is inherited by its sub-classes
    **/
    abstract class Employee{
        
        private String Name;
        private int Tenure ;
        private double wages;
        /**
         * The mutator method of Name field
         * @return Name
         */
        public String getName(){
            return Name;
        }
        /**
         * The mutator method of Tenure field
         * @return Tenure
         */
        public int getTenure(){
            return Tenure;
        }
        /**
         * The mutator method of wages field
         * @return wages
         */
        public double getBaseWage(){
            return wages;
        }
        /**
         * Basic constructor of the Employee class
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
         * The abstract method that will be used in the other classes
         * @return 
         */
        public abstract double getWages();
        
        /**
         * toString method to represent the Employee class
         * @return "I'm employee, whee!"+ the name of that employee
         */
        @Override
        public String toString(){
            return "I'm "+this.getName()+". I'm the employee, whee!";
        }
        
}
