package com.example.codewarssolutions.ds;

public class QuickSort {

    public static void quickSort(int[] array, int low, int high) {
        if (array.length == 0) return;
        int middle = low + (high - low) / 2;
        int pivot = array[middle];

        int i = low;
        int j = high;
        while (i <= j) {
            while (array[i] < pivot) {
                i++;
            }
            while (array[j] > pivot) {
                j--;
            }

            if (i <= j) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }

        if (low < j) quickSort(array, low, j);
        if (high > i) quickSort(array, i, high);
    }
}
