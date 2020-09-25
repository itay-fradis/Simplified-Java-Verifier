package oop.ex6.main;

import oop.ex6.fileanalyzer.FileAnalysis;

/**
 * main class of the program.
 * calls for the analyzer to analyze the given Sjava file
 */
public class Sjavac {

    /** wrong num of args */
    private static final String WRONG_NUM_OF_ARGS = "USAGE: wrong num of args";

    /** valid num of args */
    private static final int NUM_OF_ARGS = 1;

    /**
     * main method activates the program
     * @param args user arguments
     */
    public static void main(String[] args) {
        if (args.length != NUM_OF_ARGS){
            System.err.println(WRONG_NUM_OF_ARGS);
            System.exit(1);
        }
        String fileName = args[0];
        FileAnalysis.Analysis(fileName);
    }
}
