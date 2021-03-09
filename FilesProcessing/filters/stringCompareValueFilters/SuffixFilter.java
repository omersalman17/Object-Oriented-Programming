package filesprocessing.filters.stringCompareValueFilters;

import filesprocessing.filters.SuperFilter;

import java.io.File;

public class SuffixFilter implements SuperFilter {
    private String stringValueToCompare;

    public SuffixFilter(String stringValueToCompare){
        this.stringValueToCompare  = stringValueToCompare;
    }

    @Override
    public boolean isPass(File fileToCheck){
        return fileToCheck.getName().endsWith(this.stringValueToCompare);
    }
}
