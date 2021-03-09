package filesprocessing;

import filesprocessing.filters.SuperFilter;
import filesprocessing.orders.SuperOrder;

import java.io.File;
import java.util.ArrayList;

public class Section {

    private SuperFilter filer;
    private SuperOrder order;
    private ArrayList<File> filteredAndOrderedFiles;

    public Section(SuperFilter sectionFilter, SuperOrder sectionOrder){
        this.filer = sectionFilter;
        this.order = sectionOrder;
        filteredAndOrderedFiles = new ArrayList<File>();
    }

    void executeSectionFilterAndOrderOnDirectoryFiles(ArrayList<File> filesArrayList){
        for(File file: filesArrayList){
            if(this.filer.isPass(file))
                this.filteredAndOrderedFiles.add(file);
        }
        File [] filesArray = new File[filteredAndOrderedFiles.size()];
        filesArray = this.filteredAndOrderedFiles.toArray(filesArray);
        SuperOrder.sort(filesArray, this.order.getOrderComparator());
        this.printFilesArrayList(filesArray);
    }

    private void printFilesArrayList(File [] filesArray){
        for(File file: filesArray)
            System.out.println(file.getName());
    }
}
