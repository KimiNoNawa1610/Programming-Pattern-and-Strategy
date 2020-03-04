package Homework;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        HashMap <String, List<Integer>> wordPos = new HashMap();
        Scanner in = new Scanner (System.in);
        int pos = 0;

        System.out.println("Enter your choice of text: ");
        String text = in.nextLine();
        text = text.replaceAll("[^a-zA-Z0-9 ]","").toLowerCase();
        System.out.println("The text you suggested was: " + text);
        String [] txt = text.split(" ");
        for (String a : txt)
        {
            pos += 1;

            if (!wordPos.containsKey(a))
            {
                wordPos.put(a,new ArrayList<Integer>());
                wordPos.get(a).add(pos);
            }
            else if (wordPos.containsKey(a))
            {
                wordPos.get(a).add(pos);
            }
        }

        System.out.println("Which word do you want to search for?: ");
        String continu = in.nextLine();
        continu = continu.toLowerCase();

        while (!continu.equals("!!quit!!"))
        {
            System.out.println("Iteration worked");
            if(wordPos.containsKey(continu))
            {
                System.out.print(continu + " is found at: ");
                for (int u : wordPos.get(continu))
                {
                    System.out.print(u + " ");
                }
            }
            else
            {
                System.out.println("Word is not found");
            }
            System.out.println();
            //System.out.println("Which word do you want to search for?: ");
            //continu = in.nextLine();
            //continu = continu.toLowerCase();
            continu = "!!quit!!";
        }

    }
}
