import java.util.Arrays;
import java.util.Random;

/**
 * Description: This program takes unsorted arrays of integers and sorts them using two different quicksort methods.
 * The breakdown of the program is, first it creates the 3 arrays and fills them with sorted, random, and reversed values.
 * Then calls the quicksort method using the first as pivot and then median as pivot on all 3 arrays printing and timing
 * for each of the arrays. Using the runTest method.
 * The quicksorts break down the array into sub arrays by using a partition method and recursively calling itself on the sub arrays
 * This recursion continues until the array is broken down to base cases where the sub arrays are of length 1 or 0.
 *
 * INPUT:
 *      CONSTANTS:
 *          ARRAY_SIZE - size of the array to be sorted
 * 		Input:
 * 			Sorted, random, and reversed arrays of integers length 10.
 * 
 *
 * 	COMPUTATION:
 * 		1. Create an array of unsorted integers.
 * 		2. Call two different quicksort methods to sort the array.
 * 		3. returns the sorted arrays
 *      4. Calculate time taken for each sort method.
 *
 * 	OUTPUT:
 * 		Sorted array using the two different quicksort methods.
 *      Times for each sort method.
 * 
 * 	@author: Nick Racette
 * 	@contact: Nick.Racette@go.minnestate.edu
 * 	@since: 10/05/2024
 * 
 * 	Course: CSCI 2082-70 Data Structures and Algorithms
 *
 * 	Institution: Century College
 * 
 * 	Instructor: Mathew Nyamgawa
 */
public class Driver {

    /**
     * The main method that executes the testing for each quicksort method.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {


        QuickSort sorter = new QuickSort();
        final int ARRAY_SIZE = 10;

        // Create test arrays size 10 and Random object
        int[] sortedArrayOriginal = new int[ARRAY_SIZE];
        int[] randomArrayOriginal = new int[ARRAY_SIZE];
        int[] reversedArrayOriginal = new int[ARRAY_SIZE];
        Random randomValue = new Random();

        // Fill the arrays with sorted, random, and reversed values
        for (int i = 0; i < ARRAY_SIZE; i++) {
            sortedArrayOriginal[i] = (i + 1) * 5;
            randomArrayOriginal[i] = randomValue.nextInt(100);
            reversedArrayOriginal[i] = (ARRAY_SIZE - i) * 5;
        }

        
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("      Quicksort Algorithmes      ");
        System.out.println("      By: Nicholas Racette      ");
        System.out.println("--------------------------- START OF SORTING ---------------------------");
        /**
         * Time complexity quickSortFirst  O(n^2) -- worst case
         * Worst case because the pivot is always the smallest element
         * Time complexity quickSortMedian  O(n*log(n)) -- average case
         * Average case because the pivot is the median of 3 random elements
         */
        runTest("An array with sorted values", sortedArrayOriginal, sorter);
        /**
         * Time complexity quickSortFirst  O(n*log(n)) -- average case
         * Average case because the pivot is a random element
         * Time complexity quickSortMedian  O(n*log(n)) -- average case
         * Average case because the pivot is the median of 3 random elements
         */
        runTest("An array with randomly ordered values", randomArrayOriginal, sorter);
        /**
         * Time complexity quickSortFirst  O(n^2) -- worst case
         * Worst case because the pivot is always the largest element
         * Time complexity quickSortMedian  O(n*log(n)) -- average case
         * Average case because the pivot is the median of 3 random elements
         */
        runTest("An array with values in reversed order", reversedArrayOriginal, sorter);

        System.out.println("\n--------------------------- END OF SORTING ---------------------------");

    }

    /**
     * Runs both quick sort methods first and median pivot on the input array
     *
     * @param testName Test type description of array being passed
     * @param originalArray The input array to be sorted.
     * @param arraySorter The QuickSort object.
     */
    public static void runTest(String testName, int[] originalArray, QuickSort arraySorter) {
        System.out.println("\n--------------------------------------------------------");
        System.out.printf(" TEST CASE: %s \n", testName);
        System.out.println("--------------------------------------------------------");
        System.out.printf("Original Array: %s\n", Arrays.toString(originalArray));
        // Run Test first value as pivot
        int[] arrayForFirstPivot = Arrays.copyOf(originalArray, originalArray.length);
        long startTimeFirst = System.nanoTime();
        arraySorter.quickSortFirst(arrayForFirstPivot);
        long endTimeFirst = System.nanoTime();
        long durationFirst = endTimeFirst - startTimeFirst;
        // Run Test of median of 3 random values as pivot 
        int[] arrayForMedianPivot = Arrays.copyOf(originalArray, originalArray.length);
        long startTimeMedian = System.nanoTime();
        arraySorter.quickSortMedian(arrayForMedianPivot);
        long endTimeMedian = System.nanoTime();
        long durationMedian = endTimeMedian - startTimeMedian;
        // Print the table
        System.out.printf("Sorted Array:   %s\n\n", Arrays.toString(arrayForFirstPivot));
        System.out.print("|----------------------------------------|\n");
        System.out.printf("| %-20s | %15s |\n", "Pivot Strategy", "Time (ns)");
        System.out.print("|----------------------------------------|\n");
        System.out.printf("| %-20s | %,15d |\n", "First Element", durationFirst);
        System.out.printf("| %-20s | %,15d |\n", "Median of Three", durationMedian);
        System.out.print("|----------------------------------------|\n");
    }
}