package filesprocessing.filters.stringCompareValueFilters;

import filesprocessing.filters.SuperFilter;

import java.io.File;

public class PrefixFilter implements SuperFilter {
    private String stringValueToCompare;

    public PrefixFilter(String stringValueToCompare){
        this.stringValueToCompare  = stringValueToCompare;
    }

    @Override
    public boolean isPass(File fileToCheck){
        return fileToCheck.getName().startsWith(this.stringValueToCompare);
    }
}
