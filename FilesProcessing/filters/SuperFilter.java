package filesprocessing.filters;

import java.io.File;

public interface SuperFilter {
    double BYTES_DIVIDER = 1024.0;

    boolean isPass(File fileToCheck); // TODO why abstract declaration not needed here?
}
