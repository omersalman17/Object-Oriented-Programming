package filesprocessing;

import filesprocessing.exceptions.ErrorTypeOneException;
import filesprocessing.exceptions.ErrorTypeTwoException;
import filesprocessing.filters.AllFilter;
import filesprocessing.filters.FiltersFactory;
import filesprocessing.filters.SuperFilter;
import filesprocessing.orders.OrdersFactory;
import filesprocessing.orders.SuperOrder;
import filesprocessing.orders.alphabetOrders.AlphabetOrderByAbs;


import java.io.File;
import java.util.ArrayList;

public class Parsing {

    private ArrayList<String> commandsFileLines;
    private ArrayList<Section> sectionsArrayList = new ArrayList<Section>();
    private SuperFilter sectionFilter;
    private SuperOrder sectionOrder;


    public Parsing(ArrayList<String> commandsFileLinesArrayList,
                   ArrayList<File> filesArrayList)throws ErrorTypeTwoException {
        this.commandsFileLines = commandsFileLinesArrayList;
        try {
            this.dividingToSections();
            this.executeActionsOnSections(this.sectionsArrayList, filesArrayList);
        }
        catch (ErrorTypeTwoException type2Error){
            throw type2Error;
        }
    }

    private void dividingToSections() throws ErrorTypeTwoException {
        ArrayList<String> sectionLines = new ArrayList<String>();
        ArrayList<Integer> sectionLinesNumber = new ArrayList<Integer>();
        for (int i = 0; i < this.commandsFileLines.size(); i ++) {
            sectionLines.add(this.commandsFileLines.get(i));
            sectionLinesNumber.add(i + 1);
            if(sectionLines.size() == 4){
                String filterTitle = sectionLines.get(0);
                String filterType = sectionLines.get(1);
                String orderTitle = sectionLines.get(2);
                String orderType = sectionLines.get(3);
                int filterTypeLineNumber = sectionLinesNumber.get(1);
                int orderTypeLineNumber = sectionLinesNumber.get(3);
                if(!filterTitle.equals("FILTER") || !orderTitle.equals("ORDER")){
                    throw new ErrorTypeTwoException("bad sub-section name");
                }
                else{
                    this.createOneSectionFilter(filterType, filterTypeLineNumber);
                    this.createOneSectionOrder(orderType, orderTypeLineNumber);
                    this.sectionsArrayList.add(new Section(this.sectionFilter, this.sectionOrder));
                    if(sectionLines.get(3).equals("FILTER"))
                        i--;
                    sectionLines.clear();
                    sectionLinesNumber.clear();
                }
            }
        }
        if(sectionLines.size() == 3){
            this.createSectionForLastThreeLinesOnCommandsFile(sectionLines, sectionLinesNumber);
        }
        else if(sectionLines.size() < 3 && sectionLines.size() > 0){
            throw new ErrorTypeTwoException("Bad format of the Commands File");
        }
    }

    private void createSectionForLastThreeLinesOnCommandsFile(
            ArrayList<String> sectionLines, ArrayList<Integer> sectionLinesNumber)
            throws ErrorTypeTwoException {
        String filterTitle = sectionLines.get(0);
        String filterType = sectionLines.get(1);
        String orderTitle = sectionLines.get(2);
        String orderType = "FILTER";
        int filterTypeLineNumber = sectionLinesNumber.get(1);
        int orderTypeLineNumber = 0;
        if(!filterTitle.equals("FILTER") || !orderTitle.equals("ORDER")){
            throw new ErrorTypeTwoException("bad sub-section name");
        }
        else{
            this.createOneSectionFilter(filterType, filterTypeLineNumber);
            this.createOneSectionOrder(orderType, orderTypeLineNumber);
            this.sectionsArrayList.add(new Section(this.sectionFilter, this.sectionOrder));
        }

    }

    private void executeActionsOnSections(ArrayList<Section> sectionsArrayList,
                                          ArrayList<File> fileArrayList){
        for(Section section: sectionsArrayList){
            section.executeSectionFilterAndOrderOnDirectoryFiles(fileArrayList);
        }
    }

    private void createOneSectionFilter(String filterType, int filterTypeLineNumber) {
        String[] splitedFilterCommandLine = filterType.split("#");
        try {
            this.sectionFilter = FiltersFactory.createSuitedFilter(splitedFilterCommandLine,
                    filterTypeLineNumber);
        } catch (ErrorTypeOneException type1Error) {
            this.sectionFilter = new AllFilter();
        }
    }

    private void createOneSectionOrder(String orderType, int orderTypeLineNumber) {
        String[] splitedOrderCommandLine = orderType.split("#");
        try {
            this.sectionOrder = OrdersFactory.createOrder(splitedOrderCommandLine, orderTypeLineNumber);
        } catch (ErrorTypeOneException type1Error) {
            this.sectionOrder = new AlphabetOrderByAbs();
        }
    }
}


