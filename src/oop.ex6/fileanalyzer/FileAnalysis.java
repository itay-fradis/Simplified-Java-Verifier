package oop.ex6.fileanalyzer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * class for a file analysis
 */
public class FileAnalysis {

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

            ScopeAnalysis.Analyze(null, reader);
            return 0;
        }
        catch (IOException e){
            e.printStackTrace();
            return 1;
        }
        catch (Exception e){
            return 1;
        }
    }

}
