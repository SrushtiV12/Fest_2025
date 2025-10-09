public class HeapSort {

    /**
     * Sorts an array using the Heap Sort algorithm.
     * @param arr The array to be sorted.
     */
    public void sort(int arr[]) {
        int n = arr.length; 
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // One by one extract elements from the heap
        for (int i = n - 1; i >= 0; i--) {
            // Move current root (largest element) to the end of the array
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // Call max heapify on the reduced heap
            heapify(arr, i, 0);
        }
    } 
    void heapify(int arr[], int n, int i) {
        int largest = i; // Initialize largest as root
        int leftChild = 2 * i + 1; // Left child index
        int rightChild = 2 * i + 2; // Right child index

        // If left child is larger than root
        if (leftChild < n && arr[leftChild] > arr[largest]) {
            largest = leftChild;
        }

        // If right child is larger than current largest
        if (rightChild < n && arr[rightChild] > arr[largest]) {
            largest = rightChild;
        }

        // If largest is not root
        if (largest != i) { 
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            // Recursively heapify the affected sub-tree
            heapify(arr, n, largest);
        }
    }

    // A utility function to print an array
    static void printArray(int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n; ++i) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // Driver code to test the Heap Sort implementation
    public static void main(String args[]) {
        int arr[] = {12, 11, 13, 5, 6, 7};
        System.out.println("Original array:");
        printArray(arr);

        HeapSort ob = new HeapSort();
        ob.sort(arr);

        System.out.println("Sorted array:");
        printArray(arr);
    }
}
