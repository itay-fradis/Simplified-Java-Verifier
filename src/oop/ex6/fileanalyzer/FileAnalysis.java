package oop.ex6.fileanalyzer;

import oop.ex6.component.Method;
import oop.ex6.component.Variable;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * class for a file analysis
 */
public class FileAnalysis {

    /** IO ERROR MESSAGE */
    private static final String IO_ERR_MSG = "ERROR: IO error occurred";

    /**
     * default constructor
     */
    private FileAnalysis() {
    }

    /**
     * analyse a file and handle exceptions if there is any
     * @param toRead - file name
     */
    public static int Analysis(String toRead) {
        try (BufferedReader reader = new BufferedReader(new FileReader(toRead))){
            ScopeAnalysis scopeAnalysis = new ScopeAnalysis();
            scopeAnalysis.Analyze(reader);
            return 0;
        }
        catch (IOException e){
            System.err.println(IO_ERR_MSG);
            return 2;
        }
        catch (Exception e){
            return 1;
        }
    }

}
