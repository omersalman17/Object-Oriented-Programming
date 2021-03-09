package filesprocessing.filters.doubleCompareValueFilters;


import filesprocessing.filters.SuperFilter;
import java.io.File;

public class GreaterThanFilter implements SuperFilter {
    private double greaterThanComparisonFactor;

    public GreaterThanFilter(double greaterThanFactor){
        this.greaterThanComparisonFactor = greaterThanFactor;
    }

    @Override
    public boolean isPass(File fileToCheck){
        return(fileToCheck.length()/BYTES_DIVIDER > this.greaterThanComparisonFactor);
    }
}
