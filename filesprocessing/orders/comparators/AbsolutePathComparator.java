package filesprocessing.orders.comparators;

import java.io.File;
import java.util.Comparator;

public class AbsolutePathComparator implements Comparator<File> {

    @Override
    public int compare(File file1, File file2) {
        return file1.getAbsolutePath().compareTo(file2.getAbsolutePath());
    }
}
