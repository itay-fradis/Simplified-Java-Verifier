package oop.ex6.fileanalyzer;

import oop.ex6.classification.Classifier;
import oop.ex6.classification.LineType;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Analyze a scope in file
 */
public class ScopeAnalysis {
//    /**
//     * scope analyzer constructor
//     */
//    private ScopeAnalysis(){};
//    /**
//     * static member of analyzer
//     */
//    private static ScopeAnalysis analyzer = new ScopeAnalysis();
//
//    /**
//     * a private class represent a scope
//     */
//    private class Scope{
//        private ArrayList<Component> components;
//    }

//    /**
//     * a deque of scopes
//     */
//    private Scope[] scopes;

    /**
     * get row after
     * @param fileName
     * @return
     * @throws IOException
     */
    public static boolean analyze(String fileName) throws IOException {
        String line;
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        while ((line = reader.readLine()) != null){
            line = line.trim();
            if (!line.matches("^int\\s+[a-z];"))
                return false;
            }
        return true;
        }
}
