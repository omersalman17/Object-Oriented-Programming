import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * analyzer class for hash set objects running time performances
 */
public class SimpleSetPerformanceAnalyzer {


    /*----=  Data Members  =-----*/

    /** Strings array contains all strings from data1.txt */
    private String [] data1Words;

    /** Strings array contains all strings from data2.txt */
    private String [] data2Words;

    /** HashSets objects array conatins all HashSets objects */
    private SimpleSet[] simpleSetsArray;

    /** Strings array contains all HashSets objects names */
    private String [] simpleSetObjectsNamesArray;

    /** long type object presents the time before starting an action */
    private long timeBefore;

    /** int presents the number of words checked during the action */
    private int wordsChecked;

    /** int presents the percentage of action done */
    private int percent;


    /*----=  Magic Numbers  =-----*/

    private static final int MILLION_DIVIDER = 1000000;
    private static final int SEVEN_THOUSAND_DIVIDER = 7000;
    private static final int SEVENTY_THOUSAND_DIVIDER = 70000;
    private static final int SIMPLE_SET_ARRAY_LENGTH = 5;
    private static final int OPEN_HASH_SET_INDEX = 0;
    private static final int CLOSED_HASH_SET_INDEX = 1;
    private static final int TREE_SET_INDEX = 2;
    private static final int LINKED_LIST_INDEX = 3;
    private static final int HASH_SET_INDEX = 4;
    private static final int RESET = 0;
    private static final int TEN_VALUE= 10;


    /*----=  Constructors  =-----*/

    /**
     *  constructs a SimpleSetPerformanceAnalyzer object that loads data from data1.file and data2.file,
     *  constructs a data member of SimpleSet objects array, constructs a data member of Strings array.
     */
    private SimpleSetPerformanceAnalyzer(){
        this.data1Words = Ex4Utils.file2array("src\\data1.txt");
        this.data2Words = Ex4Utils.file2array("src\\data2.txt");
        this.simpleSetsArray = new SimpleSet[SIMPLE_SET_ARRAY_LENGTH];
        this.simpleSetObjectsNamesArray = new String[SIMPLE_SET_ARRAY_LENGTH];
    }


    /*----=  Instance and Helper Methods  =-----*/

    /**
     *  initiating all SimpleSet objects of SimpleSet array.
     */
    // used for changing the test's objects if needed.
    private void initializingSimpleSetObjects(){
        this.simpleSetsArray[OPEN_HASH_SET_INDEX] = new OpenHashSet();
        this.simpleSetObjectsNamesArray[OPEN_HASH_SET_INDEX] = "OPEN HASH SET";

        this.simpleSetsArray[CLOSED_HASH_SET_INDEX] = new ClosedHashSet();
        this.simpleSetObjectsNamesArray[CLOSED_HASH_SET_INDEX]= "CLOSED HASH SET";

        this.simpleSetsArray[TREE_SET_INDEX] = new CollectionFacadeSet(new TreeSet<String>());
        this.simpleSetObjectsNamesArray[TREE_SET_INDEX] = "JAVA'S TREE SET";

        this.simpleSetsArray[LINKED_LIST_INDEX] = new CollectionFacadeSet(new LinkedList<String>());
        this.simpleSetObjectsNamesArray[LINKED_LIST_INDEX] = "JAVA'S LINKED LIST";

        this.simpleSetsArray[HASH_SET_INDEX] = new CollectionFacadeSet(new HashSet<String>());
        this.simpleSetObjectsNamesArray[HASH_SET_INDEX] = "JAVA'S HASH SET";
    }

    /**
     * adding data from given Strings array to all SimpleSet objects
     * @param dataWordsArray - Strings array presents data
     * @param toPrint - boolean object presents printing or not the adding data run time
     */
    private void addingFromData(String [] dataWordsArray, boolean toPrint){
        for (int i = 0; i < this.simpleSetsArray.length; i++){
            System.out.println("**** starts adding to " + this.simpleSetObjectsNamesArray[i] + " ****");
            this.restartingVariables();
            for (String str: dataWordsArray){
                this.simpleSetsArray[i].add(str);
                this.variablesChange(this.simpleSetsArray[i], dataWordsArray.length);
            }
            this.printDifference(MILLION_DIVIDER, toPrint);
        }
    }

    /**
     * execute "contains" analyzing of a given String in all SimpleSet objects
     * @param str - String object to analyze "contains" method in all SimpleSet objects
     */
    private void containsAnalyzing(String str){
        for (int i = 0; i < this.simpleSetsArray.length; i++){
            System.out.println("**** " + this.simpleSetObjectsNamesArray[i] + " contains test ****");
            if ( i == LINKED_LIST_INDEX)
                this.containsSubFunc(this.simpleSetsArray[i] ,SEVEN_THOUSAND_DIVIDER, str, i);
            else
                this.containsSubFunc(this.simpleSetsArray[i] ,SEVENTY_THOUSAND_DIVIDER, str, i);
        }
    }

    /**
     * private helper method for "contains" action analyze
     * @param simpleSet given SimpleSet object
     * @param divider int presents divider for running time measured (used for printing accurate result)
     * @param str String object to analyze "contains" method in all SimpleSet objects
     * @param index int presents the index of the SimpleSet objects checked on the SimpleSet objects array
     */
    private void containsSubFunc(SimpleSet simpleSet,int divider, String str, int index){
        if( index != LINKED_LIST_INDEX)
            this.warmUp(simpleSet, divider, str);
        this.containsLoopActions(simpleSet, divider, str, true);
    }

    /**
     * private helper method for a specific action analyze, prints the running time of the action.
     * @param divider - int presents divider for running time measured (used for printing accurate result)
     * @param toPrint boolean object presents printing or not the action running time
     */
    private void printDifference(int divider, boolean toPrint){
        long difference = System.nanoTime() - this.timeBefore;
        if (toPrint)
            System.out.println("result : " + difference / divider);
        else
            System.out.println("finished");
    }

    /**
     * private helper method used for "warming up" before measuring time for specific action running time
     * on a certain SimpleSet object, according to the exercise guide lines.
     * @param simpleSet - SimpleSet object the action running on
     * @param divider - int presents divider for time measured (used for printing accurate result)
     * @param str - the action's string (adding string or contains string)
     */
    private void warmUp(SimpleSet simpleSet,int divider, String str){
        System.out.println("**** warm up ****");
        this.containsLoopActions(simpleSet, divider, str, false);
    }

    /**
     * private helper method presents the loop of "contains" action;
     * @param simpleSet SimpleSet object the action running on
     * @param divider int presents divider for time measured (used for printing accurate result)
     * @param str the action's string (adding string or contains string)
     * @param toPrint boolean object presents printing or not the action running time
     */
    private void containsLoopActions(SimpleSet simpleSet,int divider, String str, boolean toPrint){
        this.restartingVariables();
        for(int i = 0; i < divider; i++) {
            simpleSet.contains(str);
            this.variablesChange(simpleSet, divider);
        }
        this.printDifference(divider, toPrint);
    }

    /**
     * private helper method used for informative output during the action's running time;
     */
    private void restartingVariables(){
        this.timeBefore = System.nanoTime();
        this.wordsChecked = RESET;
        this.percent = TEN_VALUE;
    }

    /**
     *
     * @param simpleSet - SimpleSet object the action running on
     * @param simpleSetLength - int presents the SimpleSet's object length
     */
    private void variablesChange(SimpleSet simpleSet, int simpleSetLength){
        this.wordsChecked++;
        if (wordsChecked > simpleSetLength / TEN_VALUE){
            System.out.println("progress : " + percent + "% done");
            this.wordsChecked = RESET;
            this.percent += TEN_VALUE;
        }

    }

    /**
     * private helper method used for the user's interface of running the analyzing actions on the
     * Simple Set objects whose data loaded from data1 words array.
     * @param analyzer - SimpleSetPerformanceAnalyzer object to used for running run time analyzing actions
     *                 on Simple Set objects
     */
    private void data1Tests(SimpleSetPerformanceAnalyzer analyzer){
        System.out.println("$$$$$$$$$$$$$$$$$$ DATA 1 TESTS $$$$$$$$$$$$$$$$");
        System.out.println();

        System.out.println("########## adding data 1 test ##########");
        analyzer.addingFromData(analyzer.data1Words, true);
        System.out.println();

        System.out.println("########## contains hi in data 1 test ##########");
        analyzer.containsAnalyzing("hi");
        System.out.println();

        System.out.println("########## contains -13170890158 in data 1 test ##########");
        analyzer.containsAnalyzing("-13170890158");
        System.out.println();
    }

    /**
     * private helper method used for the user's interface of running the analyzing actions on the
     * Simple Set objects whose data loaded from data2 words array.
     * @param analyzer - SimpleSetPerformanceAnalyzer object to used for running run time analyzing actions
     *                 on Simple Set objects
     */
    private void data2Tests(SimpleSetPerformanceAnalyzer analyzer){
        System.out.println("$$$$$$$$$$$$$$$$$$ DATA 2 TESTS $$$$$$$$$$$$$$$$");
        System.out.println();

        System.out.println("########## adding data 2 test ##########");
        analyzer.addingFromData(analyzer.data2Words, true);
        System.out.println();

        System.out.println("########## contains 23 in data 2 test ##########");
        analyzer.containsAnalyzing("23");
        System.out.println();

        System.out.println("########## contains hi in data 2 test ##########");
        analyzer.containsAnalyzing("hi");
        System.out.println();
    }

    /**
     * private helper method used for the user's interface of running the analyzing actions on the
     * Simple Set objects, method prints the starting menu allows the user to choose where to load
     * data from to the analyzer's SimpleSet objects.
     * @param analyzer - SimpleSetPerformanceAnalyzer object to used for running run time analyzing actions
     *                 on Simple Set objects
     */
    private void dataSelectionMenu(SimpleSetPerformanceAnalyzer analyzer){
        Scanner scannerObj = new Scanner(System.in);
        System.out.println("please choose data number to load data from:");
        System.out.println("type 1 for data1");
        System.out.println("type 2 for data2");
        System.out.println("type 10 to execute all tests on all hash set objects initialized with both" +
                " data texts one after another");
        System.out.println("another input will stop the program");
        int dataChoiceNumber = scannerObj.nextInt();
        switch(dataChoiceNumber){
            case(1):{
               this.data1TestsMenu(analyzer, scannerObj);
                break;
            }
            case(2):{
                this.data2TestsMenu(analyzer, scannerObj);
                break;
            }
            case(10):{
                analyzer.initializingSimpleSetObjects();
                this.data1Tests(analyzer);
                analyzer.initializingSimpleSetObjects();
                this.data2Tests(analyzer);
                break;
            }
        }
    }

    /**
     * private helper method used for the user's interface of running the analyzing actions on the
     * Simple Set objects, method prints the analyzing actions on data1 data menu, allows the user to choose
     * what action to execute and measure it's running time on SimpleSet objects containing data from data1
     * file.
     * @param analyzer - SimpleSetPerformanceAnalyzer object to used for running run time analyzing actions
     *                 on Simple Set objects
     */
    private void data1TestsMenu(SimpleSetPerformanceAnalyzer analyzer, Scanner scannerObj){
        this.printData1TestsMenuInfo(analyzer);
        int testChoiceNumber = scannerObj.nextInt();
        switch(testChoiceNumber){
            case(1):{
                System.out.println("########## adding data 1 test ##########");
                analyzer.addingFromData(analyzer.data1Words, true);
                break;
            }
            case(2):{
                System.out.println("########## contains hi in data 1 test ##########");
                analyzer.addingFromData(analyzer.data1Words, false);
                analyzer.containsAnalyzing("hi");
                break;
            }
            case(3):{
                System.out.println("########## contains -13170890158 in data 1 test ##########");
                analyzer.addingFromData(analyzer.data1Words, false);
                analyzer.containsAnalyzing("-13170890158");
                break;
            }
            case (4):{
                this.dataSelectionMenu(analyzer);
                break;
            }
        }
    }

    /**
     * private helper method used for the user's interface of running the analyzing actions on the
     * Simple Set objects, method prints the analyzing actions on data2 data menu, allows the user to choose
     * what action to execute and measure it's running time on SimpleSet objects containing data from data2
     * file.
     * @param analyzer - SimpleSetPerformanceAnalyzer object to used for running run time analyzing actions
     *                 on Simple Set objects
     */
    private void data2TestsMenu(SimpleSetPerformanceAnalyzer analyzer, Scanner scannerObj){
        this.printData2TestsMenuInfo(analyzer);
        int testChoiceNumber = scannerObj.nextInt();
        switch(testChoiceNumber){
            case(1):{
                System.out.println("########## adding data 2 test ##########");
                analyzer.addingFromData(analyzer.data2Words, true);
                break;
            }
            case(2):{
                System.out.println("########## contains hi in data 2 test ##########");
                analyzer.addingFromData(analyzer.data2Words, false);
                analyzer.containsAnalyzing("hi");
                break;
            }
            case(3):{
                System.out.println("########## contains '23' in data 2 test ##########");
                analyzer.addingFromData(analyzer.data2Words, false);
                analyzer.containsAnalyzing("23");
                break;
            }
            case (4):{
                this.dataSelectionMenu(analyzer);
                break;
            }
        }
    }

    /**
     * private helper method used for the user's interface of running the analyzing actions on the
     * Simple Set objects, used for data1TestsMenu to print the common analyzing actions for data loaded
     * form data1 file or data1 file, and print the menu notes and initiating the SimpleSet objects.
     * @param analyzer - SimpleSetPerformanceAnalyzer object to used for running run time analyzing actions
     *                 on Simple Set objects
     */
    private void printData1TestsMenuInfo(SimpleSetPerformanceAnalyzer analyzer){
        this.printCommonTestsOptions();
        System.out.println("type 3 for executing 'contains' method on string '-13170890158'");
        this.printMenuNotesAndInitializeObjects(analyzer);

    }

    /**
     * private helper method used for the user's interface of running the analyzing actions on the
     * Simple Set objects, used for data2TestsMenu to print the common analyzing actions for data loaded
     * form data1 file or data1 file, and print the menu notes and initiating the SimpleSet objects.
     * @param analyzer - SimpleSetPerformanceAnalyzer object to used for running run time analyzing actions
     *                 on Simple Set objects
     */
    private void printData2TestsMenuInfo(SimpleSetPerformanceAnalyzer analyzer){
        this.printCommonTestsOptions();
        System.out.println("type 3 for executing 'contains' method on string '23'");
        this.printMenuNotesAndInitializeObjects(analyzer);
    }

    /**
     * private helper method used for the user's interface of running the analyzing actions on the
     * Simple Set objects, used for printing the common analyzing actions choosing options for SimpleSet
     * objects loaded with data from data1 file or data2 file.
     */
    private void printCommonTestsOptions(){
        System.out.println("please choose the test you wish to execute on all of the hash sets:");
        System.out.println("type 1 for adding all data to the hash sets objects");
        System.out.println("type 2 for executing 'contains' method on string 'hi'");
    }

    /**
     * private helper method used for the user's interface of running the analyzing actions on the
     * Simple Set objects, used for printing the menu notes and initializing the SimpleSet objects each run.
     * @param analyzer - SimpleSetPerformanceAnalyzer object to used for running run time analyzing actions
     *                 on Simple Set objects
     */
    private void printMenuNotesAndInitializeObjects(SimpleSetPerformanceAnalyzer analyzer){
        System.out.println("type 4 to return to data selection");
        System.out.println("another input will stop the program");
        analyzer.initializingSimpleSetObjects();
    }

    /**
     * main method used fo running the analyzing actions on the SimpleSet objects.
     * @param args - system arguments given through command line (not used on my implementation)
     */
    public static void main(String [] args){
        SimpleSetPerformanceAnalyzer analyzer = new SimpleSetPerformanceAnalyzer();
        analyzer.dataSelectionMenu(analyzer);
    }
}
