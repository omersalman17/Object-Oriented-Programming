package filesprocessing.orders;

import java.io.File;
import java.util.Comparator;

public interface SuperOrder {

    int FIRST_INDEX = 0;

    Comparator<File> getOrderComparator();

    static void sort(File [] fittingFilesArray, Comparator<File> comparator){
        int lastIndex = fittingFilesArray.length - 1;
        SuperOrder.quickSort(fittingFilesArray, FIRST_INDEX, lastIndex, comparator);
    }

    static void quickSort(File [] arr, int low, int high, Comparator<File> comparator){
        if (low < high){
            int num = partition(arr, low, high, comparator);
            quickSort(arr, low, num - 1, comparator);
            quickSort(arr, num + 1, high, comparator);
        }
    }

    static int partition(File [] filesArray, int left, int right, Comparator<File> comparator){
        File pivot = filesArray[right];
        int i = (left - 1);
        for (int j = left; j < right; j++){
            if (comparator.compare(filesArray[j], pivot) < 0){
                i++;
                File temp = filesArray[i];
                filesArray[i] = filesArray[j];
                filesArray[j] = temp;
            }
        }
        File temp = filesArray[i + 1];
        filesArray[i + 1] = filesArray[right];
        filesArray[right] = temp;
        return i + 1;
    }
}
