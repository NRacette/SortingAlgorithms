import java.util.Random;

/**
 * Implements two different QuickSort algorithms: one using the first element as the pivot,
 * and the other using the median of three random elements as the pivot.
 * Both methods break the array into two sub-arrays and recursively sort them.
 * 
 */
public class QuickSort {

    /**
     * Sorts the provided array using QuickSort using first element as the pivot.
     *
     * @param inputArray The input array to be sorted
     */
    public void quickSortFirst(int[] inputArray) {
        // check if array is null or empty
        if (inputArray == null || inputArray.length == 0) {
            return;
        } else {
            // Call the recursive quicksort method staring low at 0 and high at length - 1
            quicksortFirstPivot(inputArray, 0, inputArray.length - 1);
        }
    }

    /**
     * Recursive QuickSort method implementation which partitions using a first element pivot
     * First, it checks if the lowIndex is less than the highIndex check for base case
     * If it is not a base case, it calls the partition method to partition the array
     * The pivotIndex returned from the partition method is then used to separate the array into two sub-arrays
     * The method then recursively calls itself on the left and right sub-arrays until the base case is reached.
     *
     * @param inputArray The array being sorted.
     * @param lowIndex Starting index of the subarray.
     * @param highIndex Ending index of the subarray.
     */
    private void quicksortFirstPivot(int[] inputArray, int lowIndex, int highIndex) {
        // Initial check to see if lowIndex is less than highIndex
        if (lowIndex < highIndex) {
            // partitionIndex divides the array into two sub-arrays
            int partitionIndex  = partitionArray(inputArray, lowIndex, highIndex);
            // Recursively sort
            // sort the left sub-array
            quicksortFirstPivot(inputArray, lowIndex, partitionIndex  - 1);
            // sort the right sub-array
            quicksortFirstPivot(inputArray, partitionIndex  + 1, highIndex);
        }
    }

    /**
     * Method loops through the array and partitions it around a pivot value
     * The pivot is chosen as the first element in the subarray at lowIndex.
     * Compares each element in the subarray to the pivot value and swaps elements 
     * when pivot value is greater than current element value
     * Swaps the pivot value with the element at the partition index.
     *
     * @param inputArray The array containing the subarray to partition.
     * @param lowIndex The starting index of the subarray as pivot location.
     * @param highIndex The ending index of the subarray.
     * @return The final index of the pivot after partitioning.
     */
    private int partitionArray(int[] inputArray, int lowIndex, int highIndex) {
        // assign the pivot value to the first value in array at lowIndex
        int pivotValue = inputArray[lowIndex];
        // i starts from the low index
        int partitionIndex  = lowIndex;
        // interates though the array from lowIndex + 1 to highIndex
        for (int currentIndex  = lowIndex + 1; currentIndex  <= highIndex; currentIndex ++) {
            // Checks if current element value is less or equal to pivot value
            if (inputArray[currentIndex] < pivotValue) {
                //  increment partition Index to move to next position in array 
                partitionIndex++;
                // swap the elements
                swap(inputArray, partitionIndex, currentIndex);
                
            }
        }
        // Swap the lowIndex with the index partitionIndex the pivot position
        swap(inputArray, lowIndex, partitionIndex);
        return partitionIndex;
    }


    /**
     * Sorts array using QuickSort with a pivot chosen as the median of three.
     *
     * @param inputArray The input array that needs to be sorted.
     */
    public void quickSortMedian(int[] inputArray) {
        // check if array is null or empty
        if (inputArray == null || inputArray.length == 0) {
            return;
        } else {
            // Call the recursive quicksort method staring low at 0 and high at length - 1
            quicksortMedianPivot(inputArray, 0, inputArray.length - 1);
        }
    }


    /**
     * Recursive QuickSort implementation which partitions using a median-of-three-random pivot.
     * First, it checks if the lowIndex is less than the highIndex check for base case
     * If it is not a base case, it calls the partition method to partition the array
     * The pivotIndex returned from the partition method is then used to separate the array into two sub-arrays
     * The method then recursively calls itself on the left and right sub-arrays until the base case is reached.
     *
     * @param inputArray The array being sorted.
     * @param lowIndex The starting index of the subarray.
     * @param highIndex The ending index of the subarray.
     */
    private void quicksortMedianPivot(int[] inputArray, int lowIndex, int highIndex) {
        if (lowIndex < highIndex) {
            // Find median pivot and partition the array
            int partitionIndex = partitionMedianPivot(inputArray, lowIndex, highIndex);
            // Recursively sort
            // sort the left sub-array
            quicksortMedianPivot(inputArray, lowIndex, partitionIndex - 1);
            // sort the right sub-array
            quicksortMedianPivot(inputArray, partitionIndex + 1, highIndex);
        }
    }

    /**
     * Partitions the subarray using a median-of-three as a pivot.
     * First uses the findAndPlaceMedianPivot method to find the median pivot
     * Then using the same partition logic to break the array into two sub-arrays.
     *
     * @param inputArray The array containing the subarray to partition.
     * @param lowIndex The starting index of the subarray.
     * @param highIndex The ending index of the subarray.
     * @return The index of the pivot after partitioning.
     */
    private int partitionMedianPivot(int[] inputArray, int lowIndex, int highIndex) {
        // Find the median of three random indexes and place it at the lowIndex
        findAndPlaceMedianPivot(inputArray, lowIndex, highIndex);

        // Now that the pivot is at arr[low], we can use the same partition logic
        int partitionIndex = partitionArray(inputArray, lowIndex, highIndex);
        return partitionIndex;
    }

    /**
     * Finds the median of three random elements in the subarray
     * Swaps that median value into position first position so it becomes the pivot.
     *
     * @param inputArray The array containing the values.
     * @param lowIndex The starting index of the subarray.
     * @param highIndex The ending index of the subarray.
     */
    private void findAndPlaceMedianPivot(int[] inputArray, int lowIndex, int highIndex) {
        // Check if at least 3 elements in array
        if (highIndex - lowIndex < 2) {
            return;
        }
        Random rand = new Random();
        // Choose three random index locations in input array
        int indexOne = lowIndex + rand.nextInt(highIndex - lowIndex + 1);
        int indexTwo = lowIndex + rand.nextInt(highIndex - lowIndex + 1);
        int indexThree = lowIndex + rand.nextInt(highIndex - lowIndex + 1);
        // Get the values for three random indexes
        int valOne = inputArray[indexOne];
        int valTwo = inputArray[indexTwo];
        int valThree = inputArray[indexThree];

        int medianIndex;
        // Finding the median value
        if ((valOne >= valTwo && valOne <= valThree) || (valOne <= valTwo && valOne >= valThree)) {
            medianIndex = indexOne;
        } else if ((valTwo >= valOne && valTwo <= valThree) || (valTwo <= valOne && valTwo >= valThree)) {
            medianIndex = indexTwo;
        } else {
            medianIndex = indexThree;
        }
        // Swap the median element with the first element to be used as the pivot in partitioning method
        swap(inputArray, lowIndex, medianIndex);
    }

    /**
     * Swaps two elements locations in an array.
     *
     * @param inputArray The array containing the elements to swap.
     * @param positionOne Index of the first element.
     * @param positionTwo Index of the second element.
     */
    private void swap(int[] inputArray, int positionOne, int positionTwo) {
        int temp = inputArray[positionOne];
        inputArray[positionOne] = inputArray[positionTwo];
        inputArray[positionTwo] = temp;
    }
}