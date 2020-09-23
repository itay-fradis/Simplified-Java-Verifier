package oop.ex6.main;

import oop.ex6.fileanalyzer.FileAnalysis;

import java.io.FileNotFoundException;

public class Sjavac {

    /** sjava type suffix */
    private static final String SJAVA_TYPE = ".sjava";

    /** wrong num of args */
    private static final String WRONG_NUM_OF_ARGS = "USAGE: wrong num of args";

    /** file not of type 'sjava' */
    private static final String SJAVA_FILE_WARNING = "WARNING: Running a non s-java file";

    /** valid num of args */
    private static final int NUM_OF_ARGS = 1;

    /**
     * main method activates the program
     * @param args user arguments
     * @return output of file analysis program
     */
    public static int main(String[] args) {
        if (args.length != NUM_OF_ARGS){
            System.err.println(WRONG_NUM_OF_ARGS);
            System.exit(1);
        }
        String fileName = args[0];
        return FileAnalysis.Analysis(fileName);
    }
}
