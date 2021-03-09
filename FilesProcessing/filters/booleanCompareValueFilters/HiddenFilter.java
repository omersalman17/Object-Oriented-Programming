package filesprocessing.filters.booleanCompareValueFilters;

import filesprocessing.filters.SuperFilter;

import java.io.File;

public class HiddenFilter implements SuperFilter {
    private Boolean booleanValueToCompare;

    public HiddenFilter(Boolean booleanValueToCompare){
        this.booleanValueToCompare = booleanValueToCompare;
    }

    @Override
    public boolean isPass(File fileToCheck){
        return (fileToCheck.isHidden() == this.booleanValueToCompare);
    }
}
