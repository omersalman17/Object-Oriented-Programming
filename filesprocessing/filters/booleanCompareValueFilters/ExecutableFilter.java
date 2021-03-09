package filesprocessing.filters.booleanCompareValueFilters;

import filesprocessing.filters.SuperFilter;

import java.io.File;

public class ExecutableFilter implements SuperFilter {
    private Boolean booleanValueToCompare;

    public ExecutableFilter(Boolean booleanValueToCompare){
        this.booleanValueToCompare = booleanValueToCompare;
    }

    @Override
    public boolean isPass(File fileToCheck){
        return (fileToCheck.canExecute() == this.booleanValueToCompare);
    }
}
