package filesprocessing.filters.stringCompareValueFilters;

import filesprocessing.filters.SuperFilter;

import java.io.File;

public class FileFilter implements SuperFilter {
    private String stringValueToCompare;

    public FileFilter(String stringValueToCompare){
        this.stringValueToCompare  = stringValueToCompare;
    }

    @Override
    public boolean isPass(File fileToCheck){
        return(fileToCheck.getName().equals(stringValueToCompare));
    }
}
