package filesprocessing.orders.comparators;

import java.io.File;
import java.util.Comparator;

public class SizeComparator implements Comparator<File> {

    @Override
    public int compare(File file1, File file2){
        if (file1.length() == file2.length())
            return file1.getAbsolutePath().compareTo(file2.getAbsolutePath());
        else{
            if (file1.length() < file2.length())
                return -1;
            else{
                return 1;
            }
        }
    }
}
