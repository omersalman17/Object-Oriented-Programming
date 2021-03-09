package filesprocessing.filters.doubleCompareValueFilters;


import filesprocessing.filters.SuperFilter;
import java.io.File;

public class BetweenFilter implements SuperFilter {
    private double lowerBound;
    private double upperBound;


    public BetweenFilter(double lowerBound, double upperbound){
        this.upperBound = upperbound;
        this.lowerBound = lowerBound;
    }

    @Override
    public boolean isPass(File fileToCheck){
        return(fileToCheck.length()/BYTES_DIVIDER >= this.lowerBound &&
                fileToCheck.length()/BYTES_DIVIDER <= this.upperBound);
    }
}
