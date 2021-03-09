package filesprocessing.filters.doubleCompareValueFilters;


import filesprocessing.filters.SuperFilter;
import java.io.File;

public class SmallerThanFilter implements SuperFilter {
    private double smallerThanComparisonFactor;

    public SmallerThanFilter(double smallerThanFactor){
        this.smallerThanComparisonFactor = smallerThanFactor;
    }

    @Override
    public boolean isPass(File fileToCheck){
        return(fileToCheck.length()/BYTES_DIVIDER < this.smallerThanComparisonFactor);
    }
}
