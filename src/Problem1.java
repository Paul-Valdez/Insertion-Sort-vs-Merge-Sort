import java.util.Random;
import java.util.Stack;

public class Problem1 {
    public static void main(String[] args) throws Exception {
        /* // For testing the two sort methods
        int[] arrTest = getRandomArray(10);
        int[] arrI = arrTest.clone();
        Problem1.insertionSort(arrI);
        int[] arrM = arrTest.clone();
        Problem1.mergeSort(arrM, 0, arrM.length-1);
        System.out.println(java.util.Arrays.toString(arrI));
        System.out.println(java.util.Arrays.toString(arrM));
        */

        int[] arraySizes = { 10, 100, 200, 250, 300, 350, 400, 450, 500, 1000 };
        int sampleSize = 100000;

        System.out.println("Sample size: " + sampleSize + "\n");

        for(int h = 0; h < arraySizes.length; h++){
            int size = arraySizes[h];
            int[] arr = getRandomArray(size);

            Stack<Long> insTimes = new Stack<>();
            Stack<Long> mrgTimes = new Stack<>();

            for (int i = 0; i < sampleSize; i++) {
                long start, duration;

                // Test Insertion Sort
                start = System.nanoTime();
                Problem1.insertionSort(arr.clone());
                duration = System.nanoTime() - start;
                insTimes.add(duration);

                // Test Merge Sort
                start = System.nanoTime();
                Problem1.mergeSort(arr.clone(), 0, size-1);
                duration = System.nanoTime() - start;
                mrgTimes.add(duration);
            }


            // calculate the averages
            long insAvg = 0, mrgAvg = 0;

            while(!insTimes.isEmpty()){
                insAvg += insTimes.pop();
                mrgAvg += mrgTimes.pop();
            }

            insAvg /= sampleSize;
            mrgAvg /= sampleSize;

            System.out.println("Array Size: " + size +
                                String.format("\nInsertion Sort average: %10d", insAvg) + " ns" +
                                String.format("\nMerge Sort average:     %10d", mrgAvg) + " ns" + "\n");
        }

    } // end main


    public static void insertionSort(int[] array) {
        // Start from the second element (position 1) as the first element is considered sorted by default
        for (int i = 1; i < array.length; i++) {
            // The element to be compared and possibly inserted at the correct position
            int key = array[i];
    
            // Start comparing with the previous element
            int j = i - 1;
    
            // Move elements of arrSorted[0..i-1] that are greater than 'key' to one position ahead of their current position
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
    
            // Place the 'key' in its correct position
            array[j + 1] = key;
        }
    }


    public static void mergeSort(int[] arr, int l, int r) {
        if (l < r) {
            // Find the middle point
            int m = (l + r) / 2;

            // Sort first and second halves
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);

            // Merge the sorted halves
            merge(arr, l, m, r);
        }
    }


    public static void merge(int[] arr, int l, int m, int r) {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        // Create temp arrays
        int[] L = new int[n1];
        int[] R = new int[n2];

        // Copy data to temp arrays
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];

        // Merge the temp arrays

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarray
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements of L[] if any
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        // Copy remaining elements of R[] if any
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }


    public static int[] getRandomArray(int length) {
        Random rand = new Random();
        int[] array = new int[length];

        for (int i = 0; i < length; i++) {
            array[i] = rand.nextInt();
        }

        return array;
    }
}
