package filesprocessing.filters.stringCompareValueFilters;

import filesprocessing.filters.SuperFilter;

import java.io.File;

public class ContainsFilter implements SuperFilter {
    private String stringValueToCompare;

    public ContainsFilter(String stringValueToCompare){
        this.stringValueToCompare  = stringValueToCompare;
    }

    @Override
    public boolean isPass(File fileToCheck){
        return fileToCheck.getName().contains(this.stringValueToCompare);
    }

}
