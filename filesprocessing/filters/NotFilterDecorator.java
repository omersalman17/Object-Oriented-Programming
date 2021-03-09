package filesprocessing.filters;

import java.io.File;

public class NotFilterDecorator implements SuperFilter {
    private final SuperFilter currentFilter;

    public NotFilterDecorator(SuperFilter currentFilter){
        this.currentFilter = currentFilter;
    }

    @Override
    public boolean isPass(File fileToCheck) {
        return !this.currentFilter.isPass(fileToCheck);
    }
}
