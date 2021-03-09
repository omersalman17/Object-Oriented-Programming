package filesprocessing.orders.comparators;

import java.io.File;
import java.util.Comparator;

public class TypeComparator implements Comparator<File> {

    @Override
    public int compare(File file1, File file2){
        String file1Type = this.pointSubstring(file1);
        String file2Type = this.pointSubstring(file2);
        if (file1Type.equals(file2Type))
            return file1.getAbsolutePath().compareTo(file2.getAbsolutePath());
        return file1Type.compareTo(file2Type);
    }

    private String pointSubstring(File file){
        if(file.getName().lastIndexOf(".") > 0){
            return file.getName().substring(file.getName().lastIndexOf(".") + 1);
        }
        else
            return file.getName();

    }
}
