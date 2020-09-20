package oop.ex6.fileanalyzer;


import java.io.*;
import java.io.FileReader;
import java.io.IOException;



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
        try {
            FileReader fileReader = new FileReader(toRead);
            BufferedReader reader = new BufferedReader(fileReader);
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
