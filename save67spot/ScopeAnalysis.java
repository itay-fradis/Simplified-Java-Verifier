package oop.ex6.fileanalyzer;

import oop.ex6.classification.Classifier;
import oop.ex6.classification.LineClassification;
import oop.ex6.classification.LineDetails;
import oop.ex6.classification.LineType;
import oop.ex6.component.Component;
import oop.ex6.component.FinalUsageException;
import oop.ex6.component.VariableDeclarationException;
import oop.ex6.component.VariableType;


import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;


/**
 * Analyze a scope in file
 */
public class ScopeAnalysis {

    /** const strings */
    private static final String VARIABLE_TYPE = "variableType";

    private static final String VARIABLE_NAME = "variableName";

    private static final String VARIABLE_VALUE = "variableValue";

    private static final String IS_FINAL = "final";

    private static final String INT = "int";

    private static final String DOUBLE = "double";

    private static final String STRING = "String";

    private static final String CHAR = "char";

    private static final String BOOLEAN = "boolean";

    /** hold the codeFile variables */
    final private List<Component> variables = new ArrayList<>();

    /**
     * a private class represent a scope
     */
    private static class Scope{
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
    public void Analyze(Scope[] s, BufferedReader reader) throws Exception {
        String line;
        while ((line = reader.readLine()) != null){
            line = line.trim();
            LineType type = LineClassification.generalClassify(line);
            switch (type){
                case NORMAL_LINE:
                    parseNormalLine(line);
                    break;
                case OPEN_SCOPE_LINE:
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
     * parse normal line
     * @param line line to be parsed
     */
    private void parseNormalLine(String line) throws BadLineFormatException {
        // remove semi colon
        line = line.substring(0, line.length() - 1);
        line = line.trim();
        String[] varStrings = line.split(",");
        LineDetails detailsL = LineClassification.SemiColonClassify(varStrings[0].trim());
        switch (detailsL.getType()){
            case NEW_VARIABLE:
                Matcher matcher = detailsL.getMatcher();
                String type = matcher.group(VARIABLE_TYPE);
                String finalPrefix = matcher.group(IS_FINAL);
                for (int i = 0; i < varStrings.length; i++) {
                    varStrings[i] = varStrings[i].trim();
                    LineDetails varDetails = LineClassification.SemiColonClassify(varStrings[i]);
                    if (i > 0 && varDetails.getType() == LineType.NEW_VARIABLE){
                        throw new BadLineFormatException();
                    }
                    addVariable(varDetails, type, finalPrefix);
                }
                break;
            case VARIABLE_ASSIGNMENT:
                Component x = foundComponent(varStrings[0]);
            case BAD_LINE:
                throw new BadLineFormatException();
        }

    }

    /**
     * @param varString the given variable
     * @return the component to be assigned
     * @throws BadLineFormatException if not found
     */
    private Component foundComponent(String varString) throws BadLineFormatException {
        for (Component component: variables){
            if (component.getName().equals(varString)){
                return component;
            }
        }
        throw new BadLineFormatException();
    }

    /**
     * check if variable declaration is legal, and add it. (not completed yet)
     * @param details line details
     * @throws VariableDeclarationException undefined declaration.
     */
    private void addVariable(LineDetails details, String declaredType, String isFinal) throws VariableDeclarationException {
        Matcher matcher = details.getMatcher();
        String name = matcher.group(VARIABLE_NAME);
        String value = matcher.group(VARIABLE_VALUE);
        value = checkIfAssignedByVariable(value);
        VariableType variableTypeValue = Classifier.classifyValue(value);
        if (isFinal != null && value == null){
            throw new FinalUsageException();
        }
        if (!Classifier.isLegalVariableType(declaredType)){
            throw new VariableDeclarationException();
        }
        if (variableTypeValue != null){
            String valueType = variableTypeValue.getTypeName();
            if (declaredType.equals(INT) && !valueType.equals(INT)){
                throw new VariableDeclarationException();
            }

            if (declaredType.equals(DOUBLE) && !valueType.equals(DOUBLE) && !valueType.equals(INT)){
                throw new VariableDeclarationException();
            }

            if (declaredType.equals(STRING) && !valueType.equals(STRING) && !valueType.equals(CHAR)){
                throw new VariableDeclarationException();
            }

            if (declaredType.equals(CHAR) && !valueType.equals(CHAR)){
                throw new VariableDeclarationException();
            }

            if (declaredType.equals(BOOLEAN) && !valueType.equals(BOOLEAN) && !valueType.equals(DOUBLE) &&
                    !valueType.equals(INT)){
                throw new VariableDeclarationException();
            }
        }
        variables.add(new Component(Classifier.classifyValue(value), name, isFinal, value));
    }

    /**
     * if a variable assigned by another var, it will return's its value.
     * @param value value to be returned
     * @return updated value
     */
    private String checkIfAssignedByVariable(String value) throws VariableDeclarationException {
        for (Component variable: variables){
            if (variable.getName().equals(value)){
                if (variable.getValue() == null){
                    throw new VariableDeclarationException();
                }
                return variable.getValue();
            }
        }
        return value;
    }

}
