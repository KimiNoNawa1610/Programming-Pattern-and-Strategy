/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Catch;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author votha
 */
public class Lab12 {
 
   public static double divideAllNumsInArray(int[] arr, int cap) {
      double result = (double)arr[0];
      for (int i = 1; i < cap; i++) {
            result /= (double) arr[i];
       }
 
       return result;
   }
 
  public static void main(String[] args) {
        try {
            Scanner in = new Scanner(System.in);
 
          System.out.println("How many numbers? ");
           int cap = in.nextInt();
            int[] numList = new int[cap];
 
           System.out.println("Enter integers on one line: ");
          in.nextLine();
 
           String[] stringNums = in.nextLine().split(" ");
 
           for (int i = 0; i < cap; i++) {
              numList[i] = Integer.parseInt(stringNums[i]);
            }
 
           double result = divideAllNumsInArray(numList, cap);
          System.out.println("The result of the division is: " + result);
       }
       catch(InputMismatchException e) {
           System.out.println("Input was not an expected type");
       }
      catch(ArrayIndexOutOfBoundsException e) {
    System.out.println("Attempted to access elements in array outside of limit");
       }
      catch(NumberFormatException e) {
           System.out.println("Attempted to parse non-integer token");
      }
      catch(ArithmeticException e) {
           System.out.println("Cannot divide by zero");
 
      }
       catch (Exception e) {
           System.out.println("Unknown error occurred.");
       }
       System.out.println("Bye!");
  }
  
}


