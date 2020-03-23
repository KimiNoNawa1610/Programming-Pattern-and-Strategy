/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementcomparator;

/**
 *
 * @author votha
 */
import java.util.Comparator;

public class Lab11 {

    // An insertion sort implementation
    public static <T> void sort(T[] library, Comparator<T> c) {
        int n = library.length;
        for (int i = 1; i < n; i++) {
            T key = library[i];

            int j;
            // if compare() returns negative integer -> first argument < second argument
            // if compare() returns positive integer -> first argument > second argument
            // if compare() returns zero -> first argument == second argument
            for (j = i-1; j >= 0 && c.compare(library[j], key) > 0; j--){
                library[j + 1] = library[j];
            }

            library[j + 1] = key;
        }
    }

    public static void printArray(Album[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Album[] library = new Album[6];
        library[0] = new Album("Never Gonna Give You Up (Pianoforte)",
                "Rick Astley", 1987, 5);
        library[1] = new Album("Gangnam Style", "PSY", 2012, 4);
        library[2] = new Album("All Star", "Smash Mouth", 1999, 3);
        library[3] = new Album("When We Fall Asleep, Where Do We Go?",
                "Billie Eilish", 2019, 1);
        library[4] = new Album("Oracualar Spectacular", "MGMT", 2008, 6);
        library[5] = new Album("The Marshall Mathers LP", "Eminem", 200, 2);

        System.out.println("Original array:");
        printArray(library);

        System.out.println("Sorted by Artist:");
        sort(library, new SortByArtist());
        printArray(library);

        System.out.println("Sorted by Album, in reverse:");
        sort(library, new SortByTitleReverse());
        printArray(library);
    }
}

