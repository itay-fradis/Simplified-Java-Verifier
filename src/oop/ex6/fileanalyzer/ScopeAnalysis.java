package oop.ex6.fileanalyzer;

import oop.ex6.classification.LineClassification;
import oop.ex6.classification.LineDetails;
import oop.ex6.classification.LineType;
import oop.ex6.component.*;


import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Analyze a scope in file
 */
public class ScopeAnalysis {

    /** const strings */
    private static final String VARIABLE_TYPE = "variableType";

    private static final String ARGUMENTS = "arguments";

    private static final String VARIABLE_NAME = "variableName";

    private static final String VARIABLE_VALUE = "variableValue";

    private static final String IS_FINAL = "final";

    private static final String ARGUMENTS_DELIMITER = ",";

    private static final String METHOD_TYPE = "methodType";

    private static final String METHOD_NAME = "methodName";

    /**
     * a deque of scopes
     */
    private static LinkedList<Scope> scopes;

    /**
     * constructor
     */
    public ScopeAnalysis(){
        scopes = new LinkedList<>();
        scopes.add(new Scope());
    }

    /**
     * a private class represent a scope
     */
    private class Scope{

        /***/
        private ArrayList<Variable> variables;
        private ArrayList<Method> methods;

        /**
         *
         */
        private Scope(){
            variables = new ArrayList<>();
            methods = new ArrayList<>();
        }
    }

    /**
     * analyze lines in a scope
     * @param reader - reader of file
     * @throws IOException - when cannot read from file
     */
    public void Analyze(BufferedReader reader) throws Exception {
        String line;
        while ((line = reader.readLine()) != null){
            line = line.trim();
            LineType type = LineClassification.generalClassify(line);
            switch (type) {
                case NORMAL_LINE:
                    parseNormalLine(line);
                    break;
                case OPEN_SCOPE_LINE:
                    parseMethod(line);
                    break;
                case CLOSED_SCOPE_LINE:
                    break;
                case COMMENT:
                    continue;
                case BAD_LINE:
                    throw new Exception("test");
                default:
                    throw new Exception("error");
            }
        }
    }

    /**
     *
     * @param line
     */
    private void parseMethod(String line) throws BadLineFormatException {
        LineDetails detailsL = LineClassification.openParenthesisClassify(line);
        switch (detailsL.getType()){
            case NEW_METHOD:
                addMethod(detailsL.getMatcher());
                break;
            case BAD_LINE:
                throw new BadLineFormatException();
            //case IF:
        }
    }

    /**
     *
     * @param matcher
     */
    private void addMethod(Matcher matcher) throws BadLineFormatException {
        String mType = matcher.group(METHOD_TYPE);
        String mName = matcher.group(METHOD_NAME);
        String[] arguments = matcher.group(ARGUMENTS).split(ARGUMENTS_DELIMITER);
        if (matcher.group(ARGUMENTS).length() == 0){
            arguments = new String[0];
        }
        scopes.getLast().methods.add(MethodFactory.addMethod(mType, mName, arguments));

    }

    /**
     * parse normal line
     * @param line line to be parsed
     */
    private void parseNormalLine(String line) throws BadLineFormatException {
        LineDetails detailsL = LineClassification.SemiColonClassify(line);
        switch (detailsL.getType()){
            case NEW_VARIABLE:
                addVariables(detailsL.getMatcher());
                break;
            case RETURN:
                break;
            case BAD_LINE:
                throw new BadLineFormatException();
        }
    }

    /**
     * check if variable declaration is legal, and add it. (not completed yet)
     * @param newVarMatcer matcher
     * @throws Exception (should be a specific exception).
     */
    private void addVariables(Matcher newVarMatcer) throws VariableDeclarationException {
        String finalPrefix = newVarMatcer.group(IS_FINAL);
        String variableType = newVarMatcer.group(VARIABLE_TYPE);
        String[] arguments = newVarMatcer.group(ARGUMENTS).split(ARGUMENTS_DELIMITER);
        Pattern p = Pattern.compile(LineType.VARIABLE_ASSIGNMENT.getRegexPattern());
        for (String str: arguments){
            Matcher matcher = p.matcher(str.trim());
            if (matcher.matches()) {
                String value = matcher.group(VARIABLE_VALUE);
                value = checkIfAssignedByVariable(value);
                Scope sc = scopes.getLast();
                scopes.getLast().variables.add(
                        VariableFactory.addVariable(matcher.group(VARIABLE_NAME), value,
                        finalPrefix, variableType));
            }
            else
                throw new VariableDeclarationException();
        }
    }

    /**
     * if a variable assigned by another var, it will return's its value.
     * @param value value to be returned
     * @return updated value
     */
    private String checkIfAssignedByVariable(String value) throws VariableDeclarationException {
        for (Scope s: scopes){
            for (Variable variable: s.variables){
                if (variable.getName().equals(value)){
                    if (variable.getValue() == null){
                        throw new VariableDeclarationException();
                    }
                    return variable.getValue();
                }
            }
        }

        return value;
    }

}
