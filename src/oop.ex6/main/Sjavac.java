package oop.ex6.main;

import oop.ex6.component.ComponentAnalyzer;
import oop.ex6.fileanalyzer.FileAnalysis;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Sjavac {

    /** sjava type suffix */
    private static final String SJAVA_TYPE = ".sjava";

    /** wrong num of args */
    private static final String WRONG_NUM_OF_ARGS = "USAGE: wrong num of args";

    /** file not of type 'sjava' */
    private static final String SJAVA_FILE_WARNING = "WARNING: Running on a non s-java file";

    /** valid num of args */
    private static final int NUM_OF_ARGS = 1;

    /***/
    private static final int CODE_IS_LEGAL = 0;

    /***/
    private static final int CODE_IS_ILLEGAL = 1;

    /***/
    private static final int IO_ERROR_CODE = 2;


    public static void main(String[] args) throws FileNotFoundException {
        if (args.length != NUM_OF_ARGS){
            System.err.println(WRONG_NUM_OF_ARGS);
            System.exit(1);
        }
        String fileName = args[0];
        if (!fileName.endsWith(SJAVA_TYPE)){
            System.out.println(IO_ERROR_CODE);
            System.out.println(SJAVA_FILE_WARNING);
        }
        FileAnalysis.Analysis(fileName);
    }
}
