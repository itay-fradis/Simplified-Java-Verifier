package oop.ex6.fileanalyzer;


import java.io.*;
import java.io.FileReader;
import java.io.IOException;



/**
 * class for a file analysis
 */
public class FileAnalysis {

    /***/
    private static final int CODE_IS_LEGAL = 0;

    /***/
    private static final int ILLEGAL_CODE = 1;

    /***/
    private static final int IO_ERROR_CODE = 2;

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
            reader.close();
//            System.out.println(CODE_IS_LEGAL);
            return CODE_IS_LEGAL;

        }
        catch (IOException e){
            System.out.println(IO_ERROR_CODE);
            System.err.println(IO_ERR_MSG);
            return IO_ERROR_CODE;
        }
        catch (Exception e){
//            System.out.println(ILLEGAL_CODE);
            return ILLEGAL_CODE;
        }
    }


}
