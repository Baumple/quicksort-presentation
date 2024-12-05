import java.util.function.Consumer;
import java.util.Array;


public class S {
    private static void quicksort(int[] arr, int lo, int hi) {
        if (lo >= hi || lo < 0) { return; }

        int p = partition(arr, lo, hi);

        quicksort(arr, lo, p - 1);
        quicksort(arr, p + 1, hi);
    }

    private static int partition(int[] arr, int lo, int hi) {
        int pivot = arr[hi];
        int temp_pivot = lo;

        for (int i = lo; i < hi; i++) {
            if (arr[i] <= pivot) {
                int temp = arr[temp_pivot];
                arr[temp_pivot] = arr[i];
                arr[i] = temp;

                temp_pivot += 1;
            }
        }

        int temp = arr[temp_pivot];
        arr[temp_pivot] = arr[hi];
        arr[hi] = temp;

        return temp_pivot;
    }


    public static void main(String[] args) {
        int n = 10000;
        benchmarkSort(createArray(n), S::bubblesort);
        benchmarkSort(createArray(n), S::quicksort);
    }



    public static void benchmarkSort(int[] arr, java.util.function.Consumer<int[]> sortMethod) {
        long startTime = System.nanoTime();
        sortMethod.accept(arr);
        long endTime = System.nanoTime();
        long duration = endTime - startTime;

        System.out.printf("Time taken in ms: %d\n", duration / 1000000L);
    }



    public static void quicksort(int[] arr) {
        quicksort(arr, 0, arr.length - 1);
    }

    public static void bubblesort(int[] arr) {
        boolean isSorted = true;
        int n = arr.length;
        do {
            isSorted = true;
            for (int i = 0; i < n - 1; i++) {
                if (arr[i] > arr[i + 1]) {
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    isSorted = false;
                }
            }
            n--;
        } while (!isSorted);
    }

    public static int[] createArray(int length) {
        int[] arr = new int[length];
        var rand = new java.util.Random();

        for (int i = 0; i < length; i++) {
            arr[i] = rand.nextInt(-50, 50);
        }
        return arr;
    }

}
