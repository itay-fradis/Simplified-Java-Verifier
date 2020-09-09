package oop.ex6.fileanalyzer;

import oop.ex6.classification.Classifier;
import oop.ex6.classification.LineType;
import oop.ex6.component.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Analyze a scope in file
 */
public class ScopeAnalysis {
    /**
     * scope analyzer constructor
     */
    private ScopeAnalysis(){};
    /**
     * static member of analyzer
     */
    private static ScopeAnalysis analyzer = new ScopeAnalysis();

    /**
     * a private class represent a scope
     */
    private class Scope{
        private ArrayList<Component> components;
    }

    /**
     * a deque of scopes
     */
    private Scope[] scopes;


    public static void Analyze(Scope[] s, BufferedReader reader) throws IOException {
        String line;
        while ((line = reader.readLine()) != null){
            LineType lineType = Classifier.LineClassify(line);

            switch (lineType){
                case EMPTY_LINE:
                case COMMENT:
                    break;
                case RETURN:
                    break;
                case NEW_SCOPE:
                    break;
                case CLOSE_SCOPE:
                    break;
                case USAGE:
                    return;

            }
        }

    }
}
