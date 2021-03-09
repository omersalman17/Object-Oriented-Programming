package filesprocessing.filters.booleanCompareValueFilters;

import filesprocessing.filters.SuperFilter;

import java.io.File;

public class WritableFilter implements SuperFilter {
    private Boolean booleanValueToCompare;

    public WritableFilter(Boolean booleanValueToCompare){
        this.booleanValueToCompare = booleanValueToCompare;
    }

    @Override
    public boolean isPass(File fileToCheck){
        return (fileToCheck.canWrite() == this.booleanValueToCompare);
    }
}
