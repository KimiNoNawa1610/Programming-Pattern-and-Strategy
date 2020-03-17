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
    /** The testing method to test the employee and its sub-classes
     * @param args the command line arguments
     * print out the toString of each class.
     */ 
    public static void main(String[] args) {
        // TODO code application logic here
        ArrayList<Employee> ems=new ArrayList<>();
        Hw3 hw3=new Hw3();
        HourlyEmployee em1=new HourlyEmployee("Pam Beesly",5,40,10);
        System.out.println(em1);
        System.out.println(" ");
        SalaryEmployee em2=new SalaryEmployee("Angela Martin",10,4500);
        System.out.println(em2);
        System.out.println(" ");
        CommissionEmployee em3=new CommissionEmployee("Dwight K. Shrute",11,2000,10,40000);
        System.out.println(em3);
        System.out.println(" ");
        SeniorSalaryEmployee em4=new SeniorSalaryEmployee("Todd Packer",18, 50000);
        System.out.println(em4);
        System.out.println(" ");
        ems.add(em1);
        ems.add(em2);
        ems.add(em3);
        ems.add(em4);
        SupervisorEmployee em5=new SupervisorEmployee(" Michael L. Scott", 6,ems,20);
        System.out.println(em5);
          
    }
    
   
    
    
    
    
    

    
    
}
