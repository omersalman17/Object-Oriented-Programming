package filesprocessing.filters;

import java.io.File;

public class AllFilter implements SuperFilter {

    @Override
    public boolean isPass(File fileToCheck){
        return true;
    }
}
