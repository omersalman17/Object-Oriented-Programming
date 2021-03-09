package filesprocessing;

import filesprocessing.exceptions.ErrorTypeTwoException;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class DirectoryProcessor {

    private ArrayList<String> commandsFileLinesArrayList = new ArrayList<String>();
    private ArrayList<File> filesArrayList = new ArrayList<File>();

    public DirectoryProcessor(String [] args){
        try{
            analyzingArgsValidity(args);
            new Parsing(commandsFileLinesArrayList, filesArrayList);
        }
        catch (ErrorTypeTwoException type2Error){
        }

    }

    private void analyzingArgsValidity(String [] args) throws ErrorTypeTwoException {
        try{
            File filesDirectory = new File(args[0]);
            File [] filesArray = filesDirectory.listFiles();
            filesArrayList = filteringFilesFromSourceDirectory(filesArray);
            File commandsFile = new File(args[1]);
            commandsFileLinesToStringsArrayList(commandsFile);
        }
        catch (Exception type2exception){
            throw new ErrorTypeTwoException("I/O problem while accessing the command file");
        }

    }

    private void commandsFileLinesToStringsArrayList(File commandsFile) throws ErrorTypeTwoException {
        try (Scanner commandsFileScanner2 = new Scanner(commandsFile);) {
            while (commandsFileScanner2.hasNextLine()) {
                commandsFileLinesArrayList.add(commandsFileScanner2.nextLine());
            }
        }
        catch (Exception exception) {
            throw new ErrorTypeTwoException("I/O problem while accessing the command file");
        }
    }

    private static ArrayList<File> filteringFilesFromSourceDirectory(File [] filesArray){
        ArrayList<File> filesArrayList = new ArrayList<File>();
        for (File file: filesArray){
            if(file.isFile())
                filesArrayList.add(file);
        }
        return filesArrayList;
    }

    public static void main(String [] args){
        new DirectoryProcessor(args);
    }
}
