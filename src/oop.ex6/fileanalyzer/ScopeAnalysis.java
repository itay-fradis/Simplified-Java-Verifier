package oop.ex6.fileanalyzer;

import oop.ex6.classification.LineClassification;
import oop.ex6.classification.LineType;
import oop.ex6.component.Component;
import oop.ex6.component.ComponentFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;


/**
 * Analyze a scope in file
 */
public class ScopeAnalysis {

    private static final String VARIABLE_TYPE = "variableType";

    private static final String VARIABLE_NAME = "variableName";

    private static final String VARIABLE_VALUE = "variableValue";

    private static final String isFinal = "final";




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

    /**
     * analyze lines in a scope
     * @param s - list of previos scopes
     * @param reader - reader of file
     * @throws IOException - when cannot read from file
     */
    public static void Analyze(Scope[] s, BufferedReader reader) throws Exception {
        String line;
        while ((line = reader.readLine()) != null){
            LineType type = LineClassification.generalClassify(line);
            switch (type){
                case NORMAL_LINE:
                    LineClassification.LineDetails detailsL = LineClassification.SemiColonClassify(line);
                    switch (detailsL.getType()){
                        case NEW_VARIABLE:
                            ComponentFactory.addVariable(detailsL);
                            new Component(detailsL.getMatcher().group(VARIABLE_TYPE),
                                    detailsL.getMatcher().group(VARIABLE_NAME)
                                    , detailsL.getMatcher().group(isFinal),
                                    detailsL.getMatcher().group(VARIABLE_VALUE));
                            break;
                    }
                    break;
                case OPEN_SCOPE_LINE:
                    break;
                case CLOSED_SCOPE_LINE:
                    break;
                case BAD_LINE:
                    throw new RuntimeException("test");
                default:
                    throw new RuntimeException("error");
            }
        }
    }
}
